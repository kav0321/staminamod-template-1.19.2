package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class mega_dashdata {

    private static int tickdashmega;
    private static int tickdashmega2;
    private static boolean beingadash;
    private static boolean beingadash2;

    public static void reset(IEntityDataSaver player)
    {
        settick(player,0);
        setbeingadash(player,false);
    }

    public static void settick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega=amount;
        nbt.putInt("tickdashmega",tickdashmega);

    }
    public static int gettick(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega=nbt.getInt("tickdashmega");
        return tickdashmega;
    }

    public static void increasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega=nbt.getInt("tickdashmega");
        tickdashmega=tickdashmega+amount;
        nbt.putInt("tickdashmega",tickdashmega);
    }


    public static void decreasetick(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega=nbt.getInt("tickdashmega");
        tickdashmega=tickdashmega-amount;
        nbt.putInt("tickdashmega",tickdashmega);
    }

    public static void settick2(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega2=amount;
        nbt.putInt("tickdashmega2",tickdashmega2);

    }
    public static int gettick2(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega2=nbt.getInt("tickdashmega2");
        return tickdashmega2;
    }

    public static void increasetick2(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega2=nbt.getInt("tickdashmega2");
        tickdashmega2=tickdashmega2+amount;
        nbt.putInt("tickdashmega2",tickdashmega2);
    }

    public static void decreasetick3(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega=nbt.getInt("tickdashmega3");
        tickdashmega=tickdashmega-amount;
        nbt.putInt("tickdashmega3",tickdashmega);
    }

    public static void settick3(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega2=amount;
        nbt.putInt("tickdashmega3",tickdashmega2);

    }
    public static int gettick3(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega2=nbt.getInt("tickdashmega3");
        return tickdashmega2;
    }
    public static void decreasetick2(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega2=nbt.getInt("tickdashmega2");
        tickdashmega2=tickdashmega2-amount;
        nbt.putInt("tickdashmega2",tickdashmega2);
    }

    public static void setbeingadash(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        beingadash=amount;
        nbt.putBoolean("beingadash",beingadash);

    }
    public static boolean getbeingadash(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        beingadash=nbt.getBoolean("beingadash");

        return beingadash;
    }

    public static void setbeingadash2(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        beingadash2=amount;
        nbt.putBoolean("beingadash2",beingadash2);

    }
    public static boolean getbeingadash2(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        beingadash2=nbt.getBoolean("beingadash2");

        return beingadash2;
    }

    public static void setbeingadash3(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        beingadash2=amount;
        nbt.putBoolean("beingadash3",beingadash2);

    }
    public static boolean getbeingadash3(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        beingadash2=nbt.getBoolean("beingadash3");

        return beingadash2;
    }






    public static void setbeingadash4(IEntityDataSaver player, boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        beingadash2=amount;
        nbt.putBoolean("beingadash4",beingadash2);

    }
    public static boolean getbeingadash4(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        beingadash2=nbt.getBoolean("beingadash4");

        return beingadash2;
    }



    public static void decreasetick4(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega=nbt.getInt("tickdashmega4");
        tickdashmega=tickdashmega-amount;
        nbt.putInt("tickdashmega4",tickdashmega);
    }

    public static void settick4(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega2=amount;
        nbt.putInt("tickdashmega4",tickdashmega2);

    }
    public static int gettick4(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        tickdashmega2=nbt.getInt("tickdashmega4");
        return tickdashmega2;
    }

}
