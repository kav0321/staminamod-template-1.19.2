package net.kav.staminamod.sound;

import net.kav.staminamod.StaminaMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final Identifier DASH_SWORD_ID = new Identifier(StaminaMod.MODID,"dash_sword");
    public static final Identifier CLASH_SWORD_ID_1 = new Identifier(StaminaMod.MODID,"blockone");
    public static final Identifier CLASH_SWORD_ID_2 = new Identifier(StaminaMod.MODID,"blocktwo");
    public static final Identifier CLASH_SWORD_ID_3 = new Identifier(StaminaMod.MODID,"blockthree");
    public static final Identifier PARRY1_ID = new Identifier(StaminaMod.MODID,"parryone");
    public static final Identifier PARRY2_ID = new Identifier(StaminaMod.MODID,"parrytwo");
    public static final Identifier NO_POSTURE_ID = new Identifier(StaminaMod.MODID,"zero_posture");


    public static SoundEvent CLASH_SWORD_ONE = new SoundEvent(CLASH_SWORD_ID_1);
    public static SoundEvent DASH_SWORD_EVENT = new SoundEvent(DASH_SWORD_ID);
    public static SoundEvent CLASH_SWORD_TWO = new SoundEvent(CLASH_SWORD_ID_2);
    public static SoundEvent CLASH_SWORD_THREE = new SoundEvent(CLASH_SWORD_ID_3);
    public static SoundEvent PARRY_ONE = new SoundEvent(PARRY1_ID);
    public static SoundEvent PARRY_TWO = new SoundEvent(PARRY2_ID);
    public static SoundEvent NO_POSTURE = new SoundEvent(NO_POSTURE_ID);

}
