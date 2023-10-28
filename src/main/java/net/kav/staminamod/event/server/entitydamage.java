package net.kav.staminamod.event.server;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.data.guarddata;
import net.kav.staminamod.sound.ModSounds;
import net.kav.staminamod.util.IEntityDataSaver;
import net.kav.staminamod.util.IPosture;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class entitydamage implements ServerLivingEntityEvents.AllowDamage{
    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if(entity instanceof PlayerEntity)
        {
            if(!guarddata.getguard(((IEntityDataSaver) entity)))
            {

            }
        }
        else
        {

        }



        return true;
    }
}
