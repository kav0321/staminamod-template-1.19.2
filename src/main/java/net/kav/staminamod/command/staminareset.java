package net.kav.staminamod.command;


import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Collection;
import java.util.Iterator;

public class staminareset {
    public static void init()
    {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
            dispatcher.register
                    (
                        (CommandManager.literal("stamina").requires((serverCommandSource) ->
                                        serverCommandSource.hasPermissionLevel(2))
                                .then(CommandManager.argument("targets", EntityArgumentType.players())
                                        .then(CommandManager.literal("add").then(CommandManager.argument("amount", IntegerArgumentType.integer()).
                                                executes((commandContext) -> add(commandContext.getSource(),EntityArgumentType.getPlayers(commandContext, "targets"),IntegerArgumentType.getInteger(commandContext, "amount")))))
                                        .then(CommandManager.literal("set").then(CommandManager.argument("amount", IntegerArgumentType.integer()).
                                                executes((commandContext) -> set(commandContext.getSource(),EntityArgumentType.getPlayers(commandContext, "targets"),IntegerArgumentType.getInteger(commandContext, "amount")))))
                                        .then(CommandManager.literal("reset").
                                                executes((commandContext) -> reset(commandContext.getSource(),EntityArgumentType.getPlayers(commandContext, "targets"))))
                                )
                        )
                    );
        });
    }



    public static int reset(ServerCommandSource source, Collection<ServerPlayerEntity> targets)
    {
        Iterator<ServerPlayerEntity> playerIterator = targets.iterator();
        while (playerIterator.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerIterator.next();

            StaminaData.setMAXSTAMINA((IEntityDataSaver) serverPlayerEntity,0);


            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeFloat(0);
            ServerPlayNetworking.send(serverPlayerEntity, ModMessages.DEATH_TRANSFER_MAXSTAMINA,buf);
        }


        return 1;
    }

    public static int add(ServerCommandSource source, Collection<ServerPlayerEntity> targets,int amount)
    {
        Iterator<ServerPlayerEntity> playerIterator = targets.iterator();
        while (playerIterator.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerIterator.next();
            float incre= amount;
            StaminaData.incrementMAXSTAMINA((IEntityDataSaver) serverPlayerEntity,incre);

            float amounts=StaminaData.getMAXSTAMINA((IEntityDataSaver) serverPlayerEntity);

            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeFloat(amounts);
            ServerPlayNetworking.send(serverPlayerEntity, ModMessages.DEATH_TRANSFER_MAXSTAMINA,buf);
        }
        return 1;
    }

    public static int set(ServerCommandSource source, Collection<ServerPlayerEntity> targets,int amount)
    {
        Iterator<ServerPlayerEntity> playerIterator = targets.iterator();
        while (playerIterator.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerIterator.next();

            StaminaData.setMAXSTAMINA((IEntityDataSaver) serverPlayerEntity,amount);


            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeFloat(amount);
            ServerPlayNetworking.send(serverPlayerEntity, ModMessages.DEATH_TRANSFER_MAXSTAMINA,buf);
        }

        return 1;
    }


}
