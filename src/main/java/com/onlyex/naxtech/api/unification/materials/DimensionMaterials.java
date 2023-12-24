package com.onlyex.naxtech.api.unification.materials;

import com.onlyex.naxtech.api.unification.NTElements;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.ToolProperty;

import static com.onlyex.naxtech.api.unification.NTMaterials.*;
import static com.onlyex.naxtech.api.unification.material.info.NTMaterialFlags.GENERATE_NANITES;
import static com.onlyex.naxtech.api.unification.material.info.NTMaterialIconSet.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.api.unification.material.properties.BlastProperty.GasTier.HIGH;
import static gregtech.api.unification.material.properties.BlastProperty.GasTier.HIGHEST;
import static gregtech.api.util.GTUtility.gregtechId;



//24000-25000
public class DimensionMaterials {
    private static int startId = 24001;
    private static final int END_ID = startId + 1000;

    public static void register() {

        BlackdWarfMatter = new Material.Builder(getMaterialsId(),gregtechId("blackd_warf_matter"))
                .ingot(11)
                .liquid(new FluidBuilder())
                .color(0x000000).iconSet(METALLIC)
                .flags(NONTM, GENERATE_NANITES)
                .blast(b -> b .temp(120000, HIGH))
                .build();

        WhitedWarfMatter = new Material.Builder(getMaterialsId(), gregtechId("whited_warf_matter"))
                .ingot()
                .liquid(new FluidBuilder().customStill())
                .iconSet(CUSTOM_WWM)
                .flags(DIMENSION, GENERATE_NANITES)
                .blast(b -> b .temp(120000, HIGH))
                .build();

        Infinity = new Material.Builder(getMaterialsId(), gregtechId("infinity"))
                .ingot()
                .liquid(new FluidBuilder().customStill())
                .iconSet(CUSTOM_INFINITY)
                .flags(DIMENSION)
                .element(NTElements.If)
                .blast(b -> b .temp(120000, HIGH))
                .build();

        CosmicNeutronium = new Material.Builder(getMaterialsId(), gregtechId("cosmic_neutronium"))
                .ingot()
                .liquid(new FluidBuilder())
                .element(NTElements.CoNe)
                .color(0x323232).iconSet(CUSTOM_CN)
                .flags(DIMENSION)
                .blast(b -> b .temp(120000, HIGH))
                .build();

        Spacetime = new Material.Builder(getMaterialsId(), gregtechId("spacetime"))
                .ingot()
                .liquid(new FluidBuilder().customStill())
                .iconSet(CUSTOM_SPACETIME)
                .flags(DIMENSION)
                .element(NTElements.SpTi)
                .blast(b -> b .temp(120000, HIGH))
                .build();

        Eternity = new Material.Builder(getMaterialsId(), gregtechId("eternity"))
                .ingot()
                .liquid(new FluidBuilder().customStill())
                .iconSet(CUSTOM_ETERNITY)
                .flags(DIMENSION, GENERATE_NANITES)
                .element(NTElements.Et)
                .blast(b -> b .temp(120000, HIGH))
                .build();

        MagnetohydrodynamicallyConstrainedStarMatter = new Material.Builder(getMaterialsId(), gregtechId("magneto_hydrodynamically_constrained_star_matter"))
                .ingot()
                .liquid(new FluidBuilder().customStill())
                .iconSet(CUSTOM_MHCSM)
                .flags(NONTM, GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_RING)
                .element(NTElements.MaCoStMa)
                .blast(b -> b .temp(120000, HIGH))
                .build();

        DraconiumChaotic = new Material.Builder(getMaterialsId(),gregtechId("draconium_chaotic"))
                .ingot(10)
                .liquid(new FluidBuilder())
                .color(0x2C195A).iconSet(METALLIC)
                .flags(NONTM)
                .element(NTElements.ChaoticDraconium)
                .blast(b -> b .temp(9430, HIGH))
                .build();

        DraconiumAwakened = new Material.Builder(getMaterialsId(),gregtechId("draconium_awakened"))
                .ingot(9)
                .liquid(new FluidBuilder())
                .color(0xFFA157).iconSet(METALLIC)
                .flags(NONTM)
                .element(NTElements.AwakenedDraconium)
                .blast(b -> b .temp(8460, HIGH))
                .build();

        Draconium = new Material.Builder(getMaterialsId(),gregtechId("draconium"))
                .ingot(8)
                .liquid(new FluidBuilder())
                .color(0x6200e7).iconSet(METALLIC)
                .flags(NONTM)
                .element(NTElements.Draconium)
                .blast(b -> b .temp(7250, HIGH))
                .build();

        Lumium = new Material.Builder(getMaterialsId(), gregtechId("lumium"))
                .build();

        Enderium = new Material.Builder(getMaterialsId(), gregtechId("enderium"))
                .build();

        Signalum = new Material.Builder(getMaterialsId(), gregtechId("signalum"))
                .build();

        Mithril = new Material.Builder(getMaterialsId(), gregtechId("mithril"))
                .ingot()
                .liquid(new FluidBuilder())
                .plasma()
                .color(0x428fdb)
                .iconSet(DULL)
                .blast(b -> b .temp(10900, HIGHEST).blastStats(VA[UHV]))
                .element(NTElements.Mithril)
                .flags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE, NO_UNIFICATION)
                .build();


