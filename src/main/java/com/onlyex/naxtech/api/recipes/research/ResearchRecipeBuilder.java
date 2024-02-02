package com.onlyex.naxtech.api.recipes.research;

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

/*        if (dataStack == null) {
            dataStack = getDefaultDataItem();
        }*/

        boolean foundBehavior = false;
        if (dataStack.getItem() instanceof MetaItem<?>metaItem) {
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

    //protected abstract ItemStack getDefaultDataItem();

/*    public static class ScannerRecipeBuilder extends ResearchRecipeBuilder<ScannerRecipeBuilder> {

        public static final int DEFAULT_SCANNER_DURATION = 1200; // 60s
        public static final int DEFAULT_SCANNER_EUT = GTValues.VA[GTValues.UV];

        private int duration;

        ScannerRecipeBuilder() {}

        public ScannerRecipeBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }

        @Override
        protected ItemStack getDefaultDataItem() {
            return ResearchLineManager.getDefaultScannerItem();
        }

        @Override
        protected ResearchLineRecipeBuilder.ResearchRecipeEntry build() {
            validateResearchItem();
            if (duration <= 0) duration = DEFAULT_SCANNER_DURATION;
            if (eut <= 0) eut = DEFAULT_SCANNER_EUT;
            return new ResearchLineRecipeBuilder.ResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration,
                    eut, 0);
        }
    }*/

    public static final int RESEARCH_EUT = GTValues.VA[GTValues.ZPM];
    public static final int GO_RESEARCH_EUT = GTValues.VA[GTValues.UV];
    public static final int OP_RESEARCH_EUT = GTValues.VA[GTValues.UHV];
    public static final int SP_RESEARCH_EUT = GTValues.VA[GTValues.UEV];
    public static final int CO_RESEARCH_EUT = GTValues.VA[GTValues.UIV];
    public static final int SCA_RESEARCH_EUT = GTValues.VA[GTValues.UXV];
    public static final int SCH_RESEARCH_EUT = GTValues.VA[GTValues.OpV];
    public static final int SDI_RESEARCH_EUT = GTValues.VA[GTValues.MAX];

    //默认情况下，如果提供了足够的RWU/t，所需的总RWU将为200秒。
    //提供更多的RWU/t将允许它花费更少的时间。
    public static final int RESEARCH_TOTAL_RWUT = 4000;

    protected int rwut;
    protected int gorwut;
    protected int oprwut;
    protected int sprwut;
    protected int corwut;
    protected int scarwut;
    protected int schrwut;
    protected int sdirwut;
    protected int totalRWU;
    protected int totalGORWU;
    protected int totalOPRWU;
    protected int totalSPRWU;
    protected int totalCORWU;
    protected int totalSCARWU;
    protected int totalSCHRWU;
    protected int totalSDIRWU;
    public T RWUt(int rwut) {
        this.rwut = rwut;
        this.totalRWU = rwut * RESEARCH_TOTAL_RWUT;
        return (T) this;
    }
    public T  RWUt(int rwut, int totalRWU) {
        this.rwut = rwut;
        this.totalRWU = totalRWU;
        return (T) this;
    }

    //
    public T GORWUt(int gorwut) {
        this.gorwut = gorwut;
        this.totalGORWU = gorwut * RESEARCH_TOTAL_RWUT;
        return (T) this;
    }

    public T GORWUt(int gorwut, int totalGORWU) {
        this.gorwut = gorwut;
        this.totalGORWU = totalGORWU;
        return (T) this;
    }

    //
    public T OPRWUt(int oprwut) {
        this.oprwut = oprwut;
        this.totalOPRWU = oprwut * RESEARCH_TOTAL_RWUT;
        return (T) this;
    }

    public T OPRWUt(int oprwut, int totalOPRWU) {
        this.oprwut = oprwut;
        this.totalOPRWU = totalOPRWU;
        return (T) this;
    }

    //
    public T SPRWUt(int sprwut) {
        this.sprwut = sprwut;
        this.totalSPRWU = sprwut * RESEARCH_TOTAL_RWUT;
        return (T) this;
    }

    public T SPRWUt(int sprwut, int totalSPRWU) {
        this.sprwut = sprwut;
        this.totalSPRWU = totalSPRWU;
        return (T) this;
    }

    //
    public T CORWUt(int corwut) {
        this.corwut = corwut;
        this.totalCORWU = corwut * RESEARCH_TOTAL_RWUT;
        return (T) this;
    }

    public T CORWUt(int corwut, int totalCORWU) {
        this.corwut = corwut;
        this.totalCORWU = totalCORWU;
        return (T) this;
    }

    //
    public T SCARWUt(int scarwut) {
        this.scarwut = scarwut;
        this.totalSCARWU = scarwut * RESEARCH_TOTAL_RWUT;
        return (T) this;
    }

    public T SCARWUt(int scarwut, int totalSCARWU) {
        this.scarwut = scarwut;
        this.totalSCARWU = totalSCARWU;
        return (T) this;
    }

    //
    public T SCHRWUt(int schrwut) {
        this.schrwut = schrwut;
        this.totalSCHRWU = schrwut * RESEARCH_TOTAL_RWUT;
        return (T) this;
    }

    public T SCHRWUt(int schrwut, int totalSCHRWU) {
        this.schrwut = schrwut;
        this.totalSCHRWU = totalSCHRWU;
        return (T) this;
    }

    //
    public T SDIRWUt(int sdirwut) {
        this.sdirwut = sdirwut;
        this.totalSDIRWU = sdirwut * RESEARCH_TOTAL_RWUT;
        return (T) this;
    }

    public T SDIRWUt(int sdirwut, int totalSDIRWU) {
        this.sdirwut = sdirwut;
        this.totalSDIRWU = totalSDIRWU;
        return (T) this;
    }


    protected abstract ResearchLineRecipeBuilder.ResearchRecipeEntry research();
    protected abstract ResearchLineRecipeBuilder.GOResearchRecipeEntry goresearch();
    protected abstract ResearchLineRecipeBuilder.OPResearchRecipeEntry opresearch();
    protected abstract ResearchLineRecipeBuilder.SPResearchRecipeEntry spresearch();
    protected abstract ResearchLineRecipeBuilder.COResearchRecipeEntry coresearch();
    protected abstract ResearchLineRecipeBuilder.SCAResearchRecipeEntry scaresearch();
    protected abstract ResearchLineRecipeBuilder.SCHResearchRecipeEntry schresearch();
    protected abstract ResearchLineRecipeBuilder.SDIResearchRecipeEntry sdiresearch();

    public static class StationRecipeBuilder extends ResearchRecipeBuilder<StationRecipeBuilder> {
        StationRecipeBuilder() {/**/}
        //
        @Override
        protected ResearchLineRecipeBuilder.ResearchRecipeEntry research() {
            validateResearchItem();
            if (rwut <= 0 || totalRWU <= 0) {
                throw new IllegalArgumentException("RWU/t和总RWU都必须设置为非零！");
            }
            if (rwut > totalRWU) {
                throw new IllegalArgumentException("总RWU不能大于RWU/t!");
            }

            //“持续时间”是总RWU/t。
            //在API中未调用持续时间，因为逻辑不会将其视为正常持续时间。
            int duration = totalRWU;
            if (eut <= 0) eut = RESEARCH_EUT;
            return new ResearchLineRecipeBuilder.ResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, rwut);
        }

        @Override
        protected ResearchLineRecipeBuilder.GOResearchRecipeEntry goresearch() {
            validateResearchItem();
            if (gorwut <= 0 || totalGORWU <= 0) {
                throw new IllegalArgumentException("GO-RWU/t和总GO-RWU都必须设置为非零！");
            }
            if (gorwut > totalGORWU) {
                throw new IllegalArgumentException("总GO-RWU不能大于GO-RWU/t!");
            }

            //“持续时间”是总RWU/t。
            //在API中未调用持续时间，因为逻辑不会将其视为正常持续时间。
            int duration = totalGORWU;
            if (eut <= 0) eut = GO_RESEARCH_EUT;
            return new ResearchLineRecipeBuilder.GOResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, rwut, gorwut);
        }

        @Override
        protected ResearchLineRecipeBuilder.OPResearchRecipeEntry opresearch() {
            validateResearchItem();
            if (oprwut <= 0 || totalOPRWU <= 0) {
                throw new IllegalArgumentException("OP-RWU/t和总OP-RWU都必须设置为非零！");
            }
            if (oprwut > totalOPRWU) {
                throw new IllegalArgumentException("总OP-RWU不能大于OP-RWU/t!");
            }

            //“持续时间”是总RWU/t。
            //在API中未调用持续时间，因为逻辑不会将其视为正常持续时间。
            int duration = totalOPRWU;
            if (eut <= 0) eut = OP_RESEARCH_EUT;
            return new ResearchLineRecipeBuilder.OPResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, rwut, gorwut, oprwut);
        }

        @Override
        protected ResearchLineRecipeBuilder.SPResearchRecipeEntry spresearch() {
            validateResearchItem();

            if (sprwut <= 0 || totalSPRWU <= 0) {
                throw new IllegalArgumentException("SP-RWU/t和总SP-RWU都必须设置为非零！");
            }
            if (sprwut > totalSPRWU) {
                throw new IllegalArgumentException("总SP-RWU不能大于SP-RWU/t!");
            }

            //“持续时间”是总RWU/t。
            //在API中未调用持续时间，因为逻辑不会将其视为正常持续时间。
            int duration = totalSPRWU;
            if (eut <= 0) eut = SP_RESEARCH_EUT;
            return new ResearchLineRecipeBuilder.SPResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, rwut, gorwut, oprwut, sprwut);
        }

        @Override
        protected ResearchLineRecipeBuilder.COResearchRecipeEntry coresearch() {
            validateResearchItem();
            if (corwut <= 0 || totalCORWU <= 0) {
                throw new IllegalArgumentException("CO-RWU/t和总CO-RWU都必须设置为非零！");
            }
            if (corwut > totalCORWU) {
                throw new IllegalArgumentException("总CO-RWU不能大于CO-RWU/t!");
            }

            //“持续时间”是总RWU/t。
            //在API中未调用持续时间，因为逻辑不会将其视为正常持续时间。
            int duration = totalCORWU;
            if (eut <= 0) eut = CO_RESEARCH_EUT;
            return new ResearchLineRecipeBuilder.COResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, rwut, gorwut, oprwut, sprwut, corwut);
        }

        @Override
        protected ResearchLineRecipeBuilder.SCAResearchRecipeEntry scaresearch() {
            validateResearchItem();
            if (scarwut <= 0 || totalSCARWU <= 0) {
                throw new IllegalArgumentException("SCA-RWU/t和总SCA-RWU都必须设置为非零！");
            }
            if (scarwut > totalSCARWU) {
                throw new IllegalArgumentException("总SCA-RWU不能大于SCA-RWU/t!");
            }

            //“持续时间”是总RWU/t。
            //在API中未调用持续时间，因为逻辑不会将其视为正常持续时间。
            int duration = totalSCARWU;
            if (eut <= 0) eut = SCA_RESEARCH_EUT;
            return new ResearchLineRecipeBuilder.SCAResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, rwut, gorwut, oprwut, sprwut, corwut, scarwut);
        }

        @Override
        protected ResearchLineRecipeBuilder.SCHResearchRecipeEntry schresearch() {
            validateResearchItem();
            if (schrwut <= 0 || totalSCHRWU <= 0) {
                throw new IllegalArgumentException("SCH-RWU/t和总SCH-RWU都必须设置为非零！");
            }
            if (schrwut > totalSCHRWU) {
                throw new IllegalArgumentException("总SCH-RWU不能大于SCH-RWU/t!");
            }

            //“持续时间”是总RWU/t。
            //在API中未调用持续时间，因为逻辑不会将其视为正常持续时间。
            int duration = totalSCHRWU;
            if (eut <= 0) eut = SCH_RESEARCH_EUT;
            return new ResearchLineRecipeBuilder.SCHResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, rwut, gorwut, oprwut, sprwut, corwut, scarwut, schrwut);
        }

        @Override
        protected ResearchLineRecipeBuilder.SDIResearchRecipeEntry sdiresearch() {
            validateResearchItem();
            if (sdirwut <= 0 || totalSDIRWU <= 0) {
                throw new IllegalArgumentException("SDI-RWU/t和总SDI-RWU都必须设置为非零！");
            }
            if (sdirwut > totalSDIRWU) {
                throw new IllegalArgumentException("总SDI-RWU不能大于SDI-RWU/t!");
            }

            //“持续时间”是总RWU/t。
            //在API中未调用持续时间，因为逻辑不会将其视为正常持续时间。
            int duration = totalSDIRWU;
            if (eut <= 0) eut = SDI_RESEARCH_EUT;
            return new ResearchLineRecipeBuilder.SDIResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration, eut, rwut, gorwut, oprwut, sprwut, corwut, scarwut, schrwut, sdirwut);
        }
    }
}
