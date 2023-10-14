package net.kav.staminamod.api.damagesource;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;

public class Moddamagesource {



    public static DamageSource mob(LivingEntity attacker) {
        return new EntityDamageSource("mob", attacker);
    }
}
