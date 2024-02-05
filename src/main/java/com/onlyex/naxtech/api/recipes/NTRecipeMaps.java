package com.onlyex.naxtech.api.recipes;

import com.onlyex.naxtech.api.recipes.builders.*;
import com.onlyex.naxtech.api.recipes.builders.research.*;
import com.onlyex.naxtech.api.recipes.machines.*;
import com.onlyex.naxtech.api.recipes.research.*;
import com.onlyex.naxtech.api.recipes.research.builder.*;
import com.onlyex.naxtech.api.utils.ResearchLineManager;
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
/*    @ZenProperty
    public static final RecipeMap<ResearchLineRecipeBuilder> RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "research_line",24, 2, 6, 0,new ResearchLineRecipeBuilder(), false*//*, AssemblyLineUI::new*//*)
            .onRecipeBuild(ResearchLineManager::createDefaultResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);*/

    @ZenProperty
    public static final RecipeMap<ResearchLineRecipeBuilder> RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "research_line",48, 4, 12, 0,new ResearchLineRecipeBuilder(), false)
            .onRecipeBuild(ResearchLineManager::createResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<GOResearchLineRecipeBuilder> GO_RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "go_research_line",48, 4, 12, 0,new GOResearchLineRecipeBuilder(), false)
            .onRecipeBuild(ResearchLineManager::createGOResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<OPResearchLineRecipeBuilder> OP_RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "op_research_line",48, 4, 12, 0,new OPResearchLineRecipeBuilder(), false)
            .onRecipeBuild(ResearchLineManager::createOPResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<SPResearchLineRecipeBuilder> SP_RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "sp_research_line",48, 4, 12, 0,new SPResearchLineRecipeBuilder(), false)
            .onRecipeBuild(ResearchLineManager::createSPResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<COResearchLineRecipeBuilder> CO_RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "co_research_line",48, 4, 12, 0,new COResearchLineRecipeBuilder(), false)
            .onRecipeBuild(ResearchLineManager::createCOResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<SCAResearchLineRecipeBuilder> SCA_RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "sca_research_line",48, 4, 12, 0,new SCAResearchLineRecipeBuilder(), false)
            .onRecipeBuild(ResearchLineManager::createSCAResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<SCHResearchLineRecipeBuilder> SCH_RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "sch_research_line",48, 4, 12, 0,new SCHResearchLineRecipeBuilder(), false)
            .onRecipeBuild(ResearchLineManager::createSCHResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<SDIResearchLineRecipeBuilder> SDI_RESEARCH_LINE_RECIPES = new RecipeMapResearchLine<>(
            "sdi_research_line",48, 4, 12, 0,new SDIResearchLineRecipeBuilder(), false)
            .onRecipeBuild(ResearchLineManager::createSDIResearchRecipe)
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<DataRecipeBuilder> RESEARCH_RECIPES = new RecipeMapResearchLine<>(
            "research",24, 2, 6, 0,new DataRecipeBuilder(), false)
            .setSound(GTSoundEvents.ASSEMBLER);

    public NTRecipeMaps() {}
}
