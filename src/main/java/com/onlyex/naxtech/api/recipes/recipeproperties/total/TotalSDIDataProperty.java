package com.onlyex.naxtech.api.recipes.recipeproperties.total;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class TotalSDIDataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "sdi_total_data";

    private static TotalSDIDataProperty INSTANCE;

    protected TotalSDIDataProperty() {
        super(KEY, Integer.class);
    }

    public static TotalSDIDataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TotalSDIDataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.sdi_total_data", castValue(value)), x, y,
                color);
    }

    @Override
    public boolean hideDuration() {
        return true;
    }
}
