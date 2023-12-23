package com.onlyex.naxtech.api;

import net.minecraft.util.ResourceLocation;

import static net.minecraft.util.text.TextFormatting.*;

public class NTValues {
    public static ResourceLocation gtqtcoreId(String id) {
        return new ResourceLocation(NTValues.MOD_ID, id);
    }


    public static final int MAXX = 15;
    public static final int EXU = 16;
    public static final String[] VN = new String[]{"MAX+", "EX"};
    public static final String[] VNF = new String[]{YELLOW.toString() + BOLD  + "MAX+", BLUE.toString() + BOLD  + "EX"};
    public static final int[] VC = new int[]{0x000000, 0x000000};
    public static final String[] VOLTAGE_NAMES = new String[]{"Maximum Voltage Plus", "Unconventional Extreme Voltage"};
    public static final String MOD_ID = "naxtech",
                               MOD_ID_JEI = "jei";

    public static final int IF = 65536;
}
