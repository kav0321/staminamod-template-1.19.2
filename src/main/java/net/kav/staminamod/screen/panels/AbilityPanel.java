package net.kav.staminamod.screen.panels;

import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
//code belong to https://github.com/Globox1997/VillagerQuests/blob/1.19/src/main/java/net/villagerquests/gui/PlayerQuestInnerPanel.java
public class AbilityPanel extends WPlainPanel {
    @Override
    public void add(WWidget w, int x, int y, int width, int height) {
        children.add(0, w);
        w.setParent(this);
        w.setLocation(insets.left() + x, insets.top() + y);
        if (w.canResize()) {
            w.setSize(width, height);
        }

        expandToFit(w, insets);
    }
}
