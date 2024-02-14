package com.onlyex.naxtech.loaders.recipe;

import static com.onlyex.naxtech.api.recipes.NTRecipeMaps.*;
import static com.onlyex.naxtech.common.items.NTMetaItems.*;
import static gregtech.api.GTValues.VA;
import static gregtech.common.items.MetaItems.*;

public class AALMachineRecipeLoader {
    public static void init() {

        AAL_RECIPES.recipeBuilder()
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 60)
                .output(NULL)
                .stationResearch(b -> b
                        .researchStack(SUPRACAUSAL_MEMORY_CHIP.getStackForm())
                        .CWUt(600000)
                        .RWUt(60000)
                        .EUt(VA[8]))
                .duration(40000).EUt(VA[8]).buildAndRegister();


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
