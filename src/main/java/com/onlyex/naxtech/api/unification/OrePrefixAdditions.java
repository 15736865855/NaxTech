package com.onlyex.naxtech.api.unification;

import gregtech.common.items.MetaItems;

import static com.onlyex.naxtech.api.unification.ore.NTOrePrefix.*;

public class OrePrefixAdditions {
    public static void init() {
        MetaItems.addOrePrefix(nanites);
        MetaItems.addOrePrefix(plateCurved);
        MetaItems.addOrePrefix(milled);
        wrap.setMarkerPrefix(true);
        wrapCircuit.setMarkerPrefix(true);
        MetaItems.addOrePrefix(casingSmall);
    }
}
