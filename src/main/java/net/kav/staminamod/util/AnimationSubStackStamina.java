package net.kav.staminamod.util;

import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AdjustmentModifier;
import dev.kosmx.playerAnim.api.layered.modifier.MirrorModifier;

public class AnimationSubStackStamina {
    public final TransmissionSpeedModifierStamina speed = new TransmissionSpeedModifierStamina();
    public final MirrorModifier mirror = new MirrorModifier();
    public final ModifierLayer base = new ModifierLayer(null);
    public final AdjustmentModifier adjustmentModifier;

    public AnimationSubStackStamina(AdjustmentModifier adjustmentModifier) {
        this.adjustmentModifier = adjustmentModifier;
        mirror.setEnabled(false);
        base.addModifier(adjustmentModifier, 0);
        base.addModifier(speed, 0);
        base.addModifier(mirror, 0);
    }
}
