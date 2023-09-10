package net.kav.staminamod.INIT;

import net.kav.staminamod.Abilities.kickability.kickability;
import net.kav.staminamod.Abilities.parryability.parryabilty;
import net.kav.staminamod.api.AbilityCore;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbilityManager {
    public static Map<Integer,AbilityCore> abiltyregister = new HashMap<>();
    public static Map<Integer,String> abilties_id = new HashMap<>();
    public static List<AbilityCore> abilityCoreList = new ArrayList<>();
    public static void  INITTECHNIC()
    {
        abilityCoreList.add(new kickability(300,8,90,"kick", Text.literal("ability.kick"),Text.literal("ability.kick.description"),"kick",1));


        abilityCoreList.add(new parryabilty(300,8,180,"parry", Text.literal("ability.parry"),Text.literal("ability.parry.description"),"parry",1f));


        for(int i =0;i<abilityCoreList.size();i++)
        {
            abiltyregister.put(abilityCoreList.get(i).ID,abilityCoreList.get(i));
        }

    }
}
