package com.onlyex.naxtech.api.utils;

import com.onlyex.naxtech.api.recipes.NTRecipeMaps;
import com.onlyex.naxtech.api.recipes.research.ResearchLineRecipeBuilder;
import com.onlyex.naxtech.common.items.NTMetaItems;
import com.onlyex.naxtech.common.ConfigHolder;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IDataItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.machines.IScannerRecipeMap;
import gregtech.api.recipes.machines.RecipeMapScanner;
import gregtech.api.recipes.recipeproperties.ScanProperty;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ResearchLineManager {
    public static final String RESEARCH_NBT_TAG = "researchLineResearch";
    public static final String RESEARCH_ID_NBT_TAG = "researchId";

  /*
   * getDefaultScannerItem() 方法，返回的是默认的扫描仪物品。
   * 使用了名为 MetaItems.TOOL_DATA_STICK 的工具类，调用了它的 getStackForm() 方法来获取默认扫描仪物品的 ItemStack。
   */
/*    @NotNull
    public static ItemStack getDefaultScannerItem() {
        return NTMetaItems.RESEARCH_DATA_CARD.getStackForm();
    }*/

    /**
     * getDefaultResearchStationItem(int rwut) 方法，根据传入的参数 rwut 的值，返回默认的研究站物品。
     * 如果 rwut 大于 32，它会返回 MetaItems.TOOL_DATA_MODULE 的 ItemStack；否则，返回 MetaItems.TOOL_DATA_ORB 的 ItemStack。
     */
    @NotNull
    public static ItemStack getDefaultResearchStationItem(int rwut) {
        /*if (rwut > 32) {
            return MetaItems.TOOL_DATA_MODULE.getStackForm();
        }
        return MetaItems.TOOL_DATA_ORB.getStackForm();*/
        return null;
    }

    private ResearchLineManager() {}

    @ApiStatus.Internal
    public static void registerScannerLogic() {
        RecipeMapScanner.registerCustomScannerLogic(new ResearchLineManager.DataStickCopyScannerLogic());
    }

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
     * 首先判断stack的getItem是否是一个MetaItem（元数据物品），如果不是则直接返回false，表示不是数据项。
     * 如果是MetaItem，则将其强制转换为MetaItem<?>类型的变量metaItem。
     * 利用metaItem的getItem方法，从stack中获取一个MetaItem<?>.MetaValueItem对象valueItem。
     * 如果valueItem为null，表示无法获取到MetaItem的值，直接返回false，表示不是数据项。
     * 接下来，遍历valueItem的所有行为（behaviours），对于每个行为，判断是否是一个IDataItem（数据项）。
     * 如果是IDataItem，判断该数据项是否需要数据存储器（requireDataBank）。
     * 如果不需要数据存储器，或者isDataBank为true，则返回true，表示是一个数据项。
     * 如果未找到匹配的IDataItem行为，或者需要数据存储器但isDataBank为false，则返回false，表示不是数据项。
     */
    public static boolean isStackDataItem(@NotNull ItemStack stack, boolean isDataBank) {
        if (stack.getItem() instanceof MetaItem<?> metaItem) {
            MetaItem<?>.MetaValueItem valueItem = metaItem.getItem(stack);
            if (valueItem == null) return false;
            for (IItemBehaviour behaviour : valueItem.getBehaviours()) {
                if (behaviour instanceof IDataItem dataItem) {
                    return !dataItem.requireDataBank() || isDataBank;
                }
            }
        }
        return false;
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

    /**
     * Create the default research recipe
     *通过访问ConfigHolder.machines.enableResearch来检查是否启用了研究功能。
     * 如果没有启用研究功能，则直接返回，不执行后续操作。
     * 接着，使用builder的getRecipeEntries方法获取所有的配方条目，然后依次遍历这些条目。
     * 对于每个条目，通过调用对应的getter方法获取研究ID（researchId）、
     * 研究物品堆栈（researchStack）、数据物品堆栈（dataStack）、忽略NBT标签（ignoreNBT）、
     * 持续时间（duration）、EUt值（eUt）和RWUt值（RWUt）。
     * 然后，调用createDefaultResearchRecipe方法，并传入上述获取到的参数，用于创建默认的研究配方。
     * 这段代码的作用是通过Builder模式创建默认的研究配方。
     * 根据配置文件中的设置，如果启用了研究功能，并且存在配方条目，就依次创建对应的默认研究配方。
     */
    public static void createDefaultResearchRecipe(@NotNull ResearchLineRecipeBuilder builder) {
        if (!ConfigHolder.machines.enableResearch) return;

        for (ResearchLineRecipeBuilder.ResearchRecipeEntry entry : builder.getRecipeEntries()) {
            createDefaultResearchRecipe(entry.getResearchId(), entry.getResearchStack(), entry.getDataStack(),
                    entry.getIgnoreNBT(),
                    entry.getDuration(), entry.getEUt(), entry.getRWUt());
        }
    }
    
    public static void createDefaultResearchRecipe(@NotNull String researchId, @NotNull ItemStack researchItem,
                                                   @NotNull ItemStack dataItem, boolean ignoreNBT, int duration,
                                                   int EUt, int RWUt) {
        if (!ConfigHolder.machines.enableResearch) return;

        NBTTagCompound compound = NTUtility.getOrCreateNbtCompound(dataItem);
        writeResearchToNBT(compound, researchId);

        if (RWUt > 0) {
            RecipeBuilder<?> researchBuilder = NTRecipeMaps.RESEARCH_LINE_RECIPES.recipeBuilder()
                    .inputNBT(dataItem.getItem(), 1, dataItem.getMetadata(), NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(dataItem)
                    .EUt(EUt);
                    //.RWUt(RWUt)
                    //.totalRWU(duration)

            if (ignoreNBT) {
                researchBuilder.inputNBT(researchItem.getItem(), 1, researchItem.getMetadata(), NBTMatcher.ANY,
                        NBTCondition.ANY);
            } else {
                researchBuilder.inputs(researchItem);
            }

            researchBuilder.buildAndRegister();
        }
    }

    public static class DataStickCopyScannerLogic implements IScannerRecipeMap.ICustomScannerLogic {

        private static final int EUT = 2;
        private static final int DURATION = 100;

        /**
         * 在 createCustomRecipe 方法中，根据输入的物品列表的大小判断是否有足够的输入来创建配方。
         * 如果输入物品列表的大小大于 1，则尝试使用 createDataRecipe 方法创建数据配方，并且可以优先选择第一个物品来覆盖第二个物品。
         * 如果创建成功，则返回配方对象。如果没有足够的输入，则返回 null。
         */
        @Override
        public Recipe createCustomRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs,
                                         boolean exactVoltage) {
            if (inputs.size() > 1) {
                // 双向尝试数据配方，优先覆盖第一个
                Recipe recipe = createDataRecipe(inputs.get(0), inputs.get(1));
                if (recipe != null) return recipe;

                return createDataRecipe(inputs.get(1), inputs.get(0));
            }
            return null;
        }

        /**
         *createDataRecipe 方法用于创建数据配方。
         * 它首先检查第二个物品是否有 NBTTagCompound，如果没有则返回 null。
         * 然后，它检查第一个和第二个物品是否均为数据物品，如果不是则返回 null。
         * 接下来，它创建一个新的 ItemStack 对象作为输出，将第一个物品的复制和第二个物品的 NBTTagCompound 复制设置到输出中，
         * 并使用 RecipeMaps.SCANNER_RECIPES.recipeBuilder() 创建一个 RecipeBuilder 对象来构建配方，
         * 指定输入、不会被消耗的物品、输出、持续时间和 EUt，最后使用 build() 方法构建配方并返回结果。
         */
        @Nullable
        private Recipe createDataRecipe(@NotNull ItemStack first, @NotNull ItemStack second) {
            NBTTagCompound compound = second.getTagCompound();
            if (compound == null) return null;

            // 两者都必须是数据项
            if (!isStackDataItem(first, true)) return null;
            if (!isStackDataItem(second, true)) return null;

            ItemStack output = first.copy();
            output.setTagCompound(compound.copy());
            /*return NTRecipeMaps.SCANNER_RECIPES.recipeBuilder()
                    .inputs(first)
                    .notConsumable(second)
                    .outputs(output)
                    .duration(DURATION).EUt(EUT).build().getResult();*/
            return null;
        }

        @Nullable
        @Override
        public List<Recipe> getRepresentativeRecipes() {
            /*ItemStack copiedStick = MetaItems.TOOL_DATA_STICK.getStackForm();
            copiedStick.setTranslatableName("naxtech.scanner.copy_stick_from");
            ItemStack emptyStick = MetaItems.TOOL_DATA_STICK.getStackForm();
            emptyStick.setTranslatableName("naxtech.scanner.copy_stick_empty");
            ItemStack resultStick = MetaItems.TOOL_DATA_STICK.getStackForm();
            resultStick.setTranslatableName("naxtech.scanner.copy_stick_to");
            return Collections.singletonList(
                    NTRecipeMaps.SCANNER_RECIPES.recipeBuilder()
                            .inputs(emptyStick)
                            .notConsumable(copiedStick)
                            .outputs(resultStick)
                            .duration(DURATION).EUt(EUT)
                            .build().getResult());*/
            return null;
        }
    }
}
