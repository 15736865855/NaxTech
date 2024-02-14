package com.onlyex.naxtech.api.recipes.builders.research;

import com.onlyex.naxtech.api.recipes.recipeproperties.data.*;
import com.onlyex.naxtech.api.recipes.recipeproperties.total.*;
import com.onlyex.naxtech.api.utils.NTLog;
import com.onlyex.naxtech.common.items.NTMetaItems;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.ComputationProperty;
import gregtech.api.recipes.recipeproperties.TotalComputationProperty;
import gregtech.api.util.EnumValidationResult;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class DataRecipeBuilder extends RecipeBuilder<DataRecipeBuilder> {

    @NotNull
    public static ItemStack getDefaultResearchStationItem(
            int gorwut,int oprwut,int sprwut,int corwut,int scarwut,int schrwut,int sdirwut) {
        if (gorwut > 32) {
            return NTMetaItems.GOOWARE_RESEARCH_DATA_CARD.getStackForm();
        }
        if (oprwut > 32) {
            return NTMetaItems.OPTICAL_RESEARCH_DATA_CARD.getStackForm();
        }
        if (sprwut > 32) {
            return NTMetaItems.SPINTRONIC_RESEARCH_DATA_CARD.getStackForm();
        }
        if (corwut > 32) {
            return NTMetaItems.COSMIC_RESEARCH_DATA_CARD.getStackForm();
        }
        if (scarwut > 32) {
            return NTMetaItems.SUPRA_CAUSAL_RESEARCH_DATA_CARD.getStackForm();
        }
        if (schrwut > 32) {
            return NTMetaItems.SUPRA_CHRONAL_RESEARCH_DATA_CARD.getStackForm();
        }
        if (sdirwut > 32) {
            return NTMetaItems.SUPRA_DIMENSION_RESEARCH_DATA_CARD.getStackForm();
        }

        return NTMetaItems.RESEARCH_DATA_CARD.getStackForm();
    }

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
        return this;
    }

    /**
     * 此配方的总计算量。如果需要，应该使用this而不是调用持续时间（）。
     */
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
        return this;
    }

    /**
     * 此配方的总计算量。如果需要，应该使用this而不是调用持续时间（）。
     */
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
        return this;
    }

    /**
     * 此配方的总计算量。如果需要，应该使用this而不是调用持续时间（）。
     */
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
        return this;
    }

    /**
     * 此配方的总计算量。如果需要，应该使用this而不是调用持续时间（）。
     */
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
        return this;
    }

    /**
     * 此配方的总计算量。如果需要，应该使用this而不是调用持续时间（）。
     */
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
        return this;
    }

    /**
     * 此配方的总计算量。如果需要，应该使用this而不是调用持续时间（）。
     */
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
        return this;
    }

    /**
     * 此配方的总计算量。如果需要，应该使用this而不是调用持续时间（）。
     */
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
        return this;
    }

    /**
     * 此配方的总计算量。如果需要，应该使用this而不是调用持续时间（）。
     */
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
        return this;
    }

    /**
     * 此配方的总计算量。如果需要，应该使用this而不是调用持续时间（）。
     */
    public DataRecipeBuilder totalSDIRWU(int totalSDIRWU) {
        if (totalSDIRWU < 0) {
            NTLog.logger.error("总SDI-RWU不能小于0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalSDIDataProperty.getInstance(), totalSDIRWU);
        return duration(totalSDIRWU);
    }

}
