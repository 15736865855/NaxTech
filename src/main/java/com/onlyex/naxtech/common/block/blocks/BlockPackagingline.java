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

public class BlockPackagingline  extends VariantBlock<BlockPackagingline.MultiblockCasingType> {


    public BlockPackagingline() {
        super(Material.IRON);
        this.setTranslationKey("packaging_line");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        setCreativeTab(NAXTECH_TAB);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(BlockPackagingline.MultiblockCasingType.PACKAGING_LINE_MACHINE_CASING));
    }

    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }



    public enum MultiblockCasingType implements IStringSerializable {

        PACKAGING_LINE_MACHINE_CASING("packaging_line_machine_casing"),
        PACKAGING_LINE_CASING("packaging_line_casing"),

        PACKAGING_LINE_CONTROL_CASING("packaging_line_control_casing");


        private final String name;

        MultiblockCasingType(String name) {
            this.name = name;
        }

        @Nonnull
        @Override
        public String getName() {
            return name;
        }
    }
}
