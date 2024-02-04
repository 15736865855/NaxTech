package com.onlyex.naxtech.api.recipes.research;

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

public class ResearchLineRecipeBuilder extends RecipeBuilder<ResearchLineRecipeBuilder> {

    private final Collection<ResearchRecipeEntry> recipeEntries = new ArrayList<>();
    private final Collection<GOResearchRecipeEntry> recipeGOEntries = new ArrayList<>();
    private final Collection<OPResearchRecipeEntry> recipeOPEntries = new ArrayList<>();
    private final Collection<SPResearchRecipeEntry> recipeSPEntries = new ArrayList<>();
    private final Collection<COResearchRecipeEntry> recipeCOEntries = new ArrayList<>();
    private final Collection<SCAResearchRecipeEntry> recipeSCAEntries = new ArrayList<>();
    private final Collection<SCHResearchRecipeEntry> recipeSCHEntries = new ArrayList<>();
    private final Collection<SDIResearchRecipeEntry> recipeSDIEntries = new ArrayList<>();
    private boolean generatingRecipes = true;

    public ResearchLineRecipeBuilder() {}

    @SuppressWarnings("unused")
    public ResearchLineRecipeBuilder(Recipe recipe, RecipeMap<ResearchLineRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public ResearchLineRecipeBuilder(@NotNull ResearchLineRecipeBuilder builder) {
        super(builder);
        this.recipeEntries.addAll(builder.getRecipeEntries());
        this.recipeGOEntries.addAll(builder.getGORecipeEntries());
        this.recipeOPEntries.addAll(builder.getOPRecipeEntries());
        this.recipeSPEntries.addAll(builder.getSPRecipeEntries());
        this.recipeCOEntries.addAll(builder.getCORecipeEntries());
        this.recipeSCAEntries.addAll(builder.getSCARecipeEntries());
        this.recipeSCHEntries.addAll(builder.getSCHRecipeEntries());
        this.recipeSDIEntries.addAll(builder.getSDIRecipeEntries());
        this.generatingRecipes = builder.generatingRecipes;
    }

    @Override
    public ResearchLineRecipeBuilder copy() {
        return new ResearchLineRecipeBuilder(this);
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
    public ResearchLineRecipeBuilder stationResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> research) {
        ResearchRecipeEntry entry = research.apply(new ResearchRecipeBuilder.StationRecipeBuilder()).research();
        if (applyResearchProperty(new ResearchPropertyData.ResearchEntry(entry.researchId, entry.dataStack))) {
            this.recipeEntries.add(entry);
        }
        return this;
    }

    @NotNull
    public Collection<ResearchRecipeEntry> getRecipeEntries() {
        return this.recipeEntries;
    }


    /**
     * 用于生成包含研究数据的数据项的自动生成研究配方的条目。
     */

    public static class ResearchRecipeEntry {

        private final String researchId;
        private final ItemStack researchStack;
        private final ItemStack dataStack;
        private final boolean ignoreNBT;
        private final int duration;
        private final int EUt;
        private final int RWUt;

        /**
         * @param researchId    要存储的研究的id
         * @param researchStack 要扫描以进行研究的堆栈
         * @param dataStack     包含数据的堆栈
         * @param duration      配方的持续时间
         * @param EUt           配方的EUt
         * @param RWUt          如果在研究站，这个配方每tick需要多少RWUt
         *                      <p>
         *                      默认情况下，将在Research chStack输入上忽略NBT。如果需要NBT匹配，请参阅
         *                      {@link #ResearchRecipeEntry(String, ItemStack, ItemStack, boolean, int, int, int)}
         */
        public ResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt,
                                   int RWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
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
        public ResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, boolean ignoreNBT, int duration, int EUt,
                                   int RWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.ignoreNBT = ignoreNBT;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
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

        public int getRWUt() {
            return RWUt;
        }
    }

    //
    public ResearchLineRecipeBuilder stationGOResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> goresearch) {
        GOResearchRecipeEntry entry = goresearch.apply(new ResearchRecipeBuilder.StationRecipeBuilder()).goresearch();
        if (applyResearchProperty(new ResearchPropertyData.ResearchEntry(entry.researchId, entry.dataStack))) {
            this.recipeGOEntries.add(entry);
        }
        return this;
    }

