package com.onlyex.naxtech.api.recipes.recipeproperties.data;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class SDIDataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "sdi_data_per_tick";

    private static SDIDataProperty INSTANCE;

    protected SDIDataProperty() {
        super(KEY, Integer.class);
    }

    public static SDIDataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SDIDataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.sdi_data_per_tick", castValue(value)), x, y,
                color);
    }
}
