package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class tickdata {

    private static int tick;
    private static int tick2;
    private static int tick3;
    public static void settick1(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=amount;
        nbt.putInt("e_tick1",tick);

    }
    public static int gettick1(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("e_tick1");
        return tick;
    }

    public static void settick2(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=amount;
        nbt.putInt("e_tick2",tick2);

    }
    public static int gettick2(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=nbt.getInt("e_tick2");
        return tick2;
    }

    public static void settick3(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick3=amount;
        nbt.putInt("e_tick3",tick3);

    }
    public static int gettick3(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick3=nbt.getInt("e_tick3");
        return tick3;
    }
}
