package net.kav.staminamod.mixin.server;


import net.kav.staminamod.potion.ModPotions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitchEntity.class)
public class WitchEntityMixin {

    @Inject(at = @At(value = "HEAD"),   method = "attack",  cancellable = true)
    private void attack(LivingEntity target, float pullProgress, CallbackInfo info) {
        if (((WitchEntity)(Object)this).isDrinking()) { // Replace with the correct method for checking if the Witch is drinking
            return;
        }
        Vec3d vec3d = target.getVelocity();
        double d = target.getX() + vec3d.x - ((WitchEntity)(Object)this).getX();
        double e = target.getEyeY() - (double) 1.1f - ((WitchEntity)(Object)this).getY();
        double f = target.getZ() + vec3d.z - ((WitchEntity)(Object)this).getZ();
        double g = Math.sqrt(d * d + f * f);
        Potion potion =(((WitchEntity)(Object)this).getRandom().nextFloat() < 0.50f) ?Potions.HARMING:ModPotions.STAMINA_DEBUFF;
        if (target instanceof RaiderEntity) {
            potion = target.getHealth() <= 4.0f ? Potions.HEALING : Potions.REGENERATION;
            ((WitchEntity)(Object)this).setTarget(null);
        }

        else if (g >= 8.0 && !target.hasStatusEffect(StatusEffects.SLOWNESS)) {
            potion = Potions.SLOWNESS;
        } else if (target.getHealth() >= 8.0f && !target.hasStatusEffect(StatusEffects.POISON)) {
            potion = (((WitchEntity)(Object)this).getRandom().nextFloat() < 0.55f) ?Potions.POISON:ModPotions.STAMINA_DEBUFF2;
        } else if (g <= 3.0 && !target.hasStatusEffect(StatusEffects.WEAKNESS) && ((WitchEntity)(Object)this).getRandom().nextFloat() < 0.25f) {
            potion = (((WitchEntity)(Object)this).getRandom().nextFloat() < 0.55f) ?Potions.WEAKNESS:ModPotions.STAMINA_DEBUFF2;
        }
        PotionEntity potionEntity = new PotionEntity(((WitchEntity)(Object)this).world, ((WitchEntity)(Object)this));
        potionEntity.setItem(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
        potionEntity.setPitch(potionEntity.getPitch() - -20.0f);
        potionEntity.setVelocity(d, e + g * 0.2, f, 0.75f, 8.0f);
        if (!((WitchEntity)(Object)this).isSilent()) {
            ((WitchEntity)(Object)this).world.playSound(null, ((WitchEntity)(Object)this).getX(), ((WitchEntity)(Object)this).getY(), ((WitchEntity)(Object)this).getZ(), SoundEvents.ENTITY_WITCH_THROW, ((WitchEntity)(Object)this).getSoundCategory(), 1.0f, 0.8f + ((WitchEntity)(Object)this).getRandom().nextFloat() * 0.4f);
        }
        ((WitchEntity)(Object)this).world.spawnEntity(potionEntity);
        info.cancel(); // If you want to cancel the original method, you can add this line.
    }


}
