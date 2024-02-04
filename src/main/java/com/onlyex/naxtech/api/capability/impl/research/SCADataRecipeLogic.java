package com.onlyex.naxtech.api.capability.impl.research;

import com.onlyex.naxtech.api.capability.hatch.research.IResearchDataReceiver;
import com.onlyex.naxtech.api.capability.hatch.research.supracausal.ISCAResearchDataProvider;
import com.onlyex.naxtech.api.recipes.recipeproperties.data.SCADataProperty;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.TotalComputationProperty;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 *需要计算的多块的配方逻辑。
 *与使用{@link SCADataProperty}包含配方的RecipeMaps一起使用。
 *持有此逻辑的Multiblock必须实现{@link ISCAResearchDataProvider}。
 */
public class SCADataRecipeLogic extends MultiblockRecipeLogic {

    private final SCADataType type;

    /**
     *配方持续时间是否应视为总SCA-RWU值（因此，按每个刻度使用的SCA-RWU/t递增），
     *或正常（每次成功抽取SCA-RWU/t增加1）。如果此值为真，逻辑将尝试
     *尽可能多地绘制SCA-RWU/t以尝试加速计算过程，SCA-RWU/t被视为
     *最小值而不是静态成本。
     */
    private boolean isDurationTotalSCARWU;
    private int recipeSCARWUt;
    private boolean hasNotEnoughComputation;
    private int currentDrawnSCARWUt;

    public SCADataRecipeLogic(RecipeMapMultiblockController metaTileEntity, SCADataType type) {
        super(metaTileEntity);
        this.type = type;
        if (!(metaTileEntity instanceof IResearchDataReceiver)) {
            throw new IllegalArgumentException("MetaTileEntity必须是ISCAResearchDataReceiver的实例");
        }
    }

    @NotNull
    public ISCAResearchDataProvider getComputationProvider() {
        IResearchDataReceiver controller = (IResearchDataReceiver) metaTileEntity;
        return controller.getSCAResearchDataProvider();
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe) {
        if (!super.checkRecipe(recipe)) {
            return false;
        }
        if (!recipe.hasProperty(SCADataProperty.getInstance())) {
            return true;
        }
        ISCAResearchDataProvider provider = getComputationProvider();
        int recipeSCARWUt = recipe.getProperty(SCADataProperty.getInstance(), 0);
        return provider.requestSCARWUt(recipeSCARWUt, true) >= recipeSCARWUt;
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        this.recipeSCARWUt = recipe.getProperty(SCADataProperty.getInstance(), 0);
        this.isDurationTotalSCARWU = recipe.hasProperty(TotalComputationProperty.getInstance());
    }

    @Override
    protected void updateRecipeProgress() {
        if (recipeSCARWUt == 0) {
            super.updateRecipeProgress();
            return;
        }

        if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
            drawEnergy(recipeEUt, false);

            ISCAResearchDataProvider provider = getComputationProvider();
            int availableSCARWUt = provider.requestSCARWUt(Integer.MAX_VALUE, true);
            if (availableSCARWUt >= recipeSCARWUt) {
                // 照常进行
                this.hasNotEnoughComputation = false;
                if (isDurationTotalSCARWU) {
                    // 尽可能多地抽取SCA-RWU，并以这个数量增加进度
                    currentDrawnSCARWUt = provider.requestSCARWUt(availableSCARWUt, false);
                    progressTime += currentDrawnSCARWUt;
                } else {
                    // 只绘制配方SCA-RWU/t，进度增加1
                    provider.requestSCARWUt(recipeSCARWUt, false);
                    progressTime++;
                }
                if (progressTime > maxProgressTime) {
                    completeRecipe();
                }
            } else {
                currentDrawnSCARWUt = 0;
                this.hasNotEnoughComputation = true;
                // 如果我们需要稳定的供应，只有低SCA-RWU/t的递减进度
                if (type == SCADataType.STEADY) {
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
        this.recipeSCARWUt = 0;
        this.isDurationTotalSCARWU = false;
        this.hasNotEnoughComputation = false;
        this.currentDrawnSCARWUt = 0;
    }

    public int getRecipeSCARWUt() {
        return recipeSCARWUt;
    }

    public int getCurrentDrawnSCARWUt() {
        return isDurationTotalSCARWU ? currentDrawnSCARWUt : recipeSCARWUt;
    }

    public boolean isHasNotEnoughComputation() {
        return hasNotEnoughComputation;
    }

    @NotNull
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (this.progressTime > 0) {
            compound.setInteger("RecipeSCA-RWUt", recipeSCARWUt);
            compound.setBoolean("IsDurationTotalSCA-RWU", isDurationTotalSCARWU);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        if (this.progressTime > 0) {
            recipeSCARWUt = compound.getInteger("RecipeSCA-RWUt");
            isDurationTotalSCARWU = compound.getBoolean("IsDurationTotalSCA-RWU");
        }
    }

    /**
     * @return TOP/WAILA是否应该将配方进度显示为持续时间还是总计算量。
     */
    public boolean shouldShowDuration() {
        return !isDurationTotalSCARWU;
    }

    public enum SCADataType {
        /**
         * SCA-RWU/t像EU/t一样工作。如果没有足够的，配方恢复进度/停止
         */
        STEADY,
        /**
         * SCA-RWU/t的工作方式类似于总输入。如果没有足够的，配方将在当前进度时间停止。
         *只有在存在足够计算的情况下，滴答的进度才会增加。能量将始终被汲取。
         */
        SPORADIC
    }
}
