package net.kav.staminamod.mixin.client;

import net.kav.staminamod.util.CameraShakeUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract  class CameraAccessor {
    @Shadow
    abstract float getYaw();
    @Shadow abstract float getPitch();
    @Shadow abstract Vec3d getPos();
    @Shadow abstract void setRotation(float yaw, float pitch);
    @Inject(method = "update", at = @At("RETURN"))
    private void OnCameraUpdate(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci)
    {

        MinecraftClient mc = MinecraftClient.getInstance();

        // System.out.println(intensity+" "+duration+" c");
        if (mc.world != null && mc.player != null && mc.currentScreen == null && CameraShakeUtil.duration > 0) {
            System.out.println(CameraShakeUtil.duration+" "+CameraShakeUtil.intensity+" s");


            float yawShake = (float)(Math.random() - 0.5) * CameraShakeUtil.intensity;
            float pitchShake = (float)(Math.random() - 0.5) * CameraShakeUtil.intensity;

            setRotation(getYaw() + yawShake, getPitch() + pitchShake);

            CameraShakeUtil.duration -= tickDelta;
            if (CameraShakeUtil.duration <= 0) {
                CameraShakeUtil.intensity = 0.0f;
            }
        }
    }


}