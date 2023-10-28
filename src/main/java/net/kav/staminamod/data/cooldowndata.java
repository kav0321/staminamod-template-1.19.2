package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class cooldowndata {



    public static int CURRENTT = 0;
    public static int CURRENTT2 = 0;
    public static int CURRENTT3 = 0;



    public static void setCooldown(IEntityDataSaver player, String ability, int value) {
        NbtCompound nbt = player.getPersistentData();

        switch (ability) {
            case "ability1":
                nbt.putInt("currentT_ac", value);
                break;
            case "ability2":
                nbt.putInt("currentT2_ac", value);
                break;
            case "ability3":
                nbt.putInt("currentT3_ac", value);
                break;
            // Add more cases for other abilities as needed
        }
    }

    public static int getCooldown(IEntityDataSaver player, String ability) {
        NbtCompound nbt = player.getPersistentData();

        switch (ability) {
            case "ability1":
                return (nbt.getInt("currentT_ac")!=0)?nbt.getInt("currentT_ac"):1;
            case "ability2":
                return (nbt.getInt("currentT2_ac")!=0)?nbt.getInt("currentT2_ac"):1;
            case "ability3":
                return (nbt.getInt("currentT3_ac")!=0)?nbt.getInt("currentT3_ac"):1;
            // Add more cases for other abilities as needed
            default:
                return 0; // Default value for unknown abilities
        }
    }


}
