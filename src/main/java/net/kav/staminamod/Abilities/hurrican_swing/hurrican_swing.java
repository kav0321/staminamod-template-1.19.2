package net.kav.staminamod.Abilities.hurrican_swing;

import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.data.KickTimingData;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.data.hurrican_swing_data;
import net.kav.staminamod.data.sword_dashData;
import net.kav.staminamod.entity.ModEntities;
import net.kav.staminamod.entity.abilities.hurrican_entity;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static java.lang.Math.*;

public class hurrican_swing extends AbilityCore {
    public hurrican_swing(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename, speed, head, body, righthand, lefthand, rightleg, leftleg);
    }

    @Override
    public void tick(PlayerEntity player) {
        if(sword_dashData.getLevel(((IEntityDataSaver) player)) == true)
        {

            hurrican_swing_data.settick((IEntityDataSaver) player,0);
            return;
        }

        if(hurrican_swing_data.gettrigger((IEntityDataSaver) player))
        {
            if(player.world.isClient && hurrican_swing_data.gettick((IEntityDataSaver) player)%30==0)
            {
                StaminaData.decrementSTAMINA((IEntityDataSaver) player,8);

            }
                if(!player.world.isClient())
                {

                }


            if(hurrican_swing_data.gettick((IEntityDataSaver) player)>0)
            {
                hurrican_swing_data.decreasetick((IEntityDataSaver) player,1);
            }
            else
            {
                hurrican_swing_data.settick((IEntityDataSaver) player,0);
            }
            if(sword_dashData.gettick3((IEntityDataSaver) player)==0)
            {
                player.setVelocity(Vec3d.ZERO);
            }

            if(hurrican_swing_data.gettick((IEntityDataSaver) player)==0)
            {

                hurrican_swing_data.settrigger((IEntityDataSaver) player,false);
            }
        }

    }

    @Override
    public void ClientSideExecution() {
        hurrican_swing_data.settrigger((IEntityDataSaver) MinecraftClient.getInstance().player,true);
        hurrican_swing_data.settick((IEntityDataSaver) MinecraftClient.getInstance().player, (int) (132f/1.1f));

    }

    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {
        hurrican_swing_data.settrigger((IEntityDataSaver) player,true);
        hurrican_swing_data.settick((IEntityDataSaver) player, (int) (132/1.1));
        World world = player.getWorld();
        hurrican_entity hurrican = new hurrican_entity(ModEntities.HITBOX, world);
        hurrican.owner= player;
       // hurrican.updatePosition(player.getX()+2, player.getY(), player.getZ()+2);
        world.spawnEntity(hurrican);
        hurrican.setPosition(player.getX()+2, player.getY()-1, player.getZ()+2);
      //  hurrican.setVelocity(100,100,10);



    }
    public void movement(PlayerEntity player, double radius, double ticksPerRevolution, int tickCounter,int position) {
        double angle = (2.0 * Math.PI * (tickCounter % ticksPerRevolution)) / ticksPerRevolution;

        double xOffset = Math.sin(angle) * radius;
        double zOffset = Math.cos(angle) * radius;

        double playerX = player.getX();
        double playerZ = player.getZ();

        double targetX = playerX + xOffset;
        double targetZ = playerZ + zOffset;

       // double motionX = targetX - this.getX();
     //   double motionZ = targetZ - this.getZ();

       // double distance = Math.sqrt(motionX * motionX + motionZ * motionZ);
        //System.out.println(xOffset+ " "+zOffset);
        double speed = 2; // Adjust the speed as needed.
       // double factor = speed / distance;
        //  this.setPos(targetX, this.getY(), targetZ);
        //this.addVelocity(motionX * factor, 1, motionZ * factor);

    }

    @Override
    public boolean conditions(PlayerEntity player) {

        if(player.getMainHandStack().getItem() instanceof SwordItem || player.getMainHandStack().getItem() instanceof ToolItem)
        {
            if(player.isOnGround())
            {
                return super.conditions(player);
            }

        }
        else
        {
            this.error =Text.translatable("ability.hurricane.error_tool");
            player.sendMessage(error,true);
        }
        return false;
    }
}
