package com.onlyex.naxtech.common.items.behaviors.research;

import com.onlyex.naxtech.api.recipes.NTRecipeMaps;
import com.onlyex.naxtech.api.recipes.research.IResearchRecipeMap;
import com.onlyex.naxtech.api.utils.ResearchLineManager;
import gregtech.api.items.metaitem.stats.IDataItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.recipes.Recipe;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class DataItemOPResearch implements IItemBehaviour, IDataItem {

    private final boolean requireDataBank;

    public DataItemOPResearch() {
        this.requireDataBank = true;
    }

    public DataItemOPResearch(boolean requireDataBank) {
        this.requireDataBank = requireDataBank;
    }

    @Override
    public boolean requireDataBank() {
        return requireDataBank;
    }

    @Override
    public void addInformation(@NotNull ItemStack itemStack, List<String> lines) {
        String opresearchId = ResearchLineManager.readOPResearchId(itemStack);
        if (opresearchId == null) return;
        Collection<Recipe> recipes = ((IResearchRecipeMap) NTRecipeMaps.OP_RESEARCH_LINE_RECIPES).getDataStickEntry(opresearchId);
        if (recipes != null && !recipes.isEmpty()) {
            lines.add(I18n.format("research.op_data_item.researchline.title"));
            Collection<ItemStack> added = new ObjectOpenHashSet<>();
            for (Recipe recipe : recipes) {
                ItemStack output = recipe.getOutputs().get(0);
                if (added.add(output)) {
                    lines.add(I18n.format("research.op_data_item.researchline.data", output.getDisplayName()));
                }
            }
        }
    }
}
