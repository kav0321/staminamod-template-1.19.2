package net.kav.staminamod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.items.custom.Honey_Fruit_Punch;
import net.kav.staminamod.items.custom.ability_learner;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item HONEY_FRUIT_PUNCH= registerItem("honey_fruit_punch",new Honey_Fruit_Punch(new FabricItemSettings().group(ItemGroup.FOOD).food(ModFood.HONEY_FRUIT_PUNCH)));
    public static final Item DODGE_LEANER= registerItem("dodge_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT),270));
    public static final Item PARRY_LEANER= registerItem("parry_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT),180));
    public static final Item KICK_LEANER= registerItem("kick_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT),90));
    public static final Item DASH_LEANER= registerItem("dash_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT),360));
    public static final Item STOMP_LEARNER= registerItem("stomp_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT),450));
    public static final Item FLIP_ATTACK_SWORD= registerItem("flip_attack_sword_learner",new ability_learner(new FabricItemSettings().group(ItemGroup.COMBAT),540));
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registry.ITEM, new Identifier(StaminaMod.MODID, name),item);
    }

    public static void registerModItems()
    {
        StaminaMod.LOGGER.debug("Rendering Mod Item for "+StaminaMod.MODID);
    }

}
