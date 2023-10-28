package net.kav.staminamod.Abilities.sword_dash;

import com.google.common.collect.Multimap;
import net.kav.staminamod.api.AbilityCore;

import net.kav.staminamod.api.damagesource.Moddamagesource;
import net.kav.staminamod.api.multiple_animations;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.data.sword_dashData;
import net.kav.staminamod.sound.ModSounds;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.*;

import java.util.*;
//not completed

public class sword_dash extends AbilityCore implements multiple_animations {

    public static Map<PlayerEntity,List<LivingEntity>> players = new HashMap<>();


    public sword_dash(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename, speed, head, body, righthand, lefthand, rightleg, leftleg);
    }

    @Override
    public void tick(PlayerEntity player) {


        if (sword_dashData.gettick2((IEntityDataSaver) player) > 0) {
            sword_dashData.decreasetick2((IEntityDataSaver) player, 1);

            if (sword_dashData.gettick2((IEntityDataSaver) player) == 0) {
                if (!player.world.isClient()) {
                    if (players.containsKey(player)) {

                        for (LivingEntity entity : players.get(player)) {
                            damage_entity(player, entity);
                        }
                        players.remove(player);
                    }
                }

            }
        }

        if (sword_dashData.gettick3((IEntityDataSaver) player) > 0 && !player.world.isClient) {
            sword_dashData.decreasetick3((IEntityDataSaver) player, 1);
            for (int i = 0; i < 360; i++) {
                if (i % 80 == 0) {
                    int randomx = new Random().nextInt(0, 1);
                    int randomy = new Random().nextInt(0, 2);
                    int randomz = new Random().nextInt(0, 1);
                    ServerWorld world = (ServerWorld) player.getWorld();
                    /*world.spawnParticles(ModParticles.CITRINE_PARTICLE,
                        player.getX() + randomx, player.getY(), player.getZ() + randomz,1,
                        0, 0, 0,0.1);*/
                }
                if (sword_dashData.gettick3((IEntityDataSaver) player) < 140 && sword_dashData.gettick3((IEntityDataSaver) player) > 80) {
                    if (i % 20 == 0) {
                        int randomx = new Random().nextInt(0, 1);
                        int randomy = new Random().nextInt(0, 2);
                        int randomz = new Random().nextInt(0, 1);
                        ServerWorld world = (ServerWorld) player.getWorld();
                        world.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                                player.getX() + randomx, player.getY(), player.getZ() + randomz, 1,
                                0, -1, 0, 0.01);

                    }
                }

            }
        }

        if (sword_dashData.gettick((IEntityDataSaver) player) <= 0) {
            sword_dashData.settick((IEntityDataSaver) player, -1);
        }
        if (sword_dashData.gettick((IEntityDataSaver) player) > 0) {
            sword_dashData.decreasetick((IEntityDataSaver) player, 1);

        }


        if (sword_dashData.gettick3((IEntityDataSaver) player) < 110 && sword_dashData.gettick3((IEntityDataSaver) player) > 10) {
            for (float f = 0.0F; f < 1F; f = (float) (f + 0.13D)) {
                int x = (int) MathHelper.lerp(f, player.getPos().getX(), player.getPos().x + player.getRotationVec(1).x * 6);
                int y = (int) MathHelper.lerp(f, player.getPos().getY(), player.getPos().y + player.getRotationVec(1).y * 2);
                int z = (int) MathHelper.lerp(f, player.getPos().getZ(), player.getPos().z + player.getRotationVec(1).z * 6);

                BlockPos pos = new BlockPos(x, y, z);


                if (!player.world.isClient()) {
                    for (LivingEntity entity : getEntitiesNearby(pos, player.getWorld(), 1.5, e -> (e instanceof LivingEntity), player)) {
                        if (player.canSee(entity)) {
                            damage_entity(player,entity);
                        }


                    }

                }

            }
            //set for both client and server to set the player to dash



        }
        if (sword_dashData.getLevel(((IEntityDataSaver) player)) == true && sword_dashData.gettick((IEntityDataSaver) player) == 0) {
            //System.out.println("damn");
            Vec3d playerlooking = player.getRotationVec(1.0F);
            Vec3d vect = new Vec3d(playerlooking.getX() * 5, playerlooking.getY(), playerlooking.getZ() * 5);
            player.setVelocity(vect);
            //second delay for dmg
            sword_dashData.settick2((IEntityDataSaver) player, 160);
            sword_dashData.setparryattack(((IEntityDataSaver) player), false);
        }
    }








    private void damage_entity(PlayerEntity player, LivingEntity entity)
    {


        ServerWorld serverWorld = (ServerWorld) player.world;
        serverWorld.playSound(null, player.getBlockPos(), ModSounds.DASH_SWORD_EVENT, SoundCategory.PLAYERS, 0.5f, 1.0f);


        double damage = 0;
        ItemStack main;
        EquipmentSlot slot;
        if((player.getMainHandStack().getItem() instanceof SwordItem))
        {
            main = player.getMainHandStack();
            slot=EquipmentSlot.MAINHAND;
        }
        else
        {
            if(player.getOffHandStack().getItem() instanceof SwordItem)
            {
                main = player.getOffHandStack();
                slot=EquipmentSlot.OFFHAND;
                //System.out.println("offhand");
                damage+=4;
            }
            else {
                main =ItemStack.EMPTY;
                slot=EquipmentSlot.MAINHAND;
            }

        }



        Multimap<EntityAttribute, EntityAttributeModifier> multimap = main.getAttributeModifiers(EquipmentSlot.MAINHAND);

        if (!multimap.isEmpty()) {
            Iterator var11 = multimap.entries().iterator();
            //System.out.println(damage);
            while(var11.hasNext()) {
                Map.Entry<EntityAttribute, EntityAttributeModifier> entry = (Map.Entry)var11.next();
                EntityAttributeModifier entityAttributeModifier = (EntityAttributeModifier)entry.getValue();
                damage = entityAttributeModifier.getValue();

                //System.out.println(damage);

                    if(damage >=0)
                    {
                        damage += player.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        damage += (double) EnchantmentHelper.getAttackDamage(main, EntityGroup.DEFAULT);
                        break;
                    }




            }
            //EntityAttributeModifier entityAttributeModifier = (EntityAttributeModifier) item.getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_SPEED);

            // player.sendMessage(Text.of(Double.toString(d)),true);
        }
        if((player.getOffHandStack().getItem() instanceof SwordItem) &&slot==EquipmentSlot.MAINHAND)
        {

            Multimap<EntityAttribute, EntityAttributeModifier> multimap2 = main.getAttributeModifiers(EquipmentSlot.OFFHAND);

            if (!multimap2.isEmpty()) {
                Iterator var112 = multimap2.entries().iterator();
                while(var112.hasNext()) {
                    Map.Entry<EntityAttribute, EntityAttributeModifier> entry = (Map.Entry)var112.next();
                    EntityAttributeModifier entityAttributeModifier = (EntityAttributeModifier)entry.getValue();
                    damage += entityAttributeModifier.getValue();
                    if(damage >=0)
                    {
                        damage += player.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        damage += (double) EnchantmentHelper.getAttackDamage(main, EntityGroup.DEFAULT);
                        break;
                    }
                }

            }
        }




        // Iterate through the modifiers to find the attack damage

        //System.out.println(damage*6);
        if(entity instanceof PlayerEntity)
        {
            if(entity.isBlocking())
            {
                entity.damage(Moddamagesource.mob(player), (float) damage* ModConfigs.sword_dash_attack_multiplier/2.5f);
            }
            else
            {
                entity.damage(Moddamagesource.mob(player), (float) damage*ModConfigs.sword_dash_attack_multiplier);
            }

        }
        else
        {
            entity.damage(Moddamagesource.mob(player), (float) damage*ModConfigs.sword_dash_attack_multiplier);
        }




        sword_dashData.setparryattack(((IEntityDataSaver) player),false);
        sword_dashData.settick((IEntityDataSaver) player,0);
        sword_dashData.settick2((IEntityDataSaver) player,0);
        sword_dashData.settick3((IEntityDataSaver) player,0);
    }
    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {
        sword_dashData.setparryattack(((IEntityDataSaver) player),true);
        sword_dashData.settick((IEntityDataSaver) player,25);
        sword_dashData.settick3((IEntityDataSaver) player,200);

        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,60,10));
    }
    @Override
    public void ClientSideExecution() {
        sword_dashData.setparryattack(((IEntityDataSaver) MinecraftClient.getInstance().player),true);
        sword_dashData.settick((IEntityDataSaver) MinecraftClient.getInstance().player,25);
        sword_dashData.settick3((IEntityDataSaver) MinecraftClient.getInstance().player,200);
    }

    @Override
    public boolean conditions(PlayerEntity player) {

        if(player.getMainHandStack().getItem() instanceof SwordItem || player.getOffHandStack().getItem() instanceof SwordItem)
        {

            return super.conditions(player);
        }
        else
        {
            this.error =Text.translatable("ability.sword_dash.error_tool");
            player.sendMessage(error,true);
        }
        return false;
    }

    @Override
    public String getanimation_number() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if(player.getOffHandStack().isEmpty() && !player.getMainHandStack().isEmpty())
        {
            return this.filename;
        }
        else if(!player.getOffHandStack().isEmpty() && player.getMainHandStack().isEmpty())
        {
            return "left"+this.filename;
        }
        else if(!player.getOffHandStack().isEmpty() && !player.getMainHandStack().isEmpty())
        {
            return this.filename;
        }

        return null;
    }
}
