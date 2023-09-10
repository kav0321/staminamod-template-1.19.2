package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class Parrydata {
    private static int tick;
    private static boolean beingattack;
    public static void settick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=amount;
        nbt.putInt("tick",tick);

    }
    public static int gettick(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick");
        return tick;
    }

    public static void increasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick");
        tick=tick+amount;
        nbt.putInt("tick",tick);
    }


    public static void decreasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick");
        tick=tick-amount;
        nbt.putInt("tick",tick);
    }
    public static void setparryattack(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        beingattack=amount;
        nbt.putBoolean("beingattack",beingattack);

    }
    public static boolean getLevel(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        beingattack=nbt.getBoolean("beingattack");

        return beingattack;
    }
}
