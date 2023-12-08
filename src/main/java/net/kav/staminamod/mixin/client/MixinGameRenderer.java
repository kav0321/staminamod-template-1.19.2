package net.kav.staminamod.mixin.client;

import net.kav.staminamod.util.CameraShakeUtil;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Inject(method = "render", at = @At("TAIL"))
    private void onRenderTail(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {

    }
}
