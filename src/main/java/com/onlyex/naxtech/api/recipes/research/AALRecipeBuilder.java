package com.onlyex.naxtech.api.recipes.research;

import com.onlyex.naxtech.api.utils.NTUtility;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IDataItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.util.GTStringUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

import static com.onlyex.naxtech.api.utils.ResearchId.writeResearchToNBT;

public class AALRecipeBuilder extends RecipeBuilder<AALRecipeBuilder> {
    public AALRecipeBuilder() {/**/}

    public AALRecipeBuilder(Recipe recipe, RecipeMap<AALRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public AALRecipeBuilder(RecipeBuilder<AALRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public AALRecipeBuilder copy() {
        return new AALRecipeBuilder(this);
    }

    protected ItemStack researchStack;
    protected ItemStack dataStack;
    protected boolean ignoreNBT;
    protected String researchId;
    //protected int duration;

    public AALRecipeBuilder researchStack(@NotNull ItemStack researchStack) {
        if (!researchStack.isEmpty()) {
            this.researchStack = researchStack;
            this.ignoreNBT = true;
        }
        return this;
    }

    public AALRecipeBuilder researchStack(@NotNull ItemStack researchStack, boolean ignoreNBT) {
        if (!researchStack.isEmpty()) {
            this.researchStack = researchStack;
            this.ignoreNBT = ignoreNBT;
        }
        return this;
    }

    public AALRecipeBuilder dataStack(@NotNull ItemStack dataStack) {
        if (!dataStack.isEmpty()) {
            this.dataStack = dataStack;
        }

        boolean foundBehavior = false;
        if (dataStack.getItem() instanceof MetaItem<?> metaItem) {
            for (IItemBehaviour behaviour : metaItem.getBehaviours(dataStack)) {
                if (behaviour instanceof IDataItem) {
                    foundBehavior = true;
                    dataStack = dataStack.copy();
                    dataStack.setCount(1);
                    break;
                }
            }
        }
        if (!foundBehavior) {
            throw new IllegalArgumentException("Data ItemStack必须具有IDataItem行为");
        }
        return this;
    }

    public AALRecipeBuilder researchId(String researchId) {
        this.researchId = researchId;
        return this;
    }

    public AALRecipeBuilder writeToNBT(@NotNull ItemStack researchStack, @NotNull ItemStack dataStack) {
        //this.researchId = getResearchId();
        if (researchId == null) {
            researchId = GTStringUtils.itemStackToString(researchStack);
        }
        this.researchStack = researchStack;
        this.researchStack = getResearchStack();
        this.dataStack = dataStack;
        this.dataStack = getDataStack();

        NBTTagCompound compound = NTUtility.getOrCreateNbtCompound(dataStack);
        writeResearchToNBT(compound, researchId);
        /*inputs(dataStack)
                .*/inputNBT(researchStack.getItem(), 1, researchStack.getMetadata(), NBTMatcher.ANY, NBTCondition.ANY);

        if (ignoreNBT) {
            inputNBT(dataStack.getItem(), 1, dataStack.getMetadata(), NBTMatcher.ANY, NBTCondition.ANY);
        }else {
            inputs(dataStack);
        }
        return this;
    }

    @NotNull
    public String getResearchId() {
        return this.researchId;
    }

    @NotNull
    public ItemStack getResearchStack() {
        return researchStack;
    }

    @NotNull
    public ItemStack getDataStack() {
        return dataStack;
    }

    public boolean getIgnoreNBT() {
        return ignoreNBT;
    }
}
