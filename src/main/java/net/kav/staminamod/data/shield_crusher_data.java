package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class shield_crusher_data {

    private static int tick;
    private static boolean beingshield;

    public static void reset(IEntityDataSaver player)
    {
        settick(player,0);
        setbeingshield(player,false);
    }

    public static void settick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=amount;
        nbt.putInt("tickbeingshield",tick);

    }
    public static int gettick(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tickbeingshield");
        return tick;
    }

    public static void increasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tickbeingshield");
        tick=tick+amount;
        nbt.putInt("tickbeingshield",tick);
    }


    public static void decreasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tickbeingshield");
        tick=tick-amount;
        nbt.putInt("tickbeingshield",tick);
    }
    public static void setbeingshield(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        beingshield=amount;
        nbt.putBoolean("beingshield",beingshield);

    }
    public static boolean getbeingshield(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        beingshield=nbt.getBoolean("beingshield");

        return beingshield;
    }

}
