package net.kav.staminamod.Abilities.quickdodge;

import net.kav.staminamod.Abilities.dodge.dodge;
import net.minecraft.text.Text;

public class quickdodge extends dodge {
    public quickdodge(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename, speed, head, body, righthand, lefthand, rightleg, leftleg);
    }
}
