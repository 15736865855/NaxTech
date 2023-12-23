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

public class BlockPipelinelCasing extends VariantBlock<BlockPipelinelCasing.CasingType> {


    public BlockPipelinelCasing() {
        super(Material.IRON);
        this.setTranslationKey("pipelinel_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        setCreativeTab(NAXTECH_TAB);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(CasingType.PBI));
    }

    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }



    public enum CasingType implements IStringSerializable {

        PBI("pbi");

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
