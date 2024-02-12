package com.onlyex.naxtech.api.recipes.research;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.machines.IScannerRecipeMap;
import gregtech.core.sound.GTSoundEvents;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public class RecipeMapResearchStation<R extends RecipeBuilder<R>> extends RecipeMap<R> implements IScannerRecipeMap {
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
}
