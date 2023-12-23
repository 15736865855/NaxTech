package com.onlyex.naxtech.common.block.blocks.machinel;

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

public class BlockMachinelCasingA extends VariantBlock<BlockMachinelCasingA.CasingType> {


    public BlockMachinelCasingA() {
        super(Material.IRON);
        this.setTranslationKey("machine_casing_a");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(NAXTECH_TAB);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(CasingType.TRITANIUM_CASING));
    }

    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }



    public enum CasingType implements IStringSerializable {

        TRITANIUM_CASING("tritanium_casing"),
        QUANTUM_CASING("quantum_casing"),
        REFLECTIVE_CASING("reflective_casing"),
        GENERAL_PROCESSING_CASING("general_processing_casing"),
        MARAGING_STEEL_CASING("maraging_steel_casing"),
        BABBITT_ALLOY_CASING("babbitt_alloy_casing"),
        ZIRCONIUM_CARBIDE_CASING("zirconium_carbide_casing"),
        SUPERCRITICAL_FLUID_TURBINE_CASING("supercritical_fluid_turbine_casing"),
        CORROSION_CASING("corrosion_casing"),
        HASTELLOYX78_CASING("hastelloy_x78_casing"),
        STELLAR_CONTAINMENT_CASING("stellar_containment_casing"),
        HASTELLOYK243_CASING("hastelloy_k243_casing"),
        SUPERCRITICAL_FLUID_TURBINE_SHAFT_BLOCK("supercritical_fluid_turbine_shaft_block"),
        ALLOY_SMELTING_PIPE_CASING("alloy_smelting_pipe_casing"),
        CYCLOTRON_CASING("cyclotron_casing"),
        CYCLOTRON_COIL("cyclotron_coil");//16

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
