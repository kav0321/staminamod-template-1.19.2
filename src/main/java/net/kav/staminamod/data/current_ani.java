package net.kav.staminamod.data;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class current_ani {
    private static int anim;


    public static void setanim(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        anim=amount;
        nbt.putInt("anim_",anim);

    }
    public static int getanim(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        anim=nbt.getInt("anim_");
        return anim;
    }
}
