package com.onlyex.naxtech.common.items;


import com.onlyex.naxtech.client.renderer.texture.NTTextures;
import com.onlyex.naxtech.common.CommonProxy;
import com.onlyex.naxtech.common.items.behaviors.DataItemResearch;
import com.onlyex.naxtech.common.items.behaviors.IntBcircuitBehavior;
import com.onlyex.naxtech.common.items.behaviors.MillBallBehavior;
import com.onlyex.naxtech.common.items.behaviors.StructureWriteBehavior;
import com.onlyex.naxtech.common.items.behaviors.renderer.CosmicRenderItemBehavior;
import com.onlyex.naxtech.common.items.behaviors.renderer.HaloRenderItemBehavior;
import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.ElectricStats;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.items.metaitem.stats.IItemComponent;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.behaviors.MultiblockBuilderBehavior;
import gregtech.common.items.behaviors.ProspectorScannerBehavior;
import gregtech.common.items.behaviors.TooltipBehavior;
import net.minecraft.client.resources.I18n;

import static com.onlyex.naxtech.common.items.NTMetaItems.*;

public class NTMetaItem1 extends StandardMetaItem {

    public NTMetaItem1() {
        this.setRegistryName("meta_item_nt");
        setCreativeTabs(CommonProxy.NAXTECH_TAB);
    }

