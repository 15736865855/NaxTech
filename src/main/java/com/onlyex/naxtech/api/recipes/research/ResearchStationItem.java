package com.onlyex.naxtech.api.recipes.research;

import com.onlyex.naxtech.common.items.NTMetaItems;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IDataItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ResearchStationItem {
    @NotNull
    public static ItemStack getDefaultResearchStationItem(
            int gorwut,int oprwut,int sprwut,int corwut,int scarwut,int schrwut,int sdirwut) {
        if (gorwut > 32) {
            return NTMetaItems.GOOWARE_RESEARCH_DATA_CARD.getStackForm();
        }
        if (oprwut > 32) {
            return NTMetaItems.OPTICAL_RESEARCH_DATA_CARD.getStackForm();
        }
        if (sprwut > 32) {
            return NTMetaItems.SPINTRONIC_RESEARCH_DATA_CARD.getStackForm();
        }
        if (corwut > 32) {
            return NTMetaItems.COSMIC_RESEARCH_DATA_CARD.getStackForm();
        }
        if (scarwut > 32) {
            return NTMetaItems.SUPRA_CAUSAL_RESEARCH_DATA_CARD.getStackForm();
        }
        if (schrwut > 32) {
            return NTMetaItems.SUPRA_CHRONAL_RESEARCH_DATA_CARD.getStackForm();
        }
        if (sdirwut > 32) {
            return NTMetaItems.SUPRA_DIMENSION_RESEARCH_DATA_CARD.getStackForm();
        }

        return NTMetaItems.RESEARCH_DATA_CARD.getStackForm();
    }

    /**
     * 首先判断stack的getItem是否是一个MetaItem（元数据物品），如果不是则直接返回false，表示不是数据项。
     * 如果是MetaItem，则将其强制转换为MetaItem<?>类型的变量metaItem。
     * 利用metaItem的getItem方法，从stack中获取一个MetaItem<?>.MetaValueItem对象valueItem。
     * 如果valueItem为null，表示无法获取到MetaItem的值，直接返回false，表示不是数据项。
     * 接下来，遍历valueItem的所有行为（behaviours），对于每个行为，判断是否是一个IDataItem（数据项）。
     * 如果是IDataItem，判断该数据项是否需要数据存储器（requireDataBank）。
     * 如果不需要数据存储器，或者isDataBank为true，则返回true，表示是一个数据项。
     * 如果未找到匹配的IDataItem行为，或者需要数据存储器但isDataBank为false，则返回false，表示不是数据项。
     */
    public static boolean isStackDataItem(@NotNull ItemStack stack, boolean isDataBank) {
        if (stack.getItem() instanceof MetaItem<?> metaItem) {
            MetaItem<?>.MetaValueItem valueItem = metaItem.getItem(stack);
            if (valueItem == null) return true;
            for (IItemBehaviour behaviour : valueItem.getBehaviours()) {
                if (behaviour instanceof IDataItem dataItem) {
                    return dataItem.requireDataBank() && !isDataBank;
                }
            }
        }
        return true;
    }


}
