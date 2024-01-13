package com.onlyex.naxtech.api.items.metaitem.stats.renderer;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 *任何实现此功能的项目也将自身绑定到这里的待办事项输入模型名称。
 *将自动将宇宙着色器应用于具有给定不透明度的蒙版。
 */
public interface ICosmicRenderBehavior extends IItemRendererManager {
    /**
     * 宇宙着色器应
     *
     * @param stack  正在渲染的堆栈
     * @param player 持有物品的实体，可能为空，如果为空，则假设库存或地面。
     * @return 宇宙覆盖的蒙版区域。
     */
    @SideOnly(Side.CLIENT)
    TextureAtlasSprite getMaskTexture(ItemStack stack, @Nullable EntityLivingBase player);

    /**
     * 蒙版覆盖将呈现的不透明度。
     *
     * @param stack  正在渲染的堆栈
     * @param player 持有物品的实体，可能为空，如果为空，则假设库存或地面。
     * @return 覆盖将呈现的不透明度。
     */
    @SideOnly (Side.CLIENT)
    float getMaskOpacity(ItemStack stack, @Nullable EntityLivingBase player);
}
