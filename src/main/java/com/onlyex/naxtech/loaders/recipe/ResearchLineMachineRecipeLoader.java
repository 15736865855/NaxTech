package com.onlyex.naxtech.loaders.recipe;

import static com.onlyex.naxtech.api.recipes.NTRecipeMaps.*;
import static com.onlyex.naxtech.common.items.NTMetaItems.*;
import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class ResearchLineMachineRecipeLoader {
    public static void init() {
        RESEARCH_LINE_RECIPES.recipeBuilder()
                .input(spring, NiobiumTitanium, 4)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .fluidInputs(SolderingAlloy.getFluid(720))
                .output(SUPRACAUSAL_MEMORY_CHIP)
                .stationGOResearch(b -> b
                        .researchStack(SUPRACAUSAL_MEMORY_CHIP.getStackForm())
                        .RWUt(6000)
                        .GORWUt(600)
                        .EUt(VA[8]))
                .duration(400).EUt(VA[8]).buildAndRegister();
    }

}
