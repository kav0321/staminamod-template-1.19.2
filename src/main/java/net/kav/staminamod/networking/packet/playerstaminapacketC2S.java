package net.kav.staminamod.networking.packet;

import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.data.*;
import net.kav.staminamod.event.client.client_tick;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.util.IEntityDataSaver;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Iterator;
import java.util.Map;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static net.kav.staminamod.config.ModConfigs.*;

public class playerstaminapacketC2S {
    public static void ability_animation(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        ServerWorld world = Iterables.tryFind(server.getWorlds(), (element) -> element == player.world)
                .orNull();

        if (world == null || world.isClient) {
            return;
        }

        final var packet = Packets.AbilityAni.read(buf);



        final var forwardBuffer = new Packets.AbilityAni(player.getId(), packet.index());
        AbilityManager.abiltyregister.get(packet.index()).ServerSideExecution(server,player);
        for(PlayerEntity player1: server.getPlayerManager().getPlayerList())
        {

            ServerPlayNetworking.send((ServerPlayerEntity) player1, ModMessages.ID, forwardBuffer.write());

        }
        }
    public static void initialize_variable(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        flipdata.reset((IEntityDataSaver) player);
        hurrican_swing_data.reset((IEntityDataSaver) player);
        guarddata.reset((IEntityDataSaver) player);
        KickTimingData.reset((IEntityDataSaver) player);
        Parrydata.reset((IEntityDataSaver) player);
        StompData.reset((IEntityDataSaver) player);
        sword_dashData.reset((IEntityDataSaver) player);

        ServerPlayNetworking.send(player,ModMessages.INITIALIZES2C,new Packets.stamina_location(ModConfigs.X,ModConfigs.Y).write());
        for(AbilityCore ability: AbilityData.getAbility((IEntityDataSaver) player))
        {
            PacketByteBuf buffer= PacketByteBufs.create();
            buffer.writeInt(ability.ID);
            ServerPlayNetworking.send(player,ModMessages.INITIALIZEABILITY,buffer);
        }
        ServerPlayNetworking.send(player,ModMessages.ABILITYSYNC2,new Packets.AbilitySync(Equipdata.getability((IEntityDataSaver) player,"ability1"),"ability1").write());
        ServerPlayNetworking.send(player,ModMessages.ABILITYSYNC2,new Packets.AbilitySync(Equipdata.getability((IEntityDataSaver) player,"ability2"),"ability2").write());
        ServerPlayNetworking.send(player,ModMessages.ABILITYSYNC2,new Packets.AbilitySync(Equipdata.getability((IEntityDataSaver) player,"ability3"),"ability3").write());
        ServerPlayNetworking.send(player,ModMessages.JOIN,new Packets.tick_equip(tickdata.gettick1((IEntityDataSaver) player),tickdata.gettick2((IEntityDataSaver) player),tickdata.gettick3((IEntityDataSaver) player)).write());

        //System.out.println(tickdata.gettick1((IEntityDataSaver) player)+" " +tickdata.gettick2((IEntityDataSaver) player)+" " +tickdata.gettick3((IEntityDataSaver) player)+" sas");

        ServerPlayNetworking.send(player,Packets.ABILITY_MODCONFIG.ID0, new Packets.ABILITY_MODCONFIG(dodge_stamina,dodge_cooldown).write());

        ServerPlayNetworking.send(player,Packets.ABILITY_MODCONFIG.ID90, new Packets.ABILITY_MODCONFIG(flip_attack_sword_stamina,flip_attack_sword_cooldown).write());

        ServerPlayNetworking.send(player,Packets.ABILITY_MODCONFIG.ID180, new Packets.ABILITY_MODCONFIG(foot_stomp_stamina,foot_stomp_cooldown).write());

        ServerPlayNetworking.send(player,Packets.ABILITY_MODCONFIG.ID270, new Packets.ABILITY_MODCONFIG(hurrican_swing_stamina,hurrican_swing_cooldown).write());

        ServerPlayNetworking.send(player,Packets.ABILITY_MODCONFIG.ID360, new Packets.ABILITY_MODCONFIG(kick_stamina,kick_cooldown).write());

        ServerPlayNetworking.send(player,Packets.ABILITY_MODCONFIG.ID450, new Packets.ABILITY_MODCONFIG(parry_stamina,parry_cooldown).write());

        ServerPlayNetworking.send(player,Packets.ABILITY_MODCONFIG.ID540, new Packets.ABILITY_MODCONFIG(sword_dash,sword_cooldown).write());

        PacketByteBuf bufsda =PacketByteBufs.create();
        PacketByteBuf bufdsd =PacketByteBufs.create();
        PacketByteBuf bufkk =PacketByteBufs.create();
        PacketByteBuf bufdr =PacketByteBufs.create();

        bufsda.writeFloat(sword_dash_attack_multiplier);
        bufdsd.writeFloat(spike_damage);
        bufkk.writeFloat(kick_knockback);
        bufdr.writeFloat(dodge_range);


        ServerPlayNetworking.send(player,ModMessages.SWORD_DASH,bufsda);
        ServerPlayNetworking.send(player,ModMessages.STOMP,bufdsd);
        ServerPlayNetworking.send(player,ModMessages.KICK, bufkk);
        ServerPlayNetworking.send(player,ModMessages.PARRY, new Packets.parry_re(parry_duration,parry_amplifier).write());
        ServerPlayNetworking.send(player,ModMessages.DODGE, bufdr);


        ServerPlayNetworking.send(player,ModMessages.COOLDOWNC,new Packets.cooldown(cooldowndata.getCooldown((IEntityDataSaver) player,"ability1"),"ability1").write());
        ServerPlayNetworking.send(player,ModMessages.COOLDOWNC,new Packets.cooldown(cooldowndata.getCooldown((IEntityDataSaver) player,"ability2"),"ability2").write());
        ServerPlayNetworking.send(player,ModMessages.COOLDOWNC,new Packets.cooldown(cooldowndata.getCooldown((IEntityDataSaver) player,"ability3"),"ability3").write());
    }
    public static void disable_shield(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {

        player.disableShield(false);
    }

    public static void ability_sync(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {

    final var packet = Packets.AbilitySync.read(buf);
        Equipdata.addability((IEntityDataSaver) player,packet.id(),packet.slot());
    }
    public static void leaving2(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        final var packet = Packets.tick_equip.read(buf);
        tickdata.settick1((IEntityDataSaver) player,packet.ability1());
        tickdata.settick2((IEntityDataSaver) player,packet.ability2());
        tickdata.settick3((IEntityDataSaver) player,packet.ability3());
        //System.out.println(tickdata.gettick1((IEntityDataSaver) player)+" " +tickdata.gettick2((IEntityDataSaver) player)+" " +tickdata.gettick3((IEntityDataSaver) player)+" sl");
      //  System.out.println(packet.ability1()+" " +packet.ability2()+" " +packet.ability3()+" L");
    }
    public static void cooldown(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        final var packet = Packets.cooldown.read(buf);
        cooldowndata.setCooldown((IEntityDataSaver) player,packet.slot(),packet.cooldown());
    }
    public static void leaving(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        if(player==null)
        {
            //System.out.println("error player null");
            return;
        }
      //  System.out.println("setting player to zero");

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
    }
    public static void initialize_variable_stamina(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
    PacketByteBuf buffer = PacketByteBufs.create();
    buffer.writeFloat(StaminaData.getMAXSTAMINA(((IEntityDataSaver) player)));
        ServerPlayNetworking.send(player,ModMessages.INITIALIZES2C_MAXSTAMINA,buffer);

    }
    public static void initialize_variable_stamina2(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        int temp= (int) StaminaData.getMAXSTAMINAtemp(((IEntityDataSaver) player));
        System.out.println(temp);
        PacketByteBuf buffer2 = PacketByteBufs.create();
        buffer2.writeInt(temp);
        ServerPlayNetworking.send(player,ModMessages.EXTRA_STAMINA,buffer2);

    }

    public static void getlowstamina(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        if(!player.isCreative())
        {
            if(buf.readBoolean())
            {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,20,2));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE,20,2));
            }


            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,20,10));
        }
    }
    public static void sendweaponcost(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        float x12;
        double d = 0;
        ItemStack itemss = player.getMainHandStack();
        Item item = itemss.getItem();
        Multimap<EntityAttribute, EntityAttributeModifier> multimap = itemss.getAttributeModifiers(EquipmentSlot.MAINHAND);
        if (!multimap.isEmpty()) {
            Iterator var11 = multimap.entries().iterator();
            while(var11.hasNext()) {
                Map.Entry<EntityAttribute, EntityAttributeModifier> entry = (Map.Entry)var11.next();
                EntityAttributeModifier entityAttributeModifier = (EntityAttributeModifier)entry.getValue();
                d = entityAttributeModifier.getValue();

            }
            //EntityAttributeModifier entityAttributeModifier = (EntityAttributeModifier) item.getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_SPEED);
            d=abs(d);
            // player.sendMessage(Text.of(Double.toString(d)),true);
        }




        //////player.sendMessage(Text.of(Float.toString(SoulData.addFloatpoint(((IEntityDataSaver) player), 0f, "Stamina"))),true);


        if(player.isCreative())
        {
            x12=0;
        }
        else
            x12= (float) (d)+3.3f;

        if(x12>5)
        {
            x12= (float) (-1.1859*pow(x12,2)+20.286*x12-69.792);
        }
        if(x12<5)
        {
            x12=5;
        }

        //removed in the server side

        PacketByteBuf bufs = PacketByteBufs.create();


        bufs.writeFloat(x12-0.4f);



        //player.sendMessage(Text.literal(player.getName().toString()),true);
        ServerPlayNetworking.send(player, ModMessages.WEAPON_COST,bufs);
    }
}
