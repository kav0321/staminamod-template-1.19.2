package net.kav.staminamod.entity.abilities;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.data.sword_dashData;
import net.kav.staminamod.entity.ModEntities;
import net.kav.staminamod.entity.abstract_entity;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class hurrican_entity extends abstract_entity implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    @Nullable
    public PlayerEntity owner;
    int tick=0;
    public hurrican_entity(EntityType<? extends abstract_entity > entityType, World world) {
        super(ModEntities.HITBOX, world);
        tick=0;
        this.speed=1;
    }
    public List<Entity> getEntitiesNearby(World world, double expandDistance, Predicate<Entity> filter, Entity entity)
    {
        double yaw = entity.getYaw();

        return world.getEntitiesByClass(Entity.class, entity.getBoundingBox().expand(expandDistance, expandDistance, expandDistance), filter.and(e -> e != entity));
    }
    @Override
    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        super.setVelocity(x, y, z, speed, divergence);

    }

    @Override
    public void tick()
    {
        if(owner==null)
        {
            this.discard();
        }
        super.tick();
        if(!this.world.isClient())
        {
            if(owner==null)
            {
                this.discard();
            }
            int range = (owner.getMainHandStack().getItem() instanceof SwordItem && this.owner!=null)? 2:1;
            for (int i = 0; i < 360; i++) {
                if (i % 80 == 0) {
                    if (i % 90 == 0) {
                        int randomx = new Random().nextInt(0, 1);
                        int randomy = new Random().nextInt(0, 2);
                        int randomz = new Random().nextInt(0, 1);
                        ServerWorld world = (ServerWorld) this.getWorld();
                        DustParticleEffect whiteDustEffect = new DustParticleEffect(new Vec3f(1.0f, 1.0f, 1.0f), 1.0f);
                        world.spawnParticles(whiteDustEffect,            // Use DUST particle type
                                this.getX() + randomx, this.getY() + 3, this.getZ() + randomz, // Specify the position
                                1,               // Number of particles to spawn
                                0, 0, 0,         // Offset from the specified position
                                0.01            // Particle scale
                        );

                    }
                }

                    if (i % 40 == 0) {
                        int randomx = new Random().nextInt(0, 1);
                        int randomy = new Random().nextInt(0, 2);
                        int randomz = new Random().nextInt(0, 1);
                        ServerWorld world = (ServerWorld) this.getWorld();
                        world.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                                this.getX() + randomx, this.getY(), this.getZ() + randomz, 1,
                                0, 0, 0, 0.01);

                    }


            }
            //System.out.println("Entity position: " + this.getX() + ", " + this.getY() + ", " + this.getZ());
            for (Entity entity : getEntitiesNearby(this.getWorld(),2, e -> (e instanceof Entity),this))
            {
                if(owner==null)
                {
                    this.discard();
                }

                            if(entity!=owner)
                            {
                                if(entity instanceof PlayerEntity)
                                {
                                    ServerPlayNetworking.send((ServerPlayerEntity) entity, ModMessages.VELOCITY,new Packets.vec3d(this.getVelocity().add(entity.getVelocity()).x,this.getVelocity().add(entity.getVelocity()).y,this.getVelocity().add(entity.getVelocity()).z).write());
                                }
                                entity.setVelocity(this.getVelocity().add(entity.getVelocity()));
                            }

            }


            if(owner!=null)
            {
                tick++;
                //System.out.println(tick+ "s");
                this.updatePositions(owner,range,20,tick);
            }
            else
            {
                this.discard();
            }
            if(tick%30==0 && tick<=110)
            {
                ServerWorld world1 = (ServerWorld) this.world;
                world1.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1f, 0.2f);
            }
            if(tick>134)
            {
                this.discard();
            }
        }

    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }
    @Override
    public boolean isCollidable() {
        return true; // Allow collisions
    }

    @Override
    public boolean isPushable() {
        return true; // Allow being pushed
    }


    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean canBeRiddenInWater() {
        return true;
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
    }


    @Override
    public boolean canHit() {
        return false;
    }
    @Override
    public boolean hasNoGravity() {
        return true;
    }
    public void updatePositions(PlayerEntity player, double radius, double ticksPerRevolution, int tickCounter) {
        double angle = (2.0 * Math.PI * (tick % ticksPerRevolution)) / ticksPerRevolution;

        double xOffset = Math.sin(angle) * radius;
        double zOffset = Math.cos(angle) * radius;

        double playerX = player.getX();
        double playerZ = player.getZ();

        double targetX = playerX + xOffset;
        double targetZ = playerZ + zOffset;

        double motionX = targetX - this.getX();
        double motionZ = targetZ - this.getZ();

        double distance = Math.sqrt(motionX * motionX + motionZ * motionZ);
        //System.out.println(xOffset+ " "+zOffset);
        double speed = 1; // Adjust the speed as needed.
        double factor = speed / distance;
      //  this.setPos(targetX, this.getY(), targetZ);
        this.setVelocity(motionX * factor, 0, motionZ * factor);

    }
    @Override
    protected boolean isBurning() {
        return false;
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
