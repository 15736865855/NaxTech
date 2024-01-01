package com.onlyex.naxtech.api.unification.material.info;

import com.onlyex.naxtech.client.renderer.texture.NTTextures;
import com.onlyex.naxtech.common.items.behaviors.renderer.*;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.unification.material.info.MaterialIconSet;
import stanhebben.zenscript.annotations.ZenClass;


@ZenClass("mods.naxtesh.material.MaterialIconSet")
@ZenRegister
public class NTMaterialIconSet {
    public static final MaterialIconSet CUSTOM_MHCSM = new MaterialIconSet("mhcsm", null, true);
    public static final MaterialIconSet CUSTOM_CN = new MaterialIconSetWithRenderer("cosmic_neutronium", null, true,new DataRenderItemBehavior());
    public static final MaterialIconSet CUSTOM_SPACETIME = new MaterialIconSet("spacetime", null, true);
    public static final MaterialIconSet CUSTOM_WWM = new MaterialIconSet("whited_warf_matter", null, true);
    public static final MaterialIconSet CUSTOM_DEGENERATE_RHENIUM = new MaterialIconSet("degenerate_rhenium", null, true);
    public static final MaterialIconSet CUSTOM_HYPOGEN = new MaterialIconSet("hypogen", null, true);
    public static final MaterialIconSet CUSTOM_LEGENDARIUM = new MaterialIconSet("legendarium", null, true);


    public static final MaterialIconSetWithRenderer CUSTOM_ETERNITY = new MaterialIconSetWithRenderer("eternity", null, true, new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
    public static final MaterialIconSetWithRenderer CUSTOM_INFINITY = new MaterialIconSetWithRenderer("infinity", null, true, new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));

    public static final MaterialIconSetWithRenderer CUSTOM_UNIVERSIUM = new MaterialIconSetWithRenderer("universium", null, true,  new CosmicRenderItemBehavior(() -> NTTextures.HALO, 1));

}
