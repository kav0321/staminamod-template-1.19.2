package net.kav.staminamod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.kav.staminamod.StaminaMod;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {

    public static final DefaultParticleType CITRINE_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType SHOCKWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType BLASTWAVE = FabricParticleTypes.simple();

    public static final DefaultParticleType IMPACT1 = FabricParticleTypes.simple();
    public static final DefaultParticleType FLY_BLAST = FabricParticleTypes.simple();
    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(StaminaMod.MODID, "dash_particles"),
                CITRINE_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(StaminaMod.MODID, "shockwave"), SHOCKWAVE);
               // SHOCKWAVE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(StaminaMod.MODID, "blastwave"), BLASTWAVE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(StaminaMod.MODID, "blastwave2"), IMPACT1);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(StaminaMod.MODID, "fly_blast"), FLY_BLAST);


    }
}
