package com.onlyex.naxtech.api.recipes.recipeproperties.total;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class TotalCODataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "co_total_data";

    private static TotalCODataProperty INSTANCE;

    protected TotalCODataProperty() {
        super(KEY, Integer.class);
    }

    public static TotalCODataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TotalCODataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.co_total_data", castValue(value)), x, y,
                color);
    }

    @Override
    public boolean hideDuration() {
        return true;
    }
}
