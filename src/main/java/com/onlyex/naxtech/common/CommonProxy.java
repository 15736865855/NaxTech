package com.onlyex.naxtech.common;

import com.onlyex.naxtech.api.NTValues;
import com.onlyex.naxtech.api.recipes.properties.CACasingTierProperty;
import com.onlyex.naxtech.api.recipes.properties.CasingTierProperty;
import com.onlyex.naxtech.api.recipes.properties.PACasingTierProperty;
import com.onlyex.naxtech.api.utils.NTLog;
import com.onlyex.naxtech.common.items.NTMetaItems;
import com.onlyex.naxtech.loaders.formula.FormulaManager;
import com.onlyex.naxtech.loaders.recipe.handlers.NTRecipeHandlerList;
import gregtech.api.GregTechAPI;
import gregtech.api.block.VariantItemBlock;
import gregtech.api.cover.CoverDefinition;
import gregtech.api.recipes.recipeproperties.FusionEUToStartProperty;
import gregtech.loaders.recipe.CraftingComponent;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

import static com.onlyex.naxtech.common.block.NTMetaBlocks.*;

@Mod.EventBusSubscriber(modid = NTValues.MOD_ID)
public class CommonProxy {

    public static final CreativeTabs NAXTECH_TAB = new CreativeTabs("NaxTech") {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return NTMetaItems.ENERGISED_TESSERACT.getStackForm();
        }
    };

    public CommonProxy() {}

    public void preLoad() {}

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        NTLog.logger.info("Registering blocks...");
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(NT_WIRE_COIL);
        registry.register(GLASS_CASING);
        registry.register(CONTROL_CASING);
        registry.register(MACHINE_CASING);
        registry.register(MACHINE_CASING);
        registry.register(PACKAGING_LINE);
        registry.register(COMPONENT_ASSEMBLY_LINE_CASING);
        registry.register(ADVANCED_ASSEMBLY_LINE_CASING);
        registry.register(PIPELINE_CASING);
        registry.register(ACTIVE_MULTIBLOCK_CASING);
        registry.register(QUANTUM_FORCE_TRANSFORMER_CASING);
        registry.register(QUANTUM_FORCE_TRANSFORMER_GLASS_CASING);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        NTLog.logger.info("Registering Items...");
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(NT_WIRE_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(GLASS_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(CONTROL_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(MACHINE_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(PACKAGING_LINE, VariantItemBlock::new));
        registry.register(createItemBlock(COMPONENT_ASSEMBLY_LINE_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(ADVANCED_ASSEMBLY_LINE_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(PIPELINE_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(ACTIVE_MULTIBLOCK_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(QUANTUM_FORCE_TRANSFORMER_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(QUANTUM_FORCE_TRANSFORMER_GLASS_CASING, VariantItemBlock::new));


    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }

    //  Cover Behavior Event
    @SubscribeEvent
    public static void registerCoverBehavior(GregTechAPI.RegisterEvent<CoverDefinition> event) {
        /*NTCoverBehavior.init();

        for (BlockWireCoil.CoilType type : BlockWireCoil.CoilType.values()) {
            HEATING_COILS.put(NT_WIRE_COIL.getState(type), type);
        }*/
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        NTLog.logger.info("Registering recipes...");
        FusionEUToStartProperty.registerFusionTier(9, "(MK4)");
        FusionEUToStartProperty.registerFusionTier(10, "(MK5)");
        CasingTierProperty.registerCasingTier(1, I18n.format("naxtech.recipe.casing_tier.1"));
        CasingTierProperty.registerCasingTier(2, I18n.format("naxtech.recipe.casing_tier.2"));
        CasingTierProperty.registerCasingTier(3, I18n.format("naxtech.recipe.casing_tier.3"));
        CasingTierProperty.registerCasingTier(4, I18n.format("naxtech.recipe.casing_tier.4"));
        CasingTierProperty.registerCasingTier(5, I18n.format("naxtech.recipe.casing_tier.5"));
        PACasingTierProperty.registerPACasingTier(1, I18n.format("naxtech.machine.precise_assembler.tier.1"));
        PACasingTierProperty.registerPACasingTier(2, I18n.format("naxtech.machine.precise_assembler.tier.2"));
        PACasingTierProperty.registerPACasingTier(3, I18n.format("naxtech.machine.precise_assembler.tier.3"));
        CACasingTierProperty.registerCACasingTier(1, I18n.format("naxtech.machine.component_assembly_line.tier.1"));
        CACasingTierProperty.registerCACasingTier(2, I18n.format("naxtech.machine.component_assembly_line.tier.2"));
        CACasingTierProperty.registerCACasingTier(3, I18n.format("naxtech.machine.component_assembly_line.tier.3"));
        CACasingTierProperty.registerCACasingTier(4, I18n.format("naxtech.machine.component_assembly_line.tier.4"));
        CACasingTierProperty.registerCACasingTier(5, I18n.format("naxtech.machine.component_assembly_line.tier.5"));
        CACasingTierProperty.registerCACasingTier(6, I18n.format("naxtech.machine.component_assembly_line.tier.6"));
        CACasingTierProperty.registerCACasingTier(7, I18n.format("naxtech.machine.component_assembly_line.tier.7"));
        CACasingTierProperty.registerCACasingTier(8, I18n.format("naxtech.machine.component_assembly_line.tier.8"));
        CACasingTierProperty.registerCACasingTier(9, I18n.format("naxtech.machine.component_assembly_line.tier.9"));
        CACasingTierProperty.registerCACasingTier(10, I18n.format("naxtech.machine.component_assembly_line.tier.10"));
        CACasingTierProperty.registerCACasingTier(11, I18n.format("naxtech.machine.component_assembly_line.tier.11"));
        CACasingTierProperty.registerCACasingTier(12, I18n.format("naxtech.machine.component_assembly_line.tier.12"));
        CACasingTierProperty.registerCACasingTier(13, I18n.format("naxtech.machine.component_assembly_line.tier.13"));
        CACasingTierProperty.registerCACasingTier(14, I18n.format("naxtech.machine.component_assembly_line.tier.14"));
        FormulaManager.init();
    }

    @SubscribeEvent
    public static void registerRecipeHandlers(RegistryEvent.Register<IRecipe> event) {
        NTRecipeHandlerList.register();
    }

    //  Crafting Component Event
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void initComponents(GregTechAPI.RegisterEvent<CraftingComponent> event) {
        //MaterialComponents.init();
    }
}
