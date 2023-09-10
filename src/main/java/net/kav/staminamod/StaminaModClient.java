package net.kav.staminamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.event.client.KeyInputHandler;
import net.kav.staminamod.event.client.client_tick;
import net.kav.staminamod.event.client.clientjoin;
import net.kav.staminamod.networking.ModMessages;

public class StaminaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AbilityManager.INITTECHNIC();
        ModMessages.registerS2CPackets();
        KeyInputHandler.register();
        ClientPlayConnectionEvents.JOIN.register(new clientjoin());
        ClientTickEvents.END_WORLD_TICK.register(new client_tick());
    }
}
