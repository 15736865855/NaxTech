package com.onlyex.naxtech.api.recipes.recipeproperties.data;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class SPDataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "sp_data_per_tick";

    private static SPDataProperty INSTANCE;

    protected SPDataProperty() {
        super(KEY, Integer.class);
    }

    public static SPDataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SPDataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.sp_data_per_tick", castValue(value)), x, y,
                color);
    }
}
