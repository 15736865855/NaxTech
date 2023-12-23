package com.onlyex.naxtech.common.metatileentities.multi.electric;

import com.onlyex.naxtech.common.metatileentities.NTMetaTileEntities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class NTMetaTileEntityEnergyHatch extends gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch implements IMultiblockAbilityPart<IEnergyContainer> {

    public NTMetaTileEntityEnergyHatch(ResourceLocation metaTileEntityId, int tier, int amperage, boolean isExport) {
        super(metaTileEntityId, tier, amperage, isExport);
    }

    @Override
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> subItems) {
        for (gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch hatch : NTMetaTileEntities.INPUT_ENERGY_HATCH_4A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }

        for (gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch hatch : NTMetaTileEntities.INPUT_ENERGY_HATCH_16A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }

        for (gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch hatch : NTMetaTileEntities.OUTPUT_ENERGY_HATCH_4A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }

        for (gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch hatch : NTMetaTileEntities.OUTPUT_ENERGY_HATCH_16A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
    }
}
