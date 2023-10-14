package net.kav.staminamod.entity.abilities;

import net.kav.staminamod.entity.ModEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class foot_stomp extends ProjectileEntity implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private double YAW;
    @Nullable
    public UUID owner;

    @Nullable
    public LivingEntity direc;
    public foot_stomp(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
        tick=0;
        if(!world.isClient())
        {
            ServerWorld world1 = (ServerWorld) world;
            world1.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_STONE_BREAK, SoundCategory.PLAYERS, 1f, 1.0f);
        }
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        if(direc!=null)
        {
            System.out.println("s");
            this.setYaw(direc.getYaw());
        }

    }

        @Override
    protected void initDataTracker() {

    }


    private int tick;
    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean canHit() {
        return false;
    }
    public List<LivingEntity> getEntitiesNearby(World world, double expandDistance, Predicate<LivingEntity> filter, Entity entity)
    {
        double yaw = entity.getYaw();

        return world.getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand(expandDistance, expandDistance,  expandDistance), filter.and(e -> e != entity));
    }
        @Override
    public void tick() {

        if(!this.world.isClient())
        {
            if(direc!=null) {
                tick++;
            }
            for (LivingEntity entity : getEntitiesNearby(this.getWorld(),1, e -> (e instanceof LivingEntity),this))
            {

                if(entity instanceof LivingEntity)
                {

                    if(owner !=null)
                    {
                        if(entity instanceof PlayerEntity)
                        {
                            if(entity.getUuid()!=owner)
                            {

                                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,10,false,false));
                                entity.setVelocity(Vec3d.ZERO);
                            }
                        }
                        else {
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,10,false,false));
                            entity.setVelocity(Vec3d.ZERO);
                        }
                    }


                }

            }
            if(tick==2)
            {
                if(!this.world.isClient())
                {
                    ServerWorld world1 = (ServerWorld) this.world;
                    world1.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_STONE_BREAK, SoundCategory.PLAYERS, 2f, 0.5f);
                }
            }
        if(tick==5)
        {

            if(direc!=null) {

                big_foot_stomp stomp = new big_foot_stomp(ModEntities.STOMP2,this.getWorld());

                stomp.owner=owner;
                stomp.direc=this.direc;

                Vec3d vec3d= this.direc.getRotationVec(1);
              //  stomp.setPosition(this.getX() + vec3d.x * 2.3, this.getY(), this.getZ() + vec3d.z * 2.3);
                stomp.refreshPositionAndAngles(this.getX() + vec3d.x * 1.1, this.getY(), this.getZ() + vec3d.z * 1.1, this.getYaw(), 0);
               this.world.spawnEntity(stomp);
            }

        }
            if(tick>10)
            {
                this.discard();
            }
        }
        super.tick();
    }



    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller_idle",
                0, this::predicate));


    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        System.out.println("sas");
       if(entityHitResult.getEntity() instanceof LivingEntity)
       {
           System.out.println("sas");
           LivingEntity entity = (LivingEntity) entityHitResult.getEntity();
           if(owner !=null)
           {
               if(entity instanceof PlayerEntity)
               {
                   if(entity.getUuid()==owner)
                   {

                       return;
                   }
               }
           }

         entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,10,false,false));
         entity.setVelocity(Vec3d.ZERO);
       }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.foot.stomp", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
            return PlayState.CONTINUE;
        }

        @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
