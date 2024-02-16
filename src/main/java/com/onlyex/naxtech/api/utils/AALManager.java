package com.onlyex.naxtech.api.utils;

import com.onlyex.naxtech.api.recipes.research.builder.AALRecipeBuilder;
import com.onlyex.naxtech.common.ConfigHolder;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

import static com.onlyex.naxtech.api.recipes.NTRecipeMaps.RESEARCH_RECIPES;
import static com.onlyex.naxtech.api.utils.ResearchId.writeResearchToNBT;

public class AALManager {
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
    public static void createResearchRecipe(@NotNull AALRecipeBuilder builder) {
        if (!ConfigHolder.machines.enableResearch) return;

        for (AALRecipeBuilder.ResearchRecipeEntry entry : builder.getRecipeEntries()) {
            createResearchRecipe(
                    entry.getResearchId(),
                    entry.getResearchStack(),
                    entry.getDataStack(),
                    entry.getIgnoreNBT(),
                    entry.getDuration(),
                    entry.getEUt(),
                    entry.getCWUt(),
                    entry.getRWUt()
            );
        }
    }

    public static void createResearchRecipe(
            @NotNull String researchId, @NotNull ItemStack researchItem, @NotNull ItemStack dataItem,
                                            boolean ignoreNBT, int duration, int EUt, int CWUt, int RWUt) {
        if (!ConfigHolder.machines.enableResearch) return;

        NBTTagCompound compound = NTUtility.getOrCreateNbtCompound(dataItem);
        writeResearchToNBT(compound, researchId);

        if (RWUt > 0) {
            RecipeBuilder<?> researchBuilder = RESEARCH_RECIPES.recipeBuilder()
                    .inputNBT(dataItem.getItem(), 1, dataItem.getMetadata(), NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(dataItem)
                    .EUt(EUt)
                    .CWUt(CWUt)
                    .totalCWU(duration)
                    .RWUt(RWUt)
                    .totalRWU(duration);

            if (ignoreNBT) {
                researchBuilder.inputNBT(researchItem.getItem(), 1, researchItem.getMetadata(), NBTMatcher.ANY,
                        NBTCondition.ANY);
            } else {
                researchBuilder.inputs(researchItem);
            }

            researchBuilder.buildAndRegister();
        }
    }

    /*public static class DataStickCopyScannerLogic implements IScannerRecipeMap.ICustomScannerLogic {

        private static final int EUT = 2;
        private static final int DURATION = 100;

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

        @Nullable
        private Recipe createDataRecipe(@NotNull ItemStack first, @NotNull ItemStack second) {
            NBTTagCompound compound = second.getTagCompound();
            if (compound == null) return null;

            // 两者都必须是数据项
            if (isStackDataItem(first, true)) return null;
            if (isStackDataItem(second, true)) return null;

            ItemStack output = first.copy();
            output.setTagCompound(compound.copy());
            return RecipeMaps.SCANNER_RECIPES.recipeBuilder()
                    .inputs(first)
                    .notConsumable(second)
                    .outputs(output)
                    .duration(DURATION).EUt(EUT).build().getResult();
        }

        @Nullable
        @Override
        public List<Recipe> getRepresentativeRecipes() {
            ItemStack copiedStick = NTMetaItems.RESEARCH_DATA_CARD.getStackForm();
            copiedStick.setTranslatableName("naxtech.scanner.copy_stick_from");
            ItemStack emptyStick = NTMetaItems.RESEARCH_DATA_CARD.getStackForm();
            emptyStick.setTranslatableName("naxtech.scanner.copy_stick_empty");
            ItemStack resultStick = NTMetaItems.RESEARCH_DATA_CARD.getStackForm();
            resultStick.setTranslatableName("naxtech.scanner.copy_stick_to");
            return Collections.singletonList(
                    RecipeMaps.SCANNER_RECIPES.recipeBuilder()
                            .inputs(emptyStick)
                            .notConsumable(copiedStick)
                            .outputs(resultStick)
                            .duration(DURATION).EUt(EUT)
                            .build().getResult());
        }
    }*/

    private AALManager() {}
}
