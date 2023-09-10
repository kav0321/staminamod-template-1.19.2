package net.kav.staminamod.statusEffect;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.event.client.AttackOveride;
import net.kav.staminamod.event.client.KeyInputHandler;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.awt.*;

public class staminaboost extends StatusEffect {
    public staminaboost()
    {
        super(
                StatusEffectCategory.BENEFICIAL, Color.GREEN.getRGB()
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }
    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            if(!entity.world.isClient())
            {
               /* PacketByteBuf buf = PacketByteBufs.create();
                buf.writeFloat((float) (0.4*amplifier+0.1));
                ServerPlayerEntity player= (ServerPlayerEntity) entity;
                ServerPlayNetworking.send( player, ModMessages.EFFECT_STATUS,buf);*/
            }
            else
            {

                if(MinecraftClient.getInstance().player!=null)
                {

                    if (!MinecraftClient.getInstance().player.isSprinting() &&!AttackOveride.getCon() &&!KeyInputHandler.isabilitybeingpress() && !MinecraftClient.getInstance().player.isBlocking() && !MinecraftClient.getInstance().player.isUsingItem())
                    {
                        float recoveryrate= StaminaData.getRECOVERYRATE(((IEntityDataSaver) MinecraftClient.getInstance().player));

                        StaminaData.incrementSTAMINA(((IEntityDataSaver) MinecraftClient.getInstance().player), (float) (recoveryrate+0.25f+0.5*amplifier+0.1));
                    }


                }
            }

        }
    }

}