    // = this.addItem(,"");
    public void registerSubItems(){

        //ID 0~19

        GOOWARE_PROCESSOR = this.addItem(0, "circuit.gooware_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.ZPM);
        GOOWARE_ASSEMBLY = this.addItem(1, "circuit.gooware_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV);
        GOOWARE_COMPUTER = this.addItem(2, "circuit.gooware_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV);
        GOOWARE_MAINFRAME = this.addItem(3, "circuit.gooware_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV);

        OPTICAL_PROCESSOR = this.addItem(4,"circuit.optical_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV);
        OPTICAL_ASSEMBLY = this.addItem(5,"circuit.optical_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV);
        OPTICAL_COMPUTER = this.addItem(6,"circuit.optical_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV);
        OPTICAL_MAINFRAME = this.addItem(7,"circuit.optical_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV);

        SPINTRONIC_PROCESSOR = this.addItem(8, "circuit.spintronic_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV);
        SPINTRONIC_ASSEMBLY = this.addItem(9, "circuit.spintronic_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV);
        SPINTRONIC_COMPUTER = this.addItem(10, "circuit.spintronic_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV);
        SPINTRONIC_MAINFRAME = this.addItem(11, "circuit.spintronic_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV);

        COSMIC_PROCESSOR = this.addItem(12, "circuit.cosmic_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV);
        COSMIC_ASSEMBLY = this.addItem(13, "circuit.cosmic_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV);
        COSMIC_COMPUTER = this.addItem(14, "circuit.cosmic_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV);
        COSMIC_MAINFRAME = this.addItem(15, "circuit.cosmic_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV);

        SUPRACAUSAL_PROCESSOR = this.addItem(16, "circuit.supracausal_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV)
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        SUPRACAUSAL_ASSEMBLY = this.addItem(17, "circuit.supracausal_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV)
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        SUPRACAUSAL_COMPUTER = this.addItem(18, "circuit.supracausal_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV)
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        SUPRACAUSAL_MAINFRAME = this.addItem(19, "circuit.supracausal_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MAX)
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));

        //ID 20~59
        EU_DOPED_CUBIC_ZIRCONIA_BOULE = this.addItem(20, "boule.cubic_zirconia.europium");
        EU_DOPED_CUBIC_ZIRCONIA_WAFER = this.addItem(21, "wafer.cubic_zirconia.europium");
        CRYSTAL_INTERFACE_WAFER = this.addItem(22, "wafer.crystal.interface");
        CRYSTAL_INTERFACE_PLATE = this.addItem(23, "plate.crystal.interface");
        ENGRAVED_RUBY_CRYSTAL_CHIP = this.addItem(24, "engraved.crystal_chip.ruby");
        ENGRAVED_SAPPHIRE_CRYSTAL_CHIP = this.addItem(25, "engraved.crystal_chip.sapphire");
        ENGRAVED_DIAMOND_CRYSTAL_CHIP = this.addItem(26, "engraved.crystal_chip.diamond");
        CRYSTAL_MODULATOR_RUBY = this.addItem(27, "crystal.modulator.ruby");
        CRYSTAL_MODULATOR_SAPPHIRE = this.addItem(28, "crystal.modulator.sapphire");
        CRYSTAL_MODULATOR_DIAMOND = this.addItem(29, "crystal.modulator.diamond");
        CRYSTAL_SYSTEM_ON_CHIP_SOCKET = this.addItem(30, "crystal.system_on_chip.socket");
        GLASS_FIBER = this.addItem(31,"glass_fiber");
        EUROPIUM_DOPED_MONOCRYSTALLINE_SILICON_BOULE = this.addItem(32,"europium_doped_monocrystalline_silicon_boule");
        EUROPIUM_DOPED_WAFER = this.addItem(33,"europium_doped_wafer");
        AMERICIUM_DOPED_MONOCRYSTALLINE_SILICON_BOULE = this.addItem(34,"americium_doped_monocrystalline_silicon_boule");
        AMERICIUM_DOPED_WAFER = this.addItem(35,"americium_doped_wafer");
        STATION_MKI = this.addItem(36,"station_1");
        STATION_MKII = this.addItem(37,"station_2");
        STATION_MKIII = this.addItem(38,"station_3");
        STATION_MKIV = this.addItem(39,"station_4");
        STATION_MKV = this.addItem(40,"station_5");
        CRYSTAL_ADVANCED_RAW_CHIP = this.addItem(41,"crystal.advanced_raw_chip");
        GRINDBALL_SOAPSTONE = this.addItem(42, "mill.grindball_soapstone")
                .setMaxStackSize(1).addComponents(new MillBallBehavior());
        GRINDBALL_ALUMINIUM = this.addItem(43, "mill.grindball_aluminium")
                .setMaxStackSize(1).addComponents(new MillBallBehavior());
        NULL = this.addItem(44, "null")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.MASK_INGOT, 1));
        PINE_CONE=this.addItem(45, "item.pine_cone");
        PINE_FRAGMENT=this.addItem(46, "item.pine_fragment");
        BIOLOGY_INTEGRATED_CIRCUIT = this.addItem(47, "item.biology_integrated_circuit")
                .addComponents(new IntBcircuitBehavior());
        CHROMATIC_LENS = this.addItem(48, "chromatic_lens")
                .addOreDict("craftingLensChromatic");
        QCD_PROTECTIVE_PLATING = this.addItem(49, "qcd_protective_plating");
        COSMIC_FABRIC = this.addItem(50, "cosmic_fabric");
        CRYSTAL_OPTICAL_RAW_CHIP = this.addItem(51,"crystal.optical_raw_chip");
        CRYSTAL_SPINTRONIC_RAW_CHIP = this.addItem(52,"crystal.spintronic_raw_chip");
        CRYSTAL_COSMIC_RAW_CHIP = this.addItem(53,"crystal.cosmic_raw_chip");
        CRYSTAL_OPTICAL_INTERFACE_PLATE = this.addItem(54,"plate.crystal.optical_interface");


        //ID 60~
        RAW_TESSERACT = this.addItem(60,"dimension.raw_tesseract");
        ENERGISED_TESSERACT = this.addItem(61,"dimension.energised_tesseract");
        BLACK_BODY_NAQUADRIA_SUPERSOLID = this.addItem(62,"dimension.black_body_naquadria_supersolid");
        HYPER_STABLE_SELF_HEALING_ADHESIVE = this.addItem(63,"dimension.hyper-stable_self-healing_adhesive");
        SUPERCONDUCTOR_RARE_EARTH_COMPOSITE = this.addItem(64,"dimension.superconductor_rare-earth_composite");
        TIMEPIECE = this.addItem(65,"timepiece");
        QUANTUM_ANOMALY = this.addItem(66,"dimension.quantum_anomaly");



        //ID 100~
        ELECTRIC_MOTOR_MAX = this.addItem(100, "cover.electric_motor.max");
        ELECTRIC_PISTON_MAX = this.addItem(101, "cover.electric_piston.max");
        ELECTRIC_PUMP_MAX = this.addItem(102, "cover.electric_pump.max");
        CONVEYOR_MODULE_MAX = this.addItem(103, "cover.conveyor_module.max");
        ROBOT_ARM_MAX = this.addItem(104, "cover.robot_arm.max");
        EMITTER_MAX = this.addItem(105, "cover.emitter.max");
        SENSOR_MAX = this.addItem(106, "cover.sensor.max");
        FIELD_GENERATOR_MAX = this.addItem(107, "cover.field_generator.max");

        ELECTRIC_MOTOR_MAXX = this.addItem(108, "cover.electric_motor.maxx");
        ELECTRIC_PISTON_MAXX = this.addItem(109, "cover.electric_piston.maxx");
        ELECTRIC_PUMP_MAXX = this.addItem(110, "cover.electric_pump.maxx");
        CONVEYOR_MODULE_MAXX = this.addItem(111, "cover.conveyor_module.maxx");
        ROBOT_ARM_MAXX = this.addItem(112, "cover.robot_arm.maxx");
        EMITTER_MAXX = this.addItem(113, "cover.emitter.maxx");
        SENSOR_MAXX = this.addItem(114, "cover.sensor.maxx");
        FIELD_GENERATOR_MAXX = this.addItem(115, "cover.field_generator.maxx");

        SENSOR_EX = this.addItem(116, "cover.sensor.ex");
        FIELD_GENERATOR_EX = this.addItem(117, "cover.field_generator.ex");
        ELECTRIC_MOTOR_EX = this.addItem(118, "cover.electric_motor.ex");
        ELECTRIC_PISTON_EX = this.addItem(119, "cover.electric_piston.ex");
        ELECTRIC_PUMP_EX = this.addItem(120, "cover.electric_pump.ex");
        CONVEYOR_MODULE_EX = this.addItem(121, "cover.conveyor_module.ex");
        ROBOT_ARM_EX = this.addItem(122, "cover.robot_arm.ex");
        EMITTER_EX = this.addItem(123, "cover.emitter.ex");

        BIOCELLS = this.addItem(124,"gooware.biocells");
        LIVING_BIO_WAFER = this.addItem(125,"gooware.living_bio_wafer");
        LIVING_BIO_CHIP = this.addItem(126,"gooware.living_bio_chip");

        NUCLEAR_STAR = this.addItem(127,"nuclear_star");
        LIVING_CRYSTAL_CHIP = this.addItem(128,"living_crystal_chip");
        PROTONATED_FULLERENE_SIEVING_MATRIX = this.addItem(129, "protonated_fullerene_sieving_matrix");
        SATURATED_FULLERENE_SIEVING_MATRIX = this.addItem( 130, "saturated_fullerene_sieving_matrix");

        UNSTABLE_STAR = this.addItem(131, "unstable_star");


        //140~
        OPTICALLY_ENRICHED_CRYSTALLINE_BOULE = this.addItem(140,"optical.optically_enriched_crystalline_boule");
        PHOTONICALLY_PREPARED_WAFER = this.addItem(141,"optical.photonically_prepared_wafer");
        PHOTONICALLY_ENHANCED_WAFER = this.addItem(142,"optical.photonically_enhanced_wafer");
        RAW_EXPOSED_OPTICAL_CHIP = this.addItem(143,"optical.raw_exposed_optical_chip");
        OPTICALLY_COMPATIBLE_MEMORY = this.addItem(144,"optical.optically_compatible_memory");
        OPTICALLY_PERFECTED_CPU = this.addItem(145,"optical.optically_perfected_cpu");
        OPTICAL_CPU_CONTAINMENT_HOUSING = this.addItem(146,"optical.optical_cpu_containment_housing");
        OPTICAL_CIRCUIT_BOARD = this.addItem(147,"optical.optical_circuit_board");

        OPTICAL_CAPACITOR = this.addItem(148, "component.optical_smd.capacitor");
        OPTICAL_DIODE = this.addItem(149, "component.optical_smd.diode");
        OPTICAL_RESISTOR = this.addItem(150, "component.optical_smd.resistor");
        OPTICAL_TRANSISTOR = this.addItem(151, "component.optical_smd.transistor");
        OPTICAL_INDUCTOR = this.addItem(152,"component.optical_smd.inductor");



        //ID 180~199
        BZ_REACTION_CHAMBER = this.addItem(180, "reaction_chamber.bz");
        NONLINEAR_CHEMICAL_OSCILLATOR = this.addItem(181, "nonlinear_chemical_oscillator");
        PETRI_DISH_STERILE = this.addItem(182,"petri_dish.sterile");
        PETRI_DISH_FERTILE = this.addItem(183,"petri_dish.fertile");
        BARNARDA_C_BASE = this.addItem(184, "alga.barnarda_c.base");
        BARNARDA_C_CHLORELLA = this.addItem(185, "alga.barnarda_c.chlorella")
                .addOreDict("dyePurple");
        BARNARDA_C_BRYOPSIS_HYPNOIDES = this.addItem(186, "alga.barnarda_c.bryopsis_hypnoides")
                .addOreDict("dyeGreen");
        BARNARDA_C_ZOOXANTHELLAE = this.addItem(187, "alga.barnarda_c.zooxanthellae")
                .addOreDict("dyeOrange");
        TAU_CETI_F_BASE = this.addItem(188, "alga.tau_ceti_f.base");
        TAU_CETI_F_SCENEDESMUS_OBLIQUUS = this.addItem(189, "alga.tau_ceti_f.scenedesmus_obliquus")
                .addOreDict("dyeLime");
        TAU_CETI_F_PHAEOPHYTA = this.addItem(190, "alga.tau_ceti_f.phaeophyta")
                .addOreDict("dyeBrown");
        TAU_CETI_F_SPIRULINA = this.addItem(191, "alga.tau_ceti_f.spirulina")
                .addOreDict("dyeBlue");
        PROXIMA_B_BASE = this.addItem(192, "alga.proxima_b.base");
        PROXIMA_B_CONCHOSPORE = this.addItem(193, "alga.proxima_b.conchospore")
                .addOreDict("dyeRed");
        PROXIMA_B_POLYSIPHONIA_SENTICULOSA = this.addItem(194, "alga.proxima_b.polysiphonia_senticulosa")
                .addOreDict("dyeYellow");
        PROXIMA_B_SPIROGYRA = this.addItem(195, "alga.proxima_b.spirogyra")
                .addOreDict("dyeGray");
        BIO_DISH_PETRI_DISH_STERILE = this.addItem(196,"petri_dish.bio_dish.sterile");
        BIO_DISH_PETRI_DISH_FERTILE = this.addItem(197,"petri_dish.bio_dish.fertile");

        //ID 200~
        OPTICAL_FIBER = this.addItem(200, "optical_fiber");
        DIELECTRIC_MIRROR = this.addItem(201, "dielectric_mirror");
        EMPTY_LASER_ASSEMBLY = this.addItem(202, "laser.assembly.empty");
        HELIUM_NEON_LASER = this.addItem(203, "laser.helium_neon");
        ND_YAG_LASER = this.addItem(204, "laser.nd_yag");
        OPTICAL_LASER_CONTROL_UNIT = this.addItem(205, "optical_laser_control_unit");

        TOPOLOGICAL_INSULATOR_TUBE = this.addItem(206, "tube.topological_insulator");
        BOSE_EINSTEIN_CONDENSATE_CONTAINMENT_UNIT = this.addItem(207, "containment_unit.bose_einstein_condensate");
        BOSE_EINSTEIN_CONDENSATE = this.addItem(208, "bose_einstein_condensate");
        ESR_COMPUTATION_UNIT = this.addItem(209, "esr_computation_unit");
        EIGENFOLDED_KERR_MANIFOLD = this.addItem(210, "eigenfolded.kerr.manifold");
        HYPERDIMENSIONAL_DRONE = this.addItem(211, "hyperdimensional.drone");

        //  High Energy Physics Items
        PLASMA_CONTAINMENT_CELL = this.addItem(212, "plasma_containment_cell");
        RHENIUM_PLASMA_CONTAINMENT_CELL = this.addItem(213, "rhenium_plasma_containment_cell");
        NEUTRON_PLASMA_CONTAINMENT_CELL = this.addItem(214, "neutron_plasma_containment_cell");
        EXTREMELY_DURABLE_PLASMA_CONTAINMENT_CELL = this.addItem(215, "extremely_durable_plasma_containment_cell");
        DENSE_NEUTRON_PLASMA_CONTAINMENT_CELL = this.addItem(216, "dense_neutron_plasma_containment_cell");
        COSMIC_NEUTRON_PLASMA_CONTAINMENT_CELL = this.addItem(217, "cosmic_neutron_plasma_containment_cell");
        TIME_DILATION_CONTAINMENT_UNIT = this.addItem(218, "time_dilation_containment_unit");
        CONTAINED_RN_SINGULARITY = this.addItem(219, "contained_reissner_nordstrom_singularity");
        CONTAINED_KN_SINGULARITY = this.addItem(220, "contained_kerr_newmann_singularity");
        CONTAINED_KERR_SINGULARITY = this.addItem(221, "contained_kerr_singularity");
        CONTAINED_HIGH_DENSITY_PROTONIC_MATTER = this.addItem(222, "contained_high_density_protonic_matter");
        CONTAINED_EXOTIC_MATTER = this.addItem(223, "contained_exotic_matter");
        CHARGED_TRIPLET_NEUTRONIUM_SPHERE = this.addItem(224, "charged_triplet_neutronium_sphere");
        COSMIC_FABRIC_PLASMA_CONTAINMENT_CELL = this.addItem(225, "cosmic_fabric_plasma_containment_cell")
                .addComponents(new TooltipBehavior((lines) -> {
            lines.add(I18n.format("metaitem.cosmic_fabric_plasma_containment_cell.tooltip.1"));
            lines.add(I18n.format("metaitem.cosmic_fabric_plasma_containment_cell.tooltip.2"));
            lines.add(I18n.format("metaitem.cosmic_fabric_plasma_containment_cell.tooltip.3"));
            lines.add(I18n.format("metaitem.cosmic_fabric_plasma_containment_cell.tooltip.4"));
        }));


        //240~256 singularity  //.addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, false))
        ETERNAL_SINGULARITY = this.addItem(240, "singularity.eternal_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.ETERNAL_SINGULARITY, 1));
        GASTRONOMIC_SINGULARITY = this.addItem(241, "singularity.gastronomic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        GALACTIC_SINGULARITY = this.addItem(242, "singularity.galactic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        NATURAL_SINGULARITY = this.addItem(243, "singularity.natural_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        MAGICAL_SINGULARITY = this.addItem(244, "singularity.magical_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        FANTASTIC_SINGULARITY = this.addItem(245, "singularity.fantastic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        CHEMICAL_SINGULARITY = this.addItem(246, "singularity.chemical_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        THERMIONIC_SINGULARITY = this.addItem(247, "singularity.thermionic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        PROTONIC_SINGULARITY = this.addItem(248, "singularity.protonic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        PLATONIC_SINGULARITY = this.addItem(249, "singularity.platonic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        CUBIC_SINGULARITY = this.addItem(250, "singularity.cubic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        ASTRONOMIC_SINGULARITY = this.addItem(251, "singularity.astronomic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        TERRIFIC_SINGULARITY = this.addItem(252, "singularity.terrific_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        INTRINSIC_SINGULARITY = this.addItem(253, "singularity.intrinsic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        QUADRATIC_SINGULARITY = this.addItem(254, "singularity.quadratic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        COSMIC_SINGULARITY = this.addItem(255, "singularity.cosmic_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));
        IMPOSSIBLE_SINGULARITY = this.addItem(256, "singularity.impossible_singularity")
                .addComponents(new CosmicRenderItemBehavior(() -> NTTextures.COMBINED_SINGULARITY, 1));

        //257~

        //300~
        GOOWARE_BOARD = this.addItem(300, "board.gooware");
        OPTICAL_BOARD = this.addItem(301, "board.optical");
        SPINTRONIC_BOARD = this.addItem(302, "board.spintronic");
        GOOWARE_CIRCUIT = this.addItem(303, "circuit_board.gooware");
        OPTICAL_CIRCUIT = this.addItem(304, "circuit_board.optical");
        SPINTRONIC_CIRCUIT = this.addItem(305, "circuit_board.spintronic");

        //ID 351~
        //  Biological Components
        ELECTROCHEMICAL_GRADIENT_RECORDER = this.addItem(351, "biological.components.electrochemical_gradient_recorder");
        ULTRA_MICRO_PHASE_SEPARATOR = this.addItem(352, "biological.components.ultra_micro_phase_separator");
        QUANTUM_TUNNELING_MICROTUBULE = this.addItem(353, "biological.components.quantum_tunneling_microtubule");
        HYPERRIBOSOME = this.addItem(354, "biological.components.hyperribosome");
        NEUTRON_ABSORBING_PROTEIN = this.addItem(355, "biological.components.neutron_absorbing_protein");
        SUPEREXCITED_CONDUCTIVE_POLYMER = this.addItem(356, "biological.components.superexcited_conductive_polymer");
        DNA_ENCODER = this.addItem(357, "biological.components.dna_encoder");
        DNA_DECODER = this.addItem(358, "biological.components.dna_decoder");
        DNA_DECODE_ENCODER = this.addItem(359, "biological.components.dna_decode_encoder");
        ORDINARY_ALGAE = this.addItem(360, "algae.ordinary_algae");
        RED_ALGA = this.addItem(361, "algae.red_alga");
        GREEN_ALGA = this.addItem(362, "algae.green_alga");
        CHRYSOPHYCEAE = this.addItem(363, "algae.chrysophyceae");
        BROWN_ALGA = this.addItem(364, "algae.brown_alga");

        //400~
        BIO_PROCESSING_UNIT = this.addItem(400,"item.bio_processing_unit");
        ADVANCED_PROCESSOR_BOARD = this.addItem(401,"item.advanced_processor_board");
        ADVANCED_BOARD = this.addItem(402,"item.advanced_board");
        QUANTUM_PROCESSOR_BOARD = this.addItem(403,"item.quantum_processor_board");
        QUANTUM_BOARD = this.addItem(404,"item.quantum_board");
        NANO_PROCESSOR_BOARD = this.addItem(405,"item.nano_processor_board");
        NANO_BOARD = this.addItem(406,"item.nano_board");
        EXOTIC_BOARD = this.addItem(407, "item.exotic_board");
        EXOTIC_PROCESSOR_BOARD = this.addItem(408, "item.exotic_processor_board");


        //ID 425~
        SPINTRONIC_CAPACITOR = this.addItem(425, "component.spintronic_smd.capacitor");
        SPINTRONIC_DIODE = this.addItem(426, "component.spintronic_smd.diode");
        SPINTRONIC_RESISTOR = this.addItem(427, "component.spintronic_smd.resistor");
        SPINTRONIC_TRANSISTOR = this.addItem(428, "component.spintronic_smd.transistor");
        SPINTRONIC_INDUCTOR = this.addItem(429, "component.spintronic_smd.inductor");

        COSMIC_TRANSISTOR = this.addItem(430, "component.cosmic_smd.transistor");
        COSMIC_RESISTOR = this.addItem(431, "component.cosmic_smd.resistor");
        COSMIC_CAPACITOR = this.addItem(432, "component.cosmic_smd.capacitor");
        COSMIC_DIODE = this.addItem(433, "component.cosmic_smd.diode");
        COSMIC_INDUCTOR = this.addItem(434, "component.cosmic_smd.inductor");

        SUPRACAUSAL_TRANSISTOR = this.addItem(435, "component.supracausal_smd.transistor")
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        SUPRACAUSAL_RESISTOR = this.addItem(436, "component.supracausal_smd.resistor")
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        SUPRACAUSAL_CAPACITOR = this.addItem(437, "component.supracausal_smd.capacitor")
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        SUPRACAUSAL_DIODE = this.addItem(438, "component.supracausal_smd.diode")
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        SUPRACAUSAL_INDUCTOR = this.addItem(439, "component.supracausal_smd.inductor")
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));

        //500~
        //  Cosmic Components
        COSMIC_INFORMATION_MODULE = this.addItem(500, "component.cosmic.information_module")
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        HOLOGRAPHIC_INFORMATION_IMC = this.addItem(501, "component.cosmic.holographic_imc")
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        CLADDED_OPTICAL_FIBER_CORE = this.addItem(502, "component.cosmic.cladded_optical_fiber_core");
        CLOSED_TIMELIKE_CURVE_COMPUTATIONAL_UNIT = this.addItem(503, "component.cosmic.closed_timelike_curve_computational_unit");
        CLOSED_TIMELIKE_CURVE_GUIDANCE_UNIT = this.addItem(504, "component.cosmic.closed_timelike_curve_guidance_unit");


        //  Supracausal Components
        SPACETIME_CONDENSER = this.addItem(510, "component.supracausal.spacetime_condenser")
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        LIGHT_CONE_MODULE = this.addItem(511, "component.supracausal.light_cone_module")
                .addComponents(new HaloRenderItemBehavior(10, 0xFF000000, () -> NTTextures.HALO, true));
        EXCITATION_MAINTAINER = this.addItem(512, "component.spintronic.excitation_maintainer");





        //1200~
        WRAP_CIRCUIT_ULV = this.addItem(1200, "wrapcircuit.ulv");
        WRAP_CIRCUIT_LV = this.addItem(1201, "wrapcircuit.lv");
        WRAP_CIRCUIT_MV = this.addItem(1202, "wrapcircuit.mv");
        WRAP_CIRCUIT_HV = this.addItem(1203, "wrapcircuit.hv");
        WRAP_CIRCUIT_EV = this.addItem(1204, "wrapcircuit.ev");
        WRAP_CIRCUIT_IV = this.addItem(1205, "wrapcircuit.iv");
        WRAP_CIRCUIT_LUV = this.addItem(1206, "wrapcircuit.luv");
        WRAP_CIRCUIT_ZPM = this.addItem(1207, "wrapcircuit.zpm");
        WRAP_CIRCUIT_UV = this.addItem(1208, "wrapcircuit.uv");
        WRAP_CIRCUIT_UHV = this.addItem(1209, "wrapcircuit.uhv");
        WRAP_CIRCUIT_UEV = this.addItem(1210, "wrapcircuit.uev");
        WRAP_CIRCUIT_UIV = this.addItem(1211, "wrapcircuit.uiv");
        WRAP_CIRCUIT_UXV = this.addItem(1212, "wrapcircuit.uxv");
        WRAP_CIRCUIT_OPV = this.addItem(1213, "wrapcircuit.opv");
        WRAP_CIRCUIT_MAX = this.addItem(1214, "wrapcircuit.nax");

        WRAP_COATED_BOARD = this.addItem(1215, "wrap.coated_board");
        WRAP_PHENOLIC_BOARD = this.addItem(1216, "wrap.phenolic_board");
        WRAP_PLASTIC_BOARD = this.addItem(1217, "wrap.plastic_board");
        WRAP_EPOXY_BOARD = this.addItem(1218, "wrap.epoxy_board");
        WRAP_FIBER_BOARD = this.addItem(1219, "wrap.fiber_board");
        WRAP_MULTILAYER_FIBER_BOARD = this.addItem(1220, "wrap.multilayer_fiber_board");
        WRAP_WETWARE_BOARD = this.addItem(1221, "wrap.wetware_board");

        WRAP_BASIC_CIRCUIT_BOARD = this.addItem(1222, "wrap.basic_circuit_board");
        WRAP_GOOD_CIRCUIT_BOARD = this.addItem(1223, "wrap.good_circuit_board");
        WRAP_PLASTIC_CIRCUIT_BOARD = this.addItem(1224, "wrap.plastic_circuit_board");
        WRAP_ADVANCED_CIRCUIT_BOARD = this.addItem(1225, "wrap.advanced_circuit_board");
        WRAP_EXTREME_CIRCUIT_BOARD = this.addItem(1226, "wrap.extreme_circuit_board");
        WRAP_ELITE_CIRCUIT_BOARD = this.addItem(1227, "wrap.elite_circuit_board");
        WRAP_WETWARE_CIRCUIT_BOARD = this.addItem(1228, "wrap.wetware_circuit_board");

        WRAP_SMD_CAPACITOR = this.addItem(1229, "wrap.smd_capacitor");
        WRAP_SMD_DIODE = this.addItem(1230, "wrap.smd_diode");
        WRAP_SMD_RESISTOR = this.addItem(1231, "wrap.smd_resistor");
        WRAP_SMD_TRANSISTOR = this.addItem(1232, "wrap.smd_transistor");
        WRAP_SMD_INDUCTOR = this.addItem(1233, "wrap.smd_inductor");
        WRAP_ADVANCED_SMD_CAPACITOR = this.addItem(1234, "wrap.advanced_smd_capacitor");
        WRAP_ADVANCED_SMD_DIODE = this.addItem(1235, "wrap.advanced_smd_diode");
        WRAP_ADVANCED_SMD_RESISTOR = this.addItem(1236, "wrap.advanced_smd_resistor");
        WRAP_ADVANCED_SMD_TRANSISTOR = this.addItem(1237, "wrap.advanced_smd_transistor");
        WRAP_ADVANCED_SMD_INDUCTOR = this.addItem(1238, "wrap.advanced_smd_inductor");

        WRAP_NEURO_PROCESSOR = this.addItem(1239, "wrap.neuro_processor");
        WRAP_STEM_CELLS = this.addItem(1240, "wrap.stem_cells");
        WRAP_ENGRAVED_LAPOTRON_CHIP = this.addItem(1241, "wrap.engraved_lapotron_chip");
        WRAP_ENGRAVED_CRYSTAL_CHIP = this.addItem(1242, "wrap.engraved_crystal_chip");
        WRAP_CRYSTAL_CENTRAL_PROCESSING_UNIT = this.addItem(1243, "wrap.crystal_central_processing_unit");

        WRAP_CENTRAL_PROCESSING_UNIT = this.addItem(1244, "wrap.central_processing_unit");
        WRAP_RANDOM_ACCESS_MEMORY = this.addItem(1245, "wrap.random_access_memory");
        WRAP_INTEGRATED_LOGIC_CIRCUIT = this.addItem(1246, "wrap.integrated_logic_circuit");
        WRAP_NANO_CENTRAL_PROCESSING_UNIT = this.addItem(1247, "wrap.nano_central_processing_unit");
        WRAP_QUBIT_CENTRAL_PROCESSING_UNIT = this.addItem(1248, "wrap.qubit_central_processing_unit");
        WRAP_SIMPLE_SYSTEM_ON_CHIP = this.addItem(1249, "wrap.simple_system_on_chip");
        WRAP_SYSTEM_ON_CHIP = this.addItem(1250, "wrap.system_on_chip");
        WRAP_ADVANCED_SYSTEM_ON_CHIP = this.addItem(1251, "wrap.advanced_system_on_chip");
        WRAP_HIGHLY_ADVANCED_SOC = this.addItem(1252, "wrap.highly_advanced_system_on_chip");
        WRAP_NAND_MEMORY_CHIP = this.addItem(1253, "wrap.nand_memory_chip");
        WRAP_NOR_MEMORY_CHIP = this.addItem(1254, "wrap.nor_memory_chip");
        WRAP_ULTRA_LOW_PIC = this.addItem(1255, "wrap.ultra_low_pic");
        WRAP_LOW_PIC = this.addItem(1256, "wrap.low_pic");
        WRAP_PIC = this.addItem(1257, "wrap.pic");
        WRAP_HIGH_PIC = this.addItem(1258, "wrap.high_pic");
        WRAP_ULTRA_HIGH_PIC = this.addItem(1259, "wrap.ultra_high_pic");

        WRAP_NANO_PIC = this.addItem(1260, "wrap.nano_pic");
        WRAP_PICO_PIC = this.addItem(1261, "wrap.pico_pic");
        WRAP_FEMTO_PIC = this.addItem(1262, "wrap.femto_pic");

        WRAP_RAW_EXPOSED_OPTICAL_CHIP = this.addItem(1263, "wrap.optically_compatible_memory");
        WRAP_OPTICALLY_COMPATIBLE_MEMORY = this.addItem(1264, "wrap.optically_perfected_cpu");
        WRAP_OPTICALLY_PERFECTED_CPU = this.addItem(1265, "wrap.raw_exposed_optical_chip");
        WRAP_OPTICAL_CPU_CONTAINMENT_HOUSING = this.addItem(1266, "wrap.optical_cpu_containment_housing");
        WRAP_OPTICAL_CIRCUIT_BOARD = this.addItem(1267, "wrap.optical_circuit_board");
        WRAP_OPTICAL_CAPACITOR = this.addItem(1268, "wrap.optical_smd.capacitor");
        WRAP_OPTICAL_DIODE = this.addItem(1269, "wrap.optical_smd.diode");
        WRAP_OPTICAL_RESISTOR = this.addItem(1270, "wrap.optical_smd.inductor");
        WRAP_OPTICAL_TRANSISTOR = this.addItem(1271, "wrap.optical_smd.resistor");
        WRAP_OPTICAL_INDUCTOR = this.addItem(1272, "wrap.optical_smd.transistor");

        WRAP_LIVING_CRYSTAL_CHIP = this.addItem(1273, "wrap.living_crystal_chip");
        WRAP_LIVING_BIO_CHIP = this.addItem(1274, "wrap.gooware.living_bio_chip");
        WRAP_BIOCELLS = this.addItem(1275, "wrap.gooware.biocells");
        WRAP_GOOWARE_BOARD = this.addItem(1276, "wrap.board.gooware");
        WRAP_GOOWARE_CIRCUIT = this.addItem(1277, "wrap.circuit_board.gooware");
        WRAP_CRYSTAL_SYSTEM_ON_CHIP = this.addItem(1278, "wrap.crystal_system_on_chip");
        WRAP_CRYSTAL_ADVANCED_RAW_CHIP = this.addItem(1279, "wrap.crystal.advanced_raw_chip");

        WRAP_SPINTRONIC_CAPACITOR = this.addItem(1280, "wrap.spintronic_smd.capacitor");
        WRAP_SPINTRONIC_DIODE = this.addItem(1281, "wrap.spintronic_smd.diode");
        WRAP_SPINTRONIC_RESISTOR = this.addItem(1282, "wrap.spintronic_smd.resistor");
        WRAP_SPINTRONIC_TRANSISTOR = this.addItem(1283, "wrap.spintronic_smd.transistor");
        WRAP_SPINTRONIC_INDUCTOR = this.addItem(1284, "wrap.spintronic_smd.inductor");

        WRAP_COSMIC_INFORMATION_MODULE = this.addItem(1285, "wrap.cosmic.information_module");
        WRAP_HOLOGRAPHIC_INFORMATION_IMC = this.addItem(1286, "wrap.cosmic.holographic_imc");
        WRAP_SPACETIME_CONDENSER = this.addItem(1287, "wrap.supracausal.spacetime_condenser");
        WRAP_LIGHT_CONE_MODULE = this.addItem(1288, "wrap.supracausal.light_cone_module");

        WRAP_COSMIC_TRANSISTOR = this.addItem(1289, "wrap.cosmic_smd.transistor");
        WRAP_COSMIC_RESISTOR = this.addItem(1290, "wrap.cosmic_smd.resistor");
        WRAP_COSMIC_CAPACITOR = this.addItem(1291, "wrap.cosmic_smd.capacitor");
        WRAP_COSMIC_DIODE = this.addItem(1292, "wrap.cosmic_smd.diode");
        WRAP_COSMIC_INDUCTOR = this.addItem(1293, "wrap.cosmic_smd.inductor");

        WRAP_SUPRACAUSAL_TRANSISTOR = this.addItem(1294, "wrap.supracausal_smd.transistor");
        WRAP_SUPRACAUSAL_RESISTOR = this.addItem(1295, "wrap.supracausal_smd.resistor");
        WRAP_SUPRACAUSAL_CAPACITOR = this.addItem(1296, "wrap.supracausal_smd.capacitor");
        WRAP_SUPRACAUSAL_DIODE = this.addItem(1297, "wrap.supracausal_smd.diode");
        WRAP_SUPRACAUSAL_INDUCTOR = this.addItem(1298, "wrap.supracausal_smd.inductor");

        WRAP_ATTO_PIC = this.addItem(1299, "wrap.atto_pic");
        WRAP_ZEPTO_PIC = this.addItem(1300, "wrap.zepto_pic");
        WRAP_UHASOC_CHIP = this.addItem(1301, "wrap.uhasoc");
        WRAP_PHASE_CHANGE_MEMORY = this.addItem(1302, "wrap.phase_change_memory");
        WRAP_OPTICAL_NOR_MEMORY_CHIP = this.addItem(1303, "wrap.optical_nor_memory_chip");
        WRAP_SPIN_TRANSFER_TORQUE_MEMORY = this.addItem(1304, "wrap.spin_transfer_torque_memory");
        WRAP_SPINTRONIC_NAND_MEMORY_CHIP = this.addItem(1305, "wrap.spintronic_nand_memory_chip");

        WRAP_OPTICAL_BOARD = this.addItem(1306, "wrap.board.optical");
        WRAP_SPINTRONIC_BOARD = this.addItem(1307, "wrap.board.spintronic");
        WRAP_OPTICAL_CIRCUIT = this.addItem(1308, "wrap.circuit_board.optical");
        WRAP_SPINTRONIC_CIRCUIT = this.addItem(1309, "wrap.circuit_board.spintronic");

        WRAP_COSMIC_CPU_CHIP = this.addItem(1310, "wrap.cosmic_cpu_chip");
        WRAP_COSMIC_MEMORY_CHIP = this.addItem(1311, "wrap.cosmic_memory_chip");
        WRAP_CRYSTAL_OPTICAL_RAW_CHIP = this.addItem(1312,"wrap.crystal.optical_raw_chip");
        WRAP_CRYSTAL_SPINTRONIC_RAW_CHIP = this.addItem(1313,"wrap.crystal.spintronic_raw_chip");
        WRAP_CRYSTAL_COSMIC_RAW_CHIP = this.addItem(1314,"wrap.crystal.cosmic_raw_chip");
        WRAP_SUPRACAUSAL_MEMORY_CHIP = this.addItem(1315,"wrap.supracausal_memory_chip");



        //1400~
        NANO_PIC_WAFER = this.addItem(1400, "wafer.nano_pic");
        PICO_PIC_WAFER = this.addItem(1401, "wafer.pico_pic");
        FEMTO_PIC_WAFER = this.addItem(1402, "wafer.femto_pic");
        NANO_PIC = this.addItem(1403, "plate.nano_pic");
        PICO_PIC = this.addItem(1404, "plate.pico_pic");
        FEMTO_PIC = this.addItem(1405, "plate.femto_pic");
        ATTO_PIC_WAFER = this.addItem(1406, "wafer.atto_pic");
        ATTO_PIC = this.addItem(1407, "plate.atto_pic");
        ZEPTO_PIC_WAFER = this.addItem(1408, "wafer.zepto_pic");
        ZEPTO_PIC = this.addItem(1409, "plate.zepto_pic");
        UHASOC_WAFER = this.addItem(1410, "wafer.uhasoc");
        UHASOC_CHIP = this.addItem(1411, "plate.uhasoc");
        PHASE_CHANGE_MEMORY = this.addItem(1412, "plate.phase_change_memory");
        OPTICAL_NOR_MEMORY_CHIP = this.addItem(1413, "plate.optical_nor_memory_chip");
        SPIN_TRANSFER_TORQUE_MEMORY = this.addItem(1414, "plate.spin_transfer_torque_memory");
        SPINTRONIC_NAND_MEMORY_CHIP = this.addItem(1415, "plate.spintronic_nand_memory_chip");
        UNTREATED_COSMIC_CPU = this.addItem(1416, "wafer.untreated_cosmic_cpu");
        COSMIC_CPU = this.addItem(1417, "plate.cosmic_cpu");
        COSMIC_CPU_CHIP = this.addItem(1418, "plate.cosmic_cpu_chip");
        COSMIC_MEMORY_CHIP = this.addItem(1419, "plate.cosmic_memory_chip");
        SUPRACAUSAL_MEMORY_CHIP = this.addItem(1420, "plate.supracausal_memory_chip");

        //2000~
        PROSPECTOR_ZPM = this.addItem(2000, "prospector.zpm")
                .addComponents(new IItemComponent[]{ElectricStats.createElectricItem(4_000_000_000L, 7),
                        new ProspectorScannerBehavior(7, 7)})
                .setMaxStackSize(1)
                .setCreativeTabs(GregTechAPI.TAB_GREGTECH_TOOLS);

        PROSPECTOR_UV = this.addItem(2001, "prospector.uv")
                .addComponents(ElectricStats.createElectricItem(16_000_000_000L, 8),
                        new ProspectorScannerBehavior(9, 8))
                .setMaxStackSize(1)
                .setCreativeTabs(GregTechAPI.TAB_GREGTECH_TOOLS);

        PROSPECTOR_UIV = this.addItem(2002, "prospector.uiv")
                .addComponents(ElectricStats.createElectricItem(256_000_000_000L, 9),
                        new ProspectorScannerBehavior(11, 9))// todo
                .setMaxStackSize(1)
                .setCreativeTabs(GregTechAPI.TAB_GREGTECH_TOOLS);

        //2400~
        RESEARCH_DATA_CARD = this.addItem(2400,"research_data_card")
                .addComponents(new DataItemResearch());
        GOOWARE_RESEARCH_DATA_CARD = this.addItem(2401,"gooware_research_data_card")
                .addComponents(new DataItemResearch());
        OPTICAL_RESEARCH_DATA_CARD = this.addItem(2402,"optical_research_data_card")
                .addComponents(new DataItemResearch());
        SPINTRONIC_RESEARCH_DATA_CARD  = this.addItem(2403,"spintronic_research_data_card")
                .addComponents(new DataItemResearch());
        COSMIC_RESEARCH_DATA_CARD = this.addItem(2404,"cosmic_research_data_card")
                .addComponents(new DataItemResearch());
        SUPRA_CAUSAL_RESEARCH_DATA_CARD = this.addItem(2405,"supra_causal_research_data_card")
                .addComponents(new DataItemResearch());
        SUPRA_CHRONAL_RESEARCH_DATA_CARD = this.addItem(2406,"supra_chronal_research_data_card")
                .addComponents(new DataItemResearch());
        SUPRA_DIMENSION_RESEARCH_DATA_CARD = this.addItem(2407,"supra_dimension_research_data_card")
                .addComponents(new DataItemResearch(), new CosmicRenderItemBehavior(() -> NTTextures.SUPRA_DIMENSION_RESEARCH_DATA_CARD_HALO, 1));















        DEBUG_STRUCTURE_WRITER = this.addItem(9999, "debug.structure_writer")
                .addComponents(StructureWriteBehavior.INSTANCE);
        DEBUG_STRUCTURE_BUILDER = this.addItem(10000, "debug.structure_builder")
                .addComponents(new MultiblockBuilderBehavior());




    }
}
