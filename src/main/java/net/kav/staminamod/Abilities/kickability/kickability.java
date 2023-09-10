package net.kav.staminamod.Abilities.kickability;

import net.kav.staminamod.api.AbilityCore;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class kickability extends AbilityCore {
    public kickability(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename,float speed) {
        super(cooldown, stamina, ID, animationname, name, description, filename,speed);
    }
    @Override
    public void ClientSideExecution()
    {
        super.ClientSideExecution();
    }
    @Override
    public void tick(PlayerEntity player)
    {

    }
    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player)
    {
        for (LivingEntity entity : getEntitiesNearby(player.getWorld(),1,1, e -> (e instanceof LivingEntity),player))
        {

            Vec3d playerDirection = player.getRotationVector();
            if(entity instanceof PlayerEntity)
            {
                System.out.println("s");
                ((PlayerEntity) entity).disableShield(false);

            }


            // Calculate the direction vector from the player to the entity

            Vec3d direction = playerDirection.normalize();
            entity.takeKnockback(2,-direction.getX(),-direction.getZ());
            // Teleport the entity to the push position
            entity.damage(DamageSource.player(player),0.5f);
        }
    }
}
