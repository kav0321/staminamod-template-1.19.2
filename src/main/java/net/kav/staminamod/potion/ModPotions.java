package net.kav.staminamod.potion;

import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.mixin.server.BrewingRecipeRegistryMixin;
import net.kav.staminamod.statusEffect.ModStatusEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPotions {
    public static Potion STAMINA_BOOST;
    public static Potion STAMINA_ABSORPTION;
    public static Potion STAMINA_ABSORPTION2;
    public static Potion STAMINA_DEBUFF;
    public static Potion STAMINA_DEBUFF2;
    public static Potion registerPotion(String name, StatusEffect statusEffect,int duration, int amplifier) {
        return Registry.register(Registry.POTION, new Identifier(StaminaMod.MODID, name),
                new Potion(new StatusEffectInstance(statusEffect, duration, amplifier)));
    }



    public static void registerPotions() {
        STAMINA_BOOST = registerPotion("stamina_boost",ModStatusEffects.staminaboost,1800,0);
        STAMINA_ABSORPTION = registerPotion("stamina_absorption",ModStatusEffects.stamina_absorbtions,2400,0);
        STAMINA_ABSORPTION2 = registerPotion("stamina_absorption2",ModStatusEffects.stamina_absorbtions,1800,1);
        STAMINA_DEBUFF = registerPotion("stamina_debuff",ModStatusEffects.stamina_debuff,1800,0);
        STAMINA_DEBUFF2= registerPotion("stamina_debuff2",ModStatusEffects.stamina_debuff,1400,1);
        registerPotionRecipes();
    }

    private static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.SWEET_BERRIES,
                ModPotions.STAMINA_BOOST);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.SWIFTNESS, Items.SWEET_BERRIES,
                ModPotions.STAMINA_ABSORPTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(STAMINA_ABSORPTION, Items.GOLDEN_APPLE,
                ModPotions.STAMINA_ABSORPTION2);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.HARMING, Items.ROTTEN_FLESH,
                ModPotions.STAMINA_DEBUFF);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.STAMINA_DEBUFF, Items.POISONOUS_POTATO,
                ModPotions.STAMINA_DEBUFF2);
    }
}
