package net.kav.staminamod.event.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.staminamod.networking.ModMessages;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.registry.Registry;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class AttackOveride {
    private static boolean con;
    public static void attacks()
    {
        if(!(MinecraftClient.getInstance().player.isCreative()||MinecraftClient.getInstance().player.isSpectator()))
        {
            if(MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof SwordItem ||MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof ToolItem)

            {
                con=true;
                ClientPlayNetworking.send(ModMessages.HANDSWING, PacketByteBufs.empty());
                //CLIENT TO SERVER
            }



        }
    }
    public static void setCon(boolean cons)
    {
        con=cons;
    }

    public static boolean getCon()
    {
        return con;
    }
}
