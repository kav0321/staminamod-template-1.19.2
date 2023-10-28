package net.kav.staminamod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.networking.packet.playerstaminapacketC2S;
import net.kav.staminamod.networking.packet.playerstaminapacketS2C;
import net.minecraft.util.Identifier;

public class ModMessages {

    public static final Identifier LOWSTAMINA = new Identifier(StaminaMod.MODID, "lowstamina");
    public static final Identifier HANDSWING = new Identifier(StaminaMod.MODID, "handswing");
    public static final Identifier WEAPON_COST = new Identifier(StaminaMod.MODID, "weapon_cost");
    public static final Identifier INITIALIZEC2S = new Identifier(StaminaMod.MODID, "initializec2s");
    public static final Identifier INITIALIZES2C = new Identifier(StaminaMod.MODID, "initializes2c");
    public static final Identifier ABILITYSYNC = new Identifier(StaminaMod.MODID, "abilitysync");
    public static final Identifier ABILITYSYNC2 = new Identifier(StaminaMod.MODID, "abilitysync2");
    public static final Identifier INITIALIZEABILITY= new Identifier(StaminaMod.MODID, "initializeability");
    public static final Identifier INITIALIZEC2S_MAXSTAMINA = new Identifier(StaminaMod.MODID, "initializec2s_max");
    public static final Identifier INITIALIZES2C_MAXSTAMINA = new Identifier(StaminaMod.MODID, "initializes2c_max");
    public static final Identifier EFFECT_STATUS = new Identifier(StaminaMod.MODID,"effect_stamina");
    public static final Identifier ABILITY_ADDED = new Identifier(StaminaMod.MODID,"ability_added");
    public static final Identifier DEATH_TRANSFER_MAXSTAMINA = new Identifier(StaminaMod.MODID,"death_transfer_maxstamina");
    public static final Identifier SHIELD = new Identifier(StaminaMod.MODID,"shield");
    public static final Identifier SHIELDING = new Identifier(StaminaMod.MODID,"shielding");
    public static final Identifier LEAVING = new Identifier(StaminaMod.MODID,"leaving");
    public static final Identifier LEAVING2 = new Identifier(StaminaMod.MODID,"leaving_");
    public static final Identifier COOLDOWN = new Identifier(StaminaMod.MODID,"cooldown_bad");
    public static final Identifier COOLDOWNC = new Identifier(StaminaMod.MODID,"cooldown_bad_c");
    public static final Identifier JOIN = new Identifier(StaminaMod.MODID,"joining_ability");
    public static final Identifier EXTRA_STAMINA = new Identifier(StaminaMod.MODID,"effects");
    public static final Identifier EXTRA_STAMINA_SYN = new Identifier(StaminaMod.MODID,"effects2");
    public static final Identifier VELOCITY = new Identifier(StaminaMod.MODID,"velocity");

    public static final Identifier DODGE = new Identifier(StaminaMod.MODID,"dodge");
    public static final Identifier FLIP = new Identifier(StaminaMod.MODID,"flip");
    public static final Identifier STOMP = new Identifier(StaminaMod.MODID,"stomp");
    public static final Identifier SWING = new Identifier(StaminaMod.MODID,"swing");
    public static final Identifier KICK = new Identifier(StaminaMod.MODID,"kick");
    public static final Identifier PARRY = new Identifier(StaminaMod.MODID,"kick");
    public static final Identifier ID = new Identifier(StaminaMod.MODID, "attack_an_a");
    public static final Identifier ANIMATION = new Identifier(StaminaMod.MODID, "attack_animation_abilities");
    public static final Identifier SWORD_DASH = new Identifier(StaminaMod.MODID,"sword_dash");
    public static void registerC2SPackets()
    {
        ServerPlayNetworking.registerGlobalReceiver(HANDSWING, playerstaminapacketC2S::sendweaponcost);
        ServerPlayNetworking.registerGlobalReceiver(LOWSTAMINA, playerstaminapacketC2S::getlowstamina);
        ServerPlayNetworking.registerGlobalReceiver(INITIALIZEC2S, playerstaminapacketC2S::initialize_variable);
        ServerPlayNetworking.registerGlobalReceiver(ModMessages.ANIMATION, playerstaminapacketC2S::ability_animation);
        ServerPlayNetworking.registerGlobalReceiver(INITIALIZEC2S_MAXSTAMINA, playerstaminapacketC2S::initialize_variable_stamina);
        ServerPlayNetworking.registerGlobalReceiver(EXTRA_STAMINA_SYN, playerstaminapacketC2S::initialize_variable_stamina2);
        ServerPlayNetworking.registerGlobalReceiver(SHIELDING, playerstaminapacketC2S::disable_shield);
        ServerPlayNetworking.registerGlobalReceiver(ABILITYSYNC, playerstaminapacketC2S::ability_sync);
        ServerPlayNetworking.registerGlobalReceiver(LEAVING, playerstaminapacketC2S::leaving);
        ServerPlayNetworking.registerGlobalReceiver(LEAVING2, playerstaminapacketC2S::leaving2);
        ServerPlayNetworking.registerGlobalReceiver(COOLDOWN, playerstaminapacketC2S::cooldown);
    }



