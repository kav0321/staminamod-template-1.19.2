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



                buf.writeInt(-(amplifier * 5 + 5));
                ServerPlayNetworking.send((ServerPlayerEntity) entity, ModMessages.EXTRA_STAMINA,buf);
                StaminaData.increaseMAXSTAMINAtemp((IEntityDataSaver) player,-(amplifier * 5 + 5));




        }
        super.onRemoved(entity, attributes, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof PlayerEntity) {

            if(!entity.getActiveStatusEffects().equals(ModStatusEffects.stamina_debuff))
            {
                PacketByteBuf buf = PacketByteBufs.create();
                int temp =(amplifier * 5 + 5);
                buf.writeInt(temp);
                PlayerEntity player = (PlayerEntity) entity;

                StaminaData.increaseMAXSTAMINAtemp((IEntityDataSaver) player,temp);

                ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.EXTRA_STAMINA,buf);
            }




        }




        super.onApplied(entity, attributes, amplifier);
    }

}
