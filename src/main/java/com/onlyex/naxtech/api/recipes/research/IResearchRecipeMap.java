package com.onlyex.naxtech.api.recipes.research;

import gregtech.api.recipes.Recipe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface IResearchRecipeMap {

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
