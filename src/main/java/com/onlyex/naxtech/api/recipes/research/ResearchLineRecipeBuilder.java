package com.onlyex.naxtech.api.recipes.research;

import com.onlyex.naxtech.api.utils.NTLog;
import com.onlyex.naxtech.api.utils.ResearchLineManager;
import com.onlyex.naxtech.common.ConfigHolder;
import gregtech.api.items.metaitem.stats.IDataItem;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.ResearchProperty;
import gregtech.api.recipes.recipeproperties.ResearchPropertyData;
import gregtech.api.util.EnumValidationResult;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.UnaryOperator;

public class ResearchLineRecipeBuilder extends RecipeBuilder<ResearchLineRecipeBuilder> {

    private final Collection<ResearchRecipeEntry> recipeEntries = new ArrayList<>();

    private boolean generatingRecipes = true;

    public ResearchLineRecipeBuilder() {}

    @SuppressWarnings("unused")
    public ResearchLineRecipeBuilder(Recipe recipe, RecipeMap<ResearchLineRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public ResearchLineRecipeBuilder(@NotNull ResearchLineRecipeBuilder builder) {
        super(builder);
        this.recipeEntries.addAll(builder.getRecipeEntries());
        this.generatingRecipes = builder.generatingRecipes;
    }

    @Override
    public ResearchLineRecipeBuilder copy() {
        return new ResearchLineRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(@NotNull String key, @Nullable Object value) {
        if (key.equals(ResearchProperty.KEY)) {
            if (value instanceof ItemStack itemStack) {
                scannerResearch(itemStack);
                return true;
            }
        }
        return super.applyProperty(key, value);
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
     *不生成研究配方。
     *参数研究配方的研究ID
     *退回这个
     */
    public ResearchLineRecipeBuilder researchWithoutRecipe(@NotNull String researchId) {
        return researchWithoutRecipe(researchId, ResearchLineManager.getDefaultScannerItem());
    }

    /**
     *不生成研究配方。
     *参数研究配方的研究ID
     *参数dataStack堆栈以保存数据。必须具有{@link IDataItem}行为。
     *退回这个
     */
    public ResearchLineRecipeBuilder researchWithoutRecipe(@NotNull String researchId, @NotNull ItemStack dataStack) {
        applyResearchProperty(new ResearchPropertyData.ResearchEntry(researchId, dataStack));
        this.generatingRecipes = false;
        return this;
    }

    /**
     * Generates a research recipe for the Scanner.
     */
    public ResearchLineRecipeBuilder scannerResearch(UnaryOperator<ResearchRecipeBuilder.ScannerRecipeBuilder> research) {
        ResearchRecipeEntry entry = research.apply(new ResearchRecipeBuilder.ScannerRecipeBuilder()).build();
        if (applyResearchProperty(new ResearchPropertyData.ResearchEntry(entry.researchId, entry.dataStack))) {
            this.recipeEntries.add(entry);
        }
        return this;
    }

    /**
     * Generates a research recipe for the Scanner. All values are defaults other than the research stack.
     *
     * @param researchStack the stack to use for research
     * @return this
     */
    public ResearchLineRecipeBuilder scannerResearch(@NotNull ItemStack researchStack) {
        return scannerResearch(b -> new ResearchRecipeBuilder.ScannerRecipeBuilder().researchStack(researchStack));
    }

    /**
     * Generates a research recipe for the Research Station.
     */
    public ResearchLineRecipeBuilder stationResearch(UnaryOperator<ResearchRecipeBuilder.StationRecipeBuilder> research) {
        ResearchRecipeEntry entry = research.apply(new ResearchRecipeBuilder.StationRecipeBuilder()).build();
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
     * An entry for an autogenerated research recipe for producing a data item containing research data.
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
         * @param researchId    the id of the research to store
         * @param researchStack the stack to scan for research
         * @param dataStack     the stack to contain the data
         * @param duration      the duration of the recipe
         * @param EUt           the EUt of the recipe
         * @param RWUt          how much computation per tick this recipe needs if in Research Station
         *                      <p>
         *                      By default, will ignore NBT on researchStack input. If NBT matching is desired, see
         *                      {@link #ResearchRecipeEntry(String, ItemStack, ItemStack, boolean, int, int, int)}
         */
        public ResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, int duration, int EUt, int RWUt) {
            this.researchId = researchId;
            this.researchStack = researchStack;
            this.dataStack = dataStack;
            this.duration = duration;
            this.EUt = EUt;
            this.RWUt = RWUt;
            this.ignoreNBT = true;
        }

        /**
         * @param researchId    the id of the research to store
         * @param researchStack the stack to scan for research
         * @param dataStack     the stack to contain the data
         * @param duration      the duration of the recipe
         * @param EUt           the EUt of the recipe
         * @param RWUt          how much computation per tick this recipe needs if in Research Station
         */
        public ResearchRecipeEntry(@NotNull String researchId, @NotNull ItemStack researchStack,
                                   @NotNull ItemStack dataStack, boolean ignoreNBT, int duration, int EUt, int RWUt) {
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
}
