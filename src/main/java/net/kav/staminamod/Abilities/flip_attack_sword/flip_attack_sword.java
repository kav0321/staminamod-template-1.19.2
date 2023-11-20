package net.kav.staminamod.Abilities.flip_attack_sword;

import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.data.KickTimingData;
import net.kav.staminamod.data.flipdata;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class flip_attack_sword extends AbilityCore {
    public flip_attack_sword(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename, speed, head, body, righthand, lefthand, rightleg, leftleg);
    }

    @Override
    public void tick(PlayerEntity player) {

        if(flipdata.getDid_I_flip((IEntityDataSaver) player))
        {
            if(flipdata.gettick((IEntityDataSaver) player)>0)
            {
                flipdata.decreasetick((IEntityDataSaver) player,1);
            }
            else
            {
                flipdata.settick((IEntityDataSaver) player,0);
            }
            if(flipdata.gettick((IEntityDataSaver) player)==0)
            {
                if(!player.world.isClient())
            {
            float range = reach(player);

                for (LivingEntity entity : getEntitiesNearby(player.getWorld(),1, range, e -> (e instanceof LivingEntity),player))
                {

                    Vec3d playerDirection = player.getRotationVector();

                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,20,1));


                    // Calculate the direction vector from the player to the entity

                    Vec3d direction = playerDirection.normalize();
                    entity.takeKnockback(1,-direction.getX(),-direction.getZ());
                    // Teleport the entity to the push position
                    player.attack(entity);


                }

            }
                Vec3d playerlooking= player.getRotationVec(1.0F);
                Vec3d vect= new Vec3d(-playerlooking.getX()*3,playerlooking.getY(),-playerlooking.getZ()*3);
                player.setVelocity(vect);
                flipdata.setDid_I_flip((IEntityDataSaver) player,false);
            }
        }
    }

    @Override
    public void ClientSideExecution() {
        flipdata.settick((IEntityDataSaver) MinecraftClient.getInstance().player,20);
        flipdata.setDid_I_flip((IEntityDataSaver) MinecraftClient.getInstance().player,true);
    }

    public float reach(PlayerEntity player)
    {
        ItemStack heldItem = player.getMainHandStack();
        Item item = heldItem.getItem();
        float range;
        // Check the type of item and return the appropriate reach distance
        if (item instanceof SwordItem) {
            // Swords have a longer reach
            range = 1.3f; // Adjust this value as needed
        } else if (item instanceof AxeItem) {
            // Axes have a different reach
            range = 1.2f; // Adjust this value as needed
        } else if (item instanceof ToolItem) {
            // You can handle other tools or items here
            range = 1.1f; // Adjust this value as needed
        } else {
            // Default reach distance for other items or no item
            range = 1f;
        }
        return range;
    }
    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {
        flipdata.settick((IEntityDataSaver) player,20);
        flipdata.setDid_I_flip((IEntityDataSaver) player,true);


    }
}
