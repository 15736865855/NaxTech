package com.onlyex.naxtech.api.capability.impl.research;

import com.onlyex.naxtech.api.capability.hatch.research.IResearchDataReceiver;
import com.onlyex.naxtech.api.capability.hatch.research.gooware.IGOResearchDataProvider;
import com.onlyex.naxtech.api.recipes.recipeproperties.data.GODataProperty;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.TotalComputationProperty;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 *需要计算的多块的配方逻辑。
 *与使用{@link GODataProperty}包含配方的RecipeMaps一起使用。
 *持有此逻辑的Multiblock必须实现{@link IGOResearchDataProvider}。
 */
public class GODataRecipeLogic extends MultiblockRecipeLogic {

    private final GODataType type;

    /**
     *配方持续时间是否应视为总GO-RWU值（因此，按每个刻度使用的GO-RWU/t递增），
     *或正常（每次成功抽取GO-RWU/t增加1）。如果此值为真，逻辑将尝试
     *尽可能多地绘制GO-RWU/t以尝试加速计算过程，GO-RWU/t被视为
     *最小值而不是静态成本。
     */
    private boolean isDurationTotalGORWU;
    private int recipeGORWUt;
    private boolean hasNotEnoughComputation;
    private int currentDrawnGORWUt;

    public GODataRecipeLogic(RecipeMapMultiblockController metaTileEntity, GODataType type) {
        super(metaTileEntity);
        this.type = type;
        if (!(metaTileEntity instanceof IResearchDataReceiver)) {
            throw new IllegalArgumentException("MetaTileEntity必须是IGOResearchDataReceiver的实例");
        }
    }

    @NotNull
    public IGOResearchDataProvider getComputationProvider() {
        IResearchDataReceiver controller = (IResearchDataReceiver) metaTileEntity;
        return controller.getGOResearchDataProvider();
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe) {
        if (!super.checkRecipe(recipe)) {
            return false;
        }
        if (!recipe.hasProperty(GODataProperty.getInstance())) {
            return true;
        }
        IGOResearchDataProvider provider = getComputationProvider();
        int recipeGORWUt = recipe.getProperty(GODataProperty.getInstance(), 0);
        return provider.requestGORWUt(recipeGORWUt, true) >= recipeGORWUt;
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        this.recipeGORWUt = recipe.getProperty(GODataProperty.getInstance(), 0);
        this.isDurationTotalGORWU = recipe.hasProperty(TotalComputationProperty.getInstance());
    }

    @Override
    protected void updateRecipeProgress() {
        if (recipeGORWUt == 0) {
            super.updateRecipeProgress();
            return;
        }

        if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
            drawEnergy(recipeEUt, false);

            IGOResearchDataProvider provider = getComputationProvider();
            int availableGORWUt = provider.requestGORWUt(Integer.MAX_VALUE, true);
            if (availableGORWUt >= recipeGORWUt) {
                // 照常进行
                this.hasNotEnoughComputation = false;
                if (isDurationTotalGORWU) {
                    // 尽可能多地抽取GO-RWU，并以这个数量增加进度
                    currentDrawnGORWUt = provider.requestGORWUt(availableGORWUt, false);
                    progressTime += currentDrawnGORWUt;
                } else {
                    // 只绘制配方GO-RWU/t，进度增加1
                    provider.requestGORWUt(recipeGORWUt, false);
                    progressTime++;
                }
                if (progressTime > maxProgressTime) {
                    completeRecipe();
                }
            } else {
                currentDrawnGORWUt = 0;
                this.hasNotEnoughComputation = true;
                // 如果我们需要稳定的供应，只有低GO-RWU/t的递减进度
                if (type == GODataType.STEADY) {
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
        this.recipeGORWUt = 0;
        this.isDurationTotalGORWU = false;
        this.hasNotEnoughComputation = false;
        this.currentDrawnGORWUt = 0;
    }

    public int getRecipeGORWUt() {
        return recipeGORWUt;
    }

    public int getCurrentDrawnGORWUt() {
        return isDurationTotalGORWU ? currentDrawnGORWUt : recipeGORWUt;
    }

    public boolean isHasNotEnoughComputation() {
        return hasNotEnoughComputation;
    }

    @NotNull
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (this.progressTime > 0) {
            compound.setInteger("RecipeGO-RWUt", recipeGORWUt);
            compound.setBoolean("IsDurationTotalGO-RWU", isDurationTotalGORWU);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        if (this.progressTime > 0) {
            recipeGORWUt = compound.getInteger("RecipeGO-RWUt");
            isDurationTotalGORWU = compound.getBoolean("IsDurationTotalGO-RWU");
        }
    }

    /**
     * @return TOP/WAILA是否应该将配方进度显示为持续时间还是总计算量。
     */
    public boolean shouldShowDuration() {
        return !isDurationTotalGORWU;
    }

    public enum GODataType {
        /**
         * GO-RWU/t像EU/t一样工作。如果没有足够的，配方恢复进度/停止
         */
        STEADY,
        /**
         * GO-RWU/t的工作方式类似于总输入。如果没有足够的，配方将在当前进度时间停止。
         *只有在存在足够计算的情况下，滴答的进度才会增加。能量将始终被汲取。
         */
        SPORADIC
    }
}
