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
    public static void registerC2SPackets()
    {
        ServerPlayNetworking.registerGlobalReceiver(HANDSWING, playerstaminapacketC2S::sendweaponcost);
        ServerPlayNetworking.registerGlobalReceiver(LOWSTAMINA, playerstaminapacketC2S::getlowstamina);
        ServerPlayNetworking.registerGlobalReceiver(INITIALIZEC2S, playerstaminapacketC2S::initialize_variable);
        ServerPlayNetworking.registerGlobalReceiver(Packets.AbilityAni.ID, playerstaminapacketC2S::ability_animation);
        ServerPlayNetworking.registerGlobalReceiver(INITIALIZEC2S_MAXSTAMINA, playerstaminapacketC2S::initialize_variable_stamina);
        ServerPlayNetworking.registerGlobalReceiver(SHIELDING, playerstaminapacketC2S::disable_shield);
        ServerPlayNetworking.registerGlobalReceiver(ABILITYSYNC, playerstaminapacketC2S::ability_sync);

    }

    public static void registerS2CPackets()
    {
        ClientPlayNetworking.registerGlobalReceiver(WEAPON_COST, playerstaminapacketS2C::weapon_cost);
        ClientPlayNetworking.registerGlobalReceiver(INITIALIZES2C, playerstaminapacketS2C::initializes2c);
        ClientPlayNetworking.registerGlobalReceiver(INITIALIZES2C_MAXSTAMINA, playerstaminapacketS2C::initializes2c_stamina);
        ClientPlayNetworking.registerGlobalReceiver(INITIALIZEABILITY, playerstaminapacketS2C::initializeability);
        ClientPlayNetworking.registerGlobalReceiver(ABILITY_ADDED,playerstaminapacketS2C::ability_added);
        ClientPlayNetworking.registerGlobalReceiver(SHIELD,playerstaminapacketS2C::shield_info);
        ClientPlayNetworking.registerGlobalReceiver(ABILITYSYNC2,playerstaminapacketS2C::abilitysync);
    }
}
