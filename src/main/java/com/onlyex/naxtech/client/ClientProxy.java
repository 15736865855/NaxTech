package com.onlyex.naxtech.client;

import com.onlyex.naxtech.client.event.NTClientEventHandler;
import com.onlyex.naxtech.client.renderer.texture.NTTextures;
import com.onlyex.naxtech.client.utils.ShaderHelper;
import com.onlyex.naxtech.common.CommonProxy;
import com.onlyex.naxtech.common.block.NTMetaBlocks;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber({Side.CLIENT})
public class ClientProxy extends CommonProxy {

    public ClientProxy() {
    }
    public void preLoad()
    {
        super.preLoad();
        MinecraftForge.EVENT_BUS.register(new NTClientEventHandler());
        ShaderHelper.initShaders();
        NTTextures.preInit();
    }
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        NTMetaBlocks.registerItemModels();
    }

}
