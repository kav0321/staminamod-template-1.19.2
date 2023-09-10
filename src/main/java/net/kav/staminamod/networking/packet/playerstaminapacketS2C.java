package net.kav.staminamod.networking.packet;

import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.data.AbilityData;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.util.GlobalStamina;
import net.kav.staminamod.util.IEntityDataSaver;
import net.kav.staminamod.util.IModAnimatedPlayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class playerstaminapacketS2C {
    public static void death_transfer_maxstamina(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        StaminaData.setMAXSTAMINA((IEntityDataSaver) client.player,buf.readFloat());
    }

    public static void ability_animation(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.AbilityAni.read(buf);
        client.execute(() -> {
            var entity = client.world.getEntityById(packet.playerId());

            var animationContainer = ((IModAnimatedPlayer) entity).modid_getModAnimation();
            KeyframeAnimation animationL =  PlayerAnimationRegistry.getAnimation(new Identifier(StaminaMod.MODID, AbilityManager.abiltyregister.get(packet.index()).animationname));
            var builder = animationL.mutableCopy();


            animationL = builder.build();




            animationContainer.setAnimation(new KeyframeAnimationPlayer(animationL));
        });
    }
    public static void abilitysync(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        System.out.println("gay");
        final var packet = Packets.AbilitySync.read(buf);
        Equipdata.addability((IEntityDataSaver) MinecraftClient.getInstance().player,packet.id(),packet.slot());
    }
    public static void weapon_cost(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        float weapon_cost= buf.readFloat();

        StaminaData.decrementSTAMINA(((IEntityDataSaver) client.player),weapon_cost);
        if(StaminaData.getSTAMINA(((IEntityDataSaver) client.player))<=1)
        {
            PacketByteBuf buff= PacketByteBufs.create();

            buff.writeString(client.player.getName().getString());

            ClientPlayNetworking.send(ModMessages.LOWSTAMINA, buff);

        }
    }

    public static void initializes2c(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.stamina_location.read(buf);
        if(client.player!=null)
        {
            GlobalStamina.X=packet.x();
            GlobalStamina.Y=packet.y();
            System.out.println(GlobalStamina.X+" "+GlobalStamina.Y);
        }
    }
    public static void initializes2c_stamina(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {

        if(client.player!=null)
        {

           StaminaData.setMAXSTAMINA(((IEntityDataSaver) client.player),buf.readFloat());
        }
    }

    public static void initializeability(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        if(client.player!=null)
        {
            AbilityData.addAbility((IEntityDataSaver) client.player,AbilityManager.abiltyregister.get(buf.readInt()));
        }
    }

    public static void ability_added(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        AbilityData.addAbility((IEntityDataSaver) client.player, AbilityManager.abilityCoreList.get(0));
    }

    public static void shield_info(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        if(StaminaData.getSTAMINA(((IEntityDataSaver) client.player))<2)
        {
            client.player.disableShield(false);
            ClientPlayNetworking.send(ModMessages.SHIELDING,PacketByteBufs.empty());
        }
    }
}
