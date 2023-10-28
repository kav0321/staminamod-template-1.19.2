package net.kav.staminamod.data;


import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class StaminaData {

    private static float STAMINA = 0;
    private static float DEFAULT_MAXIMUM=25;
    private static float MAXSTAMINA = 25;

    private static int EXTRA_STAMINA = 0;
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
        MAXSTAMINA=getMAXSTAMINA(player);
        EXTRA_STAMINA= getMAXSTAMINAtemp(player);

        if (STAMINA >= MAXSTAMINA+EXTRA_STAMINA) {

            STAMINA = MAXSTAMINA+EXTRA_STAMINA;

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
            MAXSTAMINA=DEFAULT_MAXIMUM;
        }
        nbt.putFloat("Maxstamina",MAXSTAMINA);
        return MAXSTAMINA;
    }

    public static void setMAXSTAMINA(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();
        float amounts=(amount<=0)?DEFAULT_MAXIMUM:amount;

        nbt.putFloat("Maxstamina",amounts);
    }
    public static void increaseMAXSTAMINAtemp(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        EXTRA_STAMINA = nbt.getInt("Maxstamina_t");
        EXTRA_STAMINA+=amount;
        nbt.putInt("Maxstamina_t",EXTRA_STAMINA);
    }

    public static int getMAXSTAMINAtemp(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();

        return nbt.getInt("Maxstamina_t");
    }

    public static void incrementMAXSTAMINA(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();

        MAXSTAMINA=nbt.getFloat("Maxstamina");
        MAXSTAMINA+=amount;
        //System.out.println(MAXSTAMINA);
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

    }

    public static float getRECOVERYRATE(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();

        MAXSTAMINA=nbt.getFloat("Maxstamina");
        RECOVERYRATE=MAXSTAMINA/120;

        return RECOVERYRATE;
    }




}
