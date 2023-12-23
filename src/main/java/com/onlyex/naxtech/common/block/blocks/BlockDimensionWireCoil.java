package com.onlyex.naxtech.common.block.blocks;

import com.onlyex.naxtech.api.unification.NTMaterials;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.block.VariantActiveBlock;
import gregtech.api.block.VariantItemBlock;
import gregtech.api.items.toolitem.ToolClasses;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityMultiSmelter;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static com.onlyex.naxtech.common.CommonProxy.NAXTECH_TAB;
/**
 * name            the unique name of the Heating Coil
 * coilTemperature the temperature of the Heating Coil
 * level           the level of the Heating Coil - used for Multismelter parallel amount
 * energyDiscount  the energy discount of the Heating Coil
 * tier            the tier of the Heating Coil - used for cracker pyrolyse discounts
 * material        the {@link Material} of the Heating Coil, use null for no specific material
 */
public class BlockDimensionWireCoil  extends VariantActiveBlock<BlockDimensionWireCoil.CoilType> {

    public BlockDimensionWireCoil() {
        super(net.minecraft.block.material.Material.IRON);
        this.setTranslationKey("wire_coil");
        this.setCreativeTab(NAXTECH_TAB);
        this.setHardness(50.0f);
        this.setResistance(100.0f);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel(ToolClasses.WRENCH, 6);
        this.setDefaultState(this.getState(CoilType.NAQUADAH_ALLOY));
    }

    @Nonnull
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(@Nonnull ItemStack itemStack, @Nullable World worldIn, List<String> lines, @Nonnull ITooltipFlag tooltipFlag) {
        super.addInformation(itemStack, worldIn, lines, tooltipFlag);

        VariantItemBlock<CoilType, BlockDimensionWireCoil> itemBlock = (VariantItemBlock<CoilType, BlockDimensionWireCoil>) itemStack.getItem();
        IBlockState stackState = itemBlock.getBlockState(itemStack);
        CoilType coilType =  this.getState(stackState);
        lines.add(I18n.format("tile.wire_coil.tooltip_heat", coilType.coilTemperature));
        if (TooltipHelper.isShiftDown()) {
            int coilTier = coilType.ordinal();
            lines.add(I18n.format("tile.wire_coil.tooltip_smelter"));
            lines.add(I18n.format("tile.wire_coil.tooltip_parallel_smelter", coilType.level * 32));
            int EUt = MetaTileEntityMultiSmelter.getEUtForParallel(MetaTileEntityMultiSmelter.getMaxParallel(coilType.getLevel()), coilType.getEnergyDiscount());
            lines.add(I18n.format("tile.wire_coil.tooltip_energy_smelter", EUt));
            lines.add(I18n.format("tile.wire_coil.tooltip_pyro"));
            lines.add(I18n.format("tile.wire_coil.tooltip_speed_pyro", coilTier == 0 ? 75 : 50 * (coilTier + 9)));
            lines.add(I18n.format("tile.wire_coil.tooltip_cracking"));
            lines.add(I18n.format("tile.wire_coil.tooltip_energy_cracking", 100 - 10 * coilTier));
        } else {
            lines.add(I18n.format("tile.wire_coil.tooltip_extended_info"));
        }
    }

    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    protected boolean isBloomEnabled(CoilType value) {
        return ConfigHolder.client.coilsActiveEmissiveTextures;
    }


    public enum CoilType implements IStringSerializable, IHeatingCoilBlockStats {

        NAQUADAH_ALLOY("naquadah_alloy", 8101, 8, 8, Materials.NaquadahAlloy),
        ELECTRUM_FLUX("electrum_flux", 9901, 16, 16, NTMaterials.ElectrumFlux),
        AWAKENED_DRACONIUM("awakened_draconium", 10801, 32, 16, NTMaterials.DraconiumAwakened),
        INFINITY("infinity", 11701, 32, 32, NTMaterials.Infinity),
        HYPOGEN("hypogen", 12601, 64, 32, NTMaterials.Hypogen),
        ETERNAL("eternal", 13501, 64, 64, null);

        private final String name;
        private final int coilTemperature;
        private final int level;
        private final int energyDiscount;
        private final Material material;

        CoilType(String name, int coilTemperature, int level, int energyDiscount, Material material) {
            this.name = name;
            this.coilTemperature = coilTemperature;
            this.level = level;
            this.energyDiscount = energyDiscount;
            this.material = material;
        }

        @Nonnull
        public String getName() {
            return this.name;
        }


        public int getCoilTemperature() {
            return this.coilTemperature;
        }

        public int getLevel() {
            return this.level;
        }

        public int getEnergyDiscount() {
            return this.energyDiscount;
        }

        public int getTier() {
            return BlockWireCoil.CoilType.TRITANIUM.getTier() + this.ordinal();
        }

        @Nullable
        public Material getMaterial() {
            return this.material;
        }

        @Nonnull
        public String toString() {
            return this.getName();
        }
    }
}
