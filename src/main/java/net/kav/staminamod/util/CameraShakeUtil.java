package net.kav.staminamod.util;

import net.kav.staminamod.mixin.client.CameraAccessor;
import net.minecraft.client.MinecraftClient;

public class CameraShakeUtil {
    public static float duration = 0.0f;
    public static float intensity = 0.0f;

    public static void startShake(float newIntensity, float newDuration) {
        intensity = newIntensity;
        duration = newDuration;
    }


}