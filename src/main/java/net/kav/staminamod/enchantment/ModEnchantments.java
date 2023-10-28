package net.kav.staminamod.enchantment;

import net.kav.staminamod.StaminaMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {
    public static Enchantment Stamina_Absorption = register("stamina_absorption",
            new StaminaAbsorptionEnchantment(Enchantment.Rarity.RARE,
                    EnchantmentTarget.ARMOR_CHEST, EquipmentSlot.CHEST));

    public static Enchantment Stamina_debuff = register("stamina_debuff",
            new StaminadebuffEnchantment(Enchantment.Rarity.UNCOMMON,
                    EnchantmentTarget.ARMOR_CHEST, EquipmentSlot.CHEST));


    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(StaminaMod.MODID, name), enchantment);
    }

    public static void registerModEnchantments() {
        //System.out.println("Registering Enchantments for " + StaminaMod.MODID);
    }
}
