package net.kav.staminamod.Abilities.mega_dash;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.data.*;
import net.kav.staminamod.event.client.AttackOveride;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.particle.ModParticles;
import net.kav.staminamod.sound.ModSounds;
import net.kav.staminamod.util.CameraShakeUtil;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.Random;
import java.util.function.Predicate;

public class mega_dash extends AbilityCore {
    public mega_dash(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename, speed, head, body, righthand, lefthand, rightleg, leftleg);
    }

    @Override
    public void tick(PlayerEntity player) {
        if (mega_dashdata.getbeingadash4(((IEntityDataSaver) player)) == true)
        {




            if (mega_dashdata.gettick4((IEntityDataSaver) player) <= 0) {
                mega_dashdata.settick4((IEntityDataSaver) player, 0);
            }

            if (mega_dashdata.gettick4((IEntityDataSaver) player) > 0) {
                mega_dashdata.decreasetick4((IEntityDataSaver) player, 1);
                //second delay for dmg

            }

            if (mega_dashdata.getbeingadash4(((IEntityDataSaver) player)) && mega_dashdata.gettick4((IEntityDataSaver) player) == 0 && player.isOnGround() && player.getVelocity().x==0 &&player.getVelocity().z==0) {
                mega_dashdata.settick4((IEntityDataSaver) player, 0);
                mega_dashdata.setbeingadash4((IEntityDataSaver) player, false);
                if (!player.world.isClient())
                {
                  //  ServerWorld world = (ServerWorld) player.getWorld();

                    for (LivingEntity entity : getEntitiesNearby(player.getWorld(), 4.2, (Predicate<LivingEntity>) e -> (e instanceof LivingEntity),player))
                    {
                        Vec3d playerDirection = player.getRotationVector();
                        Vec3d direction = playerDirection.normalize();

                         //float dis = (float) Math.pow(Math.pow(entity.getX()-player.getX(),2)+Math.pow(entity.getY()-player.getY(),2)+Math.pow(entity.getZ()-player.getZ(),2),0.5);
                        entity.takeKnockback(1.5,-direction.getX(),-direction.getZ());

                        // Teleport the entity to the push position
                        entity.damage(DamageSource.player(player),4f);
                        // entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,120,10));

                    }

                    ServerWorld world = (ServerWorld) player.getWorld();
                    world.playSound(null,player.getBlockPos(), ModSounds.SLAM_FLYING, SoundCategory.MASTER,1.0f,1.0f);
                    world.spawnParticles(ModParticles.IMPACT1,            // Use DUST particle type
                            player.getX(), player.getY(), player.getZ(), // Specify the position
                            1,               // Number of particles to spawn
                            0, 0, 0,         // Offset from the specified position
                            0.01            // Particle scale
                    );
                }
            }


        }






        if (mega_dashdata.getbeingadash3(((IEntityDataSaver) player)) == true)
        {




            if (mega_dashdata.gettick3((IEntityDataSaver) player) <= 0) {
                mega_dashdata.settick3((IEntityDataSaver) player, 0);
            }
            if (mega_dashdata.gettick3((IEntityDataSaver) player) > 0) {
                mega_dashdata.decreasetick3((IEntityDataSaver) player, 1);
                //second delay for dmg

            }

            if (mega_dashdata.getbeingadash3(((IEntityDataSaver) player)) && mega_dashdata.gettick3((IEntityDataSaver) player) == 0) {
                mega_dashdata.settick4((IEntityDataSaver) player,50);
                mega_dashdata.setbeingadash4((IEntityDataSaver) player, true);
                mega_dashdata.setbeingadash3((IEntityDataSaver) player, false);
                if (player.world.isClient())
                {
                    CameraShakeUtil.startShake(5, 20);
                }
                else
                {
                   // ServerWorld world = (ServerWorld) player.getWorld();
                 //   world.playSound(null,player.getBlockPos(), ModSounds.SLAM_FLYING, SoundCategory.MASTER,1.0f,1.0f);

                }
            }


        }




        if (mega_dashdata.getbeingadash2(((IEntityDataSaver) player)) == true)
        {

            if(player.world.isClient)
            {
                CameraShakeUtil.startShake(0.5f, 3);
                StaminaData.decrementSTAMINA((IEntityDataSaver) player,2);
            }

            if(StompData.getDid_I_kick(((IEntityDataSaver) player)))
            {
                mega_dashdata.settick2((IEntityDataSaver) player, 0);

                if(player.world.isClient)
                {
                    player.setVelocity(new Vec3d(0,-10,0));

                    ClientPlayNetworking.send(
                            ModMessages.ANIMATION,
                            new Packets.AbilityAni(MinecraftClient.getInstance().player.getId(), this.ID, 15,"mega_dash_stomp").write());


                }

            }




            if (mega_dashdata.gettick2((IEntityDataSaver) player) <= 0) {
                mega_dashdata.settick2((IEntityDataSaver) player, 0);
            }
            if (mega_dashdata.gettick2((IEntityDataSaver) player) > 0) {
                mega_dashdata.decreasetick2((IEntityDataSaver) player, 1);

                Vec3d playerlooking = player.getRotationVec(1.0F);
                Vec3d vect;
                vect  = new Vec3d(playerlooking.getX() * 1.1, 0.1, playerlooking.getZ() *1.1);


                player.setVelocity(vect);
                //second delay for dmg

            }

            if (mega_dashdata.getbeingadash2(((IEntityDataSaver) player)) && mega_dashdata.gettick2((IEntityDataSaver) player) == 0) {
                //System.out.println("damn");
                player.setVelocity(new Vec3d(0,-10,0));
                mega_dashdata.setbeingadash2((IEntityDataSaver) player, false);
                mega_dashdata.setbeingadash3((IEntityDataSaver) player, true);
                mega_dashdata.settick3((IEntityDataSaver) player, 5);
           }


        }

        if (mega_dashdata.getbeingadash(((IEntityDataSaver) player)) == true)
        {

            if (mega_dashdata.gettick((IEntityDataSaver) player) <= 0) {
                mega_dashdata.settick((IEntityDataSaver) player, 0);
            }
            if (mega_dashdata.gettick((IEntityDataSaver) player) > 0) {
                mega_dashdata.decreasetick((IEntityDataSaver) player, 1);

            }



            if (mega_dashdata.getbeingadash(((IEntityDataSaver) player)) == true && mega_dashdata.gettick((IEntityDataSaver) player) == 0) {
                //System.out.println("damn");

                Vec3d playerlooking = player.getRotationVec(1.0F);
                Vec3d vect;
                vect  = new Vec3d(playerlooking.getX() * 0.5, 0.0000005, playerlooking.getZ() *0.5);


                player.setVelocity(vect);
                mega_dashdata.settick2((IEntityDataSaver) player, 20);
                mega_dashdata.setbeingadash((IEntityDataSaver) player, false);
                mega_dashdata.setbeingadash2((IEntityDataSaver) player, true);
            }
        }


    }

    @Override
    public void ClientSideExecution() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        mega_dashdata.settick((IEntityDataSaver) player,3);
        CameraShakeUtil.startShake(1, 3);
        mega_dashdata.setbeingadash((IEntityDataSaver) player,true);
    }

    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {
        mega_dashdata.settick((IEntityDataSaver) player,3);

        mega_dashdata.setbeingadash((IEntityDataSaver) player,true);
        if(!player.getWorld().isClient())
        {
            //((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,10));
            ServerWorld world = (ServerWorld) player.getWorld();
            //  source.getAttacker().playSound(ModSounds.PARRY_TWO,1.0f,1.0f);
            world.playSound(null,player.getBlockPos(), ModSounds.SONIC_BOOM_FLYING, SoundCategory.MASTER,1.0f,1.0f);
            //world.playSound(player.getX(),player.getY(),player.getZ(),ModSounds.NO_POSTURE, SoundCategory.MASTER,1.0f,1.0f,true);


        }

        player.getWorld().spawnParticles(ModParticles.FLY_BLAST,            // Use DUST particle type
                player.getX(), player.getY()+0.5f, player.getZ(), // Specify the position
                1,               // Number of particles to spawn
                0, 0, 0,         // Offset from the specified position
                0.01            // Particle scale
        );

    }
}
