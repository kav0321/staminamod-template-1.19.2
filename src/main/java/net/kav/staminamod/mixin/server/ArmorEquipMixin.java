package net.kav.staminamod.mixin.server;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.enchantment.ModEnchantments;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class ArmorEquipMixin {

    @Inject(at = @At(value = "HEAD"), method = "onEquipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V")
    public void emitGameEvent(EquipmentSlot slot, ItemStack oldStack, ItemStack newstack, CallbackInfo info) {

       var player = (((LivingEntity) (Object) this));
        if (slot == EquipmentSlot.CHEST) {

            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(newstack);
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment enchantment2 = entry.getKey();
                int level = entry.getValue();
                if (enchantment2.equals(ModEnchantments.Stamina_Absorption)) {
                    if (player instanceof PlayerEntity) {
                        if (!player.world.isClient()) {

                            int amount =(EnchantmentHelper.getEquipmentLevel(ModEnchantments.Stamina_Absorption, player) * 5 );
                            PacketByteBuf buf = PacketByteBufs.create();
                            buf.writeInt(amount);

                            StaminaData.increaseMAXSTAMINAtemp((IEntityDataSaver) player,amount);


                            ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.EXTRA_STAMINA, buf);


                        }
                    }
                }
            }

            Map<Enchantment, Integer> enchantments2 = EnchantmentHelper.get(oldStack);
            for (Map.Entry<Enchantment, Integer> entry : enchantments2.entrySet()) {
                Enchantment enchantment2 = entry.getKey();
                int level = entry.getValue();
                if (enchantment2.equals(ModEnchantments.Stamina_Absorption)) {
                    if (player instanceof PlayerEntity) {
                        if (!player.world.isClient()) {
                            int amount =-level* 5;
                            PacketByteBuf buf = PacketByteBufs.create();
                            buf.writeInt(amount);

                            StaminaData.increaseMAXSTAMINAtemp((IEntityDataSaver) player,amount);


                            ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.EXTRA_STAMINA, buf);
                        }
                    }
                }
            }

            Map<Enchantment, Integer> enchantments3 = EnchantmentHelper.get(newstack);
            for (Map.Entry<Enchantment, Integer> entry : enchantments3.entrySet()) {
                Enchantment enchantment2 = entry.getKey();
                int level = entry.getValue();
                if (enchantment2.equals(ModEnchantments.Stamina_debuff)) {
                    if (player instanceof PlayerEntity) {
                        if (!player.world.isClient()) {

                            int amount =-(EnchantmentHelper.getEquipmentLevel(ModEnchantments.Stamina_debuff, player) * 5 );
                            PacketByteBuf buf = PacketByteBufs.create();
                            buf.writeInt(amount);

                            StaminaData.increaseMAXSTAMINAtemp((IEntityDataSaver) player,amount);


                            ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.EXTRA_STAMINA, buf);


                        }
                    }
                }
            }

            Map<Enchantment, Integer> enchantments4 = EnchantmentHelper.get(oldStack);
            for (Map.Entry<Enchantment, Integer> entry : enchantments4.entrySet()) {
                Enchantment enchantment2 = entry.getKey();
                int level = entry.getValue();
                if (enchantment2.equals(ModEnchantments.Stamina_debuff)) {
                    if (player instanceof PlayerEntity) {
                        if (!player.world.isClient()) {
                            int amount =level* 5;
                            PacketByteBuf buf = PacketByteBufs.create();
                            buf.writeInt(amount);

                            StaminaData.increaseMAXSTAMINAtemp((IEntityDataSaver) player,amount);


                            ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.EXTRA_STAMINA, buf);
                        }
                    }
                }
            }

        }

    }


}
