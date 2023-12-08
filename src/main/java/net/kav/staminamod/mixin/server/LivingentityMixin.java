package net.kav.staminamod.mixin.server;

import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.util.IPosture;
import net.kav.staminamod.util.ModEntityAttributes;
import net.kav.staminamod.util.entityposture;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.kav.staminamod.config.ModConfigs.posturerecovery_per_tick;

@Mixin(LivingEntity.class)
public abstract class LivingentityMixin implements IPosture {
    int tick=0;
    private static final TrackedData<Float> POSTURE = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        tick++;
        if(tick%60==0)
        {
            incrementposture_float(Math.abs(posturerecovery_per_tick));
        }
    }
   // writeCustomDataToNbt

    @Inject(method = "initDataTracker", at = @At("HEAD"))
    private void injectWriteMethod(CallbackInfo info) {
        EntityType<?> entityType = ((LivingEntity)(Object)this).getType();
        Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
        String nameentity = entityId.toString();
        float posture= ModConfigs.PlayerPosture;

        for(String name: entityposture.entityname)
        {
            if(name.equals(nameentity))
            {
                posture=entityposture.getposture(name);
                System.out.println(posture+ " for "+name);
            }

        }
        ((LivingEntity)(Object)this).getDataTracker().startTracking(POSTURE, Float.valueOf(posture));

    }
    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void writeCustomDataToNbt(NbtCompound nbt,CallbackInfo info) {

        nbt.putFloat("Posture", this.getposture_number());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void readCustomDataFromNbt(NbtCompound nbt,CallbackInfo info) {
    if(nbt.contains("Posture"))
    {
        this.setposture_float(nbt.getFloat("Posture"));
    }

    }
    @Override
    public TrackedData<Float> getposture() {
        return POSTURE;
    }

    @Override
    public float getposture_number() {
        return ((LivingEntity)(Object)this).getDataTracker().get(POSTURE).floatValue();
    }

    @Override
    public void setposture_float(float posture) {
        ((LivingEntity)(Object)this).getDataTracker().set(POSTURE, Float.valueOf(MathHelper.clamp(posture, 0.0f, 20)));
    }


    @Override
    public void incrementposture_float(float posture) {
        float currentposture= getposture_number();
        ((LivingEntity)(Object)this).getDataTracker().set(POSTURE, Float.valueOf(MathHelper.clamp(currentposture+posture, 0.0f, 20)));
    }

    @Override
    public float getmaxposture() {
        EntityType<?> entityType = ((LivingEntity)(Object)this).getType();
        Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
        String nameentity = entityId.toString();
        float posture=20f;
        for(String name: entityposture.entityname)
        {
            if(name.equals(nameentity))
            {
                posture=entityposture.getposture(name);
            }
        }
        return posture;
    }
}
