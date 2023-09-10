package net.kav.staminamod.mixin.server;

import net.kav.staminamod.event.client.AttackOveride;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class doAttackMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;doAttack()Z"), method = "handleInputEvents()V",cancellable = true)
    public void doAttack(CallbackInfo info)
    {
        AttackOveride.attacks();

    }
}
