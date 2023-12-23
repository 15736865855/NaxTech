package com.onlyex.naxtech.common;

import com.onlyex.naxtech.api.capability.pollution.PollutionProvider;
import com.onlyex.naxtech.api.unification.NTMaterials;
import com.onlyex.naxtech.api.unification.OrePrefixAdditions;
import gregtech.api.unification.material.event.MaterialEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.onlyex.naxtech.api.NTValues.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class NTEventHandler {
    public NTEventHandler() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event)
    {
        NTMaterials.initregister();
        OrePrefixAdditions.init();
    }
    @SubscribeEvent
    public void attachChunkPollutionCapability(AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(new ResourceLocation(MOD_ID, "pollution_cap"), new PollutionProvider());
    }
}