        Shirabon = new Material.Builder(getMaterialsId(), gregtechId("shirabon"))
                .ingot(13)
                .liquid(new FluidBuilder().temperature(26000))
                .color(0xBE125C).iconSet(METALLIC)
                .flags(NONTM)
                .element(NTElements.Shir)
                .build();

        AstralTitanium = new Material.Builder(getMaterialsId(), gregtechId("astral_titanium"))
                .ingot(9)
                .liquid(new FluidBuilder().temperature(6900))
                .color(0xE5ABF6).iconSet(METALLIC)
                .flags(NONTM)
                .element(NTElements.AsTi)
                .build();

        CelestialTungsten = new Material.Builder(getMaterialsId(), gregtechId("celestial_tungsten"))
                .ingot(10)
                .liquid(new FluidBuilder().temperature(8700))
                .color(0x303030).iconSet(METALLIC)
                .flags(NONTM)
                .element(NTElements.CeTu)
                .build();

        Rhugnor = new Material.Builder(getMaterialsId(), gregtechId("rhugnor"))
                .fluid()
                .color(0xBE00FF).iconSet(METALLIC)
                .element(NTElements.Rhugnor)
                .build();

        Universium = new Material.Builder(getMaterialsId(), gregtechId("universium"))
                //.ingot().fluid()
                .element(NTElements.Un)//TODO 方块 流体 材质
                //.iconSet(CUSTOM_UNIVERSIUM)
                //.flags(DIMENSION, GENERATE_NANITES)
                .build();

        ElectrumFlux = new Material.Builder(getMaterialsId(), gregtechId("electrum_flux"))
                .iconSet(METALLIC)
                .build()
                .setFormula("(CuAu₄)(AuMa*)(CuAu₄ZnCu₃(Fe₅₀C)₂(NiAuAgCu₃(Fe₅₀C)₃)₄)(Ag₂FeMa)((CuAg₄)(Nq₂KeC)(Sn₉Sb)(CuAg₄BiZnCu₃(Fe₅₀C)₂(NiAuAgCu₃(Fe₅₀C)₃)₄)", true);

        Hypogen = new Material.Builder(getMaterialsId(), gregtechId("hypogen"))
                .ingot()
                .fluid()
                .plasma()
                .element(NTElements.Hypogen)
                .color(0xDC784B)
                .iconSet(CUSTOM_HYPOGEN)
                .toolStats(ToolProperty.Builder.of(20.0F, 200.0F, 2000000, 200).unbreakable().enchantability(33).magnetic().build())
                .flags(GENERATE_PLATE, NO_UNIFICATION)
                .build();

        TranscendentMetal = new Material.Builder(getMaterialsId(), gregtechId("transcendent_metal"))
                .fluid()
                .iconSet(METALLIC)
                .build();

        DegenerateRhenium = new Material.Builder(getMaterialsId(), gregtechId("degenerate_rhenium"))
                .dust()
                .fluid()
                .color(0x6666FF)
                .iconSet(CUSTOM_DEGENERATE_RHENIUM)
                .element(Elements.Rh)
                .flags(GENERATE_PLATE, NO_UNIFICATION)
                .build()
                .setFormula("§cR§de", false);

        CrystalMatrix = new Material.Builder(getMaterialsId(), gregtechId("crystal_matrix"))
                .ingot()
                .liquid(new FluidBuilder().temperature(6604))
                .color(0x70ecff)
                .iconSet(BRIGHT)
                .flags(NO_UNIFICATION)
                .element(NTElements.CrystalMatrix)
                .build();

        Ichorium = new Material.Builder(getMaterialsId(), gregtechId("ichorium"))
                .ingot()
                .liquid(new FluidBuilder().temperature(10800))
                .color(0xE5A559)
                .iconSet(BRIGHT)
                .blast(b -> b .temp(10800, HIGHEST).blastStats(VA[UV]))
                .element(NTElements.Ichorium)
                .flags(GENERATE_PLATE, NO_UNIFICATION)
                .build();

        IchorLiquid = new Material.Builder(getMaterialsId(), gregtechId("ichor_liquid"))
                .liquid(new FluidBuilder().temperature(214748))
                .plasma()
                .color(0xE5A559)
                .element(NTElements.IchorLiquid)
                .flags(NO_UNIFICATION)
                .build();

        VoidMetal = new Material.Builder(getMaterialsId(), gregtechId("void_metal"))
                .ingot()
                .liquid(new FluidBuilder().temperature(440))
                .color(0x20142C)
                .iconSet(DULL)
                .element(NTElements.VoidMetal)
                .flags(NO_UNIFICATION)
                .build();

        DragonTear = new Material.Builder(getMaterialsId(), gregtechId("dragon_tear"))
                .liquid(new FluidBuilder().temperature(2992))
                .color(0x9999FF)
                .iconSet(DULL)
                .build()
                .setFormula("⚙", false);
    }

    private static int getMaterialsId() {
        if (startId < END_ID) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }


}
