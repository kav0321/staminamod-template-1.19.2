package net.kav.staminamod.event.server;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.data.AbilityData;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class playerdeath implements ServerPlayerEvents.AfterRespawn {
    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        this.Maxstamina(oldPlayer,newPlayer,alive);
        this.IAbilitytransfer(oldPlayer,newPlayer,alive);
    }

    public void IAbilitytransfer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive)
    {
        AbilityData.setAbility((IEntityDataSaver) newPlayer,AbilityData.getAbility((IEntityDataSaver) oldPlayer));
        for(AbilityCore ability: AbilityData.getAbility((IEntityDataSaver) oldPlayer))
        {
            PacketByteBuf buffer= PacketByteBufs.create();
            buffer.writeInt(ability.ID);
            ServerPlayNetworking.send(newPlayer,ModMessages.INITIALIZEABILITY,buffer);
        }
        Equipdata.addability((IEntityDataSaver) newPlayer,Equipdata.getability((IEntityDataSaver) oldPlayer,"ability1"),"ability1");
        Equipdata.addability((IEntityDataSaver) newPlayer,Equipdata.getability((IEntityDataSaver) oldPlayer,"ability2"),"ability2");
        Equipdata.addability((IEntityDataSaver) newPlayer,Equipdata.getability((IEntityDataSaver) oldPlayer,"ability3"),"ability3");

        ServerPlayNetworking.send(newPlayer,ModMessages.ABILITYSYNC2,new Packets.AbilitySync(Equipdata.getability((IEntityDataSaver) newPlayer,"ability1"),"ability1").write());
        ServerPlayNetworking.send(newPlayer,ModMessages.ABILITYSYNC2,new Packets.AbilitySync(Equipdata.getability((IEntityDataSaver) newPlayer,"ability2"),"ability2").write());
        ServerPlayNetworking.send(newPlayer,ModMessages.ABILITYSYNC2,new Packets.AbilitySync(Equipdata.getability((IEntityDataSaver) newPlayer,"ability3"),"ability3").write());
    }
    public void Maxstamina(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive)
    {
        StaminaData.setMAXSTAMINA((IEntityDataSaver) newPlayer, StaminaData.getMAXSTAMINA((IEntityDataSaver) oldPlayer));
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeFloat(StaminaData.getMAXSTAMINA((IEntityDataSaver) oldPlayer));
        ServerPlayNetworking.send(newPlayer, ModMessages.DEATH_TRANSFER_MAXSTAMINA,buf);
    }
}
