package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class flipdata {
    private static int tick;
    private static boolean did_I_flip;
    public static void settick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=amount;
        nbt.putInt("tick_flip",tick);

    }
    public static int gettick(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_flip");
        return tick;
    }

    public static void increasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_flip");
        tick=tick+amount;
        nbt.putInt("tick_flip",tick);
    }


    public static void decreasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_flip");
        tick=tick-amount;
        nbt.putInt("tick_flip",tick);
    }
    public static void setDid_I_flip(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        did_I_flip=amount;
        nbt.putBoolean("did_I_flip",did_I_flip);

    }
    public static boolean getDid_I_flip(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        did_I_flip=nbt.getBoolean("did_I_flip");

        return did_I_flip;
    }
}
