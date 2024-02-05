package com.onlyex.naxtech.api.capability.impl.research;

import com.onlyex.naxtech.api.capability.hatch.research.IResearchDataReceiver;
import com.onlyex.naxtech.api.capability.hatch.research.optical.IOPResearchDataProvider;
import com.onlyex.naxtech.api.recipes.recipeproperties.data.OPDataProperty;
import com.onlyex.naxtech.api.recipes.recipeproperties.total.TotalOPDataProperty;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 *需要计算的多块的配方逻辑。
 *与使用{@link OPDataProperty}包含配方的RecipeMaps一起使用。
 *持有此逻辑的Multiblock必须实现{@link IOPResearchDataProvider}。
 */
public class OPDataRecipeLogic extends MultiblockRecipeLogic {

    private final OPDataType type;

    /**
     *配方持续时间是否应视为总OP-RWU值（因此，按每个刻度使用的OP-RWU/t递增），
     *或正常（每次成功抽取OP-RWU/t增加1）。如果此值为真，逻辑将尝试
     *尽可能多地绘制OP-RWU/t以尝试加速计算过程，OP-RWU/t被视为
     *最小值而不是静态成本。
     */
    private boolean isDurationTotalOPRWU;
    private int recipeOPRWUt;
    private boolean hasNotEnoughComputation;
    private int currentDrawnOPRWUt;

    public OPDataRecipeLogic(RecipeMapMultiblockController metaTileEntity, OPDataType type) {
        super(metaTileEntity);
        this.type = type;
        if (!(metaTileEntity instanceof IResearchDataReceiver)) {
            throw new IllegalArgumentException("MetaTileEntity必须是IOPResearchDataReceiver的实例");
        }
    }

    @NotNull
    public IOPResearchDataProvider getComputationProvider() {
        IResearchDataReceiver controller = (IResearchDataReceiver) metaTileEntity;
        return controller.getOPResearchDataProvider();
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe) {
        if (!super.checkRecipe(recipe)) {
            return false;
        }
        if (!recipe.hasProperty(OPDataProperty.getInstance())) {
            return true;
        }
        IOPResearchDataProvider provider = getComputationProvider();
        int recipeOPRWUt = recipe.getProperty(OPDataProperty.getInstance(), 0);
        return provider.requestOPRWUt(recipeOPRWUt, true) >= recipeOPRWUt;
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        this.recipeOPRWUt = recipe.getProperty(OPDataProperty.getInstance(), 0);
        this.isDurationTotalOPRWU = recipe.hasProperty(TotalOPDataProperty.getInstance());
    }

    @Override
    protected void updateRecipeProgress() {
        if (recipeOPRWUt == 0) {
            super.updateRecipeProgress();
            return;
        }

        if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
            drawEnergy(recipeEUt, false);

            IOPResearchDataProvider provider = getComputationProvider();
            int availableOPRWUt = provider.requestOPRWUt(Integer.MAX_VALUE, true);
            if (availableOPRWUt >= recipeOPRWUt) {
                // 照常进行
                this.hasNotEnoughComputation = false;
                if (isDurationTotalOPRWU) {
                    // 尽可能多地抽取OP-RWU，并以这个数量增加进度
                    currentDrawnOPRWUt = provider.requestOPRWUt(availableOPRWUt, false);
                    progressTime += currentDrawnOPRWUt;
                } else {
                    // 只绘制配方OP-RWU/t，进度增加1
                    provider.requestOPRWUt(recipeOPRWUt, false);
                    progressTime++;
                }
                if (progressTime > maxProgressTime) {
                    completeRecipe();
                }
            } else {
                currentDrawnOPRWUt = 0;
                this.hasNotEnoughComputation = true;
                // 如果我们需要稳定的供应，只有低OP-RWU/t的递减进度
                if (type == OPDataType.STEADY) {
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
        this.recipeOPRWUt = 0;
        this.isDurationTotalOPRWU = false;
        this.hasNotEnoughComputation = false;
        this.currentDrawnOPRWUt = 0;
    }

    public int getRecipeOPRWUt() {
        return recipeOPRWUt;
    }

    public int getCurrentDrawnOPRWUt() {
        return isDurationTotalOPRWU ? currentDrawnOPRWUt : recipeOPRWUt;
    }

    public boolean isHasNotEnoughComputation() {
        return hasNotEnoughComputation;
    }

    @NotNull
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (this.progressTime > 0) {
            compound.setInteger("RecipeOP-RWUt", recipeOPRWUt);
            compound.setBoolean("IsDurationTotalOP-RWU", isDurationTotalOPRWU);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        if (this.progressTime > 0) {
            recipeOPRWUt = compound.getInteger("RecipeOP-RWUt");
            isDurationTotalOPRWU = compound.getBoolean("IsDurationTotalOP-RWU");
        }
    }

    /**
     * @return TOP/WAILA是否应该将配方进度显示为持续时间还是总计算量。
     */
    public boolean shouldShowDuration() {
        return !isDurationTotalOPRWU;
    }

    public enum OPDataType {
        /**
         * OP-RWU/t像EU/t一样工作。如果没有足够的，配方恢复进度/停止
         */
        STEADY,
        /**
         * OP-RWU/t的工作方式类似于总输入。如果没有足够的，配方将在当前进度时间停止。
         *只有在存在足够计算的情况下，滴答的进度才会增加。能量将始终被汲取。
         */
        SPORADIC
    }
}
