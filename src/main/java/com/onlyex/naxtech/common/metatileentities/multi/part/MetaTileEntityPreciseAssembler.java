package com.onlyex.naxtech.common.metatileentities.multi.part;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.onlyex.naxtech.api.NTAPI;
import com.onlyex.naxtech.api.block.impl.WrappedIntTier;
import com.onlyex.naxtech.api.capability.NTDataCode;
import com.onlyex.naxtech.api.pattern.NTTraceabilityPredicate;
import com.onlyex.naxtech.api.recipes.NTRecipeMaps;
import com.onlyex.naxtech.api.unification.NTMaterials;
import com.onlyex.naxtech.api.utils.NTUniverUtil;
import com.onlyex.naxtech.client.renderer.texture.NTTextures;
import com.onlyex.naxtech.common.metatileentities.MetaTileEntities;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

import static gregtech.api.GTValues.LuV;

public class MetaTileEntityPreciseAssembler extends MultiMapMultiblockController {

    private int CasingTier;

    List<IBlockState> ListCasing = NTAPI.MAP_PA_CASING.entrySet().stream()
            .sorted(Comparator.comparingInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

    List<IBlockState> ListInternalCasing = NTAPI.MAP_PA_INTERNAL_CASING.entrySet().stream()
            .sorted(Comparator.comparingInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

    int maxLeng = NTUniverUtil.maxLength(new ArrayList<List<IBlockState>>() {{
        add(ListCasing);
        add(ListInternalCasing);
    }});

    List<IBlockState> finalListCasing = NTUniverUtil.consistentList(ListCasing, maxLeng);
    List<IBlockState> finalListInternalCasing = NTUniverUtil.consistentList(ListInternalCasing, maxLeng);

    public MetaTileEntityPreciseAssembler(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, new RecipeMap[]{
                RecipeMaps.ASSEMBLER_RECIPES,
                NTRecipeMaps.PRECISE_ASSEMBLER_RECIPES
        });
        this.recipeMapWorkable = new PreciseAssemblerRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityPreciseAssembler(metaTileEntityId);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object CasingTier = context.get("PACasingTieredStats");
        Object InternalCasingTier = context.get("PAInternalCasingTieredStats");

        this.CasingTier = NTUniverUtil.getOrDefault(() -> CasingTier instanceof WrappedIntTier,
                () -> ((WrappedIntTier) CasingTier).getIntTier(), 0);

        this.CasingTier = NTUniverUtil.getOrDefault(() -> InternalCasingTier instanceof WrappedIntTier,
                () -> ((WrappedIntTier) InternalCasingTier).getIntTier(), 0);

        this.writeCustomData(NTDataCode.CHANNEL_4, buf -> buf.writeInt(this.CasingTier));
    }

    @Override
    public void update() {
        super.update();

        if (this.getWorld().isRemote && this.CasingTier == 0) {
            this.writeCustomData(NTDataCode.CHANNEL_7, buf -> {});
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("DDDDDDDDD", "F       F", "F       F", "F       F", "DDDDDDDDD")
                .aisle("CMMMMMMMC", "CGGGGGGGC", "CGGGGGGGC", "CGGGGGGGC", "DDDDDDDDD")
                .aisle("CMMMMMMMC", "C       C", "C       C", "C       C", "DDDDDDDDD")
                .aisle("CMMMMMMMC", "CGGGGGGGC", "CGGGGGGGC", "CGGGGGGGC", "DDDDDDDDD")
                .aisle("DDDDSDDDD", "F       F", "F       F", "F       F", "DDDDDDDDD")
                .where('S', selfPredicate())
                .where('C', NTTraceabilityPredicate.PA_CASING.get())
                .where('D', NTTraceabilityPredicate.PA_CASING.get()
                        .setMinGlobalLimited(42)
                        .or(autoAbilities()))
                .where('F', states(getFrameState()))
                .where('G', states(getGlassState()))
                .where('M', NTTraceabilityPredicate.PA_INTERNAL_CASING.get())
                .build();
    }//TODO 更换方块

    private static IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(NTMaterials.MARM200Steel).getBlock(NTMaterials.MARM200Steel);
    }

    private static IBlockState getGlassState() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS);
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.FUSION_REACTOR_OVERLAY;
    }

    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.recipeMapWorkable.isActive(), this.recipeMapWorkable.isWorkingEnabled());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return switch (this.CasingTier) {
            case (1) -> NTTextures.PRECISE_ASSEMBLER_CASING_MK1;
            case (2) -> NTTextures.PRECISE_ASSEMBLER_CASING_MK2;
            case (3) -> NTTextures.PRECISE_ASSEMBLER_CASING_MK3;
            default -> NTTextures.PRECISE_ASSEMBLER_CASING_MK1;
        };
    }

    private int count = 0;

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = null;
        if (Blocks.AIR != null) {
            builder = MultiblockShapeInfo.builder()
                    .aisle("ETCCCCCCC", "F       F", "F       F", "F       F", "XYZCCCCCC")
                    .aisle("CMMMMMMMC", "CGGGGGGGC", "CGGGGGGGC", "CGGGGGGGC", "CCCCCCCCC")
                    .aisle("CMMMMMMMC", "C       C", "C       C", "C       C", "CCCCOCCCC")
                    .aisle("CMMMMMMMC", "CGGGGGGGC", "CGGGGGGGC", "CGGGGGGGC", "CCCCCCCCC")
                    .aisle("CCCCSCCCC", "F       F", "F       F", "F       F", "CCCCCCCCC")
                    .where('S', MetaTileEntities.PRECISE_ASSEMBLER, EnumFacing.SOUTH)
                    .where('X', gregtech.common.metatileentities.MetaTileEntities.ITEM_IMPORT_BUS[LuV], EnumFacing.NORTH)
                    .where('Y', gregtech.common.metatileentities.MetaTileEntities.ITEM_EXPORT_BUS[LuV], EnumFacing.NORTH)
                    .where('Z', gregtech.common.metatileentities.MetaTileEntities.FLUID_IMPORT_HATCH[LuV], EnumFacing.NORTH)
                    .where('E', gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH[LuV], EnumFacing.NORTH)
                    .where('T', () -> ConfigHolder.machines.enableMaintenance ? gregtech.common.metatileentities.MetaTileEntities.MAINTENANCE_HATCH : gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH[LuV], EnumFacing.NORTH)
                    .where('O', gregtech.common.metatileentities.MetaTileEntities.MUFFLER_HATCH[LuV], EnumFacing.UP)
                    .where('G', getGlassState())
                    .where('F', getFrameState())
                    .where(' ', Blocks.AIR.getDefaultState());
        }
        MultiblockShapeInfo.Builder finalBuilder = builder;

        finalListCasing.stream()
                .map(b -> {
                    if (finalBuilder != null) {
                        finalBuilder.where('C', b);
                        finalBuilder.where('M', finalListInternalCasing.get(count));
                        count++;
                    }
                    return finalBuilder;
                }).filter(Objects::nonNull).forEach(b -> shapeInfo.add(b.build()));
        count = 0;
        return shapeInfo;
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == NTDataCode.CHANNEL_4) {
            this.CasingTier = buf.readInt();
        }
        if (dataId == NTDataCode.CHANNEL_7) {
            this.writeCustomData(NTDataCode.CHANNEL_4, buf1 -> buf1.writeInt(this.CasingTier));
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.CasingTier);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.CasingTier = buf.readInt();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("naxtech.machine.precise_assembler.tooltip.1"));
        tooltip.add(I18n.format("naxtech.machine.precise_assembler.tooltip.2"));
        tooltip.add(I18n.format("naxtech.machine.precise_assembler.tooltip.3"));
        tooltip.add(I18n.format("naxtech.machine.precise_assembler.tooltip.4"));
        tooltip.add(I18n.format("naxtech.machine.precise_assembler.tooltip.5"));
    }

    protected class PreciseAssemblerRecipeLogic extends MultiblockRecipeLogic {
        public PreciseAssemblerRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        /**
         * @return Check if machine in PA
         */
        private boolean isPrecise() {
            return this.getRecipeMap() == NTRecipeMaps.PRECISE_ASSEMBLER_RECIPES;
        }

        /**
         * @param maxProgress If machine in common assembler, then get half progress time.
         */
        public void setMaxProgress(int maxProgress) {
            if (isPrecise()) {
                this.maxProgressTime = maxProgress ;
            } else {
                this.maxProgressTime = maxProgress / 2;
            }
        }

        /**
         * @return If machine in PA, then no parallel, if machine in common assembler, then get 2^{CasingTier + 4} (Mk1:32, Mk2:64, Mk3:128) parallel.
         */
        @Override
        public int getParallelLimit() {
           if (isPrecise()) {
               return 1;
           } else {
               return (int) Math.pow(2, CasingTier + 4);
           }
        }
    }
}
