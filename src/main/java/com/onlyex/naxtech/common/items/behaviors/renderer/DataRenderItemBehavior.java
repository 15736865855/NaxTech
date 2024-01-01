package com.onlyex.naxtech.common.items.behaviors.renderer;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.TransformUtils;
import com.onlyex.naxtech.api.items.metaitem.stats.renderer.IItemRendererManager;
import com.onlyex.naxtech.client.renderer.handler.DataItemRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class DataRenderItemBehavior implements IItemRendererManager {//todo null
    @Override
    public void onRendererRegistry(ResourceLocation location) {
        ModelRegistryHelper.register(new ModelResourceLocation(location, "inventory"), new DataItemRenderer(TransformUtils.DEFAULT_ITEM, modelRegistry -> modelRegistry.getObject(new ModelResourceLocation(location, "inventory"))));
    }
}
