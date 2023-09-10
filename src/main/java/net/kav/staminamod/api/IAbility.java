package net.kav.staminamod.api;

import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public interface IAbility {

    void tick(PlayerEntity player);
    void staminaconsume();
    void ClientSideExecution();
    void NBTsave(IEntityDataSaver player);
    void NBTLoad(IEntityDataSaver player);
    void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player);
}