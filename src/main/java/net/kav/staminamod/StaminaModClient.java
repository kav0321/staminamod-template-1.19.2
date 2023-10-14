package net.kav.staminamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.entity.ModEntities;
import net.kav.staminamod.event.client.KeyInputHandler;
import net.kav.staminamod.event.client.client_tick;
import net.kav.staminamod.event.client.clientjoin;
import net.kav.staminamod.event.client.playerleave;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.particle.ModParticles;
import net.kav.staminamod.particle.abilities.dash_particle;
import net.kav.staminamod.sound.ModSounds;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.util.registry.Registry;

public class StaminaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AbilityManager.INITTECHNIC();
        ModMessages.registerS2CPackets();
        KeyInputHandler.register();
        ClientPlayConnectionEvents.JOIN.register(new clientjoin());
        ClientPlayConnectionEvents.DISCONNECT.register(new playerleave());
        ClientTickEvents.END_WORLD_TICK.register(new client_tick());
        //Registry.register(Registry.SOUND_EVENT, ModSounds.DASH_SWORD_ID,ModSounds.MY_SOUND_EVENT);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CITRINE_PARTICLE, dash_particle.Factory::new);
        ModEntities.registerEntityclient();
    }
}
