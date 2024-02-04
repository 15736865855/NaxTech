package com.onlyex.naxtech.api.recipes.recipeproperties.data;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class OPDataProperty extends RecipeProperty<Integer> {

    public static final String KEY = "op_data_per_tick";

    private static OPDataProperty INSTANCE;

    protected OPDataProperty() {
        super(KEY, Integer.class);
    }

    public static OPDataProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OPDataProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("naxtech.recipe.op_data_per_tick", castValue(value)), x, y,
                color);
    }
}
