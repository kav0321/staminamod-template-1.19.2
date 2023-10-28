package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class sword_dashData {
    private static int tick;
    private static int tick2;
    private static boolean beingattack;
    public static void reset(IEntityDataSaver player)
    {
        settick(player,0);
        settick2(player,0);
        settick3(player,0);
        setparryattack(player,false);
    }

    public static void settick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=amount;
        nbt.putInt("tick_ing",tick);

    }
    public static int gettick(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_ing");
        return tick;
    }

    public static void increasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_ing");
        tick=tick+amount;
        nbt.putInt("tick_ing",tick);
    }



    public static void settick2(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=amount;
        nbt.putInt("tick_ing2",tick2);

    }
    public static int gettick2(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=nbt.getInt("tick_ing2");
        return tick2;
    }

    public static void increasetick2(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=nbt.getInt("tick_ing2");
        tick2=tick2+amount;
        nbt.putInt("tick_ing2",tick2);
    }
    public static void decreasetick2(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=nbt.getInt("tick_ing2");
        tick2=tick2-amount;
        nbt.putInt("tick_ing2",tick2);
    }

    public static void decreasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick=nbt.getInt("tick_ing");
        tick=tick-amount;
        nbt.putInt("tick_ing",tick);
    }
    public static void setparryattack(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        beingattack=amount;
        nbt.putBoolean("beingattacksing",beingattack);

    }
    public static boolean getLevel(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        beingattack=nbt.getBoolean("beingattacksing");

        return beingattack;
    }







    public static void settick3(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=amount;
        nbt.putInt("tick_ing3",tick2);

    }
    public static int gettick3(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=nbt.getInt("tick_ing3");
        return tick2;
    }

    public static void increasetick3(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=nbt.getInt("tick_ing3");
        tick2=tick2+amount;
        nbt.putInt("tick_ing3",tick2);
    }
    public static void decreasetick3(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tick2=nbt.getInt("tick_ing3");
        tick2=tick2-amount;
        nbt.putInt("tick_ing3",tick2);
    }
}
