package com.onlyex.naxtech.api.unification.material.info;

import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.info.MaterialFlags;

public class NTMaterialFlags {
    public static final MaterialFlag GENERATE_CURVED_PLATE = new MaterialFlag.Builder("generate_curved_plate")
            .requireFlags(MaterialFlags.GENERATE_PLATE)
            .build();

    public static final MaterialFlag GENERATE_NANITES = new MaterialFlag.Builder("nanites")
            .build();
}
