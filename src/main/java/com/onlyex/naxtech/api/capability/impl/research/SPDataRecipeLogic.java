package com.onlyex.naxtech.api.capability.impl.research;

import com.onlyex.naxtech.api.capability.hatch.research.IResearchDataReceiver;
import com.onlyex.naxtech.api.capability.hatch.research.spintronic.ISPResearchDataProvider;
import com.onlyex.naxtech.api.recipes.recipeproperties.data.SPDataProperty;
import com.onlyex.naxtech.api.recipes.recipeproperties.total.TotalSPDataProperty;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 *需要计算的多块的配方逻辑。
 *与使用{@link SPDataProperty}包含配方的RecipeMaps一起使用。
 *持有此逻辑的Multiblock必须实现{@link ISPResearchDataProvider}。
 */
public class SPDataRecipeLogic extends MultiblockRecipeLogic {

    private final SPDataType type;

    /**
     *配方持续时间是否应视为总SP-RWU值（因此，按每个刻度使用的SP-RWU/t递增），
     *或正常（每次成功抽取SP-RWU/t增加1）。如果此值为真，逻辑将尝试
     *尽可能多地绘制SP-RWU/t以尝试加速计算过程，SP-RWU/t被视为
     *最小值而不是静态成本。
     */
    private boolean isDurationTotalSPRWU;
    private int recipeSPRWUt;
    private boolean hasNotEnoughComputation;
    private int currentDrawnSPRWUt;

    public SPDataRecipeLogic(RecipeMapMultiblockController metaTileEntity, SPDataType type) {
        super(metaTileEntity);
        this.type = type;
        if (!(metaTileEntity instanceof IResearchDataReceiver)) {
            throw new IllegalArgumentException("MetaTileEntity必须是ISPResearchDataReceiver的实例");
        }
    }

    @NotNull
    public ISPResearchDataProvider getComputationProvider() {
        IResearchDataReceiver controller = (IResearchDataReceiver) metaTileEntity;
        return controller.getSPResearchDataProvider();
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe) {
        if (!super.checkRecipe(recipe)) {
            return false;
        }
        if (!recipe.hasProperty(SPDataProperty.getInstance())) {
            return true;
        }
        ISPResearchDataProvider provider = getComputationProvider();
        int recipeSPRWUt = recipe.getProperty(SPDataProperty.getInstance(), 0);
        return provider.requestSPRWUt(recipeSPRWUt, true) >= recipeSPRWUt;
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        this.recipeSPRWUt = recipe.getProperty(SPDataProperty.getInstance(), 0);
        this.isDurationTotalSPRWU = recipe.hasProperty(TotalSPDataProperty.getInstance());
    }

    @Override
    protected void updateRecipeProgress() {
        if (recipeSPRWUt == 0) {
            super.updateRecipeProgress();
            return;
        }

        if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
            drawEnergy(recipeEUt, false);

            ISPResearchDataProvider provider = getComputationProvider();
            int availableSPRWUt = provider.requestSPRWUt(Integer.MAX_VALUE, true);
            if (availableSPRWUt >= recipeSPRWUt) {
                // 照常进行
                this.hasNotEnoughComputation = false;
                if (isDurationTotalSPRWU) {
                    // 尽可能多地抽取SP-RWU，并以这个数量增加进度
                    currentDrawnSPRWUt = provider.requestSPRWUt(availableSPRWUt, false);
                    progressTime += currentDrawnSPRWUt;
                } else {
                    // 只绘制配方SP-RWU/t，进度增加1
                    provider.requestSPRWUt(recipeSPRWUt, false);
                    progressTime++;
                }
                if (progressTime > maxProgressTime) {
                    completeRecipe();
                }
            } else {
                currentDrawnSPRWUt = 0;
                this.hasNotEnoughComputation = true;
                // 如果我们需要稳定的供应，只有低SP-RWU/t的递减进度
                if (type == SPDataType.STEADY) {
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
        this.recipeSPRWUt = 0;
        this.isDurationTotalSPRWU = false;
        this.hasNotEnoughComputation = false;
        this.currentDrawnSPRWUt = 0;
    }

    public int getRecipeSPRWUt() {
        return recipeSPRWUt;
    }

    public int getCurrentDrawnSPRWUt() {
        return isDurationTotalSPRWU ? currentDrawnSPRWUt : recipeSPRWUt;
    }

    public boolean isHasNotEnoughComputation() {
        return hasNotEnoughComputation;
    }

    @NotNull
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (this.progressTime > 0) {
            compound.setInteger("RecipeSP-RWUt", recipeSPRWUt);
            compound.setBoolean("IsDurationTotalSP-RWU", isDurationTotalSPRWU);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        if (this.progressTime > 0) {
            recipeSPRWUt = compound.getInteger("RecipeSP-RWUt");
            isDurationTotalSPRWU = compound.getBoolean("IsDurationTotalSP-RWU");
        }
    }

    /**
     * @return TOP/WAILA是否应该将配方进度显示为持续时间还是总计算量。
     */
    public boolean shouldShowDuration() {
        return !isDurationTotalSPRWU;
    }

    public enum SPDataType {
        /**
         * SP-RWU/t像EU/t一样工作。如果没有足够的，配方恢复进度/停止
         */
        STEADY,
        /**
         * SP-RWU/t的工作方式类似于总输入。如果没有足够的，配方将在当前进度时间停止。
         *只有在存在足够计算的情况下，滴答的进度才会增加。能量将始终被汲取。
         */
        SPORADIC
    }
}
