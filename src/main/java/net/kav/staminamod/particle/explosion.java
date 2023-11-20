package net.kav.staminamod.particle;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class explosion {
    public static void spawnParticles(World world, double x, double y, double z, float power, boolean isUnderWater, boolean didDestroyBlocks, boolean isImportant) {






            float blastwavePower = power * 1.75f;
            float fireballPower = power * 1.25f;
            float smokePower = power * 0.4f;
        world.addParticle(ModParticles.SHOCKWAVE, isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
        world.addParticle(ModParticles.BLASTWAVE, isImportant, x, y, z, blastwavePower, 0, 0);
    }



    private static int nextBetween(int min, int max) {
        return MathHelper.nextBetween(Random.create(), min, max);
    }
}
