package net.kav.staminamod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.kav.staminamod.Abilities.parryability.parryabilty;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.entity.ModEntities;
import net.kav.staminamod.event.server.playerdeath;
import net.kav.staminamod.event.server.server_tick;
import net.kav.staminamod.items.ModItems;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.particle.ModParticles;
import net.kav.staminamod.sound.ModSounds;
import net.kav.staminamod.statusEffect.ModStatusEffects;
import net.kav.staminamod.util.ModLootTableModifiers;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.kav.staminamod.INIT.AbilityManager.abilityCoreList;

public class StaminaMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MODID= "staminamod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModConfigs.registerConfigs();

		ModEntities.server();
		AbilityManager.INITTECHNIC();
		ModMessages.registerC2SPackets();

		ServerPlayerEvents.AFTER_RESPAWN.register(new playerdeath());
		ServerTickEvents.END_WORLD_TICK.register(new server_tick());
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((ServerLivingEntityEvents.AllowDamage) abilityCoreList.get(1));
		ModStatusEffects.registerEffects();

		ModParticles.registerParticles();
		Registry.register(Registry.SOUND_EVENT, ModSounds.DASH_SWORD_ID,ModSounds.MY_SOUND_EVENT);
		ModLootTableModifiers.modifyLootTables();
		ModItems.registerModItems();
		LOGGER.info("Hello Fabric world!");
	}
}