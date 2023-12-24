package com.onlyex.naxtech.common.block.blocks.assembly;

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

public class BlockAdvancedAssemblyLineCasing  extends VariantBlock<BlockAdvancedAssemblyLineCasing.CasingType> {


    public BlockAdvancedAssemblyLineCasing() {
        super(Material.IRON);
        this.setTranslationKey("advanced_assembly_line_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(NAXTECH_TAB);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(CasingType.FILTER_CASING));
    }

    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }



    public enum CasingType implements IStringSerializable {

        FILTER_CASING("filter_casing"),
        ASSEMBLY_LINE_CASING("assembly_line_casing"),

        ASSEMBLY_CONTROL_CASING("assembly_control_casing");


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