package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class KickTimingData {
    private static int tick;
    private static boolean did_I_kick;
    public static void reset(IEntityDataSaver player)
    {
        settick(player,0);
        setDid_I_kick(player,false);
    }
    public static void settick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=amount;
        nbt.putInt("tick_kick",tick);

    }
    public static int gettick(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_kick");
        return tick;
    }

    public static void increasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_kick");
        tick=tick+amount;
        nbt.putInt("tick_kick",tick);
    }


    public static void decreasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_kick");
        tick=tick-amount;
        nbt.putInt("tick_kick",tick);
    }
    public static void setDid_I_kick(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        did_I_kick=amount;
        nbt.putBoolean("did_I_kick",did_I_kick);

    }
    public static boolean getDid_I_kick(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        did_I_kick=nbt.getBoolean("did_I_kick");

        return did_I_kick;
    }
}
