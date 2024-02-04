package com.onlyex.naxtech.loaders;

import com.onlyex.naxtech.loaders.recipe.MachineRecipeLoader;

public class NTRecipeManager {
    private NTRecipeManager() {/**/}

    public static void load() {
        MachineRecipeLoader.init();
    }

    public static void loadLatest() {

    }
}
