package net.kav.staminamod.api;

import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public abstract class AbilityCore implements IAbility{
    public int cooldown;
    public int stamina;
    public int ID;
    public String animationname;
    public String filename;
    public Identifier file;
    protected Text name;
    protected Text description;
    public float speed;
    public AbilityCore(int cooldown,int stamina, int ID, String animationname,Text name, Text description,String filename,float speed)
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
    }
    public List<LivingEntity> getEntitiesNearby(World world, double expandDistance, double attackRange , Predicate<LivingEntity> filter, LivingEntity entity)
    {
        double yaw = entity.getYaw();

        return world.getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand(Math.sin(Math.toRadians(-yaw)) * expandDistance, 0, Math.cos(Math.toRadians(-yaw)) * expandDistance)
                .expand(attackRange, attackRange, attackRange), filter.and(e -> e != entity));
    }

    @Override
    public void tick(PlayerEntity player) {

    }

    @Override
    public void staminaconsume() {
        StaminaData.decrementSTAMINA((IEntityDataSaver) MinecraftClient.getInstance().player,stamina);
    }

    @Override
    public void ClientSideExecution() {

    }

    @Override
    public void NBTsave(IEntityDataSaver player) {

    }

    @Override
    public void NBTLoad(IEntityDataSaver player) {

    }

    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {

    }
}
