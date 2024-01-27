package com.onlyex.naxtech.common.pipelike.research;

import gregtech.api.pipenet.block.IPipeType;

import org.jetbrains.annotations.NotNull;

public enum ResearchPipeType implements IPipeType<ResearchPipeProperties> {

    NORMAL;

    @Override
    public float getThickness() {
        return 0.375F;
    }

    @Override
    public ResearchPipeProperties modifyProperties(ResearchPipeProperties baseProperties) {
        return baseProperties;
    }

    @Override
    public boolean isPaintable() {
        return true;
    }

    @NotNull
    @Override
    public String getName() {
        return "normal";
    }
}
