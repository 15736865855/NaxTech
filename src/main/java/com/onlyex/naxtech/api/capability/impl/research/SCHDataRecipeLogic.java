package com.onlyex.naxtech.api.capability.impl.research;

import com.onlyex.naxtech.api.capability.hatch.research.IResearchDataReceiver;
import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchDataProvider;
import com.onlyex.naxtech.api.recipes.recipeproperties.data.SCHDataProperty;
import com.onlyex.naxtech.api.recipes.recipeproperties.total.TotalSCHDataProperty;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 *需要计算的多块的配方逻辑。
 *与使用{@link SCHDataProperty}包含配方的RecipeMaps一起使用。
 *持有此逻辑的Multiblock必须实现{@link ISCHResearchDataProvider}。
 */
public class SCHDataRecipeLogic extends MultiblockRecipeLogic {

    private final SCHDataType type;

    /**
     *配方持续时间是否应视为总SCH-RWU值（因此，按每个刻度使用的SCH-RWU/t递增），
     *或正常（每次成功抽取SCH-RWU/t增加1）。如果此值为真，逻辑将尝试
     *尽可能多地绘制SCH-RWU/t以尝试加速计算过程，SCH-RWU/t被视为
     *最小值而不是静态成本。
     */
    private boolean isDurationTotalSCHRWU;
    private int recipeSCHRWUt;
    private boolean hasNotEnoughComputation;
    private int currentDrawnSCHRWUt;

    public SCHDataRecipeLogic(RecipeMapMultiblockController metaTileEntity, SCHDataType type) {
        super(metaTileEntity);
        this.type = type;
        if (!(metaTileEntity instanceof IResearchDataReceiver)) {
            throw new IllegalArgumentException("MetaTileEntity必须是ISCHResearchDataReceiver的实例");
        }
    }

    @NotNull
    public ISCHResearchDataProvider getComputationProvider() {
        IResearchDataReceiver controller = (IResearchDataReceiver) metaTileEntity;
        return controller.getSCHResearchDataProvider();
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe) {
        if (!super.checkRecipe(recipe)) {
            return false;
        }
        if (!recipe.hasProperty(SCHDataProperty.getInstance())) {
            return true;
        }
        ISCHResearchDataProvider provider = getComputationProvider();
        int recipeSCHRWUt = recipe.getProperty(SCHDataProperty.getInstance(), 0);
        return provider.requestSCHRWUt(recipeSCHRWUt, true) >= recipeSCHRWUt;
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        this.recipeSCHRWUt = recipe.getProperty(SCHDataProperty.getInstance(), 0);
        this.isDurationTotalSCHRWU = recipe.hasProperty(TotalSCHDataProperty.getInstance());
    }

    @Override
    protected void updateRecipeProgress() {
        if (recipeSCHRWUt == 0) {
            super.updateRecipeProgress();
            return;
        }

        if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
            drawEnergy(recipeEUt, false);

            ISCHResearchDataProvider provider = getComputationProvider();
            int availableSCHRWUt = provider.requestSCHRWUt(Integer.MAX_VALUE, true);
            if (availableSCHRWUt >= recipeSCHRWUt) {
                // 照常进行
                this.hasNotEnoughComputation = false;
                if (isDurationTotalSCHRWU) {
                    // 尽可能多地抽取SCH-RWU，并以这个数量增加进度
                    currentDrawnSCHRWUt = provider.requestSCHRWUt(availableSCHRWUt, false);
                    progressTime += currentDrawnSCHRWUt;
                } else {
                    // 只绘制配方SCH-RWU/t，进度增加1
                    provider.requestSCHRWUt(recipeSCHRWUt, false);
                    progressTime++;
                }
                if (progressTime > maxProgressTime) {
                    completeRecipe();
                }
            } else {
                currentDrawnSCHRWUt = 0;
                this.hasNotEnoughComputation = true;
                // 如果我们需要稳定的供应，只有低SCH-RWU/t的递减进度
                if (type == SCHDataType.STEADY) {
                    decreaseProgress();
                }
            }
            if (this.hasNotEnoughEnergy && getEnergyInputPerSecond() > 19L * recipeEUt) {
                this.hasNotEnoughEnergy = false;
            }
        } else if (recipeEUt > 0) {
            this.hasNotEnoughEnergy = true;
            decreaseProgress();
        }
    }

    @Override
    protected void completeRecipe() {
        super.completeRecipe();
        this.recipeSCHRWUt = 0;
        this.isDurationTotalSCHRWU = false;
        this.hasNotEnoughComputation = false;
        this.currentDrawnSCHRWUt = 0;
    }

    public int getRecipeSCHRWUt() {
        return recipeSCHRWUt;
    }

    public int getCurrentDrawnSCHRWUt() {
        return isDurationTotalSCHRWU ? currentDrawnSCHRWUt : recipeSCHRWUt;
    }

    public boolean isHasNotEnoughComputation() {
        return hasNotEnoughComputation;
    }

    @NotNull
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (this.progressTime > 0) {
            compound.setInteger("RecipeSCH-SCHRWUt", recipeSCHRWUt);
            compound.setBoolean("IsDurationTotalSCH-RWU", isDurationTotalSCHRWU);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        if (this.progressTime > 0) {
            recipeSCHRWUt = compound.getInteger("RecipeSCH-RWUt");
            isDurationTotalSCHRWU = compound.getBoolean("IsDurationTotalSCH-RWU");
        }
    }

    /**
     * @return TOP/WAILA是否应该将配方进度显示为持续时间还是总计算量。
     */
    public boolean shouldShowDuration() {
        return !isDurationTotalSCHRWU;
    }

    public enum SCHDataType {
        /**
         * SCH-RWU/t像EU/t一样工作。如果没有足够的，配方恢复进度/停止
         */
        STEADY,
        /**
         * SCH-RWU/t的工作方式类似于总输入。如果没有足够的，配方将在当前进度时间停止。
         *只有在存在足够计算的情况下，滴答的进度才会增加。能量将始终被汲取。
         */
        SPORADIC
    }
}
