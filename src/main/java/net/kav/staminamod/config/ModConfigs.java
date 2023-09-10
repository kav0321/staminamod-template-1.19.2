package net.kav.staminamod.config;

import com.mojang.datafixers.util.Pair;

import net.kav.staminamod.StaminaMod;

import static java.lang.Math.pow;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;


    public static int X;
    public static int Y;




    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(StaminaMod.MODID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {

        configs.addKeyValuePair(new Pair<>("matrix.x", 20), "Position for x");

        configs.addKeyValuePair(new Pair<>("matrix.y", 50), "Position for y");

    }

    private static void assignConfigs() {

        X = CONFIG.getOrDefault("matrix.x", 20);

        Y = CONFIG.getOrDefault("matrix.y", 50);



        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
