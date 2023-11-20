package net.kav.staminamod.event.server;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.data.AbilityData;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.enchantment.ModEnchantments;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.statusEffect.ModStatusEffects;
import net.kav.staminamod.util.IEntityDataSaver;
import net.kav.staminamod.util.IPosture;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class server_tick  implements ServerTickEvents.EndWorldTick{
    int tick=0;
    @Override
    public void onEndTick(ServerWorld world) {
        tick++;
        for(ServerPlayerEntity player: world.getServer().getPlayerManager().getPlayerList())
        {


            IPosture pl1 = (IPosture) player;

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
