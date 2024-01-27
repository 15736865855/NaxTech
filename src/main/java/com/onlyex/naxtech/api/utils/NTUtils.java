package com.onlyex.naxtech.api.utils;

import com.onlyex.naxtech.api.NTValues;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class NTUtils {
    @Nonnull
    public static ResourceLocation naxId(@Nonnull String path) {
            return new ResourceLocation(NTValues.MOD_ID, path);
    }
}
