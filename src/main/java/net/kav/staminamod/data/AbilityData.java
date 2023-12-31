package net.kav.staminamod.data;

import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AbilityData {

    public static List<AbilityCore> Techics= new ArrayList<>();
    public static void setAbility(IEntityDataSaver player,List<AbilityCore> abilityCores)
    {


       for(AbilityCore abilities:abilityCores)
       {
          addAbility(player,abilities);
       }

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
        int i =0;
        for(AbilityCore ap: AbilityManager.abilityCoreList)
        {

            if(nbtCompound.contains(ap.filename))
            {
                //System.out.println(ap.ID+ " c");
                abilityCores.add(ap);
            }
            i++;
        }

        return abilityCores;
    }

}
