package com.onlyex.naxtech.client.renderer.texture;

import codechicken.lib.texture.TextureUtils;
import com.onlyex.naxtech.api.NTValues;
import com.onlyex.naxtech.api.utils.NTLog;
import com.onlyex.naxtech.api.utils.NTUtils;
import com.onlyex.naxtech.client.renderer.texture.custom.IsaMillRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.client.renderer.texture.Textures.iconRegisters;

public class NTTextures {
//public static final OrientedOverlayRenderer ;
/*
SimpleSidedCubeRenderer
SimpleOverlayRenderer
OrientedOverlayRenderer
*/
    //naxtech-

    //-casings
    public static NTOverlayRenderer PACKAGING_LINE = new NTOverlayRenderer("casings/solid/machine_packaging_line");
    public static NTOverlayRenderer ADVANCED_ASSEMBLY_LINE = new NTOverlayRenderer("casings/solid/advanced_assembly_line");
    public static NTOverlayRenderer QUANTUM_CONSTRAINT_CASING = new NTOverlayRenderer("casings/quantum_force_transformer_casing/quantum_constraint_casing");
    public static NTOverlayRenderer ADVANCED_INVAR_CASING = new NTOverlayRenderer("casings/solid/advanced_invar_casing");
    public static NTOverlayRenderer ADVANCED_ALUMINIUM_CASING = new NTOverlayRenderer("casings/solid/advanced_aluminium_casing");
    public static NTOverlayRenderer PRECISE_ASSEMBLER_CASING_MK1 = new NTOverlayRenderer("casings/solid/precise_assembler_casing_mk1");
    public static NTOverlayRenderer PRECISE_ASSEMBLER_CASING_MK2 = new NTOverlayRenderer("casings/solid/precise_assembler_casing_mk2");
    public static NTOverlayRenderer PRECISE_ASSEMBLER_CASING_MK3 = new NTOverlayRenderer("casings/solid/precise_assembler_casing_mk3");
    public static NTOverlayRenderer IRIDIUM_CASING = new NTOverlayRenderer("casings/solid/advanced_assembly_line");
    public static NTOverlayRenderer FLOTATION_CASING = new NTOverlayRenderer("casings/solid/flotation_casing");
    public static NTOverlayRenderer ISA_MILL_CASING = new NTOverlayRenderer("casings/solid/isa_mill_casing");

    //-multiparts
    public static NTOverlayRenderer MULTIPART_CATALYST_HATCH = new NTOverlayRenderer("multiparts/overlay_catalyst_hatch");
    public static NTOverlayRenderer MULTIPART_BUFFER_HATCH = new NTOverlayRenderer("multiparts/overlay_buffer_hatch");
    public static NTOverlayRenderer INF_WATER = new NTOverlayRenderer("multiparts/overlay_water");



    //gregtech-
    public static OrientedOverlayRenderer ISA_MILL_OVERLAY = new OrientedOverlayRenderer("multiblock/isa_mill");
    public static OrientedOverlayRenderer CRYOGENIC_REACTOR_OVERLAY = new OrientedOverlayRenderer("multiblock/cryogenic_reactor");
    public static OrientedOverlayRenderer CVD_UNIT_OVERLAY = new OrientedOverlayRenderer("multiblock/cvd_unit");
    public static OrientedOverlayRenderer CHEMICAL_PLANT_OVERLAY = new OrientedOverlayRenderer("multiblock/chemical_plant");



    public static IsaMillRenderer ISA_MILL = new IsaMillRenderer();

    public static TextureAtlasSprite HALO;
    public static TextureAtlasSprite HALO_NOISE;

    public static TextureAtlasSprite MASK_INGOT;
    public static TextureAtlasSprite ETERNAL_SINGULARITY;
    public static TextureAtlasSprite COMBINED_SINGULARITY;


    public static TextureAtlasSprite[] COSMIC;
    public static TextureAtlasSprite COSMIC_0;
    public static TextureAtlasSprite COSMIC_1;
    public static TextureAtlasSprite COSMIC_2;
    public static TextureAtlasSprite COSMIC_3;
    public static TextureAtlasSprite COSMIC_4;
    public static TextureAtlasSprite COSMIC_5;
    public static TextureAtlasSprite COSMIC_6;
    public static TextureAtlasSprite COSMIC_7;
    public static TextureAtlasSprite COSMIC_8;
    public static TextureAtlasSprite COSMIC_9;

    public static TextureAtlasSprite FORCE_FIELD;


    public static TextureAtlasSprite RESEARCH_PIPE_IN;
    public static TextureAtlasSprite RESEARCH_PIPE_SIDE;
    public static TextureAtlasSprite RESEARCH_PIPE_SIDE_OVERLAY;
    public static TextureAtlasSprite RESEARCH_PIPE_SIDE_OVERLAY_ACTIVE;

    public NTTextures() {}

    public static void register(TextureMap textureMap) {

        HALO = textureMap.registerSprite(NTUtils.naxId("items/halo"));
        HALO_NOISE = textureMap.registerSprite(NTUtils.naxId("items/halo_noise"));

        MASK_INGOT = textureMap.registerSprite(NTUtils.naxId( "items/mask_ingot"));

        ETERNAL_SINGULARITY = textureMap.registerSprite(NTUtils.naxId( "items/eternal_singularity_mask"));
        COMBINED_SINGULARITY = textureMap.registerSprite(NTUtils.naxId( "items/combined_singularity_mask"));

        COSMIC_0 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_0"));
        COSMIC_1 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_1"));
        COSMIC_2 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_2"));
        COSMIC_3 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_3"));
        COSMIC_4 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_4"));
        COSMIC_5 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_5"));
        COSMIC_6 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_6"));
        COSMIC_7 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_7"));
        COSMIC_8 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_8"));
        COSMIC_9 = textureMap.registerSprite(NTUtils.naxId("shader/cosmic_9"));

        FORCE_FIELD = textureMap.registerSprite(NTUtils.naxId("blocks/force_field"));

        COSMIC = new TextureAtlasSprite[] {
                COSMIC_0,
                COSMIC_1,
                COSMIC_2,
                COSMIC_3,
                COSMIC_4,
                COSMIC_5,
                COSMIC_6,
                COSMIC_7,
                COSMIC_8,
                COSMIC_9
        };

        //TODO 材质
        RESEARCH_PIPE_IN = textureMap.registerSprite(NTUtils.naxId("blocks/pipe/pipe_research_in"));
        RESEARCH_PIPE_SIDE = textureMap.registerSprite(NTUtils.naxId("blocks/pipe/pipe_research_side"));
        RESEARCH_PIPE_SIDE_OVERLAY = textureMap.registerSprite(NTUtils.naxId("blocks/pipe/pipe_research_side_overlay"));
        RESEARCH_PIPE_SIDE_OVERLAY_ACTIVE = textureMap.registerSprite(NTUtils.naxId("blocks/pipe/pipe_research_side_overlay_active"));
    }
    public static void preInit() {TextureUtils.addIconRegister(NTTextures::register);}
}
