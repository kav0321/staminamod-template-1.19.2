package net.kav.staminamod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.items.custom.Honey_Fruit_Punch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item HONEY_FRUIT_PUNCH= registerItem("honey_fruit_punch",new Honey_Fruit_Punch(new FabricItemSettings().group(ItemGroup.FOOD).food(ModFood.HONEY_FRUIT_PUNCH)));
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registry.ITEM, new Identifier(StaminaMod.MODID, name),item);
    }

    public static void registerModItems()
    {
        StaminaMod.LOGGER.debug("Rendering Mod Item for "+StaminaMod.MODID);
    }

}
