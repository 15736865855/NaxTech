package com.onlyex.naxtech.common.items.behaviors;

import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StructureWriteBehavior implements IItemBehaviour/*, ItemUIFactory */{

    public StructureWriteBehavior() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    /*@Override
    public ModularUI createUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer) {
        return null;
    }*/

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {

    }
}
