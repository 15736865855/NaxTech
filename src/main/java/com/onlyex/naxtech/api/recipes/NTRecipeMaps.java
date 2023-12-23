package com.onlyex.naxtech.api.recipes;

import com.onlyex.naxtech.api.recipes.builder.*;
import com.onlyex.naxtech.api.recipes.machines.*;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.AssemblerRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;
@ZenClass("mods.naxtech.recipe.NTRecipeMaps")
@ZenRegister
public class NTRecipeMaps {
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> COMPONENT_ASSEMBLER_RECIPES;

    @ZenProperty
    public static final RecipeMap<AssemblerRecipeBuilder> PACKAGING_LINE_RECIPES;
    @ZenProperty
    public static final RecipeMap<CACasingTierRecipeBuilder> COMPONENT_ASSEMBLY_LINE_RECIPES;
    @ZenProperty
    public static final RecipeMap<PACasingTierRecipeBuilder> PRECISE_ASSEMBLER_RECIPES;
    @ZenProperty
    public static final RecipeMap<NoCoilTemperatureRecipeBuilder> CRYOGENIC_REACTOR_RECIPES;
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> FLOTATION_FACTORY_RECIPES;
    @ZenProperty
    public static final RecipeMap<GrindBallTierRecipeBuilder> ISA_MILL_GRINDER;
    @ZenProperty
    public static final RecipeMap<CasingTierRecipeBuilder> CHEMICAL_PLANT_RECIPES;
    @ZenProperty
    public static final RecipeMap<QFTCasingTierRecipeBuilder> QUANTUM_FORCE_TRANSFORMER_RECIPES;
    public NTRecipeMaps() {}

    static {
        PACKAGING_LINE_RECIPES = new RecipeMap<>("packaging_line_recipes", 9, 1, 1, 0, new AssemblerRecipeBuilder(), false)
                .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
                .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.ASSEMBLER);

        //  Component Assembly Line Recipemap
        COMPONENT_ASSEMBLY_LINE_RECIPES = new RecipeMapComponentAssemblyLine<>("component_assembly_line_recipes", 12, 1,  12, 0, new CACasingTierRecipeBuilder(), false)
                .setSound(GTSoundEvents.ASSEMBLER);

        COMPONENT_ASSEMBLER_RECIPES = new RecipeMap<>("component_assembler_recipes", 6, 1, 1, 0, new SimpleRecipeBuilder(), false)
                .setSlotOverlay(false, false, false, GuiTextures.CIRCUIT_OVERLAY)
                .setSlotOverlay(false, false, true, GuiTextures.CIRCUIT_OVERLAY)
                .setSound(GTSoundEvents.ASSEMBLER);

        PRECISE_ASSEMBLER_RECIPES = new RecipeMapPreciseAssembler<>("precise_assembler_recipes", 4, 1, 4, 0, new PACasingTierRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                .setSlotOverlay(false, false, false, GuiTextures.CIRCUIT_OVERLAY)
                .setSlotOverlay(false, false, true, GuiTextures.CIRCUIT_OVERLAY)
                .setSound(GTSoundEvents.ASSEMBLER);

        CRYOGENIC_REACTOR_RECIPES = new RecipeMap<>("cryogenic_reactor_recipes",  3,  2,  2,  2, new NoCoilTemperatureRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.COOLING);

        //  Flotation Factory Recipemap
        FLOTATION_FACTORY_RECIPES = new RecipeMap<>("flotation_factory_recipes", 3, 3, 3, 3, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.CIRCULAR)
                .setSound(GTSoundEvents.BATH);

        //  Isa Mill Recipemap
        ISA_MILL_GRINDER = new RecipeMap<>("isa_mill_recipes", 3, 3, 0, 0, new GrindBallTierRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.MACERATOR);

        //  Chemical Plant Recipemap
        CHEMICAL_PLANT_RECIPES = new RecipeMapChemicalPlant<>("chemical_plant_recipes", 4, 4, 4, 4, new CasingTierRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
                .setSlotOverlay(false, false, GuiTextures.MOLECULAR_OVERLAY_1)
                .setSlotOverlay(false, true, GuiTextures.MOLECULAR_OVERLAY_4)
                .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
                .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        //  Quantum Force Transformer RecipeMap
        QUANTUM_FORCE_TRANSFORMER_RECIPES = new RecipeMap<>("quantum_force_transformer_recipes",  6,  6,  6,  6, new QFTCasingTierRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.SCIENCE);
    }

}
