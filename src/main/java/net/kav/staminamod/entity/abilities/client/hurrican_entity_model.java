package net.kav.staminamod.entity.abilities.client;

import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.entity.abilities.big_foot_stomp;
import net.kav.staminamod.entity.abilities.foot_stomp;
import net.kav.staminamod.entity.abilities.hurrican_entity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class hurrican_entity_model extends AnimatedGeoModel<hurrican_entity> {
    @Override
    public Identifier getModelResource(hurrican_entity object) {
        return new Identifier(StaminaMod.MODID, "geo/foot_stomp.geo.json");
    }

    @Override
    public Identifier getTextureResource(hurrican_entity object) {
        return new Identifier(StaminaMod.MODID, "textures/entity/abilities/foot_stomp.png");
    }

    @Override
    public Identifier getAnimationResource(hurrican_entity animatable) {
        return new Identifier(StaminaMod.MODID, "animations/foot_stomp.animation.json");
    }
}
