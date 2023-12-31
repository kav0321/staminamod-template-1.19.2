package net.kav.staminamod.api;

import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public abstract class AbilityCore implements IAbility{
    public int cooldown;
    public int cooldowncurrent;
    public int stamina;
    public int ID;
    public String animationname;
    public String filename;
    public Identifier file;
    public Text name;
    public Text description;
    public Text error;
    protected float length;

    public boolean head;
    public boolean body;
    public boolean righthand;
    public boolean lefthand;
    public boolean leftleg;
    public boolean rightleg;

    public AbilityCore(int cooldown,int stamina, int ID, String animationname,Text name, Text description,String filename,float length,boolean head, boolean body,boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg)
    {
        this.cooldown= cooldown;
        this.stamina =stamina;
        this.ID=ID;
        this.animationname= animationname;
        this.filename= filename;
        this.name=name;
        this.description=description;
        this.length = length;
        file= new Identifier(StaminaMod.MODID,"textures/gui/abilities/"+filename+".png");
        this.head =head;
        this.body=body;
        this.righthand=righthand;
        this.lefthand=lefthand;
        this.rightleg=rightleg;
        this.leftleg=leftleg;


    }
    public AbilityCore(int cooldown,int stamina, int ID, String animationname,Text name, Text description,String filename,float length)
    {
    this(cooldown,stamina,ID,animationname,name,description,filename, length,false,false,false,false,false,false);
    }
    public List<LivingEntity> getEntitiesNearby(World world, double expandDistance, double attackRange , Predicate<LivingEntity> filter, LivingEntity entity)
    {
        double yaw = entity.getYaw();

        return world.getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand(Math.sin(Math.toRadians(-yaw)) * expandDistance, 0, Math.cos(Math.toRadians(-yaw)) * expandDistance)
                .expand(attackRange, attackRange, attackRange), filter.and(e -> e != entity));
    }
    public List<LivingEntity> getEntitiesNearby(World world, double expandDistance, Predicate<LivingEntity> filter, LivingEntity entity)
    {
        double yaw = entity.getYaw();

        return world.getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand( expandDistance, expandDistance, expandDistance)
                , filter.and(e -> e != entity));
    }
    public float getspeed(String name)
    {
        return length;
    }

    public List<Entity> getEntitiesNearby(World world, double expandDistance, Predicate<Entity> filter, Entity entity)
    {
        double yaw = entity.getYaw();

        return world.getEntitiesByClass(Entity.class, entity.getBoundingBox().expand(expandDistance, expandDistance, expandDistance), filter.and(e -> e != entity));
    }


    @Override
    public abstract void tick(PlayerEntity player);

    @Override
    public void staminaconsume() {
        StaminaData.decrementSTAMINA((IEntityDataSaver) MinecraftClient.getInstance().player,stamina);
    }

    @Override
    public abstract void ClientSideExecution();
    @Deprecated
    @Override
    public void NBTsave(IEntityDataSaver player,float cooldown) {
        NbtCompound save = player.getPersistentData();
        String name = Integer.toString(this.ID);
        save.putFloat(name,cooldown);
    }

    @Deprecated
    @Override
    public void NBTLoad(IEntityDataSaver player) {

    }

    @Override
    public abstract void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player);

    @Override
    public boolean conditions(PlayerEntity player)
    {   if(player.hasVehicle())
        {
            this.error =Text.translatable("ability.parry.vehicule");
            player.sendMessage(error,true);
        }


        if(StaminaData.getSTAMINA((IEntityDataSaver) player)>stamina/2)
        {
            return true;
        }
        return false;
    }


}
