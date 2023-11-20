package net.kav.staminamod.Abilities.shield_offensive_attacks;

import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.data.hurrican_swing_data;
import net.kav.staminamod.data.shield_crusher_data;
import net.kav.staminamod.particle.ModParticles;
import net.kav.staminamod.sound.ModSounds;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.function.Predicate;

public class shield_crush extends AbilityCore {
    public shield_crush(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename, speed, head, body, righthand, lefthand, rightleg, leftleg);
    }

    @Override
    public void tick(PlayerEntity player) {
        if(!player.world.isClient())
        {
            if(shield_crusher_data.getbeingshield((IEntityDataSaver) player))
            {
                if(shield_crusher_data.gettick((IEntityDataSaver) player)>0)
                {
                    shield_crusher_data.decreasetick((IEntityDataSaver) player,1);
                }
                else
                {
                    shield_crusher_data.settick((IEntityDataSaver) player,0);
                }
                if(shield_crusher_data.gettick((IEntityDataSaver) player)==0)
                {
                    ServerWorld world = (ServerWorld) player.getWorld();
                    world.spawnParticles(ModParticles.BLASTWAVE,            // Use DUST particle type
                            player.getX(), player.getY(), player.getZ(), // Specify the position
                            1,               // Number of particles to spawn
                            0, 0, 0,         // Offset from the specified position
                            0.01            // Particle scale
                    );
                    ServerWorld serverWorld = (ServerWorld) player.world;
                    serverWorld.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_BELL_USE, SoundCategory.PLAYERS, 1f, 0.5f);
                    serverWorld.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_BELL_RESONATE, SoundCategory.PLAYERS, 1f, 0.5f);
                    for (LivingEntity entity : getEntitiesNearby(player.getWorld(), 8.0, (Predicate<LivingEntity>) e -> (e instanceof LivingEntity),player))
                    {
                        Vec3d playerDirection = player.getRotationVector();
                        Vec3d direction = playerDirection.normalize();
                        float dis = (float) Math.pow(Math.pow(entity.getX()-player.getX(),2)+Math.pow(entity.getY()-player.getY(),2)+Math.pow(entity.getZ()-player.getZ(),2),0.5);
                        entity.takeKnockback(2+1/(dis+0.01),-direction.getX(),-direction.getZ());
                        // Teleport the entity to the push position
                        entity.damage(DamageSource.player(player),0.6f);
                       // entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,120,10));

                        //entity.setVelocity(Vec3d.ZERO);
                    }
                    shield_crusher_data.setbeingshield((IEntityDataSaver) player,false);
                }
            }
        }
    }

    @Override
    public void ClientSideExecution() {

    }

    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {

        shield_crusher_data.setbeingshield((IEntityDataSaver) player,true);
        shield_crusher_data.settick((IEntityDataSaver) player,20);
    }

    @Override
    public boolean conditions(PlayerEntity player) {

        if(player.getOffHandStack().getItem() instanceof ShieldItem)
        {
            if(player.isBlocking())
            {
                return super.conditions(player);
            }
            else
            {
                this.error =Text.translatable("ability.shield_crusher.error_use");
                player.sendMessage(error,true);
            }

        }
        else
        {
            this.error =Text.translatable("ability.shield_crusher.error_tool");
            player.sendMessage(error,true);
        }
        return false;
    }
}
