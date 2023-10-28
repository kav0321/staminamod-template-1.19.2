package net.kav.staminamod.Abilities.counter_parry;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.api.multiple_animations;
import net.kav.staminamod.api.string_conversion;
import net.kav.staminamod.config.ModConfigs;

import net.kav.staminamod.data.current_ani;
import net.kav.staminamod.data.guarddata;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.sound.ModSounds;
import net.kav.staminamod.util.IEntityDataSaver;
import net.kav.staminamod.util.IPosture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import java.util.Random;

import static java.lang.Math.abs;

public class guard_counter extends AbilityCore implements ServerLivingEntityEvents.AllowDamage, multiple_animations {

    private int max_animation=2;
    public guard_counter(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename, speed, head, body, righthand, lefthand, rightleg, leftleg);
        max_animation=2;
    }

    @Override
    public void tick(PlayerEntity player) {

            if(guarddata.gettick((IEntityDataSaver) player)<=0)
            {
                guarddata.settick((IEntityDataSaver) player,-1);
            }
            if(guarddata.gettick((IEntityDataSaver) player)>=0)
            {
                guarddata.decreasetick((IEntityDataSaver) player,1);
            }

            if(guarddata.getguard(((IEntityDataSaver) player))==true&&guarddata.gettick((IEntityDataSaver) player)==0)
            {
                guarddata.setparryguard(((IEntityDataSaver) player),false);

            }

    }

    @Override
    public void ClientSideExecution() {
        guarddata.setparryguard(((IEntityDataSaver) MinecraftClient.getInstance().player), true);
        guarddata.settick((IEntityDataSaver) MinecraftClient.getInstance().player,35);
    }

    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {
        guarddata.setparryguard(((IEntityDataSaver) player), true);
        guarddata.settick((IEntityDataSaver) player,35);
    }

    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if(entity instanceof PlayerEntity  && source.getAttacker() !=null)
        {

                if(!entity.canSee(source.getAttacker()))
                {
                    return true;
                }
                float playerYaw=entity.getYaw();
                BlockPos playerPos= entity.getBlockPos();

                double x = entity.getX()+  Math.sin(Math.toRadians(-playerYaw));
                double y = entity.getY();
                double z = entity.getZ()+  Math.cos(Math.toRadians(-playerYaw));
                BlockPos frontPos = new BlockPos(x,y,z);

                if(guarddata.getguard(((IEntityDataSaver) entity)))
                {

                    if(source.getAttacker() instanceof LivingEntity)
                    {
                        double distanceSquared = source.getAttacker().getPos().squaredDistanceTo(x, y, z);
                        if(distanceSquared<=2)
                        {
                            IPosture entity1= (IPosture) source.getAttacker();
                            entity1.incrementposture_float(-1f);
                            Random random = new Random();
                            int choice = random.nextInt(3);
                            if (choice == 0) {
                                source.getAttacker().playSound(ModSounds.CLASH_SWORD_ONE,1.0f,1.0f);
                            } else if (choice == 1) {
                                source.getAttacker().playSound(ModSounds.CLASH_SWORD_TWO,1.0f,1.0f);
                            } else {
                                source.getAttacker().playSound(ModSounds.CLASH_SWORD_THREE,1.0f,1.0f);
                            }

                            entity.getMainHandStack().damage(1,entity.world.random, (ServerPlayerEntity) entity);
                            //source.getAttacker().playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE,1.0f,1.0f);
                            guarddata.setparryguard(((IEntityDataSaver) entity),false);
                            if(entity1.getposture_number()==0 && source.getAttacker()!=null)
                            {
                                //((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                                ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                                ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                               // source.getAttacker().damage(DamageSource.player((PlayerEntity) entity),0.1f);
                                entity1.setposture_float(entity1.getmaxposture());
                                source.getAttacker().playSound(ModSounds.NO_POSTURE,1.0f,1.0f);
                            }
                            return false;
                        }

                    }
                }
                else
                { //player getting damaged
                    if(!source.isFire() && !source.isMagic() )
                    {  IPosture entity1= (IPosture) entity;
                        if(source.isExplosive())
                        {

                            entity1.incrementposture_float(-10f);
                            EntityType<?> entityType = entity.getType();
                            Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
                            //System.out.println(entity1.getmaxposture()+" "+entity1.getposture_number()+" "+entityId.toString());
                        }
                        else if(source.isFallingBlock())
                        {

                            entity1.incrementposture_float(-12f);
                            EntityType<?> entityType = entity.getType();
                            Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
                            // System.out.println(entity1.getmaxposture()+" "+entity1.getposture_number()+" "+entityId.toString());
                        }
                        else
                        {
                            if(source.getAttacker() instanceof PlayerEntity)
                            {
                                if(((PlayerEntity) source.getAttacker()).getMainHandStack().getItem() instanceof SwordItem)
                                {
                                    entity1.incrementposture_float(-0.8f);
                                    EntityType<?> entityType = entity.getType();
                                    Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
                                }
                                else  if(((PlayerEntity) source.getAttacker()).getMainHandStack().getItem() instanceof AxeItem)
                                {
                                    entity1.incrementposture_float(-5f);
                                    EntityType<?> entityType = entity.getType();
                                    Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
                                }
                            }
                            else
                            {
                                if(!(source.getAttacker() instanceof ProjectileEntity) )
                                {
                                    entity1.incrementposture_float(-0.8f);
                                    EntityType<?> entityType = entity.getType();
                                    Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
                                }

                            }

                            //System.out.println(entity1.getmaxposture()+" "+entity1.getposture_number()+" "+entityId.toString());
                        }

                        if(entity1.getposture_number()==0 && source.getAttacker()!=null)
                        {
                            entity1.setposture_float(entity1.getmaxposture());
                            source.getAttacker().playSound(ModSounds.NO_POSTURE,1.0f,1.0f);
                           // entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,ModConfigs.parry_duration,ModConfigs.parry_amplifier));
//            source.getAttacker().damage(DamageSource.player((PlayerEntity) entity),0.1f);


                        }

                    }
                }
            return true;
        }
        else
        {

            //other entity getting damaged
            if(!source.isFire() && !source.isMagic() )
            {
                IPosture entity1= (IPosture) entity;
                if(source.isExplosive())
                {

                    entity1.incrementposture_float(-10f);
                    EntityType<?> entityType = entity.getType();
                    Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
                    //System.out.println(entity1.getmaxposture()+" "+entity1.getposture_number()+" "+entityId.toString());
                }
                else if(source.isFallingBlock())
                {

                    entity1.incrementposture_float(-12f);
                    EntityType<?> entityType = entity.getType();
                    Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
                    // System.out.println(entity1.getmaxposture()+" "+entity1.getposture_number()+" "+entityId.toString());
                }
                else
                {
                    if(source.getAttacker() instanceof PlayerEntity)
                    {
                        if(((PlayerEntity) source.getAttacker()).getMainHandStack()!= ItemStack.EMPTY)
                        {
                            entity1.incrementposture_float(-1f);
                            EntityType<?> entityType = entity.getType();
                            Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
                        }
                    }
                    else
                    {
                        entity1.incrementposture_float(-1f);
                        EntityType<?> entityType = entity.getType();
                        Identifier entityId = Registry.ENTITY_TYPE.getId(entityType);
                    }

                    //System.out.println(entity1.getmaxposture()+" "+entity1.getposture_number()+" "+entityId.toString());
                }

                if(entity1.getposture_number()==0 && source.getAttacker()!=null)
                {
                    source.getAttacker().playSound(ModSounds.NO_POSTURE,1.0f,1.0f);
                   // entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,ModConfigs.parry_duration,ModConfigs.parry_amplifier));
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,ModConfigs.parry_duration,ModConfigs.parry_amplifier));
//            source.getAttacker().damage(DamageSource.player((PlayerEntity) entity),0.1f);
                    entity1.setposture_float(entity1.getmaxposture());

                }

            }
        }
        return true;
    }

    @Override
    public String getanimation_number() {
        String name = this.filename;
        int rand;
        if(current_ani.getanim((IEntityDataSaver)MinecraftClient.getInstance().player)==0)
        {
            rand = getRandomOneTwoOrThree();
        }
        else if(current_ani.getanim((IEntityDataSaver)MinecraftClient.getInstance().player)==1)
        {
            rand = 2;
        }
        else if(current_ani.getanim((IEntityDataSaver)MinecraftClient.getInstance().player)==2)
        {
            rand = 3;
        }
        else
        {
            rand =1;
        }

        current_ani.setanim((IEntityDataSaver)MinecraftClient.getInstance().player,rand);


      //  int rand=new Random().nextInt(1,max_animation);
        String index= string_conversion.intToWord(rand);
      //  System.out.println(name+index);

        return name+index;
    }

    public static int getRandomOneTwoOrThree() {
        Random random = new Random();
        return random.nextInt(3) + 1;  // generates a random integer between 0 and 2, then adds 1
    }
    public boolean conditions(PlayerEntity player) {

        if(player.getMainHandStack().getItem() instanceof SwordItem )
        {
            if(player.getMainHandStack().getItem()!= Items.WOODEN_SWORD )
            {
                this.error =Text.translatable("");
                return super.conditions(player);
            }
            else
            {
                this.error =Text.translatable("ability.guard_counter.error_tool_wood");
                player.sendMessage(error,true);
            }

        }
        else
        {
            this.error =Text.translatable("ability.guard_counter.error_tool");
            player.sendMessage(error,true);
        }
        return false;
    }
}
