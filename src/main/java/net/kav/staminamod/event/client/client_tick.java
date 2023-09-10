package net.kav.staminamod.event.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.statusEffect.ModStatusEffects;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;

public class client_tick implements ClientTickEvents.EndWorldTick{
    int x1 = 0;
    public static int getTick = 0;
    @Override
    public void onEndTick(ClientWorld world) {
        if(getTick>-1)
        {
            if(getTick==0)
            {
                // System.out.println(getTick);
            }

            getTick--;
            if(getTick==-1)
            {
                getTick=0;
            }

        }
        x1++;
        if (x1 >= 5) {
            x1 = 0;

        }
        this.stamina();
        if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().contains("ability1") && Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability1")!=0)
        {
            AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability1")).tick(MinecraftClient.getInstance().player);
        }
        if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().contains("ability2") &&Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability2")!=0)
        {
            AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability2")).tick(MinecraftClient.getInstance().player);
        }
        if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().contains("ability3") &&Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability3")!=0)
        {
            AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability3")).tick(MinecraftClient.getInstance().player);
        }

    }

    public void stamina()
    {
        if (!MinecraftClient.getInstance().player.isSprinting() &&!AttackOveride.getCon() &&!KeyInputHandler.isabilitybeingpress() && !MinecraftClient.getInstance().player.isBlocking() && !MinecraftClient.getInstance().player.isUsingItem()) {
            float recoveryrate= StaminaData.getRECOVERYRATE(((IEntityDataSaver) MinecraftClient.getInstance().player));
            float stamina = StaminaData.getSTAMINA((IEntityDataSaver) MinecraftClient.getInstance().player);
            if(stamina<1)
            {
                PacketByteBuf buff = PacketByteBufs.create();
                buff.writeBoolean(false);
                ClientPlayNetworking.send(ModMessages.LOWSTAMINA, buff);
            }
            if(!MinecraftClient.getInstance().player.getActiveStatusEffects().containsKey(ModStatusEffects.staminaboost))
            {
                StaminaData.incrementSTAMINA(((IEntityDataSaver) MinecraftClient.getInstance().player),recoveryrate+0.25f);
            }

        }
        AttackOveride.setCon(false);
    }
}
