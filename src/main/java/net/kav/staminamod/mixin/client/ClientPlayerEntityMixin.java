package net.kav.staminamod.mixin.client;

import com.mojang.authlib.GameProfile;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.api.layered.modifier.AdjustmentModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import dev.kosmx.playerAnim.core.util.Vec3f;
import dev.kosmx.playerAnim.impl.IAnimatedPlayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.util.AnimationSubStackStamina;
import net.kav.staminamod.util.IModAnimatedPlayerStamina;
import net.kav.staminamod.util.TransmissionSpeedModifierStamina;
import net.kav.staminamod.util.firstperson;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends PlayerEntity implements IModAnimatedPlayerStamina {
    private final AnimationSubStackStamina attackAnimationstamina = new AnimationSubStackStamina(createAttackAdjustmentstamina());

    //Unique annotation will rename private methods/fields if needed to avoid collisions.
    @Unique
    private final ModifierLayer<IAnimation> modAnimationContainer = new ModifierLayer<>();

    public ClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
        super(world, pos, yaw, gameProfile, publicKey);
    }

    @Override
    public void tick() {
        super.tick();

    }

    /**
     * Add the animation registration to the end of the constructor
     * Or you can use {@link dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess#REGISTER_ANIMATION_EVENT} event for this
     */
    //belong to better combat, not my code so check there mod out. I don't like to take credit for myself, the animation was a big step for me and without better combat this would not exist
    private AdjustmentModifier createAttackAdjustmentstamina() {
        var player = (PlayerEntity)this;
        return new AdjustmentModifier((partName) -> {
            // System.out.println("Player pitch: " + player.getPitch());
            float rotationX = 0;
            float rotationY = 0;
            float rotationZ = 0;
            float offsetX = 0;
            float offsetY = 0;
            float offsetZ = 0;





            return Optional.of(new AdjustmentModifier.PartModifier(
                    new Vec3f(rotationX, rotationY, rotationZ),
                    new Vec3f(offsetX, offsetY, offsetZ))
            );
        });
    }

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void init(ClientWorld world, GameProfile profile, PlayerPublicKey publicKey, CallbackInfo ci) {
        //Mixin does not know (yet) that this will be merged with AbstractClientPlayerEntity
       // PlayerAnimationAccess.getPlayerAnimLayer((AbstractClientPlayerEntity) (Object)this).addAnimLayer(5000, modAnimationContainer); //Register the layer with a priority
        //The priority will tell, how important is this animation compared to other mods. Higher number means higher priority
        //Mods with higher priority will override the lower priority mods (if they want to animation anything)

        var stack = ((IAnimatedPlayer) this).getAnimationStack();
        stack.addAnimLayer(1000, attackAnimationstamina.base);
    }

    /**
     * Override the interface function, so we can use it in the future
     */
    @Override
    public ModifierLayer<IAnimation> modid_getModAnimation() {
        return modAnimationContainer;
    }


    @Override
    public void playAttackAnimation2stamina(String name, float length, int index) {
        boolean head = AbilityManager.abiltyregister.get(index).head;
        boolean torso= AbilityManager.abiltyregister.get(index).body;
        boolean rightArm= AbilityManager.abiltyregister.get(index).righthand;
        boolean leftArm= AbilityManager.abiltyregister.get(index).lefthand;
        boolean rightLeg= AbilityManager.abiltyregister.get(index).rightleg;
        boolean leftLeg= AbilityManager.abiltyregister.get(index).leftleg;

        var animationContainer = ((IModAnimatedPlayerStamina) this).modid_getModAnimation();
        KeyframeAnimation animationL;
        animationL=   PlayerAnimationRegistry.getAnimation(new Identifier(StaminaMod.MODID, name));
        if(length==0)
        {

            length=animationL.endTick;
        }
        var speed = ((float)animationL.endTick) / length;
      //  builder.getPart("head").setEnabled(false);


        //System.out.println(AbilityManager.abiltyregister.get(packet.index()).animationname);
        var builder = animationL.mutableCopy();
        builder.getPart("head").setEnabled(false);

        animationL = builder.build();
        var torso1 = builder.getPart("torso");
        animationL = builder.build();
        var rightArm1 = builder.getPart("rightArm");
        animationL = builder.build();
        var leftArm1 = builder.getPart("leftArm");
        var rightLeg1 = builder.getPart("rightLeg");
        var leftLeg1 = builder.getPart("leftLeg");

        //System.out.println(head);

        torso1.setEnabled(!torso);
        rightArm1.setEnabled(!rightArm);
        leftArm1.setEnabled(!leftArm);
        rightLeg1.setEnabled(!rightLeg);
        leftLeg1.setEnabled(!leftLeg);

        //((multiple_animations) AbilityManager.abiltyregister.get(packet.index())).getanimation_number()

        //animationContainer.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn(7, Ease.LINEAR), new KeyframeAnimationPlayer(animationL));
        attackAnimationstamina.speed.set(speed,
                List.of(
                        new TransmissionSpeedModifierStamina.Gear(length, speed)
                ));
        attackAnimationstamina.base.replaceAnimationWithFade(
                AbstractFadeModifier.standardFadeIn(2, Ease.LINEAR),
                new KeyframeAnimationPlayer(animationL));
        //animationContainer.setAnimation(new firstperson(animationL));
    }
}

