package net.kav.staminamod.mixin.client;

import net.kav.staminamod.data.hurrican_swing_data;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin {

    @Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
    private void tickMovement(CallbackInfo info) {
        // This effectively cancels the processing of movement inputs.
        assert MinecraftClient.getInstance().player != null;
        if(hurrican_swing_data.gettick((IEntityDataSaver) MinecraftClient.getInstance().player)!=0)
       {
           info.cancel();
       }

    }
}
