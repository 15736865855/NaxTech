package com.onlyex.naxtech.api.recipes.builders.research;

import com.onlyex.naxtech.api.recipes.research.builder.*;
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
    protected abstract GOAALRecipeBuilder.GOResearchRecipeEntry goresearch();
    protected abstract OPAALRecipeBuilder.OPResearchRecipeEntry opresearch();
    protected abstract SPAALRecipeBuilder.SPResearchRecipeEntry spresearch();
    protected abstract COAALRecipeBuilder.COResearchRecipeEntry coresearch();
    protected abstract SCAAALRecipeBuilder.SCAResearchRecipeEntry scaresearch();
    protected abstract SCHAALRecipeBuilder.SCHResearchRecipeEntry schresearch();
    protected abstract SDIAALRecipeBuilder.SDIResearchRecipeEntry sdiresearch();

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
            return DataRecipeBuilder.getDefaultResearchStationItem(gorwut, oprwut, sprwut, corwut, scarwut, schrwut, sdirwut);
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

        @Override
        public GOAALRecipeBuilder.GOResearchRecipeEntry goresearch() {
            validateResearchItem();
            if (gorwut <= 0 || totalGORWU <= 0) {
                throw new IllegalArgumentException("GO-RWU/t和总GO-RWU都必须设置为非零！");
            }
            if (gorwut > totalGORWU) {
                throw new IllegalArgumentException("总GO-RWU不能大于GO-RWU/t!");
            }

            int duration = totalGORWU;
            if (eut <= 0) eut = GO_RESEARCH_EUT;
            return new GOAALRecipeBuilder.GOResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, cwut, rwut, gorwut);
        }

        @Override
        public OPAALRecipeBuilder.OPResearchRecipeEntry opresearch() {
            validateResearchItem();
            if (oprwut <= 0 || totalOPRWU <= 0) {
                throw new IllegalArgumentException("OP-RWU/t和总OP-RWU都必须设置为非零！");
            }
            if (oprwut > totalOPRWU) {
                throw new IllegalArgumentException("总OP-RWU不能大于OP-RWU/t!");
            }

            int duration = totalOPRWU;
            if (eut <= 0) eut = OP_RESEARCH_EUT;
            return new OPAALRecipeBuilder.OPResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, cwut, rwut, gorwut, oprwut);
        }

        @Override
        public SPAALRecipeBuilder.SPResearchRecipeEntry spresearch() {
            validateResearchItem();

            if (sprwut <= 0 || totalSPRWU <= 0) {
                throw new IllegalArgumentException("SP-RWU/t和总SP-RWU都必须设置为非零！");
            }
            if (sprwut > totalSPRWU) {
                throw new IllegalArgumentException("总SP-RWU不能大于SP-RWU/t!");
            }

            int duration = totalSPRWU;
            if (eut <= 0) eut = SP_RESEARCH_EUT;
            return new SPAALRecipeBuilder.SPResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, cwut, rwut, gorwut, oprwut, sprwut);
        }

        @Override
        public COAALRecipeBuilder.COResearchRecipeEntry coresearch() {
            validateResearchItem();
            if (corwut <= 0 || totalCORWU <= 0) {
                throw new IllegalArgumentException("CO-RWU/t和总CO-RWU都必须设置为非零！");
            }
            if (corwut > totalCORWU) {
                throw new IllegalArgumentException("总CO-RWU不能大于CO-RWU/t!");
            }

            int duration = totalCORWU;
            if (eut <= 0) eut = CO_RESEARCH_EUT;
            return new COAALRecipeBuilder.COResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, cwut, rwut, gorwut, oprwut, sprwut, corwut);
        }

        @Override
        public SCAAALRecipeBuilder.SCAResearchRecipeEntry scaresearch() {
            validateResearchItem();
            if (scarwut <= 0 || totalSCARWU <= 0) {
                throw new IllegalArgumentException("SCA-RWU/t和总SCA-RWU都必须设置为非零！");
            }
            if (scarwut > totalSCARWU) {
                throw new IllegalArgumentException("总SCA-RWU不能大于SCA-RWU/t!");
            }


            int duration = totalSCARWU;
            if (eut <= 0) eut = SCA_RESEARCH_EUT;
            return new SCAAALRecipeBuilder.SCAResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, cwut, rwut, gorwut, oprwut, sprwut, corwut, scarwut);
        }

        @Override
        public SCHAALRecipeBuilder.SCHResearchRecipeEntry schresearch() {
            validateResearchItem();
            if (schrwut <= 0 || totalSCHRWU <= 0) {
                throw new IllegalArgumentException("SCH-RWU/t和总SCH-RWU都必须设置为非零！");
            }
            if (schrwut > totalSCHRWU) {
                throw new IllegalArgumentException("总SCH-RWU不能大于SCH-RWU/t!");
            }

            int duration = totalSCHRWU;
            if (eut <= 0) eut = SCH_RESEARCH_EUT;
            return new SCHAALRecipeBuilder.SCHResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, cwut, rwut, gorwut, oprwut, sprwut, corwut, scarwut, schrwut);
        }

        @Override
        public SDIAALRecipeBuilder.SDIResearchRecipeEntry sdiresearch() {
            validateResearchItem();
            if (sdirwut <= 0 || totalSDIRWU <= 0) {
                throw new IllegalArgumentException("SDI-RWU/t和总SDI-RWU都必须设置为非零！");
            }
            if (sdirwut > totalSDIRWU) {
                throw new IllegalArgumentException("总SDI-RWU不能大于SDI-RWU/t!");
            }

            int duration = totalSDIRWU;
            if (eut <= 0) eut = SDI_RESEARCH_EUT;
            return new SDIAALRecipeBuilder.SDIResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, cwut, rwut, gorwut, oprwut, sprwut, corwut, scarwut, schrwut, sdirwut);
        }

    }
}