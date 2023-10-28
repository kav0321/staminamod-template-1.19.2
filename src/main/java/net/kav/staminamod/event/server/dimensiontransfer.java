package net.kav.staminamod.event.server;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.data.AbilityData;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.data.cooldowndata;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.advancement.criterion.ChangedDimensionCriterion;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class dimensiontransfer implements ServerWorldEvents.Load {

    @Override
    public void onWorldLoad(MinecraftServer server, ServerWorld world) {
        System.out.println("a");
        for(AbilityCore ability: AbilityData.getAbility((IEntityDataSaver) (ServerPlayerEntity)(Object)this))
        {

            PacketByteBuf buffer= PacketByteBufs.create();
            buffer.writeInt(ability.ID);
            ServerPlayNetworking.send((ServerPlayerEntity)(Object)this, ModMessages.INITIALIZEABILITY,buffer);
        }

        ServerPlayNetworking.send((ServerPlayerEntity)(Object)this,ModMessages.ABILITYSYNC2,new Packets.AbilitySync(Equipdata.getability((IEntityDataSaver) (ServerPlayerEntity)(Object)this,"ability1"),"ability1").write());
        ServerPlayNetworking.send((ServerPlayerEntity)(Object)this,ModMessages.ABILITYSYNC2,new Packets.AbilitySync(Equipdata.getability((IEntityDataSaver) (ServerPlayerEntity)(Object)this,"ability2"),"ability2").write());
        ServerPlayNetworking.send((ServerPlayerEntity)(Object)this,ModMessages.ABILITYSYNC2,new Packets.AbilitySync(Equipdata.getability((IEntityDataSaver) (ServerPlayerEntity)(Object)this,"ability3"),"ability3").write());

        ServerPlayNetworking.send((ServerPlayerEntity)(Object)this,ModMessages.COOLDOWNC,new Packets.cooldown(cooldowndata.getCooldown((IEntityDataSaver) (ServerPlayerEntity)(Object)this,"ability1"),"ability1").write());
        ServerPlayNetworking.send((ServerPlayerEntity)(Object)this,ModMessages.COOLDOWNC,new Packets.cooldown(cooldowndata.getCooldown((IEntityDataSaver) (ServerPlayerEntity)(Object)this,"ability2"),"ability2").write());
        ServerPlayNetworking.send((ServerPlayerEntity)(Object)this,ModMessages.COOLDOWNC,new Packets.cooldown(cooldowndata.getCooldown((IEntityDataSaver) (ServerPlayerEntity)(Object)this,"ability3"),"ability3").write());
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeFloat(StaminaData.getMAXSTAMINA((IEntityDataSaver) (ServerPlayerEntity)(Object)this));
        ServerPlayNetworking.send((ServerPlayerEntity)(Object)this, ModMessages.DEATH_TRANSFER_MAXSTAMINA,buf);
    }
}

