package net.kav.staminamod.statusEffect;

import net.kav.staminamod.StaminaMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStatusEffects {
    public static StatusEffect staminaboost;
    public static StatusEffect stamina_absorbtions;
    public static StatusEffect registerStatusEffect(String name)
    {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(StaminaMod.MODID, name),
                new staminaboost());
    }
    public static StatusEffect registerStatusEffect2(String name)
    {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(StaminaMod.MODID, name),
                new stamina_absorbsion());
    }
    public static void registerEffects() {
        staminaboost = registerStatusEffect("stamina_boost");
        stamina_absorbtions= registerStatusEffect2("stamina_absorbtion");
    }
}
