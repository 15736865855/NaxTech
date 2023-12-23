package com.onlyex.naxtech.loaders.formula;

import com.onlyex.naxtech.api.utils.NTUniverUtil;
import com.onlyex.naxtech.common.metatileentities.multi.electric.NTMetaTileEntityBufferHatch;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import static gregtech.api.unification.material.Materials.SodiumHydroxide;
import static gregtech.api.unification.material.Materials.SulfuricAcid;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class BufferFormula {

    public static void addBuffer() {
        //  Item
        addItem(OreDictUnifier.get(dust, SodiumHydroxide), 3.15, 13.55);

        //  Fluid
        addFluid(SulfuricAcid.getFluid(), -3.15, 1.55);
    }
    private static void addItem(ItemStack stack, double phChange, double phLimit) {
        NTMetaTileEntityBufferHatch.BufferItemMap.put(NTUniverUtil.stackToInt(stack), new double[] {phChange, phLimit});
    }

    private static void addFluid(Fluid fluid, double phChange, double phLimit) {
        NTMetaTileEntityBufferHatch.BufferFluidMap.put(fluid.getName(), new double[] {phChange, phLimit});
    }
}
