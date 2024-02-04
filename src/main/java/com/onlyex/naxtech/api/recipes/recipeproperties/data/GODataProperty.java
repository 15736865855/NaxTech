package com.onlyex.naxtech.api.recipes.recipeproperties.data;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GODataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "go_data_per_tick";

    private static GODataProperty INSTANCE;

    protected GODataProperty() {
        super(KEY, Integer.class);
    }

    public static GODataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GODataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.go_data_per_tick", castValue(value)), x, y,
                color);
    }
}
