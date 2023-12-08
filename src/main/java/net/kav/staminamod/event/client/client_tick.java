package net.kav.staminamod.event.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.statusEffect.ModStatusEffects;
import net.kav.staminamod.util.CameraShakeUtil;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class client_tick implements ClientTickEvents.EndWorldTick{
    int x1 = 0;
    int ticks=0;
    public static int getTick1 = 0;
    public static int getTick2 = 0;
    public static int getTick3 = 0;



    public static int CURRENTT = 0;
    public static int CURRENTT2 = 0;
    public static int CURRENTT3 = 0;
    @Override
    public void onEndTick(ClientWorld world) {
        if(MinecraftClient.getInstance().player.isSneaking())
        {
            //CameraShakeUtil.startShake(5, 100);
        }
        ticks++;
        if(getTick1 >-1)
        {
            if(getTick1 ==0)
            {
                //
            }
           // System.out.println("t");
            if(ticks%10==0&&getTick1!=0)
            {
                ClientPlayNetworking.send(ModMessages.LEAVING2,new Packets.tick_equip(client_tick.getTick1,client_tick.getTick2,client_tick.getTick3).write());
            }
            //System.out.println(getTick1);
            getTick1--;
            if(getTick1 ==-1)
            {
                getTick1 =0;
            }

        }

        if(getTick2 >-1)
        {
            if(getTick2 ==0)
            {

            }
            //System.out.println("t");
            if(ticks%10==0 &&getTick2!=0)
            {
                ClientPlayNetworking.send(ModMessages.LEAVING2,new Packets.tick_equip(client_tick.getTick1,client_tick.getTick2,client_tick.getTick3).write());
            }

            getTick2--;
            if(getTick2 ==-1)
            {
                getTick2 =0;
            }

        }

        if(getTick3 >-1&&getTick3!=0)
        {
            if(getTick3 ==0)
            {

            }
            //System.out.println("t");
            if(ticks%20==0&&getTick3!=0)
            {
                ClientPlayNetworking.send(ModMessages.LEAVING2,new Packets.tick_equip(client_tick.getTick1,client_tick.getTick2,client_tick.getTick3).write());
                ticks=0;
            }
            getTick3--;
            if(getTick3 ==-1)
            {
                getTick3 =0;
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
                StaminaData.incrementSTAMINA(((IEntityDataSaver) MinecraftClient.getInstance().player),recoveryrate);
            }

        }
        AttackOveride.setCon(false);
    }
    public static int getGetTick(String tick)
    {
        if(tick=="ability1")
        {
            return client_tick.getTick1;
        }
        else if(tick=="ability2")
        {
            return  client_tick.getTick2;
        }
        else if(tick=="ability3")
        {
            return  client_tick.getTick3;
        }

        return  client_tick.getTick1;
    }


    public static void setGetTick(String tick,int ticks)
    {
        if(tick=="ability1")
        {
             client_tick.getTick1=ticks;
        }
        else if(tick=="ability2")
        {
            client_tick.getTick2=ticks;
        }
        else if(tick=="ability3")
        {
            client_tick.getTick3=ticks;
        }


    }

    public static void decreaseTick(String tick,int amount)
    {
        if(tick=="ability1")
        {
            client_tick.getTick1-=amount;
        }
        else if(tick=="ability2")
        {
            client_tick.getTick2-=amount;
        }
        else if(tick=="ability3")
        {
            client_tick.getTick3-=amount;
        }


    }
}
