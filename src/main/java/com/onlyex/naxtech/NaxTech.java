package com.onlyex.naxtech;

import com.onlyex.naxtech.api.NTAPI;
import com.onlyex.naxtech.api.capability.pollution.IPollution;
import com.onlyex.naxtech.api.capability.pollution.PollutionProvider;
import com.onlyex.naxtech.api.utils.NTLog;
import com.onlyex.naxtech.api.worldgen.NTWorldGenRegistry;
import com.onlyex.naxtech.common.block.NTMetaBlocks;
import com.onlyex.naxtech.common.items.NTMetaItems;
import com.onlyex.naxtech.common.metatileentities.NTMetaTileEntities;
import com.onlyex.naxtech.common.CommonProxy;
import gregtech.api.worldgen.config.WorldGenRegistry;
import gregtech.common.ConfigHolder;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.IOException;

import static com.onlyex.naxtech.api.NTValues.MOD_ID;

@Mod(
        modid = MOD_ID,
        name="NaxTech",
        acceptedMinecraftVersions = "[1.12.2,1.13)",
        version = "0.0.1" ,
        dependencies = "required-after:gregtech@[2.8.5-beta,) ;"
)
public class NaxTech {

    @SidedProxy(
            modId = MOD_ID,
            clientSide = "com.onlyex.naxtech.client.ClientProxy",
            serverSide = "com.onlyex.naxtech.common.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod.EventHandler
    public void onConstruction(FMLConstructionEvent event)
    {
        ConfigHolder.machines.highTierContent = true;
    }
    @Mod.EventHandler
    public void onpreInit(FMLPreInitializationEvent event) {
        NTLog.init(event.getModLog());
        ConfigHolder.machines.highTierContent = true;
        NTLog.logger.info("Enabled GregTechCEu highTierContent");
        NTMetaItems.init();
        NTMetaBlocks.init();
        NTAPI.APIBlockInit();
        NTMetaTileEntities.init();
        NTWorldGenRegistry.init();

        CapabilityManager.INSTANCE.register(IPollution.class, new PollutionProvider.Storage(), PollutionProvider.Impl::new);
        proxy.preLoad();
        proxy.preInit(event);

    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        NTWorldGenRegistry.override();
        try {
            WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();
        } catch (IOException | RuntimeException exception) {
            NTLog.logger.fatal("Failed to initialize worldgen registry.", exception);
        }
    }
}
