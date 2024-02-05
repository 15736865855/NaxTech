package com.onlyex.naxtech.api.recipes.recipeproperties.total;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class TotalDataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "total_data";

    private static TotalDataProperty INSTANCE;

    protected TotalDataProperty() {
        super(KEY, Integer.class);
    }

    public static TotalDataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TotalDataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.total_data", castValue(value)), x, y,
                color);
    }

    @Override
    public boolean hideDuration() {
        return true;
    }
}
