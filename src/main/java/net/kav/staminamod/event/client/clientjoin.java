package net.kav.staminamod.event.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.Packets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class clientjoin implements ClientPlayConnectionEvents.Join{
    @Override
    public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
        PacketByteBuf bufm = PacketByteBufs.empty();


        ClientPlayNetworking.send(ModMessages.INITIALIZEC2S,bufm);
        PacketByteBuf bufs = PacketByteBufs.empty();


        ClientPlayNetworking.send(ModMessages.INITIALIZEC2S_MAXSTAMINA,bufs);




    }
}
