package com.onlyex.naxtech.api.capability;

import gregtech.api.recipes.Recipe;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public interface IDataAccessHatch {

    /**
     *这个方法用于检查给定的配方是否可用。
     *它默认使用 isRecipeAvailable(Recipe recipe, Collection<IDataAccessHatch> seen) 方法，传入一个空的 “seen” 列表来避免无限递归。
     *返回一个布尔值，表示该配方是否可用。
     */
    default boolean isRecipeAvailable(@NotNull Recipe recipe) {
        Collection<IDataAccessHatch> list = new ArrayList<>();
        list.add(this);
        return isRecipeAvailable(recipe, list);
    }

    /**
     *这个方法用于检查给定的配方是否可用，同时传入一个已经检查过的数据访问孔（Data Access Hatch）列表。
     *返回一个布尔值，表示该配方是否可用。
     */
    boolean isRecipeAvailable(@NotNull Recipe recipe, @NotNull Collection<IDataAccessHatch> seen);

}
