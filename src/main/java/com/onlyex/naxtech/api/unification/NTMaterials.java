package com.onlyex.naxtech.api.unification;

import com.onlyex.naxtech.api.unification.materials.*;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregtech.api.unification.material.Materials.EXT2_METAL;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class NTMaterials {

    public static final List<MaterialFlag> ONTEA = new ArrayList<>();
    public static final List<MaterialFlag> NONTM = new ArrayList<>();
    public static final List<MaterialFlag> NOR = new ArrayList<>();
    public static final List<MaterialFlag> DIMENSION = new ArrayList<>();

    static {
        ONTEA.addAll(EXT2_METAL);
        ONTEA.addAll(Arrays.asList(GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_DENSE, GENERATE_DOUBLE_PLATE, GENERATE_FOIL));

        NONTM.addAll(ONTEA);
        NONTM.addAll(Arrays.asList(NO_UNIFICATION, EXCLUDE_BLOCK_CRAFTING_RECIPES));

        NOR.addAll(Arrays.asList(NO_UNIFICATION, DISABLE_DECOMPOSITION));

        DIMENSION.addAll(NONTM);
        DIMENSION.addAll(Arrays.asList(GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROTOR, GENERATE_ROUND, GENERATE_FINE_WIRE, GENERATE_RING));
    }

    //材料
    public static Material BlackdWarfMatter;
    public static Material WhitedWarfMatter;
    public static Material Infinity;
    public static Material CosmicNeutronium;
    public static Material Spacetime;
    public static Material Universium;
    public static Material Eternity;
    public static Material MagnetohydrodynamicallyConstrainedStarMatter;
    public static Material DraconiumAwakened;
    public static Material Draconium;
    public static Material Lumium;
    public static Material Enderium;
    public static Material Signalum;
    public static Material Mithril;
    public static Material Shirabon;
    public static Material AstralTitanium;
    public static Material CelestialTungsten;
    public static Material Rhugnor;
    public static Material ElectrumFlux;
    public static Material Hypogen;
    public static Material MARM200Steel;
    public static Material TranscendentMetal;
    public static Material Celestite;
    public static Material DegenerateRhenium;
    public static Material Vibranium;
    public static Material Adamantium;
    public static Material DraconiumChaotic;
    public static Material Orichalcum;
    public static Material CrystalMatrix;
    public static Material Taranium;
    public static Material Ichorium;
    public static Material IchorLiquid;
    public static Material VoidMetal;
    public static Material Legendarium;
    public static Material DragonDust;
    public static Material DragonTear;
    public static Material Quantium;
    public static Material QuantumAlloy;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;

    //流体
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    //public static Material ;
    public static Material PineOil;
    public static Material BlazingPyrotheum;
    public static Material GelidCryotheum;
    public static Material DraconiumSlagSolution;
    public static Material ResidualDraconiumSolution;
    public static Material MutatedIivingSolder;
    public static Material EthylCyanoacrylateSuperGlue;
    public static Material CyanoacrylatePolymer;
    public static Material EthylCyanoacetate;
    public static Material CopperSulfatePentahydrate;
    public static Material CopperSulfate;
    public static Material SolidAcidCatalystMixture;
    public static Material CyanoaceticAcid;
    public static Material SodiumCyanide;
    public static Material ChloroaceticMixture;
    public static Material TrichloroaceticAcid;
    public static Material DichloroaceticAcid;
    public static Material ChloroaceticAcid;
    public static Material AceticAnhydride;
    public static Material TemporalFluid;
    public static Material SpatialFluid;
    public static Material Rawstarmatter;
    public static Material PrimordialMatter;
    public static Material DimensionallyTranscendentResidue;
    public static Material DimensionallyTranscendentCrudecatalyst;
    public static Material DimensionallyTranscendentProsaiccatalyst;
    public static Material DimensionallyTranscendentResplendentcatalyst;
    public static Material DimensionallyTranscendentExoticcatalyst;
    public static Material DimensionallyTranscendentStellarcatalyst;
    public static Material ExciteddtCC;
    public static Material ExciteddtPC;
    public static Material ExciteddtRC;
    public static Material ExciteddtEC;
    public static Material ExciteddtSC;
    public static Material DragonBreath;
    public static Material ConcentrateDragonBreath;
    public static Material DragonBlood;
    public static Material NitratedDragonDustSolution;



    public NTMaterials() {}
    public static void initregister() {
        // 24000-25000
        DimensionMaterials.register();
        // 25001-26000
        FluidsMaterials.register();
        //26001-27000
        MachineCasingMaterials.register();
        //27001-28000
        DustMaterials.register();

    }
}
