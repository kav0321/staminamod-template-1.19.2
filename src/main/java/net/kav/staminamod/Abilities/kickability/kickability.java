package net.kav.staminamod.Abilities.kickability;

import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.data.KickTimingData;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class kickability extends AbilityCore {
    public kickability(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename,speed,head,body,righthand,lefthand,rightleg,leftleg);
    }
    @Override
    public void ClientSideExecution()
    {
        PlayerEntity player = MinecraftClient.getInstance().player;
        KickTimingData.settick((IEntityDataSaver) player,40);
        KickTimingData.setDid_I_kick((IEntityDataSaver) player,true);
    }
    @Override
    public void tick(PlayerEntity player)
    {

        if(KickTimingData.getDid_I_kick((IEntityDataSaver) player))
        {
            if(KickTimingData.gettick((IEntityDataSaver) player)>0)
            {
                KickTimingData.decreasetick((IEntityDataSaver) player,1);
            }
            else
            {
                KickTimingData.settick((IEntityDataSaver) player,0);
            }
            if(KickTimingData.gettick((IEntityDataSaver) player)==0)
            {
                if(!player.world.isClient())
                {
                    for (LivingEntity entity : getEntitiesNearby(player.getWorld(),1,1, e -> (e instanceof LivingEntity),player))
                    {

                        Vec3d playerDirection = player.getRotationVector();
                        if(entity instanceof PlayerEntity)
                        {

                            ((PlayerEntity) entity).disableShield(false);

                        }


                        // Calculate the direction vector from the player to the entity

                        Vec3d direction = playerDirection.normalize();
                        entity.takeKnockback(ModConfigs.kick_knockback,-direction.getX(),-direction.getZ());
                        // Teleport the entity to the push position
                        entity.damage(DamageSource.player(player),0.5f);

                    }
                }
                KickTimingData.setDid_I_kick((IEntityDataSaver) player,false);
                KickTimingData.settick((IEntityDataSaver) player,0);

            }
        }
    }
    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player)
    {
        KickTimingData.settick((IEntityDataSaver) player,40);
        KickTimingData.setDid_I_kick((IEntityDataSaver) player,true);
    }

    @Override
    public boolean conditions(PlayerEntity player) {
        return super.conditions(player);
    }
}
