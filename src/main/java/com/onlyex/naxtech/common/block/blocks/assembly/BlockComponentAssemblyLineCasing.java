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
import javax.annotation.ParametersAreNonnullByDefault;

import static com.onlyex.naxtech.common.CommonProxy.NAXTECH_TAB;

@ParametersAreNonnullByDefault
public class BlockComponentAssemblyLineCasing extends VariantBlock<BlockComponentAssemblyLineCasing.CasingTier> {
    public BlockComponentAssemblyLineCasing() {
        super(Material.IRON);
        this.setTranslationKey("component_assembly_line_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(NAXTECH_TAB);
        this.setHarvestLevel("wrench", 3);
        this.setDefaultState(this.getState(CasingTier.LV));
    }
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingTier implements IStringSerializable {
        LV("lv"),
        MV("mv"),
        HV("hv"),
        EV("ev"),
        IV("iv"),
        LuV("luv"),
        ZPM("zpm"),
        UV("uv"),
        UHV("uhv"),
        UEV("uev"),
        UIV("uiv"),
        UXV("uxv"),
        OpV("opv"),
        MAX("max");

        private final String name;

        CasingTier(String name) {
            this.name = name;
        }

        @Nonnull
        @Override
        public String getName() {
            return name;
        }
    }
}
