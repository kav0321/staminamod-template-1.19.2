package net.kav.staminamod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.items.custom.Honey_Fruit_Punch;
import net.kav.staminamod.items.custom.ability_learner;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item HONEY_FRUIT_PUNCH= registerItem("honey_fruit_punch",new Honey_Fruit_Punch(new FabricItemSettings().group(ItemGroup.FOOD).food(ModFood.HONEY_FRUIT_PUNCH).rarity(Rarity.RARE).maxCount(16)));
    public static final Item DODGE_LEANER= registerItem("dodge_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.COMMON).maxCount(1),270));
    public static final Item PARRY_LEANER= registerItem("parry_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON).maxCount(1),180));
    public static final Item KICK_LEANER= registerItem("kick_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON).maxCount(1),90));
    public static final Item DASH_LEANER= registerItem("dash_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.EPIC).maxCount(1),360));
    public static final Item STOMP_LEARNER= registerItem("stomp_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE).maxCount(1),450));
    public static final Item FLIP_ATTACK_SWORD= registerItem("flip_attack_sword_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON).maxCount(1),540));
    public static final Item GUARD_COUNTER= registerItem("guard_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON).maxCount(1),720));
    public static final Item SHIELD_CURSHER= registerItem("shield_crusher",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON).maxCount(1),810));
    public static final Item MEGA_DASH= registerItem("mega_dash",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON).maxCount(1),900));

    public static final Item SWING= registerItem("full_swing_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE).maxCount(1),630));
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registry.ITEM, new Identifier(StaminaMod.MODID, name),item);
    }

    public static void registerModItems()
    {
        StaminaMod.LOGGER.debug("Rendering Mod Item for "+StaminaMod.MODID);
    }

}
