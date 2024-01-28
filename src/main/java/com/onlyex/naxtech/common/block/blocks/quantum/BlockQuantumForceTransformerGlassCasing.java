package com.onlyex.naxtech.common.block.blocks.quantum;

import com.onlyex.naxtech.api.block.ITierGlassBlockState;
import gregtech.api.GTValues;
import gregtech.api.block.VariantActiveBlock;
import gregtech.api.block.VariantItemBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockQuantumForceTransformerGlassCasing extends VariantActiveBlock<BlockQuantumForceTransformerGlassCasing.GlassType> {
    public BlockQuantumForceTransformerGlassCasing() {
        super(Material.GLASS);
        setTranslationKey("quantum_force_transformer_glasses_casing");
        setHardness(5.0F);
        setResistance(10.0F);
        setSoundType(SoundType.GLASS);
        setHarvestLevel("pickaxe", 4);
        setDefaultState(this.getState(GlassType.FORCE_FIELD_CONSTRAINED_GLASS));
        this.useNeighborBrightness = true;
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    @Nonnull
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean canRenderInLayer(@Nonnull IBlockState state, @Nonnull BlockRenderLayer layer) {
//        BlockQuantumForceTransformerGlassCasing.GlassType type = getState(state);
//        if( type == GlassType.INFINITY_GLASS
//        ) {
//            return super.canRenderInLayer(state, layer);
//        }
//        return layer == BlockRenderLayer.TRANSLUCENT;
        return super.canRenderInLayer(state, layer);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos, @Nonnull EnumFacing side) {
        IBlockState sideState = world.getBlockState(pos.offset(side));

        return sideState.getBlock() == this ?
                getState(sideState) != getState(state) :
                super.shouldSideBeRendered(state, world, pos, side);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World player, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        VariantItemBlock<GlassType, BlockQuantumForceTransformerGlassCasing> itemBlock = (VariantItemBlock<BlockQuantumForceTransformerGlassCasing.GlassType, BlockQuantumForceTransformerGlassCasing>) stack.getItem();
        IBlockState stackState = itemBlock.getBlockState(stack);
        BlockQuantumForceTransformerGlassCasing.GlassType glassType =  this.getState(stackState);
        tooltip.add(I18n.format("naxtech.glass_tier.tooltip", glassType.getTireNameColored()));
        if (glassType.isOpticalGlass) {
            tooltip.add(glassType.getOpticalTierName());
        }
    }

    public enum GlassType implements IStringSerializable, ITierGlassBlockState {
        FORCE_FIELD_CONSTRAINED_GLASS("force_field_constrained_glass", GTValues.UHV, false),
        COSMIC_MICROWAVE_BACKGROUND_RADIATION_ABSORPTION_GLASS("cosmic_microwave_background_radiation_absorption_glass", GTValues.UEV, false),
        SPACETIME_SUPERCONDENSER_GLASS("spacetime_supercondenser_glass", GTValues.UIV, false),
        SUPRACAUSAL_LIGHT_CONE_GLASS("supracausal_light_cone_glass", GTValues.UXV, false);

        private final String name;
        private final int tier;
        private final boolean isOpticalGlass;

        GlassType(String name, int tier, boolean isOpticalGlass) {
            this.name = name;
            this.tier = tier;
            this.isOpticalGlass = isOpticalGlass;
        }

        @Nonnull
        @Override
        public String getName() {return this.name;}

        @Override
        public int getGlassTier() {return this.tier;}

        public boolean isOpticalGlass() {return isOpticalGlass;}

        @Nonnull
        @Override
        public String toString() {return getName();}
    }
}