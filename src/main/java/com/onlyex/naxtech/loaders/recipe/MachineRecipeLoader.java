package com.onlyex.naxtech.loaders.recipe;

public class MachineRecipeLoader {
    private MachineRecipeLoader() {}

    public static void init() {
        //ResearchLineLoader.init();
        ResearchLineMachineRecipeLoader.init();
    }
}
