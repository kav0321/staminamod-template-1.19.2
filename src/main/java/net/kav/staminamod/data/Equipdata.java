package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class Equipdata {

    private static int ability1;
    private static int ability2;
    private static int ability3;

    public static void addability(IEntityDataSaver player, int id, String string)
    {
        NbtCompound abilitiesTag = player.getPersistentData();

        abilitiesTag.putInt(string,id);

    }

    public static int getability(IEntityDataSaver player, String string)
    {
        NbtCompound abilitiesTag = player.getPersistentData();
        int temp = abilitiesTag.getInt(string);
        return temp;

    }


}
