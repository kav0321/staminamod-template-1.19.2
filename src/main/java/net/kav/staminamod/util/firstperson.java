package net.kav.staminamod.util;

import dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import org.jetbrains.annotations.NotNull;

public class firstperson extends KeyframeAnimationPlayer {
    public firstperson(@NotNull KeyframeAnimation animation) {
        super(animation,0);
    }

    @Override
    public FirstPersonMode getFirstPersonMode(float tickDelta) {
        return FirstPersonMode.THIRD_PERSON_MODEL;
    }
}
