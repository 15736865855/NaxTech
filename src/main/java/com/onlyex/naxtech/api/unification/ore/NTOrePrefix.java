package com.onlyex.naxtech.api.unification.ore;


import com.onlyex.naxtech.api.unification.material.info.NTMaterialFlags;
import com.onlyex.naxtech.api.unification.material.info.NTMaterialIconType;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.client.resources.I18n;
import stanhebben.zenscript.annotations.ZenClass;

import java.util.Collections;

import static gregtech.api.GTValues.M;
import static gregtech.api.unification.ore.OrePrefix.Conditions.hasIngotProperty;
import static gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

@ZenClass("mods.naxtech.ore.NTOrePrefix")
@ZenRegister
public class NTOrePrefix {

    public static final OrePrefix plateCurved = new OrePrefix("plateCurved", M, null, NTMaterialIconType.plateCurved, ENABLE_UNIFICATION,
            (material -> material.hasFlag(MaterialFlags.GENERATE_PLATE) && material.hasFlag(NTMaterialFlags.GENERATE_CURVED_PLATE)));
    public static final OrePrefix milled = new OrePrefix("milled", -1, null, NTMaterialIconType.milled, ENABLE_UNIFICATION,
            hasOreProperty, mat -> Collections.singletonList(I18n.format("metaitem.milled.tooltip.flotation")));
    public static final OrePrefix nanites = new OrePrefix("nanites", -1, null, NTMaterialIconType.nanites, ENABLE_UNIFICATION,
            (material -> material.hasFlag(NTMaterialFlags.GENERATE_NANITES)));
    public static final OrePrefix casingSmall = new OrePrefix("casingSmall", M, null, NTMaterialIconType.casingSmall, ENABLE_UNIFICATION,
            hasIngotProperty);
    public static final OrePrefix wrapCircuit = new OrePrefix("wrapCircuit", -1, null, NTMaterialIconType.wrapCircuit, ENABLE_UNIFICATION,
            null);
    public static final OrePrefix wrap = new OrePrefix("wrap", -1, null, NTMaterialIconType.wrap, ENABLE_UNIFICATION,
            null);
    public static void init() {
        milled.addSecondaryMaterial(new MaterialStack(Materials.Stone, OrePrefix.stone.getMaterialAmount(Materials.Stone)));
    }

}
