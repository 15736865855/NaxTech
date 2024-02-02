package com.onlyex.naxtech.api.recipes;

import com.onlyex.naxtech.api.recipes.builders.*;
import com.onlyex.naxtech.api.recipes.machines.RecipeMapComponentAssemblyLine;
import com.onlyex.naxtech.api.recipes.machines.RecipeMapPreciseAssembler;
import com.onlyex.naxtech.api.recipes.research.RecipeMapResearchLine;
import com.onlyex.naxtech.api.recipes.research.ResearchLineRecipeBuilder;
import com.onlyex.naxtech.api.utils.ResearchLineManager;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.AssemblerRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;
@ZenClass("com.onlyex.naxtech.recipe.NTRecipeMaps")
@ZenRegister
public final class NTRecipeMaps {

    @ZenProperty
    public static final RecipeMap<AssemblerRecipeBuilder> PACKAGING_LINE_RECIPES = new RecipeMap<>
            ("packaging_line_recipes",
                    9, 1, 1, 0,
                    new AssemblerRecipeBuilder(), false)
                .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
                .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.ASSEMBLER);

        //  Component Assembly Line Recipemap
    @ZenProperty
    public static final RecipeMap<CACasingTierRecipeBuilder> COMPONENT_ASSEMBLY_LINE_RECIPES = new RecipeMapComponentAssemblyLine<>
                ("component_assembly_line_recipes",
                        12, 1,  12, 0,
                        new CACasingTierRecipeBuilder(), false)
                .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> COMPONENT_ASSEMBLER_RECIPES = new RecipeMap<>
            ("component_assembler_recipes",
                    6, 1, 1, 0,
                    new SimpleRecipeBuilder(), false)
                .setSlotOverlay(false, false, false, GuiTextures.CIRCUIT_OVERLAY)
                .setSlotOverlay(false, false, true, GuiTextures.CIRCUIT_OVERLAY)
                .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<PACasingTierRecipeBuilder> PRECISE_ASSEMBLER_RECIPES = new RecipeMapPreciseAssembler<>
            ("precise_assembler_recipes",
                    4, 1, 4, 0,
                    new PACasingTierRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                .setSlotOverlay(false, false, false, GuiTextures.CIRCUIT_OVERLAY)
                .setSlotOverlay(false, false, true, GuiTextures.CIRCUIT_OVERLAY)
                .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<NoCoilTemperatureRecipeBuilder> CRYOGENIC_REACTOR_RECIPES = new RecipeMap<>
            ("cryogenic_reactor_recipes",
                    3,  2,  2,  2,
                    new NoCoilTemperatureRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.COOLING);

        //  Flotation Factory Recipemap
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> FLOTATION_FACTORY_RECIPES = new RecipeMap<>
                ("flotation_factory_recipes",
                        3, 3, 3, 3,
                        new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.CIRCULAR)
                .setSound(GTSoundEvents.BATH);

        //  Quantum Force Transformer RecipeMap
    @ZenProperty
    public static final RecipeMap<QFTCasingTierRecipeBuilder> QUANTUM_FORCE_TRANSFORMER_RECIPES = new RecipeMap<>
                ("quantum_force_transformer_recipes",
                        6,  6,  6,  6,
                        new QFTCasingTierRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.SCIENCE);



    /////////////////////////////////////////////////////TODO TODO
    @ZenProperty
    public static final RecipeMap<ResearchLineRecipeBuilder> RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "research_line",12, 12, 12, 12,new ResearchLineRecipeBuilder(), false/*, AssemblyLineUI::new*/)
            .onRecipeBuild(ResearchLineManager::createDefaultResearchRecipe);//TODO

    public NTRecipeMaps() {}
}
