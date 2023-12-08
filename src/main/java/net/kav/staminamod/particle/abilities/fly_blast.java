package net.kav.staminamod.particle.abilities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class fly_blast extends ExplosionLargeParticle {

    private final SpriteProvider sprites;
   // private static final Quaternion QUATERNION = new Quaternion(0F, -0.7F, 0.7F, 0F);

    fly_blast(ClientWorld clientWorld, double d, double e, double f, double g, SpriteProvider spriteProvider) {
        super(clientWorld, d, e, f, g, spriteProvider);
        this.maxAge = 9;
        this.scale = 2f;
        this.sprites=spriteProvider;
        this.setSpriteForAge(spriteProvider);

    }
    private int extendedLifetime = 0;


    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    //Makes the particle emissive


    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(sprites); // Assuming the second parameter can be 0 or based on your requirement
    }
    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {

        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }


        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new fly_blast(clientWorld, d, e, f, g, this.spriteProvider);
        }
    }

}
