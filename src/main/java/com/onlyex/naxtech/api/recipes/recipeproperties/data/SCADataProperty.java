package com.onlyex.naxtech.api.recipes.recipeproperties.data;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class SCADataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "sca_data_per_tick";

    private static SCADataProperty INSTANCE;

    protected SCADataProperty() {
        super(KEY, Integer.class);
    }

    public static SCADataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SCADataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.sca_data_per_tick", castValue(value)), x, y,
                color);
    }
}
