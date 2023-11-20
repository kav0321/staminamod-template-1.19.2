package net.kav.staminamod.util;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;

public interface IModAnimatedPlayer {
    ModifierLayer<IAnimation> modid_getModAnimation();
}
