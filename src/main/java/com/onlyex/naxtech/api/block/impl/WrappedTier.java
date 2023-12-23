package com.onlyex.naxtech.api.block.impl;

import com.onlyex.naxtech.api.block.ITier;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public abstract class WrappedTier implements ITier {
    private final IStringSerializable inner;

    public WrappedTier(IStringSerializable inner) {
        this.inner = inner;
    }

    @Override
    @Nonnull
    public String getName() {
        return inner.getName();
    }

    public abstract Object getTier();
}
