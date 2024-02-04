package com.onlyex.naxtech.api.recipes.recipeproperties.research;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class ResearchPropertyData implements Iterable<ResearchPropertyData.ResearchEntry> {

    private final Collection<ResearchEntry> entries = new ArrayList<>();

    public ResearchPropertyData() {}

    /**
     * @param entry 要添加的条目
     */
    public void add(@NotNull ResearchEntry entry) {
        this.entries.add(entry);
    }

    @NotNull
    @Override
    public Iterator<ResearchEntry> iterator() {
        return this.entries.iterator();
    }

    /**
         * 包含可研究配方信息的条目。
         * <p>
         * 用于内部研究存储和JEI集成。
         */
    public static final class ResearchEntry {

        private final String researchId;
        private final ItemStack dataItem;

            /**
             * @param researchId 研究的ID
             * @param dataItem   允许包含研究的项目
             */
            public ResearchEntry(@NotNull String researchId, @NotNull ItemStack dataItem) {
                this.researchId = researchId;
                this.dataItem = dataItem;
            }

            @NotNull
            public String researchId() {
                return researchId;
            }

            @NotNull
            public ItemStack dataItem() {
                return dataItem;
            }
        }
}
