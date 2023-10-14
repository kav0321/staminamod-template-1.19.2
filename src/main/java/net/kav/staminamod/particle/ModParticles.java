package net.kav.staminamod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.kav.staminamod.StaminaMod;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {

    public static final DefaultParticleType CITRINE_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(StaminaMod.MODID, "dash_particles"),
                CITRINE_PARTICLE);
    }
}
