package com.onlyex.naxtech.loaders.recipe;

import static com.onlyex.naxtech.api.recipes.NTRecipeMaps.RESEARCH_RECIPES;
import static com.onlyex.naxtech.common.items.NTMetaItems.GALACTIC_SINGULARITY;
import static com.onlyex.naxtech.common.items.NTMetaItems.NULL;
import static gregtech.api.GTValues.VA;
import static gregtech.common.items.MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT;

public class AALMachineRecipeLoader {
    public static void init() {

        RESEARCH_RECIPES.recipeBuilder()
                .input(GALACTIC_SINGULARITY,1)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 60)
                .output(NULL)
                .CWUt(600000)
                .RWUt(60000)
                .duration(40000)
                .EUt(VA[8])
                .buildAndRegister();


    }

}
