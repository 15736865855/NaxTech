package com.onlyex.naxtech.common.block.blocks.machinel;

import gregtech.api.block.IStateHarvestLevel;
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

public class BlockMachinelCasingB extends VariantBlock<BlockMachinelCasingB.CasingType> {


    public BlockMachinelCasingB() {
        super(Material.IRON);
        this.setTranslationKey("machine_casing_b");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(NAXTECH_TAB);
        this.setDefaultState(this.getState(CasingType.DRACONIUM_CASING));
    }

    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }



    public enum CasingType implements IStringSerializable , IStateHarvestLevel {

        DRACONIUM_CASING("draconium_casing",2),
        DRAGON_FUSION_COIL("dragon_fusion_coil",2),
        HG_1223_CASING("hg_1223_casing",2),
        MACERATOR_CASING("macerator_casing", 4),
        BRICK("brick", 4),
        TALONITE_CASING("talonite_casing", 4),
        MACHINE_CASING("machine_casing", 4),
        TURBINE_CASING("turbine_casing", 4),
        FISHING_CASING("fishing_casing", 4),
        VACUUM_CASING("vacuum_casing",2),
        PROCESS("process_casing",2),
        ASEPTIC_FARM_CASING("aseptic_farm_machine_casing",2);

        private final String name;
        private final int harvestLevel;

        CasingType(String name, int harvestLevel) {
            this.name = name;
            this.harvestLevel = harvestLevel;
        }

        @Nonnull
        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getHarvestLevel(IBlockState state) {
            return harvestLevel;
        }
        @Override
        public String getHarvestTool(IBlockState state) {
            return "wrench";
        }
    }
}
