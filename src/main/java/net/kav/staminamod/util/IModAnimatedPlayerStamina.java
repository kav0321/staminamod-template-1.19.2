package net.kav.staminamod.util;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;

public interface IModAnimatedPlayerStamina {
    ModifierLayer<IAnimation> modid_getModAnimation();

    void playAttackAnimation2stamina(String name, float length, int index);
}
