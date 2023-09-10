package net.kav.staminamod.screen.buttons;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kav.staminamod.StaminaMod;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class arrow_button extends WButton {
    public boolean isclick=false;

    Icon icon;


    int tick=0;
    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {

        if(isclick)
        {
            tick++;

            icon = new TextureIcon(new Identifier(StaminaMod.MODID,"textures/gui/buttons/arrow_button_select.png"));
            if(tick==20)
            {
                isclick=false;
                tick=0;
            }
        }
        else if (this.isEnabled()) {

            boolean hovered = (mouseX >= 0 && mouseY >= 0 && mouseX < getWidth() && mouseY < getHeight());
            icon = new TextureIcon(new Identifier(StaminaMod.MODID,"textures/gui/buttons/arrow_button_empty.png"));
            if (hovered) {
                icon = new TextureIcon(new Identifier(StaminaMod.MODID,"textures/gui/buttons/arrow_button.png"));
            }

        }

        else {
            System.out.println("sasa");
            icon = new TextureIcon(new Identifier(StaminaMod.MODID,"textures/gui/buttons/arrow_button_empty"));
        }
        icon.paint(matrices, x , y, 32);

    }

    @Override
    public void setSize(int x, int y) {
        this.width = 32;
        this.height = 32;
    }
    @Override
    public WButton setOnClick(@Nullable Runnable onClick) {
        isclick=true;
        icon =  new TextureIcon(new Identifier(StaminaMod.MODID,"textures/gui/buttons/arrow_button_select.png"));
        return super.setOnClick(onClick);
    }


}
