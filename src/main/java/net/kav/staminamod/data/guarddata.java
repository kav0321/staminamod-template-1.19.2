package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class guarddata {
    private static int tick;
    private static boolean beingattack;

    public static void reset(IEntityDataSaver player)
    {
        settick(player,0);
        setparryguard(player,false);
    }

    public static void settick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=amount;
        nbt.putInt("tickguard",tick);

    }
    public static int gettick(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tickguard");
        return tick;
    }

    public static void increasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tickguard");
        tick=tick+amount;
        nbt.putInt("tickguard",tick);
    }


    public static void decreasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tickguard");
        tick=tick-amount;
        nbt.putInt("tickguard",tick);
    }
    public static void setparryguard(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        beingattack=amount;
        nbt.putBoolean("beingguard",beingattack);

    }
    public static boolean getguard(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        beingattack=nbt.getBoolean("beingguard");

        return beingattack;
    }
}
