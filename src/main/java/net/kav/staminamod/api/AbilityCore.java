package net.kav.staminamod.api;

import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbilityCore implements IAbility{
    public int cooldown;
    public int stamina;
    public int ID;
    public String animationname;
    public String filename;
    public Identifier file;
    public Text name;
    public Text description;
    public Text error;
    protected float speed;

    public boolean head;
    public boolean body;
    public boolean righthand;
    public boolean lefthand;
    public boolean leftleg;
    public boolean rightleg;
    public AbilityCore(int cooldown,int stamina, int ID, String animationname,Text name, Text description,String filename,float speed,boolean head, boolean body,boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg)
    {
        this.cooldown= cooldown;
        this.stamina =stamina;
        this.ID=ID;
        this.animationname= animationname;
        this.filename= filename;
        this.name=name;
        this.description=description;
        this.speed =speed;
        file= new Identifier(StaminaMod.MODID,"textures/gui/abilities/"+filename+".png");
        this.head =head;
        this.body=body;
        this.righthand=righthand;
        this.lefthand=lefthand;
        this.rightleg=rightleg;
        this.leftleg=leftleg;


    }
    public AbilityCore(int cooldown,int stamina, int ID, String animationname,Text name, Text description,String filename,float speed)
    {
    this(cooldown,stamina,ID,animationname,name,description,filename,speed,false,false,false,false,false,false);
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
        return speed;
    }

    public List<Entity> getEntitiesNearby(World world, double expandDistance, Predicate<Entity> filter, Entity entity)
    {
        double yaw = entity.getYaw();

        return world.getEntitiesByClass(Entity.class, entity.getBoundingBox().expand(expandDistance, expandDistance, expandDistance), filter.and(e -> e != entity));
    }

    public List<LivingEntity> getEntitiesNearby(BlockPos pos, World world, double radius , Predicate<LivingEntity> filter, LivingEntity entity)
    {

// Calculate the bounding box based on the radius and center BlockPos
        Box boundingBox = new Box(pos).expand(radius);

// Get a list of nearby entities that match the predicate
        return new ArrayList<>(world.getEntitiesByClass(LivingEntity.class, boundingBox, filter.and(e -> e != entity)));
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
    public void NBTsave(IEntityDataSaver player) {

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
