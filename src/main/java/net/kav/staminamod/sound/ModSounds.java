package net.kav.staminamod.sound;

import net.kav.staminamod.StaminaMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

    public static final Identifier DASH_SWORD_ID = new Identifier(StaminaMod.MODID,"dash_sword");
    public static SoundEvent MY_SOUND_EVENT = new SoundEvent(DASH_SWORD_ID);


}