    @NotNull
    public Collection<GOResearchRecipeEntry> getGORecipeEntries() {
        return this.recipeGOEntries;
    }

    /**
     * 用于生成包含研究数据的数据项的自动生成研究配方的条目。
     */

    public static class GOResearchRecipeEntry {

        private final String researchId;
        private final ItemStack researchStack;
        private final ItemStack dataStack;
        private final boolean ignoreNBT;
        private final int duration;
        private final int EUt;
        private final int RWUt;
        private final int GORWUt;

        /**
         * @param researchId    要存储的研究的id
         * @param researchStack 要扫描以进行研究的堆栈
         * @param dataStack     包含数据的堆栈
         * @param duration      配方的持续时间
         * @param EUt           配方的EUt
         * @param RWUt          如果在研究站，这个配方每tick需要多少RWUt
         *                      <p>
         *                      默认情况下，将在Research chStack输入上忽略NBT。如果需要NBT匹配，请参阅
         *                      {@link #GOResearchRecipeEntry(String, ItemStack, ItemStack, boolean, int, int, int, int)}
         */
        public GOResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt,
                                   int RWUt, int GORWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
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
        public GOResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, boolean ignoreNBT, int duration, int EUt,
                                   int RWUt, int GORWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.ignoreNBT = ignoreNBT;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;

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

        public int getRWUt() {
            return RWUt;
        }
        public int getGORWUt() {
            return GORWUt;
        }
    }

