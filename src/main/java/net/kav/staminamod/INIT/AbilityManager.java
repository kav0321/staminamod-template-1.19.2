package net.kav.staminamod.INIT;

import net.kav.staminamod.Abilities.counter_parry.guard_counter;
import net.kav.staminamod.Abilities.dodge.dodge;
import net.kav.staminamod.Abilities.flip_attack_sword.flip_attack_sword;
import net.kav.staminamod.Abilities.foot_stomp.stomp;
import net.kav.staminamod.Abilities.hurrican_swing.hurrican_swing;
import net.kav.staminamod.Abilities.kickability.kickability;
import net.kav.staminamod.Abilities.mega_dash.mega_dash;
import net.kav.staminamod.Abilities.parryability.parryabilty;
import net.kav.staminamod.Abilities.shield_offensive_attacks.shield_crush;
import net.kav.staminamod.Abilities.sword_dash.sword_dash;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.entity.abilities.foot_stomp;
import net.minecraft.text.Text;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;

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

        abilityCoreList.add(new kickability(ModConfigs.kick_cooldown,ModConfigs.kick_stamina,90,"kick", Text.translatable("ability.kick"),Text.translatable("ability.kick.description"),"kick",1,true,false,false,false,false,false));


        abilityCoreList.add(new parryabilty(ModConfigs.parry_cooldown,ModConfigs.parry_stamina,180,"parry", Text.translatable("ability.parry"),Text.translatable("ability.parry.description"),"parry",1f,true,false,false,false,false,false));
        abilityCoreList.add(new dodge(ModConfigs.dodge_cooldown, ModConfigs.dodge_stamina,270,"dodge", Text.translatable("ability.dodge"),Text.translatable("ability.dodge.description"),"dodge",1.3f,true,false,false,false,false,false));
        abilityCoreList.add(new sword_dash(ModConfigs.sword_cooldown,ModConfigs.sword_dash,360,"dash", Text.translatable("ability.dash"),Text.translatable("ability.dash.description"),"dash",1f,true,false,false,false,false,false));
        abilityCoreList.add(new stomp(ModConfigs.foot_stomp_cooldown,ModConfigs.foot_stomp_stamina,450,"stomp", Text.translatable("ability.stomp"),Text.translatable("ability.stomp.description"),"stomp",1f,true,false,false,false,false,false));
        abilityCoreList.add(new flip_attack_sword(ModConfigs.flip_attack_sword_cooldown,ModConfigs.flip_attack_sword_stamina,540,"flip_attack_sword", Text.translatable("ability.flip_attack_sword"),Text.translatable("ability.flip_attack_sword.description"),"flip_attack_sword",1f,true,false,false,false,false,false));
        abilityCoreList.add(new hurrican_swing(ModConfigs.hurrican_swing_cooldown,ModConfigs.hurrican_swing_stamina,630,"hurrican_swing", Text.translatable("ability.hurrican_swing"),Text.translatable("ability.hurrican_swing.description"),"hurrican_swing",1.1f,false,false,false,false,false,false));
        abilityCoreList.add(new guard_counter(6,5,720,"guard_counter", Text.translatable("ability.guard_counter"),Text.translatable("ability.guard_counter.description"),"guard_counter",2f,true,false,false,false,false,false));
        abilityCoreList.add(new shield_crush(300,10,810,"shield_cursher", Text.translatable("ability.shield_cursher"),Text.translatable("ability.shield_cursher.description"),"shield_cursher",2f,false,false,false,false,false,false));
        abilityCoreList.add(new mega_dash(60,10,900,"mega_dash", Text.translatable("ability.mega_dash"),Text.translatable("ability.mega_dash.description"),"mega_dash",2f,false,false,false,false,false,false));


        for(int i =0;i<abilityCoreList.size();i++)
        {
            //System.out.println(abilityCoreList.size()+" abilities");
            abiltyregister.put(abilityCoreList.get(i).ID,abilityCoreList.get(i));
        }

    }

    public static void  INITTECHNIC2()
    {
        abilityCoreList.get(0).stamina=ModConfigs.kick_stamina;
        abilityCoreList.get(0).cooldown=ModConfigs.kick_cooldown;

        abilityCoreList.get(1).stamina=ModConfigs.parry_stamina;
        abilityCoreList.get(1).cooldown=ModConfigs.parry_cooldown;

        abilityCoreList.get(2).stamina=ModConfigs.dodge_stamina;
        abilityCoreList.get(2).cooldown=ModConfigs.dodge_cooldown;

        abilityCoreList.get(3).stamina=ModConfigs.sword_dash;
        abilityCoreList.get(3).cooldown=ModConfigs.sword_cooldown;

        abilityCoreList.get(4).stamina=ModConfigs.foot_stomp_stamina;
        abilityCoreList.get(4).cooldown=ModConfigs.foot_stomp_cooldown;

        abilityCoreList.get(5).stamina=ModConfigs.flip_attack_sword_stamina;
        abilityCoreList.get(5).cooldown=ModConfigs.flip_attack_sword_cooldown;

        abilityCoreList.get(6).stamina=ModConfigs.hurrican_swing_stamina;
        abilityCoreList.get(6).cooldown=ModConfigs.hurrican_swing_cooldown;
    }
}
