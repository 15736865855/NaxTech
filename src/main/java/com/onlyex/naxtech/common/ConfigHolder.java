package com.onlyex.naxtech.common;

import com.onlyex.naxtech.api.NTValues;
import net.minecraftforge.common.config.Config;

@Config(modid = NTValues.MOD_ID, name = NTValues.MOD_ID + '/' + NTValues.MOD_ID)
public class ConfigHolder {

    @Config.Comment("GT机器、管道、电缆和电气项目的配置选项")
    @Config.Name("机器选项")
    @Config.RequiresMcRestart
    public static MachineOptions machines = new MachineOptions();
    public static class MachineOptions {
        @Config.Comment({ "是否为配方启用研究线研究。","默认值：真" })
        @Config.RequiresMcRestart
        public boolean enableResearch = true;
    }
}
