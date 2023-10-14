package net.kav.staminamod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.math.MatrixStack;

public class AbilityScreen extends CottonClientScreen {
    public AbilityScreen(GuiDescription description) {
        super(description);
    }
    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        super.render(matrices, mouseX, mouseY, partialTicks);
        assert this.client != null;
        if (this.client.player != null) {
            int scaledWidth = this.client.getWindow().getScaledWidth();
            int scaledHeight = this.client.getWindow().getScaledHeight();

            if(!AbiltyGui.SLOT_TEMP.equals(AbiltyGui.SLOT_TEMP_empty))
            {
                RenderSystem.setShaderTexture(0, AbiltyGui.SLOT_TEMP);
                InventoryScreen.drawTexture(matrices,mouseX,mouseY,0,0,32,32,32,32);
            }


            //InventoryScreen.drawEntity(scaledWidth / 2 +75, scaledHeight / 2 -60, 30, -mouseX/20+28, -mouseY/4, this.client.player);
        }
    }

}