    public static void registerS2CPackets()
    {
        ClientPlayNetworking.registerGlobalReceiver(WEAPON_COST, playerstaminapacketS2C::weapon_cost);

        ClientPlayNetworking.registerGlobalReceiver(EXTRA_STAMINA, playerstaminapacketS2C::ABSORBTION);

        ClientPlayNetworking.registerGlobalReceiver(INITIALIZES2C, playerstaminapacketS2C::initializes2c);
        ClientPlayNetworking.registerGlobalReceiver(INITIALIZES2C_MAXSTAMINA, playerstaminapacketS2C::initializes2c_stamina);
        ClientPlayNetworking.registerGlobalReceiver(INITIALIZEABILITY, playerstaminapacketS2C::initializeability);
        ClientPlayNetworking.registerGlobalReceiver(ABILITY_ADDED,playerstaminapacketS2C::ability_added);
        ClientPlayNetworking.registerGlobalReceiver(SHIELD,playerstaminapacketS2C::shield_info);
        ClientPlayNetworking.registerGlobalReceiver(ABILITYSYNC2,playerstaminapacketS2C::abilitysync);
        ClientPlayNetworking.registerGlobalReceiver(DEATH_TRANSFER_MAXSTAMINA,playerstaminapacketS2C::death_transfer_maxstamina);
        ClientPlayNetworking.registerGlobalReceiver(JOIN,playerstaminapacketS2C::tick_equip);
        ClientPlayNetworking.registerGlobalReceiver(ModMessages.ID,playerstaminapacketS2C::ability_animation);

        ClientPlayNetworking.registerGlobalReceiver(Packets.ABILITY_MODCONFIG.ID0, playerstaminapacketS2C::DODGE);
        ClientPlayNetworking.registerGlobalReceiver(Packets.ABILITY_MODCONFIG.ID90, playerstaminapacketS2C::FLIP);
        ClientPlayNetworking.registerGlobalReceiver(Packets.ABILITY_MODCONFIG.ID180, playerstaminapacketS2C::STOMP);
        ClientPlayNetworking.registerGlobalReceiver(Packets.ABILITY_MODCONFIG.ID270, playerstaminapacketS2C::HURRICAN);
        ClientPlayNetworking.registerGlobalReceiver(Packets.ABILITY_MODCONFIG.ID360, playerstaminapacketS2C::KICK);
        ClientPlayNetworking.registerGlobalReceiver(Packets.ABILITY_MODCONFIG.ID450, playerstaminapacketS2C::PARRY);
        ClientPlayNetworking.registerGlobalReceiver(Packets.ABILITY_MODCONFIG.ID540, playerstaminapacketS2C::DASH);

        ClientPlayNetworking.registerGlobalReceiver(SWORD_DASH, playerstaminapacketS2C::SWORD_DASH_ATTACK_M);
        ClientPlayNetworking.registerGlobalReceiver(STOMP, playerstaminapacketS2C::SPIKE_DMG);
        ClientPlayNetworking.registerGlobalReceiver(KICK, playerstaminapacketS2C::KICK_K);
        ClientPlayNetworking.registerGlobalReceiver(PARRY, playerstaminapacketS2C::PARRY_E);
        ClientPlayNetworking.registerGlobalReceiver(DODGE, playerstaminapacketS2C::D_R);
        ClientPlayNetworking.registerGlobalReceiver(COOLDOWNC, playerstaminapacketS2C::cooldownc);
        ClientPlayNetworking.registerGlobalReceiver(VELOCITY, playerstaminapacketS2C::VELOCITY);
    }
}
