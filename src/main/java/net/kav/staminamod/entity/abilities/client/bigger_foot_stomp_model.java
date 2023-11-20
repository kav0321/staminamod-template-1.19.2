package net.kav.staminamod.entity.abilities.client;

import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.entity.abilities.big_foot_stomp;
import net.kav.staminamod.entity.abilities.bigger_foot_stomp;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class bigger_foot_stomp_model extends AnimatedGeoModel<bigger_foot_stomp> {

    @Override
    public Identifier getModelResource(bigger_foot_stomp object) {
        return new Identifier(StaminaMod.MODID, "geo/foot_stomp.geo.json");
    }

    @Override
    public Identifier getTextureResource(bigger_foot_stomp object) {
        return new Identifier(StaminaMod.MODID, "textures/entity/abilities/foot_stomp.png");
    }

    @Override
    public Identifier getAnimationResource(bigger_foot_stomp animatable) {
        return new Identifier(StaminaMod.MODID, "animations/foot_stomp.animation.json");
    }
}