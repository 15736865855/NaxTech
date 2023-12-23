package com.onlyex.naxtech.loaders.recipe.blocks;


import static gregtech.api.GTValues.UV;
import static gregtech.api.GTValues.VA;

public class Coils {

    /**
     * @param tier Based on the original duration formula of CEu, with a rounding of 5
     * @return New coil recipe duration
     */
    private static int resultDuration(int tier) {
        return (45 + tier * 5) * 20;
    }

    /**
     * @param tier Based on the original EUt formula of CEu
     * @return New coil EUt (based on UV tier)
     */
    private static int resultEUt(int tier) {
        return VA[UV + tier];
    }

    public static void init() {

        //  TODO 新的线圈配方，使用云母绝缘体

        //  Adamantium Coil
        /*ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtDouble, Adamantium, 8)
                .input(foil, AwakenedDraconium, 8)
                .fluidInputs(Tritanium.getFluid(L))
                .outputs(NTMetaBlocks.EP_WIRE_COIL.getItemVariant(EPBlockWireCoil.CoilType.ADAMANTIUM))
                .EUt(resultEUt(1))
                .duration(resultDuration(1))
                .buildAndRegister();*/
    }
}
