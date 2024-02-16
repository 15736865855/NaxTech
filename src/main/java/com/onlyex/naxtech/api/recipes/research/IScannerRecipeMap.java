package com.onlyex.naxtech.api.recipes.research;

import gregtech.api.recipes.Recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface IScannerRecipeMap {

    /**
     * @return 所有代表性配方的列表，由已注册的{@link ICustomScannerLogic}实现详细说明。
     */
    @NotNull
    default List<Recipe> getRepresentativeRecipes() {
        return Collections.emptyList();
    }

    interface ICustomScannerLogic {

        /**
         * @return 给定当前扫描仪的输入运行的自定义配方。仅当注册时才会调用
         *未找到要运行的配方。如果您的逻辑不应该运行配方，则返回null。
         */
        @Nullable
        Recipe createCustomRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs,
                                  boolean exactVoltage);

        /**
         * @return 从未注册但添加到JEI以演示自定义逻辑的食谱列表。
         *不需要，可以返回空或null不添加任何。
         */
        @Nullable
        default List<Recipe> getRepresentativeRecipes() {
            return null;
        }
    }

    /**
     * addDataStickEntry(String researchId, Recipe recipe)
     * 方法用于将配方 recipe 添加到与研究ID researchId 相关联的数据存储库注册表中。
     * 这个方法用于将配方与特定的研究ID关联起来。
     */
    void addDataStickEntry(@NotNull String researchId, @NotNull Recipe recipe);

    /**
     * getDataStickEntry(String researchId)
     * 方法用于获取与研究ID researchId 相关联的配方集合。
     * 如果存在与该研究ID关联的配方集合，则返回该集合；否则返回空。
     */
    @Nullable
    Collection<Recipe> getDataStickEntry(@NotNull String researchId);

    /**
     * removeDataStickEntry(String researchId, Recipe recipe)
     * 方法用于从与研究ID researchId 相关联的数据存储库注册表中删除配方 recipe。
     * 如果删除成功，则返回 true；否则返回 false。
     * 这个方法用于删除特定研究ID相关的配方。
     */
    boolean removeDataStickEntry(@NotNull String researchId, @NotNull Recipe recipe);
}
