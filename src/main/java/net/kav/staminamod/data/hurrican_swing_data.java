package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class hurrican_swing_data {

    private static int tick;
    private static boolean trigger;
    private static int range;
    public static void reset(IEntityDataSaver player)
    {
        settick(player,0);
        settrigger(player,false);
    }
    public static void settick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=amount;
        nbt.putInt("tick_h",tick);

    }


    public static int gettick(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_h");
        return tick;
    }

    public static void increasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_h");
        tick=tick+amount;
        nbt.putInt("tick_h",tick);
    }


    public static void decreasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_h");
        tick=tick-amount;
        nbt.putInt("tick_h",tick);
    }
    public static void settrigger(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        trigger =amount;
        nbt.putBoolean("trigger_h", trigger);

    }
    public static boolean gettrigger(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        trigger =nbt.getBoolean("trigger_h");

        return trigger;
    }


}
