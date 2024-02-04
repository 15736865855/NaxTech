package com.onlyex.naxtech.common.block;

import com.onlyex.naxtech.client.renderer.pipe.ResearchPipeRenderer;
import com.onlyex.naxtech.common.block.blocks.*;
import com.onlyex.naxtech.common.block.blocks.assembly.BlockAdvancedAssemblyLineCasing;
import com.onlyex.naxtech.common.block.blocks.assembly.BlockComponentAssemblyLineCasing;
import com.onlyex.naxtech.common.block.blocks.dimension.BlockDimensionCasing;
import com.onlyex.naxtech.common.block.blocks.dimension.BlockDimensionWireCoil;
import com.onlyex.naxtech.common.block.blocks.machinel.BlockMachinelCasing;
import com.onlyex.naxtech.common.block.blocks.machinel.BlockMachinelCasingA;
import com.onlyex.naxtech.common.block.blocks.machinel.BlockMachinelCasingB;
import com.onlyex.naxtech.common.block.blocks.quantum.BlockQuantumForceTransformerCasing;
import com.onlyex.naxtech.common.block.blocks.quantum.BlockQuantumForceTransformerGlassCasing;
import com.onlyex.naxtech.common.block.wood.BlockPineLeaves;
import com.onlyex.naxtech.common.block.wood.BlockPineLog;
import com.onlyex.naxtech.common.block.wood.BlockPineSapling;
import com.onlyex.naxtech.common.pipelike.research.ResearchPipeType;
import com.onlyex.naxtech.common.pipelike.research.block.*;
import com.onlyex.naxtech.common.pipelike.research.tile.*;
import gregtech.client.model.SimpleStateMapper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.onlyex.naxtech.api.utils.NTUtils.naxId;
import static gregtech.common.blocks.MetaBlocks.statePropertiesToString;

public class NTMetaBlocks {
    //dimension
    public static BlockDimensionWireCoil NT_WIRE_COIL;//线圈
    public static BlockDimensionCasing DIMENSION_CASING;


    //glass
    public static BlockBoronSilicateGlassCasing BORON_SILICATE_GLASS_CASING;//强化硅酸盐硼玻璃-1

    //control
    public static BlockControlCasing CONTROL_CASING;//控制外壳-1

    //machinel
    public static BlockMachinelCasing MACHINE_CASING;//机械外壳-1
    public static BlockMachinelCasingA MACHINE_CASING_A;//机械外壳-2
    public static BlockMachinelCasingB MACHINE_CASING_B;//机械外壳-3

    //pipeline
    public static BlockPipelinelCasing PIPELINE_CASING;//管道外壳-1

    //quantum
    public static BlockQuantumForceTransformerCasing QUANTUM_FORCE_TRANSFORMER_CASING;//量子操纵者外壳
    public static BlockQuantumForceTransformerGlassCasing QUANTUM_FORCE_TRANSFORMER_GLASS_CASING;//量子操纵者玻璃

    //Assembly
    public static BlockComponentAssemblyLineCasing COMPONENT_ASSEMBLY_LINE_CASING;//部件装配线外壳
    public static BlockAdvancedAssemblyLineCasing ADVANCED_ASSEMBLY_LINE_CASING;//进阶装配线外壳

    public static BlockActiveMultiblockCasing ACTIVE_MULTIBLOCK_CASING;//活跃多块外壳
    public static BlockPackagingline PACKAGING_LINE;//封装线外壳
    public static BlockPCBFactoryCasing PCB_FACTORY_CASING;//PCB
    public static BlockADVFactoryCasing ADV_FACTORY_CASING;//ADV
    public static BlockDHPCAFactoryCasing DHPCA_FACTORY_CASING;//DHPCA



