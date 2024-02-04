package com.onlyex.naxtech.api.recipes.recipeproperties.data;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class SCHDataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "sch_data_per_tick";

    private static SCHDataProperty INSTANCE;

    protected SCHDataProperty() {
        super(KEY, Integer.class);
    }

    public static SCHDataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SCHDataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.sch_data_per_tick", castValue(value)), x, y,
                color);
    }
}
