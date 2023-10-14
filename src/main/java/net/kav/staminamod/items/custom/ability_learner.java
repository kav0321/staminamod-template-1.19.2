package net.kav.staminamod.items.custom;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.data.AbilityData;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.statusEffect.ModStatusEffects;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class ability_learner extends Item {
    private int id;
    public ability_learner(Settings settings, int ID) {
        super(settings);
        this.id=ID;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        AbilityData.addAbility((IEntityDataSaver) user, AbilityManager.abiltyregister.get(id));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}/*  if (!world.isClient) {
            AbilityData.addAbility((IEntityDataSaver) user, AbilityManager.abiltyregister.get(id));
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeInt(id);

            ServerPlayNetworking.send((ServerPlayerEntity) user, ModMessages.ABILITY_ADDED, buf);

        }*/
