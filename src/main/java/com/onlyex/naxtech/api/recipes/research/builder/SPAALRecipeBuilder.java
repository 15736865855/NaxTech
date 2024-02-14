package com.onlyex.naxtech.api.recipes.research.builder;

import com.onlyex.naxtech.api.recipes.builders.research.ResearchRecipeBuilder;
import com.onlyex.naxtech.api.recipes.recipeproperties.research.ResearchProperty;
import com.onlyex.naxtech.api.recipes.recipeproperties.research.ResearchPropertyData;
import com.onlyex.naxtech.api.utils.NTLog;
import com.onlyex.naxtech.common.ConfigHolder;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.UnaryOperator;

public class SPAALRecipeBuilder extends RecipeBuilder<SPAALRecipeBuilder> {
    private final Collection<SPResearchRecipeEntry> recipeSPEntries = new ArrayList<>();
    private boolean generatingRecipes = true;

    public SPAALRecipeBuilder() {}

    @SuppressWarnings("unused")
    public SPAALRecipeBuilder(Recipe recipe, RecipeMap<SPAALRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public SPAALRecipeBuilder(@NotNull SPAALRecipeBuilder builder) {
        super(builder);
        this.recipeSPEntries.addAll(builder.getSPRecipeEntries());
        this.generatingRecipes = builder.generatingRecipes;
    }

    @Override
    public SPAALRecipeBuilder copy() {
        return new SPAALRecipeBuilder(this);
    }

    private boolean applyResearchProperty(ResearchPropertyData.ResearchEntry researchEntry) {
        if (!ConfigHolder.machines.enableResearch) return false;
        if (researchEntry == null) {
            NTLog.logger.error("研究线研究条目不能为空。", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
            return false;
        }

        if (!generatingRecipes) {
            NTLog.logger.error("使用researchWithoutRecipe（）时无法生成配方",
                    new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
            return false;
        }

        if (recipePropertyStorage != null && recipePropertyStorage.hasRecipeProperty(ResearchProperty.getInstance())) {
            ResearchPropertyData property = recipePropertyStorage.getRecipePropertyValue(ResearchProperty.getInstance(),
                    null);
            if (property == null) throw new IllegalStateException("该属性的值为空");
            property.add(researchEntry);
            return true;
        }

        ResearchPropertyData property = new ResearchPropertyData();
        if (applyProperty(ResearchProperty.getInstance(), property)) {
            property.add(researchEntry);
            return true;
        }

        return false;
    }

    /**
     * 为研究站生成研究配方。
     */
    //
    public SPAALRecipeBuilder stationSPResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> spresearch) {
        SPResearchRecipeEntry entry = spresearch.apply(new ResearchRecipeBuilder.StationRecipeBuilder()).spresearch();
        if (applyResearchProperty(new ResearchPropertyData.ResearchEntry(entry.researchId, entry.dataStack))) {
            this.recipeSPEntries.add(entry);
        }
        return this;
    }

    @NotNull
    public Collection<SPResearchRecipeEntry> getSPRecipeEntries() {
        return this.recipeSPEntries;
    }

    /**
     * 用于生成包含研究数据的数据项的自动生成研究配方的条目。
     */

    public static class SPResearchRecipeEntry {

        private final String researchId;
        private final ItemStack researchStack;
        private final ItemStack dataStack;
        private final boolean ignoreNBT;
        private final int duration;
        private final int EUt;
        private final int CWUt;
        private final int RWUt;
        private final int GORWUt;
        private final int OPRWUt;
        private final int SPRWUt;

        /**
         * @param researchId    要存储的研究的id
         * @param researchStack 要扫描以进行研究的堆栈
         * @param dataStack     包含数据的堆栈
         * @param duration      配方的持续时间
         * @param EUt           配方的EUt
         * @param RWUt          如果在研究站，这个配方每tick需要多少RWUt
         *                      <p>
         *                      默认情况下，将在Research chStack输入上忽略NBT。如果需要NBT匹配，请参阅
         *                      {@link #SPResearchRecipeEntry(String, ItemStack, ItemStack, boolean, int
         *                      , int, int, int, int, int, int)}
         */
        public SPResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt,
                                     int CWUt,int RWUt, int GORWUt, int OPRWUt, int SPRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
            this.CWUt = CWUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
            this.ignoreNBT = true;
        }

        /**
         * @param researchId    要存储的研究的id
         * @param researchStack 要扫描以进行研究的堆栈
         * @param dataStack     包含数据的堆栈
         * @param duration      配方的持续时间
         * @param EUt           配方的EUt
         * @param RWUt          如果在研究站，这个配方每tick需要多少RWUt
         */
        public SPResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, boolean ignoreNBT, int duration, int EUt,
                                     int CWUt,int RWUt, int GORWUt, int OPRWUt, int SPRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.ignoreNBT = ignoreNBT;
            this.duration = duration;
            this.EUt = EUt;
            this.CWUt = CWUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
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

        public int getDuration() {
            return duration;
        }

        public int getEUt() {
            return EUt;
        }
        public int getCWUt() {
            return CWUt;
        }

        public int getRWUt() {
            return RWUt;
        }
        public int getGORWUt() {
            return GORWUt;
        }
        public int getOPRWUt() {
            return OPRWUt;
        }
        public int getSPRWUt() {
            return SPRWUt;
        }
    }
}
