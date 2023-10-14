package net.kav.staminamod.INIT;

import net.kav.staminamod.Abilities.dodge.dodge;
import net.kav.staminamod.Abilities.flip_attack_sword.flip_attack_sword;
import net.kav.staminamod.Abilities.foot_stomp.stomp;
import net.kav.staminamod.Abilities.kickability.kickability;
import net.kav.staminamod.Abilities.parryability.parryabilty;
import net.kav.staminamod.Abilities.sword_dash.sword_dash;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.entity.abilities.foot_stomp;
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
        abilityCoreList.add(new kickability(300,8,90,"kick", Text.translatable("ability.kick"),Text.translatable("ability.kick.description"),"kick",1,true,false,false,false,false,false));


        abilityCoreList.add(new parryabilty(300,8,180,"parry", Text.translatable("ability.parry"),Text.translatable("ability.parry.description"),"parry",1f,true,false,false,false,false,false));
        abilityCoreList.add(new dodge(10,8,270,"dodge", Text.translatable("ability.dodge"),Text.translatable("ability.dodge.description"),"dodge",2f,true,false,false,false,false,false));
        abilityCoreList.add(new sword_dash(200,10,360,"dash", Text.translatable("ability.dash"),Text.translatable("ability.dash.description"),"dash",2f,true,false,false,false,false,false));
        abilityCoreList.add(new stomp(10,8,450,"stomp", Text.translatable("ability.stomp"),Text.translatable("ability.stomp.description"),"stomp",1f,true,false,false,false,false,false));
        abilityCoreList.add(new flip_attack_sword(100,8,540,"flip_attack_sword", Text.translatable("ability.flip_attack_sword"),Text.translatable("ability.flip_attack_sword.description"),"flip_attack_sword",1f,true,false,false,false,false,false));


        for(int i =0;i<abilityCoreList.size();i++)
        {
            System.out.println(abilityCoreList.size()+" abilities");
            abiltyregister.put(abilityCoreList.get(i).ID,abilityCoreList.get(i));
        }

    }
}
