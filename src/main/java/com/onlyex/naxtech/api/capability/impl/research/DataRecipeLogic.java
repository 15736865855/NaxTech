package com.onlyex.naxtech.api.capability.impl.research;

import com.onlyex.naxtech.api.capability.hatch.research.IResearchDataReceiver;
import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchDataProvider;
import com.onlyex.naxtech.api.recipes.recipeproperties.data.DataProperty;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.TotalComputationProperty;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 *需要计算的多块的配方逻辑。
 *与使用{@link DataProperty}包含配方的RecipeMaps一起使用。
 *持有此逻辑的Multiblock必须实现{@link IResearchDataProvider}。
 */
public class DataRecipeLogic extends MultiblockRecipeLogic {

    private final DataType type;

    /**
     *配方持续时间是否应视为总RWU值（因此，按每个刻度使用的RWU/t递增），
     *或正常（每次成功抽取RWU/t增加1）。如果此值为真，逻辑将尝试
     *尽可能多地绘制RWU/t以尝试加速计算过程，RWU/t被视为
     *最小值而不是静态成本。
     */
    private boolean isDurationTotalRWU;
    private int recipeRWUt;
    private boolean hasNotEnoughComputation;
    private int currentDrawnRWUt;

    public DataRecipeLogic(RecipeMapMultiblockController metaTileEntity, DataType type) {
        super(metaTileEntity);
        this.type = type;
        if (!(metaTileEntity instanceof IResearchDataReceiver)) {
            throw new IllegalArgumentException("MetaTileEntity必须是IResearchDataReceiver的实例");
        }
    }

    @NotNull
    public IResearchDataProvider getComputationProvider() {
        IResearchDataReceiver controller = (IResearchDataReceiver) metaTileEntity;
        return controller.getResearchDataProvider();
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe) {
        if (!super.checkRecipe(recipe)) {
            return false;
        }
        if (!recipe.hasProperty(DataProperty.getInstance())) {
            return true;
        }
        IResearchDataProvider provider = getComputationProvider();
        int recipeRWUt = recipe.getProperty(DataProperty.getInstance(), 0);
        return provider.requestRWUt(recipeRWUt, true) >= recipeRWUt;
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        this.recipeRWUt = recipe.getProperty(DataProperty.getInstance(), 0);
        this.isDurationTotalRWU = recipe.hasProperty(TotalComputationProperty.getInstance());
    }

    @Override
    protected void updateRecipeProgress() {
        if (recipeRWUt == 0) {
            super.updateRecipeProgress();
            return;
        }

        if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
            drawEnergy(recipeEUt, false);

            IResearchDataProvider provider = getComputationProvider();
            int availableRWUt = provider.requestRWUt(Integer.MAX_VALUE, true);
            if (availableRWUt >= recipeRWUt) {
                // 照常进行
                this.hasNotEnoughComputation = false;
                if (isDurationTotalRWU) {
                    // 尽可能多地抽取RWU，并以这个数量增加进度
                    currentDrawnRWUt = provider.requestRWUt(availableRWUt, false);
                    progressTime += currentDrawnRWUt;
                } else {
                    // 只绘制配方RWU/t，进度增加1
                    provider.requestRWUt(recipeRWUt, false);
                    progressTime++;
                }
                if (progressTime > maxProgressTime) {
                    completeRecipe();
                }
            } else {
                currentDrawnRWUt = 0;
                this.hasNotEnoughComputation = true;
                // 如果我们需要稳定的供应，只有低RWU/t的递减进度
                if (type == DataType.STEADY) {
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
        this.recipeRWUt = 0;
        this.isDurationTotalRWU = false;
        this.hasNotEnoughComputation = false;
        this.currentDrawnRWUt = 0;
    }

    public int getRecipeRWUt() {
        return recipeRWUt;
    }

    public int getCurrentDrawnRWUt() {
        return isDurationTotalRWU ? currentDrawnRWUt : recipeRWUt;
    }

    public boolean isHasNotEnoughComputation() {
        return hasNotEnoughComputation;
    }

    @NotNull
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (this.progressTime > 0) {
            compound.setInteger("RecipeRWUt", recipeRWUt);
            compound.setBoolean("IsDurationTotalRWU", isDurationTotalRWU);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        if (this.progressTime > 0) {
            recipeRWUt = compound.getInteger("RecipeRWUt");
            isDurationTotalRWU = compound.getBoolean("IsDurationTotalRWU");
        }
    }

    /**
     * @return TOP/WAILA是否应该将配方进度显示为持续时间还是总计算量。
     */
    public boolean shouldShowDuration() {
        return !isDurationTotalRWU;
    }

    public enum DataType {
        /**
         * RWU/t像EU/t一样工作。如果没有足够的，配方恢复进度/停止
         */
        STEADY,
        /**
         * RWU/t的工作方式类似于总输入。如果没有足够的，配方将在当前进度时间停止。
         *只有在存在足够计算的情况下，滴答的进度才会增加。能量将始终被汲取。
         */
        SPORADIC
    }
}
