package net.kav.staminamod.Abilities.foot_stomp;

import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.data.KickTimingData;
import net.kav.staminamod.data.StompData;
import net.kav.staminamod.entity.ModEntities;
import net.kav.staminamod.entity.abilities.foot_stomp;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class stomp extends AbilityCore {
    public stomp(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename, speed, head, body, righthand, lefthand, rightleg, leftleg);
    }

    @Override
    public void tick(PlayerEntity player) {
        if(!player.world.isClient())
        {
            if(StompData.getDid_I_kick((IEntityDataSaver) player))
            {
                if(StompData.gettick((IEntityDataSaver) player)>0)
                {
                    StompData.decreasetick((IEntityDataSaver) player,1);
                }
                else
                {
                    StompData.settick((IEntityDataSaver) player,0);
                }
                if(StompData.gettick((IEntityDataSaver) player)==1)
                {
                    foot_stomp stomp = new foot_stomp(ModEntities.STOMP,player.getWorld());
                    stomp.setYaw(player.getYaw());
                    stomp.owner=player.getUuid();
                    stomp.direc=player;

                    Vec3d vec3d= player.getRotationVec(1);
                    stomp.setPosition(player.getX() + vec3d.x * 1.1, player.getY(), player.getZ() + vec3d.z * 1.1);
                    stomp.refreshPositionAndAngles(player.getX() + vec3d.x * 1.1, player.getY(), player.getZ() + vec3d.z * 1.1, player.getYaw(), 0);

                    player.world.spawnEntity(stomp);

                    stomp.direc=player;
                    if(!player.world.isClient())
                    {
                        ServerWorld world1 = (ServerWorld) player.world;
                        world1.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.2f, 0.1f);
                    }
                }
            }
        }
    }

    @Override
    public void ClientSideExecution() {

    }

    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {
        StompData.settick((IEntityDataSaver) player,30);
        StompData.setDid_I_kick((IEntityDataSaver) player,true);



       // stomp.setPosition(player.getX() + vec3d.x * 1.1, player.getY(), player.getZ() + vec3d.z * 1.1);
      //  stomp.refreshPositionAndAngles(player.getX() + vec3d.x * 1.1, player.getY(), player.getZ() + vec3d.z * 1.1, player.getYaw(), 0);


    }
}
