package net.kav.staminamod.event.server;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.data.AbilityData;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class server_tick  implements ServerTickEvents.EndWorldTick{
    @Override
    public void onEndTick(ServerWorld world) {
        for(ServerPlayerEntity player: world.getServer().getPlayerManager().getPlayerList())
        {
            if(player.isSneaking() && player.getMainHandStack().getItem()== Items.DIAMOND)
            {

                AbilityData.addAbility((IEntityDataSaver) player,AbilityManager.abiltyregister.get(90));
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt(90);
                ServerPlayNetworking.send(player, ModMessages.ABILITY_ADDED, buf);
            }

            if(player.isSneaking()&& player.getMainHandStack().getItem()== Items.COPPER_INGOT )
            {

                AbilityData.addAbility((IEntityDataSaver) player,AbilityManager.abiltyregister.get(270));
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt(270);

               ServerPlayNetworking.send(player, ModMessages.ABILITY_ADDED, buf);
            }

            if(((IEntityDataSaver) player).getPersistentData().contains("ability1") && Equipdata.getability(((IEntityDataSaver) player),"ability1")!=0)
            {
                AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) player),"ability1")).tick(player);
            }
            if(((IEntityDataSaver) player).getPersistentData().contains("ability2") &&Equipdata.getability(((IEntityDataSaver) player),"ability2")!=0)
            {
                AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) player),"ability2")).tick(player);
            }
            if(((IEntityDataSaver) player).getPersistentData().contains("ability3") &&Equipdata.getability(((IEntityDataSaver) player),"ability3")!=0)
            {
                AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) player),"ability3")).tick(player);
            }
        }
    }
}
