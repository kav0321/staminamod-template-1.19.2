package net.kav.staminamod.data;


import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class StaminaData {

    private static float STAMINA = 0;
    private static float MAXSTAMINA = 20;
    private static float RECOVERYRATE = 0.7f;

    public static float getSTAMINA(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getFloat("Stamina");
    }

    public static void setSTAMINA(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();
        nbt.putFloat("Stamina",amount);
    }

    public static void incrementSTAMINA(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();
        STAMINA=nbt.getFloat("Stamina");


        if (STAMINA >= MAXSTAMINA) {

            STAMINA = MAXSTAMINA;

        }
        else
        {
            STAMINA+=amount;
        }

        nbt.putFloat("Stamina",STAMINA);
    }
    public static void decrementSTAMINA(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();
        STAMINA=nbt.getFloat("Stamina");


        if (STAMINA <= 0) {

            STAMINA = 0;

        }
        else
        {
            STAMINA-=amount;
        }

        nbt.putFloat("Stamina",STAMINA);
    }

    public static float getMAXSTAMINA(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        MAXSTAMINA =nbt.getFloat("Maxstamina");
        if(MAXSTAMINA<=0)
        {
            MAXSTAMINA=20;
        }
        nbt.putFloat("Maxstamina",MAXSTAMINA);
        return MAXSTAMINA;
    }

    public static void setMAXSTAMINA(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();

        nbt.putFloat("Maxstamina",amount);
    }

    public static void incrementMAXSTAMINA(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();



        MAXSTAMINA=nbt.getFloat("Maxstamina");
        MAXSTAMINA+=amount;

        //recovery rate to be ajusted

        nbt.putFloat("Maxstamina",MAXSTAMINA);
        nbt.putFloat("Recoveryrate",RECOVERYRATE);
    }

    public static void decrementMAXSTAMINA(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();



        MAXSTAMINA=nbt.getFloat("Maxstamina");
        MAXSTAMINA-=amount;

        if(MAXSTAMINA<=0)
        {
            MAXSTAMINA=5;
        }
        //recovery rate to be ajusted

        nbt.putFloat("Maxstamina",MAXSTAMINA);
        nbt.putFloat("Recoveryrate",RECOVERYRATE);
    }

    public static float getRECOVERYRATE(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        RECOVERYRATE=nbt.getFloat("Recoveryrate");

        return RECOVERYRATE;
    }

    public static void setRECOVERYRATE(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();
        RECOVERYRATE=nbt.getFloat("Recoveryrate");

        RECOVERYRATE=amount;
        nbt.putFloat("Recoveryrate",RECOVERYRATE);
    }


}
