package com.onlyex.naxtech.api.block;

import com.onlyex.naxtech.api.utils.NTUniverUtil;
import gregtech.api.GTValues;
import gregtech.client.utils.TooltipHelper;
import net.minecraft.client.resources.I18n;

public interface ITierGlassBlockState extends ITier{

    @Override
    default Object getTier() {
        return this.getGlassTier();
    }

    boolean isOpticalGlass();
    int getGlassTier();

    default long getTireVoltage(){
        return GTValues.V[getGlassTier()];
    }

    default String getTireName(){
        return GTValues.VN[getGlassTier()];
    }

    default String getTireNameColored(){
        return GTValues.VNF[getGlassTier()];
    }

    default String getOpticalTierName() {
        return TooltipHelper.BLINKING_CYAN + I18n.format("naxtech.optical_glass_tier.tooltip." + NTUniverUtil.getOpticalGlassTier(getGlassTier()));
    }
}
