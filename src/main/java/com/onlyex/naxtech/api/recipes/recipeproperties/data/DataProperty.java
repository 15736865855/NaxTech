package com.onlyex.naxtech.api.recipes.recipeproperties.data;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class DataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "data_per_tick";

    private static DataProperty INSTANCE;

    protected DataProperty() {
        super(KEY, Integer.class);
    }

    public static DataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.data_per_tick", castValue(value)), x, y,
                color);
    }
}
