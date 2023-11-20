package net.kav.staminamod.mixin.client;

import net.kav.staminamod.event.client.AttackOveride;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.minecraft.util.hit.HitResult.Type.BLOCK;

@Mixin(MinecraftClient.class)
public class doAttackMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;doAttack()Z"), method = "handleInputEvents()V",cancellable = true)
    public void doAttack(CallbackInfo info)
    {

        if (((MinecraftClient)(Object)this).crosshairTarget.getType() == BLOCK &&
                isGrassBlock(((BlockHitResult) ((MinecraftClient) (Object) this).crosshairTarget).getBlockPos())) {
            AttackOveride.attacks();
        } else
        {
            switch (((MinecraftClient)(Object)this).crosshairTarget.getType()) {

                case ENTITY: {
                    AttackOveride.attacks();

                    break;
                }
                case MISS: {
                    AttackOveride.attacks();

                }
            }

        }


    }

    public boolean isGrassBlock(BlockPos pos) {
        Block block = MinecraftClient.getInstance().world.getBlockState(pos).getBlock();
        return block == Blocks.TALL_GRASS;
    }
    //@At(value = "HEAD")
    /*   @Inject(method = "doAttack()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;attackEntity(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/Entity;)V"))
    private void injectBeforeAttackEntity(CallbackInfoReturnable<Boolean> ci) {
        // Your custom logic to run before the attackEntity call
        // You can access this.attackCooldown, this.crosshairTarget, etc.
        System.out.println("he");
    }*/
}
