package net.kav.staminamod.Abilities.parryability;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.data.Parrydata;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class parryabilty extends AbilityCore implements ServerLivingEntityEvents.AllowDamage{
    public parryabilty(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename,float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename,speed,head,body,righthand,lefthand,rightleg,leftleg);
    }

    @Override
    public void ClientSideExecution()
    {

    }
    @Override
    public void tick(PlayerEntity player)
    {
        if(!player.world.isClient())
        {
            if(Parrydata.gettick((IEntityDataSaver) player)<=0)
            {
                Parrydata.settick((IEntityDataSaver) player,-1);
            }
            if(Parrydata.gettick((IEntityDataSaver) player)>=0)
            {
                Parrydata.decreasetick((IEntityDataSaver) player,1);
            }

            if(Parrydata.getLevel(((IEntityDataSaver) player))==true&&Parrydata.gettick((IEntityDataSaver) player)==0)
            {
                Parrydata.setparryattack(((IEntityDataSaver) player),false);

            }
        }
    }
    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {
        Parrydata.setparryattack(((IEntityDataSaver) player),true);
        Parrydata.settick((IEntityDataSaver) player,40);
    }

    @Override
    public boolean conditions(PlayerEntity player) {
        return super.conditions(player);
    }

    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if(entity instanceof PlayerEntity)
        {
            float playerYaw=entity.getYaw();
            BlockPos playerPos= entity.getBlockPos();

            double x = entity.getX()+  Math.sin(Math.toRadians(-playerYaw));
            double y = entity.getY();
            double z = entity.getZ()+  Math.cos(Math.toRadians(-playerYaw));
            BlockPos frontPos = new BlockPos(x,y,z);

            if(entity.isBlocking())
            {
                ServerPlayNetworking.send(((ServerPlayerEntity) entity), ModMessages.SHIELD, PacketByteBufs.empty());
            }
            else if(Parrydata.getLevel(((IEntityDataSaver) entity)))
            {

                if(source.getAttacker() instanceof LivingEntity)
                {
                    double distanceSquared = source.getAttacker().getPos().squaredDistanceTo(x, y, z);
                    if(distanceSquared<=1.0)
                    {
                        ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,200,10));
                        ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,200,10));
                        ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,200,10));
                        source.getAttacker().damage(DamageSource.player((PlayerEntity) entity),0.1f);
                        source.getAttacker().playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE,1.0f,1.0f);
                        Parrydata.setparryattack(((IEntityDataSaver) entity),false);
                    }

                }
            }
        }
        return true;
    }
}
