package net.kav.staminamod.util;

import net.minecraft.util.math.Vec3d;

public class Transform
{
    public Vec3d position;
    public Vec3d eulerRot;

    public Transform()
    {
        this.position = new Vec3d(0d, 0d, 0d);
        this.eulerRot = new Vec3d(0d, 0d, 0d);
    }

    public Transform(Vec3d position, Vec3d eulerRot)
    {
        this.position = position;
        this.eulerRot = eulerRot;
    }
}
