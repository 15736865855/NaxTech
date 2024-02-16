package com.onlyex.naxtech.loaders.recipe;

import static com.onlyex.naxtech.api.recipes.NTRecipeMaps.*;
import static com.onlyex.naxtech.common.items.NTMetaItems.*;
import static gregtech.api.GTValues.VA;
import static gregtech.common.items.MetaItems.*;

public class AALMachineRecipeLoader {
    public static void init() {

        AAL_RECIPES.recipeBuilder()
                .input(GALACTIC_SINGULARITY,1)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 60)
                .writeToNBT(NANO_PIC.getStackForm(), RESEARCH_DATA_CARD.getStackForm())
                .output(NULL)
                .duration(40000)
                .EUt(VA[9])
                .buildAndRegister();


        RESEARCH_RECIPES.recipeBuilder()
                .input(GALACTIC_SINGULARITY,1)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 60)
                .writeToNBT(NANO_PIC.getStackForm(), RESEARCH_DATA_CARD.getStackForm())
                .CWUt(96)
                .RWUt(36)
                .duration(40000)
                .EUt(VA[8])
                .buildAndRegister();


    }

}
