package com.onlyex.naxtech.common.items;

import com.onlyex.naxtech.api.NTValues;
import gregtech.api.GTValues;
import gregtech.api.items.toolitem.*;
import gregtech.common.items.tool.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;

import static gregtech.common.items.ToolItems.register;

public class NTToolItems {
    public static IGTTool Choocher;
    public static IGTTool Jinitaimei;
    public static IGTTool Choocher_HV;
    public static IGTTool Jinitaimei_HV;
    public static IGTTool HARD_HAMMER_LV;
    public static IGTTool HARD_HAMMER_MV;
    public static IGTTool HARD_HAMMER_HV;
    public static IGTTool HARD_HAMMER_EV;
    public static IGTTool HARD_HAMMER_IV;
    public static  IGTTool VAJRA ;

    private NTToolItems() {/**/}

    public static void init() {
        Choocher = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "choocher")
                .toolStats(b -> b.blockBreaking()
                        .crafting()
                        .damagePerCraftingAction(2)
                        .attackDamage(1.0F)
                        .attackSpeed(-2.8F)
                        .behaviors(new EntityDamageBehavior(2.0F, EntityGolem.class), BlockRotatingBehavior.INSTANCE))
                .oreDict(ToolOreDict.toolHammer)
                .secondaryOreDicts(ToolOreDict.toolWrench)
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .symbol('t')
                .toolClasses("hammer", "wrench"));

        Jinitaimei = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "jinitaimei")
                .toolStats(b -> b.blockBreaking()
                        .crafting()
                        .damagePerCraftingAction(2)
                        .attackDamage(1.0F)
                        .attackSpeed(-2.8F)
                        .behaviors(new EntityDamageBehavior(2.0F, EntityGolem.class), BlockRotatingBehavior.INSTANCE))
                .oreDict(ToolOreDict.toolWireCutter)
                .secondaryOreDicts(ToolOreDict.toolScrewdriver)
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .symbol('a')
                .toolClasses("wirecutter", "screwdriver"));

        Choocher_HV = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "choocher_hv")
                .toolStats(b -> b.blockBreaking()
                        .crafting()
                        .damagePerCraftingAction(2)
                        .attackDamage(1.0F)
                        .attackSpeed(-2.8F)
                        .behaviors(new EntityDamageBehavior(2.0F, EntityGolem.class), BlockRotatingBehavior.INSTANCE))
                .oreDict(ToolOreDict.toolHammer)
                .secondaryOreDicts(ToolOreDict.toolWrench)
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .electric(GTValues.HV)
                .toolClasses("hammer", "wrench"));

        Jinitaimei_HV = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "jinitaimei_hv")
                .toolStats(b -> b.blockBreaking()
                        .crafting()
                        .damagePerCraftingAction(2)
                        .attackDamage(1.0F)
                        .attackSpeed(-2.8F)
                        .behaviors(new EntityDamageBehavior(2.0F, EntityGolem.class), BlockRotatingBehavior.INSTANCE))
                .oreDict(ToolOreDict.toolWireCutter)
                .secondaryOreDicts(ToolOreDict.toolScrewdriver)
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .electric(GTValues.HV)
                .toolClasses("wirecutter", "screwdriver"));


        HARD_HAMMER_LV = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "hammer_lv")
                .toolStats(b -> b.blockBreaking().crafting().damagePerCraftingAction(2)
                        .attackDamage(1.0F).attackSpeed(3.2F).aoe(1, 1, 0)
                        .behaviors(TorchPlaceBehavior.INSTANCE))
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .electric(GTValues.LV)
                .toolClasses(ToolClasses.PICKAXE, ToolClasses.HARD_HAMMER));

        HARD_HAMMER_MV = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "hammer_mv")
                .toolStats(b -> b.blockBreaking().crafting().damagePerCraftingAction(2)
                        .attackDamage(1.0F).attackSpeed(3.6F).aoe(1, 1, 0)
                        .behaviors(TorchPlaceBehavior.INSTANCE))
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .electric(GTValues.MV)
                .toolClasses(ToolClasses.PICKAXE, ToolClasses.HARD_HAMMER));

        HARD_HAMMER_HV = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "hammer_hv")
                .toolStats(b -> b.blockBreaking().crafting().damagePerCraftingAction(2)
                        .attackDamage(1.0F).attackSpeed(4.2F).aoe(1, 1, 0)
                        .defaultEnchantment(Enchantments.FORTUNE,1)
                        .behaviors(TorchPlaceBehavior.INSTANCE))
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .electric(GTValues.HV)
                .toolClasses(ToolClasses.PICKAXE, ToolClasses.HARD_HAMMER));

        HARD_HAMMER_EV = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "hammer_ev")
                .toolStats(b -> b.blockBreaking().crafting().damagePerCraftingAction(2)
                        .attackDamage(1.0F).attackSpeed(4.8F).aoe(1, 1, 0)
                        .defaultEnchantment(Enchantments.FORTUNE,2)
                        .behaviors(TorchPlaceBehavior.INSTANCE))
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .electric(GTValues.EV)
                .toolClasses(ToolClasses.PICKAXE, ToolClasses.HARD_HAMMER));

        HARD_HAMMER_IV = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "hammer_iv")
                .toolStats(b -> b.blockBreaking().crafting().damagePerCraftingAction(2)
                        .attackDamage(1.0F).attackSpeed(5.6F).aoe(1, 1, 0)
                        .defaultEnchantment(Enchantments.FORTUNE,3)
                        .behaviors(TorchPlaceBehavior.INSTANCE))
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .electric(GTValues.IV)
                .toolClasses(ToolClasses.PICKAXE, ToolClasses.HARD_HAMMER));

        VAJRA = register(ItemGTTool.Builder.of(NTValues.MOD_ID, "vajra")
                .toolStats(b -> b.blockBreaking().crafting().damagePerCraftingAction(2)
                        .attackDamage(10.0F).attackSpeed(5.6F).defaultEnchantment(Enchantments.SILK_TOUCH,1)
                        .behaviors(TreeFellingBehavior.INSTANCE)
                        .efficiencyMultiplier(10F)
                        .behaviors(TorchPlaceBehavior.INSTANCE))
                .sound(SoundEvents.BLOCK_ANVIL_LAND)
                .electric(GTValues.IV)
                .toolClasses(ToolClasses.PICKAXE, ToolClasses.AXE,ToolClasses.SWORD,ToolClasses.SHOVEL,ToolClasses.HOE));
    }

}