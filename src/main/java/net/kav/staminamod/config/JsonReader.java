package net.kav.staminamod.config;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class JsonReader {

    public static void  init() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new postureloader());
    }
}
