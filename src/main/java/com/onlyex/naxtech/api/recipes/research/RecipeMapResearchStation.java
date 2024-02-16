package com.onlyex.naxtech.api.recipes.research;

import com.onlyex.naxtech.api.recipes.recipeproperties.research.ResearchProperty;
import com.onlyex.naxtech.api.recipes.recipeproperties.research.ResearchPropertyData;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.core.sound.GTSoundEvents;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

@ApiStatus.Internal
public class RecipeMapResearchStation<R extends RecipeBuilder<R>> extends RecipeMap<R> implements IScannerRecipeMap {
    private final Map<String, Collection<Recipe>> researchEntries = new Object2ObjectOpenHashMap<>();

    public RecipeMapResearchStation(@NotNull String unlocalizedName,int maxInputs, int maxOutputs, int maxFluidInputs,
int maxFluidOutputs, @NotNull R defaultRecipeBuilder, boolean isHidden) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden);
        setSound(GTSoundEvents.ASSEMBLER);
        //setSound(GTValues.FOOLS.get() ? GTSoundEvents.SCIENCE : GTSoundEvents.COMPUTATION);
    }//TODO MUI

/*    public RecipeMapResearchStation(@NotNull String unlocalizedName, @NotNull R defaultRecipeBuilder,
@NotNull RecipeMapUIFunction recipeMapUI) {
        super(unlocalizedName, defaultRecipeBuilder, recipeMapUI, 2, 1, 0, 0);
        setSound(GTValues.FOOLS.get() ? GTSoundEvents.SCIENCE : GTSoundEvents.COMPUTATION);
    }*/

    @Override
    public boolean compileRecipe(Recipe recipe) {
        if (!super.compileRecipe(recipe)) return false;
        if (recipe.hasProperty(ResearchProperty.getInstance())) {
            ResearchPropertyData data = recipe.getProperty(ResearchProperty.getInstance(), null);
            if (data != null) {
                for (ResearchPropertyData.ResearchEntry entry : data) {
                    addDataStickEntry(entry.researchId(), recipe);
                }
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean removeRecipe(@NotNull Recipe recipe) {
        if (!super.removeRecipe(recipe)) return false;
        if (recipe.hasProperty(ResearchProperty.getInstance())) {
            ResearchPropertyData data = recipe.getProperty(ResearchProperty.getInstance(), null);
            if (data != null) {
                for (ResearchPropertyData.ResearchEntry entry : data) {
                    removeDataStickEntry(entry.researchId(), recipe);
                }
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public void addDataStickEntry(@NotNull String researchId, @NotNull Recipe recipe) {
        Collection<Recipe> collection = researchEntries.computeIfAbsent(researchId, (k) -> new ObjectOpenHashSet<>());
        collection.add(recipe);
    }

    @Nullable
    @Override
    public Collection<Recipe> getDataStickEntry(@NotNull String researchId) {
        return researchEntries.get(researchId);
    }

    @Override
    public boolean removeDataStickEntry(@NotNull String researchId, @NotNull Recipe recipe) {
        Collection<Recipe> collection = researchEntries.get(researchId);
        if (collection == null) return false;
        if (collection.remove(recipe)) {
            if (collection.isEmpty()) {
                return researchEntries.remove(researchId) != null;
            }
            return true;
        }
        return false;
    }
}
