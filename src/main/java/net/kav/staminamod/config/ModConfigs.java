package net.kav.staminamod.config;

import com.mojang.datafixers.util.Pair;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.networking.packet.Packets;

import static java.lang.Math.pow;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;


    public static int X;
    public static int Y;
   // public static float defaultmaxistamina;
    public static float sword_dash_attack_multiplier;
    public static float spike_damage;
    public static float kick_knockback;
    public static int parry_duration;
    public static int parry_amplifier;
    public static float dodge_range;


    public static int dodge_stamina;
    public static int flip_attack_sword_stamina;
    public static int foot_stomp_stamina;
    public static int hurrican_swing_stamina;
    public static int kick_stamina;
    public static int parry_stamina;
    public static int sword_dash;

    public static int dodge_cooldown;
    public static int flip_attack_sword_cooldown;
    public static int foot_stomp_cooldown;
    public static int hurrican_swing_cooldown;
    public static int kick_cooldown;
    public static int parry_cooldown;
    public static int sword_cooldown;


    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(StaminaMod.MODID + "config").provider(configs).request();

        assignConfigs();



    }

    private static void createConfigs() {

        configs.addKeyValuePair(new Pair<>("matrix.x", 20), "Position for x");

        configs.addKeyValuePair(new Pair<>("matrix.y", 50), "Position for y");


        configs.addKeyValuePair(new Pair<>("matrix.dodge_stamina", 6), "Default Dodge Stamina");
        configs.addKeyValuePair(new Pair<>("matrix.flip_attack_sword_stamina", 7), "Default Flip Attack Sword Stamina");
        configs.addKeyValuePair(new Pair<>("matrix.foot_stomp_stamina", 15), "Default Foot Stomp Stamina");
        configs.addKeyValuePair(new Pair<>("matrix.hurrican_swing_stamina", 8), "Default Hurrican Swing Stamina");
        configs.addKeyValuePair(new Pair<>("matrix.kick_stamina", 9), "Default Kick Stamina");
        configs.addKeyValuePair(new Pair<>("matrix.parry_stamina", 8), "Default Parry Stamina");
        configs.addKeyValuePair(new Pair<>("matrix.sword_dash", 17), "Default Sword Dash Stamina");

        configs.addKeyValuePair(new Pair<>("matrix.dodge_cooldown", 10), "Default Dodge Cooldown in ticks");
        configs.addKeyValuePair(new Pair<>("matrix.flip_attack_sword_cooldown", 300), "Default Flip Attack Sword v in ticks");
        configs.addKeyValuePair(new Pair<>("matrix.foot_stomp_cooldown", 1000), "Default Foot Stomp Cooldown in ticks");
        configs.addKeyValuePair(new Pair<>("matrix.hurrican_swing_cooldown", 1000), "Default Hurrican Swing Cooldown in ticks");
        configs.addKeyValuePair(new Pair<>("matrix.kick_cooldown", 300), "Default Kick Cooldown in ticks");
        configs.addKeyValuePair(new Pair<>("matrix.parry_cooldown", 300), "Default Parry Cooldown in ticks");
        configs.addKeyValuePair(new Pair<>("matrix.sword_cooldown", 500), "Default Sword Dash Cooldown in ticks");

        configs.addKeyValuePair(new Pair<>("matrix.sword_dash_attack_multiplier", 6f), "Default Sword Dash Attack Multiplier. It scale with your weapon damage");
        configs.addKeyValuePair(new Pair<>("matrix.spike_damage", 4f), "Default Spike Attack Damage. How much Damage the foot stomp does");
        configs.addKeyValuePair(new Pair<>("matrix.kick_knockback", 2f), "Default kick knockback");
        configs.addKeyValuePair(new Pair<>("matrix.parry_duration", 200), "Default Parry Duration. How long are your enemies paralysed");
        configs.addKeyValuePair(new Pair<>("matrix.parry_amplifier", 10), "Default Parry Amplifier. Amplifier of the effect from being paralyse which include blindless, weekness and slowness");
        configs.addKeyValuePair(new Pair<>("matrix.dodge_range", 1.3f), "Default Parry Stamina");

        //configs.addKeyValuePair(new Pair<>("matrix.defaultmaxistamina", 25f), "Default Maximum Stamina");

    }

    private static void assignConfigs() {

        X = CONFIG.getOrDefault("matrix.x", 20);

        Y = CONFIG.getOrDefault("matrix.y", 50);

        //defaultmaxistamina=CONFIG.getOrDefault("matrix.defaultmaxistamina", 25);

        dodge_stamina=CONFIG.getOrDefault("matrix.dodge_stamina", 6);
        flip_attack_sword_stamina=CONFIG.getOrDefault("matrix.flip_attack_sword_stamina", 7);
        foot_stomp_stamina=CONFIG.getOrDefault("matrix.foot_stomp_stamina", 15);
        hurrican_swing_stamina=CONFIG.getOrDefault("matrix.hurrican_swing_stamina", 8);
        kick_stamina=CONFIG.getOrDefault("matrix.kick_stamina", 9);
        parry_stamina=CONFIG.getOrDefault("matrix.parry_stamina", 8);
        sword_dash=CONFIG.getOrDefault("matrix.sword_dash", 17);

        dodge_cooldown=CONFIG.getOrDefault("matrix.dodge_cooldown", 10);
        flip_attack_sword_cooldown=CONFIG.getOrDefault("matrix.flip_attack_sword_cooldown", 300);
        foot_stomp_cooldown=CONFIG.getOrDefault("matrix.foot_stomp_cooldown", 1000);
        hurrican_swing_cooldown=CONFIG.getOrDefault("matrix.hurrican_swing_cooldown", 1000);
        kick_cooldown=CONFIG.getOrDefault("matrix.kick_cooldown", 300);
        parry_cooldown=CONFIG.getOrDefault("matrix.parry_cooldown", 300);
        sword_cooldown=CONFIG.getOrDefault("matrix.sword_cooldown", 500);


        sword_dash_attack_multiplier=CONFIG.getOrDefault("matrix.sword_dash_attack_multiplier", 6f);
        spike_damage=CONFIG.getOrDefault("matrix.spike_damage", 4f);
        kick_knockback=CONFIG.getOrDefault("matrix.kick_knockback", 2f);
        parry_duration=CONFIG.getOrDefault("matrix.parry_duration", 200);
        parry_amplifier=CONFIG.getOrDefault("matrix.parry_amplifier", 10);
        dodge_range= CONFIG.getOrDefault("matrix.dodge_range", 1.3f);



        //System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
