package com.onlyex.naxtech.api.recipes.recipeproperties.total;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class TotalSPDataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "sp_total_data";

    private static TotalSPDataProperty INSTANCE;

    protected TotalSPDataProperty() {
        super(KEY, Integer.class);
    }

    public static TotalSPDataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TotalSPDataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.sp_total_data", castValue(value)), x, y,
                color);
    }

    @Override
    public boolean hideDuration() {
        return true;
    }
}
