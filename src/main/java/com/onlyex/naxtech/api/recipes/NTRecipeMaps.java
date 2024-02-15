package com.onlyex.naxtech.api.recipes;

import com.onlyex.naxtech.api.recipes.builders.CACasingTierRecipeBuilder;
import com.onlyex.naxtech.api.recipes.builders.NoCoilTemperatureRecipeBuilder;
import com.onlyex.naxtech.api.recipes.builders.PACasingTierRecipeBuilder;
import com.onlyex.naxtech.api.recipes.builders.QFTCasingTierRecipeBuilder;
import com.onlyex.naxtech.api.recipes.research.DataRecipeBuilder;
import com.onlyex.naxtech.api.recipes.machines.RecipeMapComponentAssemblyLine;
import com.onlyex.naxtech.api.recipes.machines.RecipeMapPreciseAssembler;
import com.onlyex.naxtech.api.recipes.research.RecipeMapAAL;
import com.onlyex.naxtech.api.recipes.research.RecipeMapResearchStation;
import com.onlyex.naxtech.api.recipes.research.builder.AALRecipeBuilder;
import com.onlyex.naxtech.api.utils.AALManager;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;
@ZenClass("com.onlyex.naxtech.recipe.NTRecipeMaps")
@ZenRegister
public final class NTRecipeMaps {
    @ZenProperty
    public static final RecipeMap<CACasingTierRecipeBuilder> COMPONENT_ASSEMBLY_LINE_RECIPES = new RecipeMapComponentAssemblyLine<>(
            "component_assembly_line_recipes",
                        12, 1,  12, 0,
                        new CACasingTierRecipeBuilder(), false)
                .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> COMPONENT_ASSEMBLER_RECIPES = new RecipeMap<>(
            "component_assembler_recipes",
                    6, 1, 1, 0,
                    new SimpleRecipeBuilder(), false)
                .setSlotOverlay(false, false, false, GuiTextures.CIRCUIT_OVERLAY)
                .setSlotOverlay(false, false, true, GuiTextures.CIRCUIT_OVERLAY)
                .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<PACasingTierRecipeBuilder> PRECISE_ASSEMBLER_RECIPES = new RecipeMapPreciseAssembler<>(
            "precise_assembler_recipes",
                    4, 1, 4, 0,
                    new PACasingTierRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                .setSlotOverlay(false, false, false, GuiTextures.CIRCUIT_OVERLAY)
                .setSlotOverlay(false, false, true, GuiTextures.CIRCUIT_OVERLAY)
                .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<NoCoilTemperatureRecipeBuilder> CRYOGENIC_REACTOR_RECIPES = new RecipeMap<>(
            "cryogenic_reactor_recipes",
                    3,  2,  2,  2,
                    new NoCoilTemperatureRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.COOLING);

        //  Flotation Factory Recipemap
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> FLOTATION_FACTORY_RECIPES = new RecipeMap<>(
            "flotation_factory_recipes",
                        3, 3, 3, 3,
                        new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.CIRCULAR)
                .setSound(GTSoundEvents.BATH);

        //  Quantum Force Transformer RecipeMap
    @ZenProperty
    public static final RecipeMap<QFTCasingTierRecipeBuilder> QUANTUM_FORCE_TRANSFORMER_RECIPES = new RecipeMap<>(
            "quantum_force_transformer_recipes",
                        6,  6,  6,  6,
                        new QFTCasingTierRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.SCIENCE);



    /////////////////////////////////////////////////////TODO MUI
/*    @ZenProperty
    public static final RecipeMap<AALRecipeBuilder> AAL_RECIPES = new RecipeMapAAL<>(
            "research_line",new AALRecipeBuilder(), false, AssemblyLineUI::new)
            .onRecipeBuild(AALManager::createDefaultResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);*/

    @ZenProperty
    public static final RecipeMap<DataRecipeBuilder> RESEARCH_RECIPES = new RecipeMapResearchStation<>(
            "research",
            24, 2, 6, 0,
            new DataRecipeBuilder(), false);

    public NTRecipeMaps() {}
}
