package net.kav.staminamod.statusEffect;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.awt.*;

public class stamina_absorbsion extends StatusEffect {
    protected stamina_absorbsion() {
        super(
                StatusEffectCategory.BENEFICIAL, Color.GREEN.getRGB()
        );
    }


    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            PacketByteBuf buf = PacketByteBufs.create();


            float maxstamina= StaminaData.getMAXSTAMINAtemp((IEntityDataSaver) player);
            float reminder= StaminaData.getMAXSTAMINA((IEntityDataSaver) player)-maxstamina;
            buf.writeInt((int) -reminder);
            ServerPlayNetworking.send((ServerPlayerEntity) entity, ModMessages.EFFECTS,buf);

            StaminaData.incrementMAXSTAMINA((IEntityDataSaver) entity, -reminder);
        }
        super.onRemoved(entity, attributes, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        if (entity instanceof PlayerEntity) {
            if(entity.world.isClient())
            {
                System.out.println("ngas");
            }
        }

            if (entity instanceof PlayerEntity) {
                System.out.println("sae");

                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt((amplifier * 2 + 5));
                PlayerEntity player = (PlayerEntity) entity;

                StaminaData.setMAXSTAMINAtemp((IEntityDataSaver) player,StaminaData.getMAXSTAMINA((IEntityDataSaver) player));

                ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.EFFECTS,buf);

                StaminaData.incrementMAXSTAMINA((IEntityDataSaver) entity, (amplifier * 2 + 5));
            }


        super.onApplied(entity, attributes, amplifier);
    }

}
