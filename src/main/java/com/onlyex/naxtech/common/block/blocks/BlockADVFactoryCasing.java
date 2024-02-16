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
import javax.annotation.ParametersAreNonnullByDefault;

import static com.onlyex.naxtech.common.CommonProxy.NAXTECH_TAB;

@ParametersAreNonnullByDefault
public class BlockADVFactoryCasing extends VariantBlock<BlockADVFactoryCasing.CasingType> {
    public BlockADVFactoryCasing() {
        super(Material.IRON);
        this.setTranslationKey("adv_factory_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(NAXTECH_TAB);
        this.setHarvestLevel("wrench", 3);
        this.setDefaultState(this.getState(CasingType.MACHINE_TECH));
    }
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {
        MACHINE_TECH("machine_tech"),
        MACHINE_BASIC("machine_basic"),
        DIMENSIONAL_CASING_B("machine_dimensional_cover_blue"),
        DIMENSIONAL_CASING_O("machine_dimensional_cover_orange"),
        MACHINE_LESU("machine_lesu"),
        MACHINE_MATTERFAB("machine_matterfab"),
        MACHINE_MATTERFAB_ACTIVE("machine_matterfab_active"),
        MACHINE_MATTERFAB_ACTIVE_ANIMATED("machine_matterfab_active_animated"),
        MACHINE_MATTERFAB_ANIMATED("machine_matterfab_animated"),
        MACHINE_VENT_ROTARING("machine_vent_rotating"),
        MACHINE_TUBBINE("machine_tubbine");

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