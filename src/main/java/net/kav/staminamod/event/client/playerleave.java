package net.kav.staminamod.event.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kav.staminamod.data.*;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.sound.ModSounds;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class playerleave implements ClientPlayConnectionEvents.Disconnect{
    @Override
    public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
        PlayerEntity player= MinecraftClient.getInstance().player;
        Parrydata.settick((IEntityDataSaver) player,0);
        Parrydata.setparryattack((IEntityDataSaver) player,false);

        KickTimingData.setDid_I_kick((IEntityDataSaver) player,false);
        KickTimingData.settick((IEntityDataSaver) player,0);

        sword_dashData.settick((IEntityDataSaver) player,0);
        sword_dashData.settick2((IEntityDataSaver) player,0);
        sword_dashData.settick3((IEntityDataSaver) player,0);
        sword_dashData.setparryattack((IEntityDataSaver) player,false);



        StompData.settick((IEntityDataSaver) player,0);
        StompData.setDid_I_kick((IEntityDataSaver) player,false);

        flipdata.setDid_I_flip((IEntityDataSaver) player,false);
        flipdata.settick((IEntityDataSaver) player,0);
        ClientPlayNetworking.send(ModMessages.LEAVING,new Packets.tick_equip(client_tick.getTick1,client_tick.getTick2,client_tick.getTick3).write());
    }
}
