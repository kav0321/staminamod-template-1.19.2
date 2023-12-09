package net.kav.staminamod.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.kav.staminamod.StaminaMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup STAMINA = FabricItemGroupBuilder.build(
            new Identifier(StaminaMod.MODID, "stamina"), () -> new ItemStack(ModItems.MEGA_DASH));
}
