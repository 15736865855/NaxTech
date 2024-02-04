package com.onlyex.naxtech.api.capability.impl.research;

import com.onlyex.naxtech.api.capability.hatch.research.IResearchDataReceiver;
import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchDataProvider;
import com.onlyex.naxtech.api.recipes.recipeproperties.data.CODataProperty;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.TotalComputationProperty;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 *需要计算的多块的配方逻辑。
 *与使用{@link CODataProperty}包含配方的RecipeMaps一起使用。
 *持有此逻辑的Multiblock必须实现{@link ICOResearchDataProvider}。
 */
public class CODataRecipeLogic extends MultiblockRecipeLogic {

    private final CODataType type;

    /**
     *配方持续时间是否应视为总CO-RWU值（因此，按每个刻度使用的CO-RWU/t递增），
     *或正常（每次成功抽取CO-RWU/t增加1）。如果此值为真，逻辑将尝试
     *尽可能多地绘制CO-RWU/t以尝试加速计算过程，CO-RWU/t被视为
     *最小值而不是静态成本。
     */
    private boolean isDurationTotalCORWU;
    private int recipeCORWUt;
    private boolean hasNotEnoughComputation;
    private int currentDrawnCORWUt;

    public CODataRecipeLogic(RecipeMapMultiblockController metaTileEntity, CODataType type) {
        super(metaTileEntity);
        this.type = type;
        if (!(metaTileEntity instanceof IResearchDataReceiver)) {
            throw new IllegalArgumentException("MetaTileEntity必须是ICOResearchDataReceiver的实例");
        }
    }

    @NotNull
    public ICOResearchDataProvider getComputationProvider() {
        IResearchDataReceiver controller = (IResearchDataReceiver) metaTileEntity;
        return controller.getCOResearchDataProvider();
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe) {
        if (!super.checkRecipe(recipe)) {
            return false;
        }
        if (!recipe.hasProperty(CODataProperty.getInstance())) {
            return true;
        }
        ICOResearchDataProvider provider = getComputationProvider();
        int recipeCORWUt = recipe.getProperty(CODataProperty.getInstance(), 0);
        return provider.requestCORWUt(recipeCORWUt, true) >= recipeCORWUt;
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        this.recipeCORWUt = recipe.getProperty(CODataProperty.getInstance(), 0);
        this.isDurationTotalCORWU = recipe.hasProperty(TotalComputationProperty.getInstance());
    }

    @Override
    protected void updateRecipeProgress() {
        if (recipeCORWUt == 0) {
            super.updateRecipeProgress();
            return;
        }

        if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
            drawEnergy(recipeEUt, false);

            ICOResearchDataProvider provider = getComputationProvider();
            int availableCORWUt = provider.requestCORWUt(Integer.MAX_VALUE, true);
            if (availableCORWUt >= recipeCORWUt) {
                // 照常进行
                this.hasNotEnoughComputation = false;
                if (isDurationTotalCORWU) {
                    // 尽可能多地抽取CO-RWU，并以这个数量增加进度
                    currentDrawnCORWUt = provider.requestCORWUt(availableCORWUt, false);
                    progressTime += currentDrawnCORWUt;
                } else {
                    // 只绘制配方CO-RWU/t，进度增加1
                    provider.requestCORWUt(recipeCORWUt, false);
                    progressTime++;
                }
                if (progressTime > maxProgressTime) {
                    completeRecipe();
                }
            } else {
                currentDrawnCORWUt = 0;
                this.hasNotEnoughComputation = true;
                // 如果我们需要稳定的供应，只有低CO-RWU/t的递减进度
                if (type == CODataType.STEADY) {
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
        this.recipeCORWUt = 0;
        this.isDurationTotalCORWU = false;
        this.hasNotEnoughComputation = false;
        this.currentDrawnCORWUt = 0;
    }

    public int getRecipeCORWUt() {
        return recipeCORWUt;
    }

    public int getCurrentDrawnCORWUt() {
        return isDurationTotalCORWU ? currentDrawnCORWUt : recipeCORWUt;
    }

    public boolean isHasNotEnoughComputation() {
        return hasNotEnoughComputation;
    }

    @NotNull
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (this.progressTime > 0) {
            compound.setInteger("RecipeCO-RWUt", recipeCORWUt);
            compound.setBoolean("IsDurationTotalCO-RWU", isDurationTotalCORWU);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        if (this.progressTime > 0) {
            recipeCORWUt = compound.getInteger("RecipeCO-RWUt");
            isDurationTotalCORWU = compound.getBoolean("IsDurationTotalCO-RWU");
        }
    }

    /**
     * @return TOP/WAILA是否应该将配方进度显示为持续时间还是总计算量。
     */
    public boolean shouldShowDuration() {
        return !isDurationTotalCORWU;
    }

    public enum CODataType {
        /**
         * CO-RWU/t像EU/t一样工作。如果没有足够的，配方恢复进度/停止
         */
        STEADY,
        /**
         * CO-RWU/t的工作方式类似于总输入。如果没有足够的，配方将在当前进度时间停止。
         *只有在存在足够计算的情况下，滴答的进度才会增加。能量将始终被汲取。
         */
        SPORADIC
    }
}
