package net.kav.staminamod.screen;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;

public class AbilityScreen extends CottonClientScreen {
    public AbilityScreen(GuiDescription description) {
        super(description);
    }
    @Override
    public boolean shouldPause() {
        return false;
    }
}
