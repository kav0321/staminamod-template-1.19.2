package net.kav.staminamod.screen.togels;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.kav.staminamod.StaminaMod;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class slotselect extends WButton {

    public Identifier SLOT_TEMP = new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
    Icon ability;
    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        Icon icon;
        ability = new TextureIcon(SLOT_TEMP);



        if(this.isEnabled())
        {
            boolean hovered = (mouseX >= 0 && mouseY >= 0 && mouseX < getWidth() && mouseY < getHeight());
            icon = new TextureIcon(new Identifier(StaminaMod.MODID,"textures/gui/slot/slot.png"));
            if (hovered) {
                icon = new TextureIcon(new Identifier(StaminaMod.MODID,"textures/gui/slot/slot_select.png"));
            }
        }
        else
        {
            icon = new TextureIcon(new Identifier(StaminaMod.MODID,"textures/gui/slot/slot_hover.png"));
        }

        ability.paint(matrices, x , y, 32);
        icon.paint(matrices, x , y, 32);

    }

    @Override
    public void setSize(int x, int y) {
        this.width = 32;
        this.height = 32;
    }
}
