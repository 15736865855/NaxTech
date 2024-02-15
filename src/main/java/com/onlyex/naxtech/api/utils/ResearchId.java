package com.onlyex.naxtech.api.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResearchId {
    public static final String RESEARCH_NBT_TAG = "researchLineResearch";
    public static final String RESEARCH_ID_NBT_TAG = "researchId";

    /**
     * 将给定的研究ID写入到传入的NBTTagCompound中
     */
    public static void writeResearchToNBT(@NotNull NBTTagCompound stackCompound, @NotNull String researchId) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(RESEARCH_ID_NBT_TAG, researchId);
        stackCompound.setTag(RESEARCH_NBT_TAG, compound);
    }

    /**
     * 从给定的ItemStack中读取存储的研究ID，并返回该研究ID字符串。如果ItemStack中没有存储研究ID的相关信息，则返回null。
     */
    @Nullable
    public static String readResearchId(@NotNull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!hasResearchTag(compound)) return null;

        NBTTagCompound researchCompound = compound.getCompoundTag(RESEARCH_NBT_TAG);
        String researchId = researchCompound.getString(RESEARCH_ID_NBT_TAG);
        return researchId.isEmpty() ? null : researchId;
    }

    /**
     * 用于检查指定的物品堆栈是否具有研究NBTTagCompound。
     */
    public static boolean hasResearchTag(@NotNull ItemStack stack) {
        return hasResearchTag(stack.getTagCompound());
    }

    /**
     * 用于检查指定的NBTTagCompound是否具有研究NBTTagCompound。
     * 该方法首先检查传入的NBTTagCompound是否为null或者为空，如果是则直接返回false。
     * 如果NBTTagCompound不为空，则利用compound的hasKey方法来检查是否存在以RESEARCH_NBT_TAG为键的Constants.NBT.TAG_COMPOUND类型的数据。
     * 如果存在则返回true，表示具有研究NBTTagCompound；否则返回false。
     */
    private static boolean hasResearchTag(@Nullable NBTTagCompound compound) {
        if (compound == null || compound.isEmpty()) return false;
        return compound.hasKey(RESEARCH_NBT_TAG, Constants.NBT.TAG_COMPOUND);
    }
}
