package com.onlyex.naxtech.loaders.recipe;

import static com.onlyex.naxtech.api.recipes.NTRecipeMaps.*;
import static com.onlyex.naxtech.common.items.NTMetaItems.*;
import static gregtech.api.GTValues.VA;
import static gregtech.common.items.MetaItems.*;

public class ResearchLineMachineRecipeLoader {
    public static void init() {
        RESEARCH_LINE_RECIPES.recipeBuilder()
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 60)
                .output(NULL)
                .stationResearch(b -> b
                        .researchStack(SUPRACAUSAL_MEMORY_CHIP.getStackForm(10))
                        .CWUt(600000)
                        .RWUt(60000)
                        .EUt(VA[8]))
                .duration(40000).EUt(VA[8]).buildAndRegister();

        GO_RESEARCH_LINE_RECIPES.recipeBuilder()
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 128)
                .input(ZEPTO_PIC_WAFER, 12)
                .output(NANO_PIC_WAFER)
                .stationGOResearch(b -> b
                        .input(NULL,1)
                        .input(ZEPTO_PIC_WAFER,2)
                        .input(ZEPTO_PIC_WAFER,1)
                        .researchStack(ZEPTO_PIC.getStackForm())
                        .CWUt(600000)
                        .RWUt(60000)
                        .GORWUt(6000)
                        .EUt(VA[12]))
                .duration(400000).EUt(VA[13]).buildAndRegister();

    }

}