    //
    public ResearchLineRecipeBuilder stationOPResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> opresearch) {
        OPResearchRecipeEntry entry = opresearch.apply(new ResearchRecipeBuilder.StationRecipeBuilder()).opresearch();
        if (applyResearchProperty(new ResearchPropertyData.ResearchEntry(entry.researchId, entry.dataStack))) {
            this.recipeOPEntries.add(entry);
        }
        return this;
    }

    @NotNull
    public Collection<OPResearchRecipeEntry> getOPRecipeEntries() {
        return this.recipeOPEntries;
    }

    /**
     * 用于生成包含研究数据的数据项的自动生成研究配方的条目。
     */

    public static class OPResearchRecipeEntry {

        private final String researchId;
        private final ItemStack researchStack;
        private final ItemStack dataStack;
        private final boolean ignoreNBT;
        private final int duration;
        private final int EUt;
        private final int RWUt;
        private final int GORWUt;
        private final int OPRWUt;

        /**
         * @param researchId    要存储的研究的id
         * @param researchStack 要扫描以进行研究的堆栈
         * @param dataStack     包含数据的堆栈
         * @param duration      配方的持续时间
         * @param EUt           配方的EUt
         * @param RWUt          如果在研究站，这个配方每tick需要多少RWUt
         *                      <p>
         *                      默认情况下，将在Research chStack输入上忽略NBT。如果需要NBT匹配，请参阅
         *                      {@link #OPResearchRecipeEntry(String, ItemStack, ItemStack, boolean, int, int, int, int, int)}
         */
        public OPResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
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
        public OPResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, boolean ignoreNBT, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.ignoreNBT = ignoreNBT;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
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

        public int getRWUt() {
            return RWUt;
        }
        public int getGORWUt() {
            return GORWUt;
        }
        public int getOPRWUt() {
            return OPRWUt;
        }
    }

    //
    public ResearchLineRecipeBuilder stationSPResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> spresearch) {
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
         *                      , int, int, int, int, int)}
         */
        public SPResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
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
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.ignoreNBT = ignoreNBT;
            this.duration = duration;
            this.EUt = EUt;
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

    //
    public ResearchLineRecipeBuilder stationCOResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> coresearch) {
        COResearchRecipeEntry entry = coresearch.apply(new ResearchRecipeBuilder.StationRecipeBuilder()).coresearch();
        if (applyResearchProperty(new ResearchPropertyData.ResearchEntry(entry.researchId, entry.dataStack))) {
            this.recipeCOEntries.add(entry);
        }
        return this;
    }

    @NotNull
    public Collection<COResearchRecipeEntry> getCORecipeEntries() {
        return this.recipeCOEntries;
    }

    /**
     * 用于生成包含研究数据的数据项的自动生成研究配方的条目。
     */

    public static class COResearchRecipeEntry {

        private final String researchId;
        private final ItemStack researchStack;
        private final ItemStack dataStack;
        private final boolean ignoreNBT;
        private final int duration;
        private final int EUt;
        private final int RWUt;
        private final int GORWUt;
        private final int OPRWUt;
        private final int SPRWUt;
        private final int CORWUt;

        /**
         * @param researchId    要存储的研究的id
         * @param researchStack 要扫描以进行研究的堆栈
         * @param dataStack     包含数据的堆栈
         * @param duration      配方的持续时间
         * @param EUt           配方的EUt
         * @param RWUt          如果在研究站，这个配方每tick需要多少RWUt
         *                      <p>
         *                      默认情况下，将在Research chStack输入上忽略NBT。如果需要NBT匹配，请参阅
         *                      {@link #COResearchRecipeEntry(String, ItemStack, ItemStack, boolean, int
         *                      , int, int, int, int, int, int)}
         */
        public COResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt, int CORWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
            this.CORWUt = CORWUt;
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
        public COResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, boolean ignoreNBT, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt, int CORWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.ignoreNBT = ignoreNBT;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
            this.CORWUt = CORWUt;
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
        public int getCORWUt() {
            return CORWUt;
        }
    }

    //
    public ResearchLineRecipeBuilder stationSCAResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> scaresearch) {
        SCAResearchRecipeEntry entry = scaresearch.apply(new ResearchRecipeBuilder.StationRecipeBuilder()).scaresearch();
        if (applyResearchProperty(new ResearchPropertyData.ResearchEntry(entry.researchId, entry.dataStack))) {
            this.recipeSCAEntries.add(entry);
        }
        return this;
    }

    @NotNull
    public Collection<SCAResearchRecipeEntry> getSCARecipeEntries() {
        return this.recipeSCAEntries;
    }

    /**
     * 用于生成包含研究数据的数据项的自动生成研究配方的条目。
     */

    public static class SCAResearchRecipeEntry {

        private final String researchId;
        private final ItemStack researchStack;
        private final ItemStack dataStack;
        private final boolean ignoreNBT;
        private final int duration;
        private final int EUt;
        private final int RWUt;
        private final int GORWUt;
        private final int OPRWUt;
        private final int SPRWUt;
        private final int CORWUt;
        private final int SCARWUt;


        /**
         * @param researchId    要存储的研究的id
         * @param researchStack 要扫描以进行研究的堆栈
         * @param dataStack     包含数据的堆栈
         * @param duration      配方的持续时间
         * @param EUt           配方的EUt
         * @param RWUt          如果在研究站，这个配方每tick需要多少RWUt
         *                      <p>
         *                      默认情况下，将在Research chStack输入上忽略NBT。如果需要NBT匹配，请参阅
         *                      {@link #SCAResearchRecipeEntry(String, ItemStack, ItemStack, boolean, int, int, int, int, int, int, int, int)}
         */
        public SCAResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt, int CORWUt, int SCARWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
            this.CORWUt = CORWUt;
            this.SCARWUt = SCARWUt;
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
        public SCAResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, boolean ignoreNBT, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt, int CORWUt, int SCARWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.ignoreNBT = ignoreNBT;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
            this.CORWUt = CORWUt;
            this.SCARWUt = SCARWUt;
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
        public int getCORWUt() {
            return CORWUt;
        }
        public int getSCARWUt() {
            return SCARWUt;
        }
    }

    //
    public ResearchLineRecipeBuilder stationSCHResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> schresearch) {
        SCHResearchRecipeEntry entry = schresearch.apply(new ResearchRecipeBuilder.StationRecipeBuilder()).schresearch();
        if (applyResearchProperty(new ResearchPropertyData.ResearchEntry(entry.researchId, entry.dataStack))) {
            this.recipeSCHEntries.add(entry);
        }
        return this;
    }

    @NotNull
    public Collection<SCHResearchRecipeEntry> getSCHRecipeEntries() {
        return this.recipeSCHEntries;
    }

    /**
     * 用于生成包含研究数据的数据项的自动生成研究配方的条目。
     */

    public static class SCHResearchRecipeEntry {

        private final String researchId;
        private final ItemStack researchStack;
        private final ItemStack dataStack;
        private final boolean ignoreNBT;
        private final int duration;
        private final int EUt;
        private final int RWUt;
        private final int GORWUt;
        private final int OPRWUt;
        private final int SPRWUt;
        private final int CORWUt;
        private final int SCARWUt;
        private final int SCHRWUt;

        /**
         * @param researchId    要存储的研究的id
         * @param researchStack 要扫描以进行研究的堆栈
         * @param dataStack     包含数据的堆栈
         * @param duration      配方的持续时间
         * @param EUt           配方的EUt
         * @param RWUt          如果在研究站，这个配方每tick需要多少RWUt
         *                      <p>
         *                      默认情况下，将在Research chStack输入上忽略NBT。如果需要NBT匹配，请参阅
         *                      {@link #SCHResearchRecipeEntry(String, ItemStack, ItemStack, boolean, int
         *                      , int, int, int, int, int, int, int, int)}
         */
        public SCHResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt, int CORWUt, int SCARWUt, int SCHRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
            this.CORWUt = CORWUt;
            this.SCARWUt = SCARWUt;
            this.SCHRWUt = SCHRWUt;
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
        public SCHResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, boolean ignoreNBT, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt, int CORWUt, int SCARWUt, int SCHRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.ignoreNBT = ignoreNBT;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
            this.CORWUt = CORWUt;
            this.SCARWUt = SCARWUt;
            this.SCHRWUt = SCHRWUt;
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
        public int getCORWUt() {
            return CORWUt;
        }
        public int getSCARWUt() {
            return SCARWUt;
        }
        public int getSCHRWUt() {
            return SCHRWUt;
        }
    }

    //
    public ResearchLineRecipeBuilder stationSDIResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> sdiresearch) {
        SDIResearchRecipeEntry entry = sdiresearch.apply(new ResearchRecipeBuilder.StationRecipeBuilder()).sdiresearch();
        if (applyResearchProperty(new ResearchPropertyData.ResearchEntry(entry.researchId, entry.dataStack))) {
            this.recipeSDIEntries.add(entry);
        }
        return this;
    }

    @NotNull
    public Collection<SDIResearchRecipeEntry> getSDIRecipeEntries() {
        return this.recipeSDIEntries;
    }

    /**
     * 用于生成包含研究数据的数据项的自动生成研究配方的条目。
     */

    public static class SDIResearchRecipeEntry {

        private final String researchId;
        private final ItemStack researchStack;
        private final ItemStack dataStack;
        private final boolean ignoreNBT;
        private final int duration;
        private final int EUt;
        private final int RWUt;
        private final int GORWUt;
        private final int OPRWUt;
        private final int SPRWUt;
        private final int CORWUt;
        private final int SCARWUt;
        private final int SCHRWUt;
        private final int SDIRWUt;

        /**
         * @param researchId    要存储的研究的id
         * @param researchStack 要扫描以进行研究的堆栈
         * @param dataStack     包含数据的堆栈
         * @param duration      配方的持续时间
         * @param EUt           配方的EUt
         * @param RWUt          如果在研究站，这个配方每tick需要多少RWUt
         *                      <p>
         *                      默认情况下，将在Research chStack输入上忽略NBT。如果需要NBT匹配，请参阅
         *                      {@link #SDIResearchRecipeEntry(String, ItemStack, ItemStack, boolean, int
         *                      , int, int, int, int, int, int, int, int, int)}
         */
        public SDIResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt, int CORWUt, int SCARWUt, int SCHRWUt, int SDIRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
            this.CORWUt = CORWUt;
            this.SCARWUt = SCARWUt;
            this.SCHRWUt = SCHRWUt;
            this.SDIRWUt = SDIRWUt;
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
        public SDIResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, boolean ignoreNBT, int duration, int EUt,
                                   int RWUt, int GORWUt, int OPRWUt, int SPRWUt, int CORWUt, int SCARWUt, int SCHRWUt, int SDIRWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.ignoreNBT = ignoreNBT;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.GORWUt = GORWUt;
            this.OPRWUt = OPRWUt;
            this.SPRWUt = SPRWUt;
            this.CORWUt = CORWUt;
            this.SCARWUt = SCARWUt;
            this.SCHRWUt = SCHRWUt;
            this.SDIRWUt = SDIRWUt;
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
        public int getCORWUt() {
            return CORWUt;
        }
        public int getSCARWUt() {
            return SCARWUt;
        }
        public int getSCHRWUt() {
            return SCHRWUt;
        }
        public int getSDIRWUt() {
            return SDIRWUt;
        }
    }

}
