package net.kav.staminamod.networking.packet;


import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.api.multiple_animations;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.data.AbilityData;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.data.cooldowndata;
import net.kav.staminamod.event.client.client_tick;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.util.GlobalStamina;
import net.kav.staminamod.util.IEntityDataSaver;
import net.kav.staminamod.util.IModAnimatedPlayer;
import net.kav.staminamod.util.firstperson;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class playerstaminapacketS2C {
    public static void death_transfer_maxstamina(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        StaminaData.setMAXSTAMINA((IEntityDataSaver) client.player,buf.readFloat());
        playerstaminapacketS2C.modified=false;
    }
    public static void tick_equip(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.tick_equip.read(buf);

        client_tick.getTick1=packet.ability1();
        client_tick.getTick2=packet.ability2();
        client_tick.getTick3=packet.ability3();
    }
    public static boolean modified = false;
    public static void ability_animation(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.AbilityAni.read(buf);
        client.execute(() -> {
            var entity = client.world.getEntityById(packet.playerId());
            boolean head = AbilityManager.abiltyregister.get(packet.index()).head;
            boolean torso= AbilityManager.abiltyregister.get(packet.index()).body;
            boolean rightArm= AbilityManager.abiltyregister.get(packet.index()).righthand;
            boolean leftArm= AbilityManager.abiltyregister.get(packet.index()).lefthand;
            boolean rightLeg= AbilityManager.abiltyregister.get(packet.index()).rightleg;
            boolean leftLeg= AbilityManager.abiltyregister.get(packet.index()).leftleg;

            var animationContainer = ((IModAnimatedPlayer) entity).modid_getModAnimation();
            KeyframeAnimation animationL;
            if(AbilityManager.abiltyregister.get(packet.index()) instanceof multiple_animations)
            {
                animationL=   PlayerAnimationRegistry.getAnimation(new Identifier(StaminaMod.MODID, ((multiple_animations) AbilityManager.abiltyregister.get(packet.index())).getanimation_number()));
                //System.out.println(((multiple_animations) AbilityManager.abiltyregister.get(packet.index())).getanimation_number());
            }
            else
            {
                animationL=   PlayerAnimationRegistry.getAnimation(new Identifier(StaminaMod.MODID, AbilityManager.abiltyregister.get(packet.index()).animationname));
            }


            if (modified ) {
                animationContainer.removeModifier(0);
            }


            modified = true;

            //System.out.println(AbilityManager.abiltyregister.get(packet.index()).animationname);
            var builder = animationL.mutableCopy();
            builder.getPart("head").setEnabled(false);

            animationL = builder.build();
            var torso1 = builder.getPart("torso");
            animationL = builder.build();
            var rightArm1 = builder.getPart("rightArm");
            animationL = builder.build();
            var leftArm1 = builder.getPart("leftArm");
            var rightLeg1 = builder.getPart("rightLeg");
            var leftLeg1 = builder.getPart("leftLeg");

            //System.out.println(head);

            torso1.setEnabled(!torso);
            rightArm1.setEnabled(!rightArm);
            leftArm1.setEnabled(!leftArm);
            rightLeg1.setEnabled(!rightLeg);
            leftLeg1.setEnabled(!leftLeg);

           animationContainer.addModifierBefore(new SpeedModifier(AbilityManager.abiltyregister.get(packet.index()).speed));
           animationContainer.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn(7, Ease.LINEAR), new KeyframeAnimationPlayer(animationL));

            animationContainer.setAnimation(new firstperson(animationL));

        });
    }
    public static void SWORD_DASH_ATTACK_M(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        ModConfigs.sword_dash_attack_multiplier=buf.readFloat();
    }
    public static void SPIKE_DMG(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        ModConfigs.spike_damage=buf.readFloat();
    }
    public static void KICK_K(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {

        ModConfigs.kick_knockback=buf.readFloat();
    }
    public static void PARRY_E(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.parry_re.read(buf);
        ModConfigs.parry_duration= packet.duration();
        ModConfigs.parry_amplifier=packet.amplitude();
    }
    public static void D_R(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        ModConfigs.dodge_range=buf.readFloat();
    }
    public static void VELOCITY(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet= Packets.vec3d.read(buf);
        client.player.setVelocity(packet.x(),packet.y(),packet.z());
    }

    public static void cooldownc(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
      final var packet=Packets.cooldown.read(buf);
        cooldowndata.setCooldown((IEntityDataSaver) client.player,packet.slot(),packet.cooldown());
    }
    public static void DODGE(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.ABILITY_MODCONFIG.read(buf);
        ModConfigs.dodge_stamina= packet.stamina();
        ModConfigs.dodge_cooldown=packet.cooldown();
    }
    public static void FLIP(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.ABILITY_MODCONFIG.read(buf);
        ModConfigs.flip_attack_sword_stamina= packet.stamina();
        ModConfigs.flip_attack_sword_cooldown=packet.cooldown();
    }
    public static void STOMP(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.ABILITY_MODCONFIG.read(buf);
        ModConfigs.foot_stomp_stamina= packet.stamina();
        ModConfigs.foot_stomp_cooldown=packet.cooldown();
    }
    public static void HURRICAN(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.ABILITY_MODCONFIG.read(buf);
        ModConfigs.hurrican_swing_stamina= packet.stamina();
        ModConfigs.hurrican_swing_cooldown=packet.cooldown();
    }
    public static void KICK(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.ABILITY_MODCONFIG.read(buf);
        ModConfigs.kick_stamina= packet.stamina();
        ModConfigs.kick_cooldown=packet.cooldown();
    }
    public static void PARRY(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.ABILITY_MODCONFIG.read(buf);
        ModConfigs.parry_stamina= packet.stamina();
        ModConfigs.parry_cooldown=packet.cooldown();
    }
    public static void DASH(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        final var packet = Packets.ABILITY_MODCONFIG.read(buf);
        ModConfigs.sword_dash= packet.stamina();
        ModConfigs.sword_cooldown=packet.cooldown();
    }

    public static void abilitysync(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        //System.out.println("syn");
        final var packet = Packets.AbilitySync.read(buf);
        Equipdata.addability((IEntityDataSaver) MinecraftClient.getInstance().player,packet.id(),packet.slot());
    }

    public static void ABSORBTION(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {

        StaminaData.increaseMAXSTAMINAtemp((IEntityDataSaver) client.player, buf.readInt());
    }
    public static void ABSORBTION2(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {


        StaminaData.increaseMAXSTAMINAtemp((IEntityDataSaver) client.player, buf.readInt());
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
            //System.out.println(GlobalStamina.X+" "+GlobalStamina.Y);
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


            AbilityData.addAbility((IEntityDataSaver) client.player,AbilityManager.abiltyregister.get(buf.readInt()));

    }

    public static void ability_added(MinecraftClient client, ClientPlayNetworkHandler Handler, PacketByteBuf buf, PacketSender respondSender)
    {
        AbilityData.addAbility((IEntityDataSaver) client.player, AbilityManager.abiltyregister.get(buf.readInt()));
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
