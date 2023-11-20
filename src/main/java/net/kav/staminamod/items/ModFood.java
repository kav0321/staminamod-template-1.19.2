package net.kav.staminamod.items;

import net.kav.staminamod.statusEffect.ModStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFood {
    public static final FoodComponent HONEY_FRUIT_PUNCH = new FoodComponent.Builder().hunger(6).saturationModifier(1.0f).statusEffect(new StatusEffectInstance(ModStatusEffects.staminaboost, 1200, 2), 1.0f).alwaysEdible().build();
}
