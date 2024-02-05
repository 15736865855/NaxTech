package com.onlyex.naxtech.loaders.recipe;

import static com.onlyex.naxtech.api.recipes.NTRecipeMaps.*;
import static com.onlyex.naxtech.common.items.NTMetaItems.*;
import static gregtech.api.GTValues.VA;
import static gregtech.common.items.MetaItems.*;

public class ResearchLineMachineRecipeLoader {
    public static void init() {
        RESEARCH_LINE_RECIPES.recipeBuilder()
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .output(NULL)
                .stationResearch(b -> b
                        .researchStack(SUPRACAUSAL_MEMORY_CHIP.getStackForm())
                        .CWUt(600000)
                        .RWUt(60000)
                        //.GORWUt(6000)
                        .EUt(VA[8]))
                .duration(400).EUt(VA[8]).buildAndRegister();
    }

}
