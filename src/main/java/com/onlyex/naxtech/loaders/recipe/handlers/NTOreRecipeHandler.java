package com.onlyex.naxtech.loaders.recipe.handlers;

import com.onlyex.naxtech.api.unification.ore.NTOrePrefix;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;

public class NTOreRecipeHandler {
    private NTOreRecipeHandler() {/**/}

    public static void register() {
        NTOrePrefix.milled.addProcessingHandler(PropertyKey.ORE, NTOreRecipeHandler::processMilled);
    }

    public static void processMilled(OrePrefix milledPrefix, Material material, OreProperty property) {

        /*NTRecipeMaps.ISA_MILL_GRINDER.recipeBuilder().EUt(GTValues.VA[ZPM]).duration(1500)
                .input(OrePrefix.crushed, material, 16)
                .output(milledPrefix, material, 16)
                .circuitMeta(11)
                .grindBallTier(1)
                .buildAndRegister();
        NTRecipeMaps.ISA_MILL_GRINDER.recipeBuilder().EUt(GTValues.VA[ZPM]).duration(1200)
                .input(OrePrefix.crushed, material, 16)
                .output(milledPrefix, material, 32)
                .circuitMeta(10)
                .grindBallTier(2)
                .buildAndRegister();*/
    }
}
