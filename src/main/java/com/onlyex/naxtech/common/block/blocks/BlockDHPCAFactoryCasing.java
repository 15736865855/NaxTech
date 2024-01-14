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
public class BlockDHPCAFactoryCasing extends VariantBlock<BlockDHPCAFactoryCasing.CasingType> {
    public BlockDHPCAFactoryCasing() {
        super(Material.IRON);
        this.setTranslationKey("dhpca_factory_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(NAXTECH_TAB);
        this.setHarvestLevel("wrench", 3);
        this.setDefaultState(this.getState(CasingType.BIOWARE_COMPUTER_CASING));
    }
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public static enum CasingType implements IStringSerializable {
        BIOWARE_COMPUTER_CASING("bioware_computer_casing"),
        BIOWARE_COMPUTER_HEAT_VENT("bioware_computer_heat_vent"),
        BIOWARE_ADVANCED_COMPUTER_CASING("bioware_advanced_computer_casing");

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