package com.onlyex.naxtech.api.utils;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class NTUtils {
    @Nonnull
    public static ResourceLocation epId(@Nonnull String path) {
            return new ResourceLocation("naxtech", path);
    }
}
