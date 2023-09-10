package net.kav.staminamod.data;

import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.List;

public class AbilityData {

    public static List<AbilityCore> Techics= new ArrayList<>();
    public static void setAbility(IEntityDataSaver player,List<AbilityCore> abilityCores)
    {
        NbtCompound nbtCompound= player.getPersistentData();
       List<Integer> ID= new ArrayList<>();
       for(AbilityCore abilities:abilityCores)
       {
           ID.add(abilities.ID);
       }

        nbtCompound.putIntArray("id_abilities",ID);
    }

    public static <T extends AbilityCore> void addAbility (IEntityDataSaver player, T abilityCores)
    {



        NbtCompound nbtCompound= player.getPersistentData();


        nbtCompound.putInt(abilityCores.filename,abilityCores.ID);

    }

    public static List<AbilityCore> getAbility(IEntityDataSaver player)
    {
        List<AbilityCore> abilityCores = new ArrayList<>();
        NbtCompound nbtCompound= player.getPersistentData();

        for(AbilityCore ap: AbilityManager.abilityCoreList)
        {
            if(nbtCompound.contains(ap.filename))
            {
                abilityCores.add(ap);
            }
        }

        return abilityCores;
    }

}
