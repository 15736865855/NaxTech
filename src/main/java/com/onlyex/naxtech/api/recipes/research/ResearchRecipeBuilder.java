package com.onlyex.naxtech.api.recipes.research;

import com.onlyex.naxtech.api.recipes.research.builder.AALRecipeBuilder;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IDataItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.util.GTStringUtils;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class ResearchRecipeBuilder<T extends ResearchRecipeBuilder<T>> {
    protected ItemStack researchStack;
    protected ItemStack dataStack;
    protected boolean ignoreNBT;
    protected String researchId;
    protected int eut;

    protected ResearchRecipeBuilder(){}

    public T researchStack(@NotNull ItemStack researchStack) {
        if (!researchStack.isEmpty()) {
            this.researchStack = researchStack;
            this.ignoreNBT = true;
        }
        return (T) this;
    }

    public T researchStack(@NotNull ItemStack researchStack, boolean ignoreNBT) {
        if (!researchStack.isEmpty()) {
            this.researchStack = researchStack;
            this.ignoreNBT = ignoreNBT;
        }
        return (T) this;
    }


    public T dataStack(@NotNull ItemStack dataStack) {
        if (!dataStack.isEmpty()) {
            this.dataStack = dataStack;
        }
        return (T) this;
    }

    public T researchId(String researchId) {
        this.researchId = researchId;
        return (T) this;
    }

    public T EUt(int eut) {
        this.eut = eut;
        return (T) this;
    }

    protected void validateResearchItem() {
        if (researchStack == null) {
            throw new IllegalArgumentException("研究堆栈不能为空!");
        }

        if (researchId == null) {
            researchId = GTStringUtils.itemStackToString(researchStack);
        }

        if (dataStack == null) {
            dataStack = getDefaultDataItem();
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
    }

    protected abstract ItemStack getDefaultDataItem();

    protected abstract AALRecipeBuilder.ResearchRecipeEntry research();

    public static class StationRecipeBuilder extends ResearchRecipeBuilder<ResearchRecipeBuilder.StationRecipeBuilder> {

        public static final int RESEARCH_EUT = GTValues.VA[GTValues.ZPM];
        public static final int GO_RESEARCH_EUT = GTValues.VA[GTValues.UV];
        public static final int OP_RESEARCH_EUT = GTValues.VA[GTValues.UHV];
        public static final int SP_RESEARCH_EUT = GTValues.VA[GTValues.UEV];
        public static final int CO_RESEARCH_EUT = GTValues.VA[GTValues.UIV];
        public static final int SCA_RESEARCH_EUT = GTValues.VA[GTValues.UXV];
        public static final int SCH_RESEARCH_EUT = GTValues.VA[GTValues.OpV];
        public static final int SDI_RESEARCH_EUT = GTValues.VA[GTValues.MAX];

        public static final int RESEARCH_TOTAL_RWUT = 4000;

        private int cwut;
        private int rwut;
        private int gorwut;
        private int oprwut;
        private int sprwut;
        private int corwut;
        private int scarwut;
        private int schrwut;
        private int sdirwut;

        private int totalCWU;
        private int totalRWU;
        private int totalGORWU;
        private int totalOPRWU;
        private int totalSPRWU;
        private int totalCORWU;
        private int totalSCARWU;
        private int totalSCHRWU;
        private int totalSDIRWU;

        public StationRecipeBuilder CWUt(int cwut) {
            this.cwut = cwut;
            this.totalCWU = cwut * RESEARCH_TOTAL_RWUT * totalRWU;
            return this;
        }

        public StationRecipeBuilder CWUt(int cwut, int totalCWU) {
            this.cwut = cwut;
            this.totalCWU = totalCWU;
            return this;
        }
        public StationRecipeBuilder RWUt(int rwut) {
            this.rwut = rwut;
            this.totalRWU = rwut * RESEARCH_TOTAL_RWUT;
            return this;
        }
        public StationRecipeBuilder  RWUt(int rwut, int totalRWU) {
            this.rwut = rwut;
            this.totalRWU = totalRWU;
            return this;
        }

        public StationRecipeBuilder GORWUt(int gorwut) {
            this.gorwut = gorwut;
            this.totalGORWU = gorwut * RESEARCH_TOTAL_RWUT;
            return this;
        }

        public StationRecipeBuilder GORWUt(int gorwut, int totalGORWU) {
            this.gorwut = gorwut;
            this.totalGORWU = totalGORWU;
            return this;
        }

        public StationRecipeBuilder OPRWUt(int oprwut) {
            this.oprwut = oprwut;
            this.totalOPRWU = oprwut * RESEARCH_TOTAL_RWUT;
            return this;
        }

        public StationRecipeBuilder OPRWUt(int oprwut, int totalOPRWU) {
            this.oprwut = oprwut;
            this.totalOPRWU = totalOPRWU;
            return this;
        }

        public StationRecipeBuilder SPRWUt(int sprwut) {
            this.sprwut = sprwut;
            this.totalSPRWU = sprwut * RESEARCH_TOTAL_RWUT;
            return this;
        }

        public StationRecipeBuilder SPRWUt(int sprwut, int totalSPRWU) {
            this.sprwut = sprwut;
            this.totalSPRWU = totalSPRWU;
            return this;
        }

        public StationRecipeBuilder CORWUt(int corwut) {
            this.corwut = corwut;
            this.totalCORWU = corwut * RESEARCH_TOTAL_RWUT;
            return this;
        }

        public StationRecipeBuilder CORWUt(int corwut, int totalCORWU) {
            this.corwut = corwut;
            this.totalCORWU = totalCORWU;
            return this;
        }

        public StationRecipeBuilder SCARWUt(int scarwut) {
            this.scarwut = scarwut;
            this.totalSCARWU = scarwut * RESEARCH_TOTAL_RWUT;
            return this;
        }

        public StationRecipeBuilder SCARWUt(int scarwut, int totalSCARWU) {
            this.scarwut = scarwut;
            this.totalSCARWU = totalSCARWU;
            return this;
        }

        public StationRecipeBuilder SCHRWUt(int schrwut) {
            this.schrwut = schrwut;
            this.totalSCHRWU = schrwut * RESEARCH_TOTAL_RWUT;
            return this;
        }

        public StationRecipeBuilder SCHRWUt(int schrwut, int totalSCHRWU) {
            this.schrwut = schrwut;
            this.totalSCHRWU = totalSCHRWU;
            return this;
        }

        public StationRecipeBuilder SDIRWUt(int sdirwut) {
            this.sdirwut = sdirwut;
            this.totalSDIRWU = sdirwut * RESEARCH_TOTAL_RWUT;
            return this;
        }

        public StationRecipeBuilder SDIRWUt(int sdirwut, int totalSDIRWU) {
            this.sdirwut = sdirwut;
            this.totalSDIRWU = totalSDIRWU;
            return this;
        }

        public StationRecipeBuilder() {}

        @Override
        protected ItemStack getDefaultDataItem() {
            return ResearchStationItem.getDefaultResearchStationItem(gorwut, oprwut, sprwut, corwut, scarwut, schrwut, sdirwut);
        }

        @Override
        public AALRecipeBuilder.ResearchRecipeEntry research() {
            validateResearchItem();
            if (rwut <= 0 || totalRWU <= 0) {
                throw new IllegalArgumentException("RWU/t和总RWU都必须设置为非零！");
            }
            if (rwut > totalRWU) {
                throw new IllegalArgumentException("总RWU不能大于RWU/t!");
            }

            int duration = totalRWU;
            if (eut <= 0) eut = RESEARCH_EUT;
            return new AALRecipeBuilder.ResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, cwut, rwut);
        }

    }
}