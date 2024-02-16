package com.onlyex.naxtech.api.recipes.research;

import com.onlyex.naxtech.api.recipes.recipeproperties.data.*;
import com.onlyex.naxtech.api.recipes.recipeproperties.total.*;
import com.onlyex.naxtech.api.utils.NTLog;
import com.onlyex.naxtech.api.utils.NTUtility;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IDataItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.recipeproperties.ComputationProperty;
import gregtech.api.recipes.recipeproperties.TotalComputationProperty;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTStringUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

import static com.onlyex.naxtech.api.utils.ResearchId.writeResearchToNBT;

public class DataRecipeBuilder extends RecipeBuilder<DataRecipeBuilder> {

    public DataRecipeBuilder() {/**/}

    public DataRecipeBuilder(Recipe recipe, RecipeMap<DataRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public DataRecipeBuilder(RecipeBuilder<DataRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public DataRecipeBuilder copy() {
        return new DataRecipeBuilder(this);
    }

    protected ItemStack researchStack;
    protected ItemStack dataStack;
    protected boolean ignoreNBT;
    protected String researchId;
    //protected int duration;

    public DataRecipeBuilder researchStack(@NotNull ItemStack researchStack) {
        if (!researchStack.isEmpty()) {
            this.researchStack = researchStack;
            this.ignoreNBT = true;
        }
        return this;
    }

    public DataRecipeBuilder researchStack(@NotNull ItemStack researchStack, boolean ignoreNBT) {
        if (!researchStack.isEmpty()) {
            this.researchStack = researchStack;
            this.ignoreNBT = ignoreNBT;
        }
        return this;
    }

    public DataRecipeBuilder dataStack(@NotNull ItemStack dataStack) {
        if (!dataStack.isEmpty()) {
            this.dataStack = dataStack;
        }

        boolean foundBehavior = false;
        if (dataStack.getItem() instanceof MetaItem<?> metaItem) {
            for (IItemBehaviour behaviour : metaItem.getBehaviours(dataStack)) {
                if (behaviour instanceof IDataItem) {
                    foundBehavior = true;
                    dataStack = dataStack.copy();
                    dataStack.setCount(1);
                    break;
                }
            }
        }
        if (!foundBehavior) {
            throw new IllegalArgumentException("Data ItemStack必须具有IDataItem行为");
        }
        return this;
    }

    public DataRecipeBuilder researchId(String researchId) {
        this.researchId = researchId;
        return this;
    }

    public DataRecipeBuilder writeToNBT(@NotNull ItemStack researchStack, @NotNull ItemStack dataStack) {
        //this.researchId = getResearchId();
        if (researchId == null) {
            researchId = GTStringUtils.itemStackToString(researchStack);
        }
        this.researchStack = researchStack;
        this.researchStack = getResearchStack();
        this.dataStack = dataStack;
        this.dataStack = getDataStack();

        NBTTagCompound compound = NTUtility.getOrCreateNbtCompound(dataStack);
        writeResearchToNBT(compound, researchId);
        inputNBT(dataStack.getItem(), 1, dataStack.getMetadata(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(dataStack);

        if (ignoreNBT) {
            inputNBT(researchStack.getItem(), 1, researchStack.getMetadata(), NBTMatcher.ANY, NBTCondition.ANY);
        } /*else {
            inputs(researchStack);
        }*/
        return this;
    }

    @NotNull
    public String getResearchId() {
        return this.researchId;
    }

    @NotNull
    public ItemStack getResearchStack() {
        return researchStack;
    }

    @NotNull
    public ItemStack getDataStack() {
        return dataStack;
    }

    public boolean getIgnoreNBT() {
        return ignoreNBT;
    }

/*    public int getDuration() {
        return duration;
    }*/

    @Override
    public boolean applyProperty(@NotNull String key, Object value) {
        if (key.equals(ComputationProperty.KEY)) {
            this.CWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalComputationProperty.KEY)) {
            this.totalCWU(((Number) value).intValue());
            return true;
        }
        if (key.equals(DataProperty.KEY)) {
            this.RWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalDataProperty.KEY)) {
            this.totalRWU(((Number) value).intValue());
            return true;
        }
        if (key.equals(GODataProperty.KEY)) {
            this.GORWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalGODataProperty.KEY)) {
            this.totalGORWU(((Number) value).intValue());
            return true;
        }
        if (key.equals(OPDataProperty.KEY)) {
            this.OPRWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalOPDataProperty.KEY)) {
            this.totalOPRWU(((Number) value).intValue());
            return true;
        }
        if (key.equals(SPDataProperty.KEY)) {
            this.SPRWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalSPDataProperty.KEY)) {
            this.totalSPRWU(((Number) value).intValue());
            return true;
        }
        if (key.equals(CODataProperty.KEY)) {
            this.CORWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalCODataProperty.KEY)) {
            this.totalCORWU(((Number) value).intValue());
            return true;
        }
        if (key.equals(SCADataProperty.KEY)) {
            this.SCARWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalSCADataProperty.KEY)) {
            this.totalSCARWU(((Number) value).intValue());
            return true;
        }
        if (key.equals(SCHDataProperty.KEY)) {
            this.SCHRWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalSCHDataProperty.KEY)) {
            this.totalSCHRWU(((Number) value).intValue());
            return true;
        }
        if (key.equals(SDIDataProperty.KEY)) {
            this.SDIRWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalSDIDataProperty.KEY)) {
            this.totalSDIRWU(((Number) value).intValue());
            return true;
        }
        return super.applyProperty(key, value);
    }

    public DataRecipeBuilder CWUt(int cwut) {
        if (cwut < 0) {
            NTLog.logger.error("CWU/t不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(ComputationProperty.getInstance(), cwut);
        totalCWU(cwut * 4096);
        return this;
    }
    public DataRecipeBuilder totalCWU(int totalCWU) {
        if (totalCWU < 0) {
            NTLog.logger.error("总CWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalComputationProperty.getInstance(), totalCWU);
        return duration(totalCWU);
    }

    public DataRecipeBuilder RWUt(int rwut) {
        if (rwut < 0) {
            NTLog.logger.error("RWU/t不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(DataProperty.getInstance(), rwut);
        totalRWU(rwut * 4096);
        return this;
    }
    public DataRecipeBuilder totalRWU(int totalRWU) {
        if (totalRWU < 0) {
            NTLog.logger.error("总RWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalDataProperty.getInstance(), totalRWU);
        return duration(totalRWU);
    }

    public DataRecipeBuilder GORWUt(int gorwut) {
        if (gorwut < 0) {
            NTLog.logger.error("GO-RWU/t不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(GODataProperty.getInstance(), gorwut);
        totalGORWU(gorwut * 4096);
        return this;
    }
    public DataRecipeBuilder totalGORWU(int totalGORWU) {
        if (totalGORWU < 0) {
            NTLog.logger.error("总GO-RWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalGODataProperty.getInstance(), totalGORWU);
        return duration(totalGORWU);
    }

    public DataRecipeBuilder OPRWUt(int oprwut) {
        if (oprwut < 0) {
            NTLog.logger.error("OP-RWU/t不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(OPDataProperty.getInstance(), oprwut);
        totalOPRWU(oprwut * 4096);
        return this;
    }
    public DataRecipeBuilder totalOPRWU(int totalOPRWU) {
        if (totalOPRWU < 0) {
            NTLog.logger.error("总OP-RWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalOPDataProperty.getInstance(), totalOPRWU);
        return duration(totalOPRWU);
    }

    public DataRecipeBuilder SPRWUt(int sprwut) {
        if (sprwut < 0) {
            NTLog.logger.error("SP-RWU/t不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(SPDataProperty.getInstance(), sprwut);
        totalSPRWU(sprwut * 4096);
        return this;
    }
    public DataRecipeBuilder totalSPRWU(int totalSPRWU) {
        if (totalSPRWU < 0) {
            NTLog.logger.error("总SP-RWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalSPDataProperty.getInstance(), totalSPRWU);
        return duration(totalSPRWU);
    }

    public DataRecipeBuilder CORWUt(int corwut) {
        if (corwut < 0) {
            NTLog.logger.error("CO-RWU/t不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(CODataProperty.getInstance(), corwut);
        totalCORWU(corwut * 4096);
        return this;
    }
    public DataRecipeBuilder totalCORWU(int totalCORWU) {
        if (totalCORWU < 0) {
            NTLog.logger.error("总CO-RWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalCODataProperty.getInstance(), totalCORWU);
        return duration(totalCORWU);
    }

    public DataRecipeBuilder SCARWUt(int scarwut) {
        if (scarwut < 0) {
            NTLog.logger.error("SCA-RWU/t不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(SCADataProperty.getInstance(), scarwut);
        totalSCARWU(scarwut * 4096);
        return this;
    }
    public DataRecipeBuilder totalSCARWU(int totalSCARWU) {
        if (totalSCARWU < 0) {
            NTLog.logger.error("总SCA-RWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalSCADataProperty.getInstance(), totalSCARWU);
        return duration(totalSCARWU);
    }

    public DataRecipeBuilder SCHRWUt(int schrwut) {
        if (schrwut < 0) {
            NTLog.logger.error("SCH-RWU/t不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(SCHDataProperty.getInstance(), schrwut);
        totalSCHRWU(schrwut * 4096);
        return this;
    }
    public DataRecipeBuilder totalSCHRWU(int totalSCHRWU) {
        if (totalSCHRWU < 0) {
            NTLog.logger.error("总SCH-RWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalSCHDataProperty.getInstance(), totalSCHRWU);
        return duration(totalSCHRWU);
    }

    public DataRecipeBuilder SDIRWUt(int sdirwut) {
        if (sdirwut < 0) {
            NTLog.logger.error("SDI-RWU/t不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(SDIDataProperty.getInstance(), sdirwut);
        totalSDIRWU(sdirwut * 4096);
        return this;
    }
    public DataRecipeBuilder totalSDIRWU(int totalSDIRWU) {
        if (totalSDIRWU < 0) {
            NTLog.logger.error("总SDI-RWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalSDIDataProperty.getInstance(), totalSDIRWU);
        return duration(totalSDIRWU);
    }

}
