package com.onlyex.naxtech.common.block.blocks;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

import static com.onlyex.naxtech.common.CommonProxy.NAXTECH_TAB;

public class BlockControlCasing extends VariantBlock<BlockControlCasing.CasingType> {


    public BlockControlCasing() {
        super(Material.IRON);
        this.setTranslationKey("control_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        setCreativeTab(NAXTECH_TAB);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(CasingType.ISA_MILL_CASING));
    }

    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }



    public enum CasingType implements IStringSerializable {

        ISA_MILL_CASING("isa_mill_casing"),
        ISA_MILL_CASING_GEARBOX("isa_mill_casing_gearbox"),
        ISA_MILL_CASING_PIPE("isa_mill_casing_pipe"),
        FLOTATION_CASING("flotation_casing"),
        FLOTATION_CASING_GEARBOX("flotation_casing_gearbox"),
        FLOTATION_CASING_PIPE("flotation_casing_pipe");

        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @Nonnull
        @Override
        public String getName() {
            return name;
        }
    }
}