    public static final BlockResearchPipe[] RESEARCH_PIPES = new BlockResearchPipe[ResearchPipeType.values().length];
    public static final BlockGOResearchPipe[] GO_RESEARCH_PIPES = new BlockGOResearchPipe[ResearchPipeType.values().length];
    public static final BlockOPResearchPipe[] OP_RESEARCH_PIPES = new BlockOPResearchPipe[ResearchPipeType.values().length];
    public static final BlockSPResearchPipe[] SP_RESEARCH_PIPES = new BlockSPResearchPipe[ResearchPipeType.values().length];
    public static final BlockCOResearchPipe[] CO_RESEARCH_PIPES = new BlockCOResearchPipe[ResearchPipeType.values().length];
    public static final BlockSCAResearchPipe[] SCA_RESEARCH_PIPES = new BlockSCAResearchPipe[ResearchPipeType.values().length];
    public static final BlockSCHResearchPipe[] SCH_RESEARCH_PIPES = new BlockSCHResearchPipe[ResearchPipeType.values().length];
    public static final BlockSDIResearchPipe[] SDI_RESEARCH_PIPES = new BlockSDIResearchPipe[ResearchPipeType.values().length];

    public static final BlockPineLeaves PINE_LEAVES = new BlockPineLeaves();
    public static final BlockPineLog PINE_LOG = new BlockPineLog();
    public static final BlockPineSapling PINE_SAPLING = new BlockPineSapling();
    private NTMetaBlocks() {}
    public static void init() {

        for (ResearchPipeType type : ResearchPipeType.values()) {
            RESEARCH_PIPES[type.ordinal()] = new BlockResearchPipe(type);
            RESEARCH_PIPES[type.ordinal()].setRegistryName(String.format("research_pipe_%s", type.getName()));
            RESEARCH_PIPES[type.ordinal()].setTranslationKey(String.format("research_pipe_%s", type.getName()));
        }
        for (ResearchPipeType type : ResearchPipeType.values()) {
            GO_RESEARCH_PIPES[type.ordinal()] = new BlockGOResearchPipe(type);
            GO_RESEARCH_PIPES[type.ordinal()].setRegistryName(String.format("go_research_pipe_%s", type.getName()));
            GO_RESEARCH_PIPES[type.ordinal()].setTranslationKey(String.format("go_research_pipe_%s", type.getName()));
        }
        for (ResearchPipeType type : ResearchPipeType.values()) {
            OP_RESEARCH_PIPES[type.ordinal()] = new BlockOPResearchPipe(type);
            OP_RESEARCH_PIPES[type.ordinal()].setRegistryName(String.format("op_research_pipe_%s", type.getName()));
            OP_RESEARCH_PIPES[type.ordinal()].setTranslationKey(String.format("op_research_pipe_%s", type.getName()));
        }
        for (ResearchPipeType type : ResearchPipeType.values()) {
            SP_RESEARCH_PIPES[type.ordinal()] = new BlockSPResearchPipe(type);
            SP_RESEARCH_PIPES[type.ordinal()].setRegistryName(String.format("sp_research_pipe_%s", type.getName()));
            SP_RESEARCH_PIPES[type.ordinal()].setTranslationKey(String.format("sp_research_pipe_%s", type.getName()));
        }
        for (ResearchPipeType type : ResearchPipeType.values()) {
            CO_RESEARCH_PIPES[type.ordinal()] = new BlockCOResearchPipe(type);
            CO_RESEARCH_PIPES[type.ordinal()].setRegistryName(String.format("co_research_pipe_%s", type.getName()));
            CO_RESEARCH_PIPES[type.ordinal()].setTranslationKey(String.format("co_research_pipe_%s", type.getName()));
        }
        for (ResearchPipeType type : ResearchPipeType.values()) {
            SCA_RESEARCH_PIPES[type.ordinal()] = new BlockSCAResearchPipe(type);
            SCA_RESEARCH_PIPES[type.ordinal()].setRegistryName(String.format("sca_research_pipe_%s", type.getName()));
            SCA_RESEARCH_PIPES[type.ordinal()].setTranslationKey(String.format("sca_research_pipe_%s", type.getName()));
        }
        for (ResearchPipeType type : ResearchPipeType.values()) {
            SCH_RESEARCH_PIPES[type.ordinal()] = new BlockSCHResearchPipe(type);
            SCH_RESEARCH_PIPES[type.ordinal()].setRegistryName(String.format("sch_research_pipe_%s", type.getName()));
            SCH_RESEARCH_PIPES[type.ordinal()].setTranslationKey(String.format("sch_research_pipe_%s", type.getName()));
        }
        for (ResearchPipeType type : ResearchPipeType.values()) {
            SDI_RESEARCH_PIPES[type.ordinal()] = new BlockSDIResearchPipe(type);
            SDI_RESEARCH_PIPES[type.ordinal()].setRegistryName(String.format("sdi_research_pipe_%s", type.getName()));
            SDI_RESEARCH_PIPES[type.ordinal()].setTranslationKey(String.format("sdi_research_pipe_%s", type.getName()));
        }


        NT_WIRE_COIL = new BlockDimensionWireCoil();
        NT_WIRE_COIL.setRegistryName("wire_coil");
        BORON_SILICATE_GLASS_CASING = new BlockBoronSilicateGlassCasing();
        BORON_SILICATE_GLASS_CASING.setRegistryName("boron_silicate_glasses_casing");
        CONTROL_CASING = new BlockControlCasing();
        CONTROL_CASING.setRegistryName("control_casing");
        MACHINE_CASING = new BlockMachinelCasing();
        MACHINE_CASING.setRegistryName("machine_casing");
        PACKAGING_LINE = new BlockPackagingline();
        PACKAGING_LINE.setRegistryName("packaging_line");
        COMPONENT_ASSEMBLY_LINE_CASING = new BlockComponentAssemblyLineCasing();
        COMPONENT_ASSEMBLY_LINE_CASING.setRegistryName("component_assembly_line_casing");
        ADVANCED_ASSEMBLY_LINE_CASING = new BlockAdvancedAssemblyLineCasing();
        ADVANCED_ASSEMBLY_LINE_CASING.setRegistryName("advanced_assembly_line_casing");
        PIPELINE_CASING = new BlockPipelinelCasing();
        PIPELINE_CASING.setRegistryName("pipelinel_casing");
        ACTIVE_MULTIBLOCK_CASING = new BlockActiveMultiblockCasing();
        ACTIVE_MULTIBLOCK_CASING.setRegistryName("active_multiblock_casing");
        QUANTUM_FORCE_TRANSFORMER_CASING = new BlockQuantumForceTransformerCasing();
        QUANTUM_FORCE_TRANSFORMER_CASING.setRegistryName("quantum_force_transformer_casing");
        QUANTUM_FORCE_TRANSFORMER_GLASS_CASING = new BlockQuantumForceTransformerGlassCasing();
        QUANTUM_FORCE_TRANSFORMER_GLASS_CASING.setRegistryName("quantum_force_transformer_glasses_casing");
        MACHINE_CASING_A = new BlockMachinelCasingA();
        MACHINE_CASING_A.setRegistryName("machine_casing_a");
        MACHINE_CASING_B = new BlockMachinelCasingB();
        MACHINE_CASING_B.setRegistryName("machine_casing_B");
        PCB_FACTORY_CASING = new BlockPCBFactoryCasing();
        PCB_FACTORY_CASING.setRegistryName("pcb_factory_casing");
        DIMENSION_CASING = new BlockDimensionCasing();
        DIMENSION_CASING.setRegistryName("dimension_casing");
        ADV_FACTORY_CASING = new BlockADVFactoryCasing();
        ADV_FACTORY_CASING.setRegistryName("adv_factory_casing");
        DHPCA_FACTORY_CASING = new BlockDHPCAFactoryCasing();
        DHPCA_FACTORY_CASING.setRegistryName("dhpca_factory_casing");

        registerTileEntity();
    }
    public static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityResearchPipe.class, naxId("research_pipe"));
        GameRegistry.registerTileEntity(TileEntityGOResearchPipe.class, naxId("go_research_pipe"));
        GameRegistry.registerTileEntity(TileEntityOPResearchPipe.class, naxId("op_research_pipe"));
        GameRegistry.registerTileEntity(TileEntitySPResearchPipe.class, naxId("sp_research_pipe"));
        GameRegistry.registerTileEntity(TileEntityCOResearchPipe.class, naxId("co_research_pipe"));
        GameRegistry.registerTileEntity(TileEntitySCAResearchPipe.class, naxId("sca_research_pipe"));
        GameRegistry.registerTileEntity(TileEntitySCHResearchPipe.class, naxId("sch_research_pipe"));
        GameRegistry.registerTileEntity(TileEntitySDIResearchPipe.class, naxId("sdi_research_pipe"));
    }
    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {

        for (BlockResearchPipe pipe : RESEARCH_PIPES)
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(pipe),
                    stack -> ResearchPipeRenderer.INSTANCE.getModelLocation());
        for (BlockGOResearchPipe pipe : GO_RESEARCH_PIPES)
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(pipe),
                    stack -> ResearchPipeRenderer.INSTANCE.getModelLocation());
        for (BlockOPResearchPipe pipe : OP_RESEARCH_PIPES)
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(pipe),
                    stack -> ResearchPipeRenderer.INSTANCE.getModelLocation());
        for (BlockSPResearchPipe pipe : SP_RESEARCH_PIPES)
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(pipe),
                    stack -> ResearchPipeRenderer.INSTANCE.getModelLocation());
        for (BlockCOResearchPipe pipe : CO_RESEARCH_PIPES)
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(pipe),
                    stack -> ResearchPipeRenderer.INSTANCE.getModelLocation());
        for (BlockSCAResearchPipe pipe : SCA_RESEARCH_PIPES)
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(pipe),
                    stack -> ResearchPipeRenderer.INSTANCE.getModelLocation());
        for (BlockSCHResearchPipe pipe : SCH_RESEARCH_PIPES)
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(pipe),
                    stack -> ResearchPipeRenderer.INSTANCE.getModelLocation());
        for (BlockSDIResearchPipe pipe : SDI_RESEARCH_PIPES)
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(pipe),
                    stack -> ResearchPipeRenderer.INSTANCE.getModelLocation());

        registerItemModel(CONTROL_CASING);
        registerItemModel(MACHINE_CASING);
        registerItemModel(PACKAGING_LINE);
        registerItemModel(COMPONENT_ASSEMBLY_LINE_CASING);
        registerItemModel(ADVANCED_ASSEMBLY_LINE_CASING);
        registerItemModel(PIPELINE_CASING);
        registerItemModel(QUANTUM_FORCE_TRANSFORMER_CASING);
        registerItemModel(MACHINE_CASING_A);
        registerItemModel(MACHINE_CASING_B);
        registerItemModel(PCB_FACTORY_CASING);
        registerItemModel(DIMENSION_CASING);
        registerItemModel(ADV_FACTORY_CASING);
        registerItemModel(PINE_LEAVES);
        registerItemModel(PINE_LOG);
        registerItemModel(PINE_SAPLING);
        registerItemModel(DHPCA_FACTORY_CASING);


        //  VariantActiveBlock Registry
        NT_WIRE_COIL.onModelRegister();
        BORON_SILICATE_GLASS_CASING.onModelRegister();
        ACTIVE_MULTIBLOCK_CASING.onModelRegister();
        QUANTUM_FORCE_TRANSFORMER_GLASS_CASING.onModelRegister();

    }

    @SideOnly(Side.CLIENT)
    public static void registerStateMappers() {

        IStateMapper normalStateMapper;
        normalStateMapper = new SimpleStateMapper(ResearchPipeRenderer.INSTANCE.getModelLocation());
        for (BlockResearchPipe pipe : RESEARCH_PIPES) {
            ModelLoader.setCustomStateMapper(pipe, normalStateMapper);
        }
        for (BlockGOResearchPipe pipe : GO_RESEARCH_PIPES) {
            ModelLoader.setCustomStateMapper(pipe, normalStateMapper);
        }
        for (BlockOPResearchPipe pipe : OP_RESEARCH_PIPES) {
            ModelLoader.setCustomStateMapper(pipe, normalStateMapper);
        }
        for (BlockSPResearchPipe pipe : SP_RESEARCH_PIPES) {
            ModelLoader.setCustomStateMapper(pipe, normalStateMapper);
        }
        for (BlockCOResearchPipe pipe : CO_RESEARCH_PIPES) {
            ModelLoader.setCustomStateMapper(pipe, normalStateMapper);
        }
        for (BlockSCAResearchPipe pipe : SCA_RESEARCH_PIPES) {
            ModelLoader.setCustomStateMapper(pipe, normalStateMapper);
        }
        for (BlockSCHResearchPipe pipe : SCH_RESEARCH_PIPES) {
            ModelLoader.setCustomStateMapper(pipe, normalStateMapper);
        }
        for (BlockSDIResearchPipe pipe : SDI_RESEARCH_PIPES) {
            ModelLoader.setCustomStateMapper(pipe, normalStateMapper);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            // noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(state.getProperties())));
        }
    }

}
