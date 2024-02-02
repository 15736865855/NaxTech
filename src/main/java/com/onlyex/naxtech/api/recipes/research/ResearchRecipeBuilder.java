package com.onlyex.naxtech.api.recipes.research;

import com.onlyex.naxtech.api.utils.ResearchLineManager;
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

        if (dataStack == null) {
            dataStack = getDefaultDataItem();
        }

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

    protected abstract ItemStack getDefaultDataItem();

    protected abstract ResearchLineRecipeBuilder.ResearchRecipeEntry build();

    public static class ScannerRecipeBuilder extends ResearchRecipeBuilder<ScannerRecipeBuilder> {

        public static final int DEFAULT_SCANNER_DURATION = 1200; // 60s
        public static final int DEFAULT_SCANNER_EUT = GTValues.VA[GTValues.UV];

        private int duration;

        ScannerRecipeBuilder() {/**/}

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
    }

    public static class StationRecipeBuilder extends ResearchRecipeBuilder<StationRecipeBuilder> {

        public static final int DEFAULT_STATION_EUT = GTValues.VA[GTValues.UV];
        //默认情况下，如果提供了足够的RWU/t，所需的总RWU将为200秒。
        //提供更多的RWU/t将允许它花费更少的时间。
        public static final int DEFAULT_STATION_TOTAL_RWUT = 4000;

        private int rwut;
        private int totalRWU;

        StationRecipeBuilder() {/**/}

        public StationRecipeBuilder RWUt(int rwut) {
            this.rwut = rwut;
            this.totalRWU = rwut * DEFAULT_STATION_TOTAL_RWUT;
            return this;
        }

        public StationRecipeBuilder RWUt(int rwut, int totalRWU) {
            this.rwut = rwut;
            this.totalRWU = totalRWU;
            return this;
        }

        @Override
        protected ItemStack getDefaultDataItem() {
            return ResearchLineManager.getDefaultResearchStationItem(rwut);
        }

        @Override
        protected ResearchLineRecipeBuilder.ResearchRecipeEntry build() {
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
            if (eut <= 0) eut = DEFAULT_STATION_EUT;

            return new ResearchLineRecipeBuilder.ResearchRecipeEntry(researchId, researchStack, dataStack, ignoreNBT,
                    duration,
                    eut, rwut);
        }
    }
}
