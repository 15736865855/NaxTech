package com.onlyex.naxtech.api.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResearchId {
    public static final String RESEARCH_NBT_TAG = "researchLineResearch";
    public static final String RESEARCH_ID_NBT_TAG = "researchId";
    public static final String GO_RESEARCH_ID_NBT_TAG = "goresearchId";
    public static final String OP_RESEARCH_ID_NBT_TAG = "opresearchId";
    public static final String SP_RESEARCH_ID_NBT_TAG = "spresearchId";
    public static final String CO_RESEARCH_ID_NBT_TAG = "coresearchId";
    public static final String SCA_RESEARCH_ID_NBT_TAG = "scaresearchId";
    public static final String SCH_RESEARCH_ID_NBT_TAG = "schresearchId";
    public static final String SDI_RESEARCH_ID_NBT_TAG = "sdiresearchId";

    /**
     * 将给定的研究ID写入到传入的NBTTagCompound中
     */
    public static void writeResearchToNBT(@NotNull NBTTagCompound stackCompound, @NotNull String researchId) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(RESEARCH_ID_NBT_TAG, researchId);
        stackCompound.setTag(RESEARCH_NBT_TAG, compound);
    }
    public static void writeGOResearchToNBT(@NotNull NBTTagCompound stackCompound, @NotNull String goresearchId) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(GO_RESEARCH_ID_NBT_TAG, goresearchId);
        stackCompound.setTag(RESEARCH_NBT_TAG, compound);
    }
    public static void writeOPResearchToNBT(@NotNull NBTTagCompound stackCompound, @NotNull String opresearchId) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(OP_RESEARCH_ID_NBT_TAG, opresearchId);
        stackCompound.setTag(RESEARCH_NBT_TAG, compound);
    }
    public static void writeSPResearchToNBT(@NotNull NBTTagCompound stackCompound, @NotNull String spresearchId) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(SP_RESEARCH_ID_NBT_TAG, spresearchId);
        stackCompound.setTag(RESEARCH_NBT_TAG, compound);
    }
    public static void writeCOResearchToNBT(@NotNull NBTTagCompound stackCompound, @NotNull String coresearchId) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(CO_RESEARCH_ID_NBT_TAG, coresearchId);
        stackCompound.setTag(RESEARCH_NBT_TAG, compound);
    }
    public static void writeSCAResearchToNBT(@NotNull NBTTagCompound stackCompound, @NotNull String scaresearchId) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(SCA_RESEARCH_ID_NBT_TAG, scaresearchId);
        stackCompound.setTag(RESEARCH_NBT_TAG, compound);
    }
    public static void writeSCHResearchToNBT(@NotNull NBTTagCompound stackCompound, @NotNull String schresearchId) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(SCH_RESEARCH_ID_NBT_TAG, schresearchId);
        stackCompound.setTag(RESEARCH_NBT_TAG, compound);
    }
    public static void writeSDIResearchToNBT(@NotNull NBTTagCompound stackCompound, @NotNull String sdiresearchId) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(SDI_RESEARCH_ID_NBT_TAG, sdiresearchId);
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
    @Nullable
    public static String readGOResearchId(@NotNull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!hasResearchTag(compound)) return null;

        NBTTagCompound goresearchCompound = compound.getCompoundTag(RESEARCH_NBT_TAG);
        String goresearchId = goresearchCompound.getString(GO_RESEARCH_ID_NBT_TAG);
        return goresearchId.isEmpty() ? null : goresearchId;
    }
    @Nullable
    public static String readOPResearchId(@NotNull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!hasResearchTag(compound)) return null;

        NBTTagCompound opresearchCompound = compound.getCompoundTag(RESEARCH_NBT_TAG);
        String opresearchId = opresearchCompound.getString(OP_RESEARCH_ID_NBT_TAG);
        return opresearchId.isEmpty() ? null : opresearchId;
    }
    @Nullable
    public static String readSPResearchId(@NotNull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!hasResearchTag(compound)) return null;

        NBTTagCompound spresearchCompound = compound.getCompoundTag(RESEARCH_NBT_TAG);
        String spresearchId = spresearchCompound.getString(SP_RESEARCH_ID_NBT_TAG);
        return spresearchId.isEmpty() ? null : spresearchId;
    }
    @Nullable
    public static String readCOResearchId(@NotNull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!hasResearchTag(compound)) return null;

        NBTTagCompound coresearchCompound = compound.getCompoundTag(RESEARCH_NBT_TAG);
        String coresearchId = coresearchCompound.getString(CO_RESEARCH_ID_NBT_TAG);
        return coresearchId.isEmpty() ? null : coresearchId;
    }
    @Nullable
    public static String readSCAResearchId(@NotNull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!hasResearchTag(compound)) return null;

        NBTTagCompound scaresearchCompound = compound.getCompoundTag(RESEARCH_NBT_TAG);
        String scaresearchId = scaresearchCompound.getString(SCA_RESEARCH_ID_NBT_TAG);
        return scaresearchId.isEmpty() ? null : scaresearchId;
    }
    @Nullable
    public static String readSCHResearchId(@NotNull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!hasResearchTag(compound)) return null;

        NBTTagCompound schresearchCompound = compound.getCompoundTag(RESEARCH_NBT_TAG);
        String schresearchId = schresearchCompound.getString(SCH_RESEARCH_ID_NBT_TAG);
        return schresearchId.isEmpty() ? null : schresearchId;
    }
    @Nullable
    public static String readSDIResearchId(@NotNull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!hasResearchTag(compound)) return null;

        NBTTagCompound sdiresearchCompound = compound.getCompoundTag(RESEARCH_NBT_TAG);
        String sdiresearchId = sdiresearchCompound.getString(SDI_RESEARCH_ID_NBT_TAG);
        return sdiresearchId.isEmpty() ? null : sdiresearchId;
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
