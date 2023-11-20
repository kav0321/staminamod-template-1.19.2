package net.kav.staminamod.mixin.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.playerstaminapacketS2C;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
    @Inject(method = "addPlayer", at = @At("TAIL"))
    private void onPlayerAdded(int entityId, AbstractClientPlayerEntity player, CallbackInfo ci) {
        if (MinecraftClient.getInstance().player != null && player.getId() == MinecraftClient.getInstance().player.getId()) {
            // Your code here to handle client-side rendering or data syncing when the player spawns in a new dimension.
            PacketByteBuf bufm = PacketByteBufs.empty();

            playerstaminapacketS2C.modified=false;
            ClientPlayNetworking.send(ModMessages.INITIALIZEC2S,bufm);
            PacketByteBuf bufs = PacketByteBufs.empty();


            ClientPlayNetworking.send(ModMessages.INITIALIZEC2S_MAXSTAMINA,bufs);
            ClientPlayNetworking.send(ModMessages.EXTRA_STAMINA_SYN,PacketByteBufs.empty());


        }
    }
}