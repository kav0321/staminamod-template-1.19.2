package net.kav.staminamod.Abilities.parryability;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.api.multiple_animations;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.data.Parrydata;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.sound.ModSounds;
import net.kav.staminamod.util.IEntityDataSaver;
import net.kav.staminamod.util.IPosture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import static java.lang.Math.abs;
import static net.kav.staminamod.config.ModConfigs.parry_posture_dmg;

public class parryabilty extends AbilityCore implements ServerLivingEntityEvents.AllowDamage, multiple_animations {
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
        Parrydata.settick((IEntityDataSaver) player,30);
    }

    public boolean conditions(PlayerEntity player) {

        if(player.getOffHandStack().getItem() instanceof SwordItem ||player.getOffHandStack().getItem()==Items.SHIELD ||player.getMainHandStack().getItem() instanceof SwordItem ||player.getMainHandStack().getItem()==Items.SHIELD)
        {
            if(player.getOffHandStack().getItem()!= Items.WOODEN_SWORD && player.getMainHandStack().getItem()!=Items.WOODEN_SWORD)
            {

                return super.conditions(player);
            }
            else
            {
                this.error =Text.translatable("ability.parry.error_tool_wooden");
                player.sendMessage(error,true);
            }

        }
        else
        {
            this.error =Text.translatable("ability.parry.error_tool");
            player.sendMessage(error,true);
        }
        return false;
    }

    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if(entity instanceof PlayerEntity && source.getAttacker() !=null)
        {
            if(source.getAttacker() instanceof LivingEntity)
            {
                if(!entity.canSee(source.getAttacker()) && abs(entity.getMaxHealth()-((LivingEntity) source.getAttacker()).getMaxHealth())>30)
                {
                    return true;
                }
                float playerYaw=entity.getYaw();
                BlockPos playerPos= entity.getBlockPos();

                double x = entity.getX()+  Math.sin(Math.toRadians(-playerYaw));
                double y = entity.getY();
                double z = entity.getZ()+  Math.cos(Math.toRadians(-playerYaw));
                BlockPos frontPos = new BlockPos(x,y,z);

               if(Parrydata.getLevel(((IEntityDataSaver) entity)))
                {

                    if(source.getAttacker() instanceof LivingEntity)
                    {
                        double distanceSquared = source.getAttacker().getPos().squaredDistanceTo(x, y, z);


                        if(distanceSquared<=1.0 )
                        {
                            IPosture entity1= (IPosture) source.getAttacker();
                            System.out.println(parry_posture_dmg);
                            entity1.incrementposture_float(parry_posture_dmg);
                            Vec3d playerDirection = entity.getRotationVector();
                            Vec3d direction = playerDirection.normalize();
                            ((LivingEntity) source.getAttacker()).takeKnockback(0.2,-direction.getX(),-direction.getZ());

                            if(entity1.getposture_number()<=0)
                            {
                               // ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                                ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                                ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                               // source.getAttacker().damage(DamageSource.player((PlayerEntity) entity),0.1f);
                                if(!entity.getWorld().isClient())
                                {
                                    //((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,10));
                                    ServerWorld world = (ServerWorld) source.getAttacker().getWorld();
                                  //  source.getAttacker().playSound(ModSounds.PARRY_TWO,1.0f,1.0f);
                                   // world.playSound(entity.getX(),entity.getY(),entity.getZ(),ModSounds.PARRY_TWO, SoundCategory.MASTER,1.0f,1.0f,true);
                                    //world.playSound(entity.getX(),entity.getY(),entity.getZ(),ModSounds.NO_POSTURE, SoundCategory.MASTER,1.0f,1.0f,true);
                                    world.playSound(null,entity.getBlockPos(), ModSounds.NO_POSTURE, SoundCategory.MASTER,1.0f,1.0f);
                                    world.playSound(null,entity.getBlockPos(), ModSounds.PARRY_TWO, SoundCategory.MASTER,1.0f,1.0f);


                                }

                               // source.getAttacker().playSound(ModSounds.NO_POSTURE,1.0f,1.0f);
                                Parrydata.setparryattack(((IEntityDataSaver) entity),false);
                                entity1.setposture_float(entity1.getmaxposture());
                                return false;
                            }
                            if(!entity.getWorld().isClient())
                            {
                                ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,10));
                                ServerWorld world = (ServerWorld) entity.getWorld();
                               // world.playSound(entity.getX(),entity.getY(),entity.getZ(),ModSounds.PARRY_ONE, SoundCategory.MASTER,1.0f,1.0f,true);
                                world.playSound(null,entity.getBlockPos(), ModSounds.PARRY_ONE, SoundCategory.MASTER,1.0f,1.0f);
                                Parrydata.setparryattack(((IEntityDataSaver) entity),false);
                            }

                            return false;
                        }

                    }
                }

            }


        }
        return true;
    }


    @Override
    public String getanimation_number() {
        if((MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof SwordItem ||MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof ShieldItem) && !(MinecraftClient.getInstance().player.getOffHandStack().getItem() instanceof SwordItem ||MinecraftClient.getInstance().player.getOffHandStack().getItem() instanceof ShieldItem))
        {
            return this.animationname+"right";
        }
        if(!(MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof SwordItem ||MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof ShieldItem) && (MinecraftClient.getInstance().player.getOffHandStack().getItem() instanceof SwordItem ||MinecraftClient.getInstance().player.getOffHandStack().getItem() instanceof ShieldItem))
        {
            return this.animationname;
        }

        if((MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof SwordItem ||MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof ShieldItem) && (MinecraftClient.getInstance().player.getOffHandStack().getItem() instanceof SwordItem ||MinecraftClient.getInstance().player.getOffHandStack().getItem() instanceof ShieldItem))
        {
            return this.animationname;
        }
        return "";
    }
}
