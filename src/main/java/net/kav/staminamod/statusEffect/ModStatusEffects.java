package net.kav.staminamod.statusEffect;

import net.kav.staminamod.StaminaMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStatusEffects {
    public static StatusEffect staminaboost;
    public static StatusEffect registerStatusEffect(String name)
    {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(StaminaMod.MODID, name),
                new staminaboost());
    }

    public static void registerEffects() {
        staminaboost = registerStatusEffect("stamina_boost");
    }
}
