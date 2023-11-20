package net.kav.staminamod.particle.abilities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class dash_particle extends SpriteBillboardParticle {

    protected dash_particle(ClientWorld clientWorld, double xCoord, double yCoord, double zCoord, SpriteProvider spriteset, double xd, double yd, double zd) {
        super(clientWorld, xCoord, yCoord, zCoord, xd, yd, zd);

        this.maxAge = 16;
        this.scale = 1f;
        this.setSpriteForAge(spriteset);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }
    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }
    private void fadeOut() {
        this.alpha = (-(1/(float)maxAge) * age + 1);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new dash_particle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
