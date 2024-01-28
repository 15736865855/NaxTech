package com.onlyex.naxtech.api.utils;

import com.onlyex.naxtech.api.NTValues;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class NTUtils {
    @NotNull
    public static ResourceLocation naxId(@NotNull String path) {
            return new ResourceLocation(NTValues.MOD_ID, path);
    }
}
