package com.onlyex.naxtech.api.capability.impl.research;

import com.onlyex.naxtech.api.capability.hatch.research.IResearchDataReceiver;
import com.onlyex.naxtech.api.capability.hatch.research.supradimension.ISDIResearchDataProvider;
import com.onlyex.naxtech.api.recipes.recipeproperties.data.SDIDataProperty;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.TotalComputationProperty;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 *需要计算的多块的配方逻辑。
 *与使用{@link SDIDataProperty}包含配方的RecipeMaps一起使用。
 *持有此逻辑的Multiblock必须实现{@link ISDIResearchDataProvider}。
 */
public class SDIDataRecipeLogic extends MultiblockRecipeLogic {

    private final SDIDataType type;

    /**
     *配方持续时间是否应视为总SDI-RWU值（因此，按每个刻度使用的SDI-RWU/t递增），
     *或正常（每次成功抽取SDI-RWU/t增加1）。如果此值为真，逻辑将尝试
     *尽可能多地绘制SDI-RWU/t以尝试加速计算过程，SDI-RWU/t被视为
     *最小值而不是静态成本。
     */
    private boolean isDurationTotalSDIRWU;
    private int recipeSDIRWUt;
    private boolean hasNotEnoughComputation;
    private int currentDrawnSDIRWUt;

    public SDIDataRecipeLogic(RecipeMapMultiblockController metaTileEntity, SDIDataType type) {
        super(metaTileEntity);
        this.type = type;
        if (!(metaTileEntity instanceof IResearchDataReceiver)) {
            throw new IllegalArgumentException("MetaTileEntity必须是ISDIResearchDataReceiver的实例");
        }
    }

    @NotNull
    public ISDIResearchDataProvider getComputationProvider() {
        IResearchDataReceiver controller = (IResearchDataReceiver) metaTileEntity;
        return controller.getSDIResearchDataProvider();
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe) {
        if (!super.checkRecipe(recipe)) {
            return false;
        }
        if (!recipe.hasProperty(SDIDataProperty.getInstance())) {
            return true;
        }
        ISDIResearchDataProvider provider = getComputationProvider();
        int recipeSDIRWUt = recipe.getProperty(SDIDataProperty.getInstance(), 0);
        return provider.requestSDIRWUt(recipeSDIRWUt, true) >= recipeSDIRWUt;
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        this.recipeSDIRWUt = recipe.getProperty(SDIDataProperty.getInstance(), 0);
        this.isDurationTotalSDIRWU = recipe.hasProperty(TotalComputationProperty.getInstance());
    }

    @Override
    protected void updateRecipeProgress() {
        if (recipeSDIRWUt == 0) {
            super.updateRecipeProgress();
            return;
        }

        if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
            drawEnergy(recipeEUt, false);

            ISDIResearchDataProvider provider = getComputationProvider();
            int availableSDIRWUt = provider.requestSDIRWUt(Integer.MAX_VALUE, true);
            if (availableSDIRWUt >= recipeSDIRWUt) {
                // 照常进行
                this.hasNotEnoughComputation = false;
                if (isDurationTotalSDIRWU) {
                    // 尽可能多地抽取SDI-RWU，并以这个数量增加进度
                    currentDrawnSDIRWUt = provider.requestSDIRWUt(availableSDIRWUt, false);
                    progressTime += currentDrawnSDIRWUt;
                } else {
                    // 只绘制配方SDI-RWU/t，进度增加1
                    provider.requestSDIRWUt(recipeSDIRWUt, false);
                    progressTime++;
                }
                if (progressTime > maxProgressTime) {
                    completeRecipe();
                }
            } else {
                currentDrawnSDIRWUt = 0;
                this.hasNotEnoughComputation = true;
                // 如果我们需要稳定的供应，只有低SDI-RWU/t的递减进度
                if (type == SDIDataType.STEADY) {
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
        this.recipeSDIRWUt = 0;
        this.isDurationTotalSDIRWU = false;
        this.hasNotEnoughComputation = false;
        this.currentDrawnSDIRWUt = 0;
    }

    public int getRecipeSDIRWUt() {
        return recipeSDIRWUt;
    }

    public int getCurrentDrawnSDIRWUt() {
        return isDurationTotalSDIRWU ? currentDrawnSDIRWUt : recipeSDIRWUt;
    }

    public boolean isHasNotEnoughComputation() {
        return hasNotEnoughComputation;
    }

    @NotNull
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (this.progressTime > 0) {
            compound.setInteger("RecipeSDI-RWUt", recipeSDIRWUt);
            compound.setBoolean("IsDurationTotalSDI-RWU", isDurationTotalSDIRWU);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        if (this.progressTime > 0) {
            recipeSDIRWUt = compound.getInteger("RecipeSDI-RWUt");
            isDurationTotalSDIRWU = compound.getBoolean("IsDurationTotalSDI-RWU");
        }
    }

    /**
     * @return TOP/WAILA是否应该将配方进度显示为持续时间还是总计算量。
     */
    public boolean shouldShowDuration() {
        return !isDurationTotalSDIRWU;
    }

    public enum SDIDataType {
        /**
         * SDI-RWU/t像EU/t一样工作。如果没有足够的，配方恢复进度/停止
         */
        STEADY,
        /**
         * SDI-RWU/t的工作方式类似于总输入。如果没有足够的，配方将在当前进度时间停止。
         *只有在存在足够计算的情况下，滴答的进度才会增加。能量将始终被汲取。
         */
        SPORADIC
    }
}
