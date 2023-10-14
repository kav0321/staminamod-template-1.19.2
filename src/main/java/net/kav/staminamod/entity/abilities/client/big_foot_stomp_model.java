package net.kav.staminamod.entity.abilities.client;

import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.entity.abilities.big_foot_stomp;
import net.kav.staminamod.entity.abilities.foot_stomp;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class big_foot_stomp_model extends AnimatedGeoModel<big_foot_stomp> {

    @Override
    public Identifier getModelResource(big_foot_stomp object) {
        return new Identifier(StaminaMod.MODID, "geo/foot_stomp.geo.json");
    }

    @Override
    public Identifier getTextureResource(big_foot_stomp object) {
        return new Identifier(StaminaMod.MODID, "textures/entity/abilities/foot_stomp.png");
    }

    @Override
    public Identifier getAnimationResource(big_foot_stomp animatable) {
        return new Identifier(StaminaMod.MODID, "animations/foot_stomp.animation.json");
    }
}
