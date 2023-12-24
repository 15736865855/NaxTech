package com.onlyex.naxtech.common.items.behaviors;

import com.onlyex.naxtech.api.items.metaitem.stats.IEPDataItem;
import com.onlyex.naxtech.api.utils.NTDataManager;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import java.util.List;

public class NTDataItemBehavior implements IItemBehaviour, IEPDataItem {
    @Override
    public void addInformation(ItemStack itemStack, List<String> lines) {
        IItemBehaviour.super.addInformation(itemStack, lines);
        if (NTDataManager.hasEPDataTag(itemStack)) {
            lines.add("§kUnknownInformation");
            lines.add(I18n.format("behavior.data_item.require_decoding"));
        }
//        String EPDataId = EPDataManager.readEPDataId(itemStack);
//        if (EPDataId == null) return;
//        Collection<Recipe> recipes = ((IResearchRecipeMap) RecipeMaps.ASSEMBLY_LINE_RECIPES).getDataStickEntry(EPDataId);
//        if (recipes != null && !recipes.isEmpty()) {
//            lines.add(I18n.format("behavior.data_item.assemblyline.title"));
//            Collection<ItemStack> added = new ObjectOpenHashSet<>();
//            for (Recipe recipes : recipes) {
//                ItemStack output = recipes.getOutputs().get(0);
//                if (added.add(output)) {
//                    lines.add(I18n.format("behavior.data_item.assemblyline.data", output.getDisplayName()));
//                }
//            }
//        }
    }
}
