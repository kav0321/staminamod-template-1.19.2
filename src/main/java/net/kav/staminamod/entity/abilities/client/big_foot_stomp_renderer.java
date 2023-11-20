package net.kav.staminamod.entity.abilities.client;

import net.kav.staminamod.entity.abilities.big_foot_stomp;
import net.kav.staminamod.entity.abilities.foot_stomp;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class big_foot_stomp_renderer extends GeoProjectilesRenderer<big_foot_stomp> {
    public big_foot_stomp_renderer(EntityRendererFactory.Context ctx) {
        super(ctx, new big_foot_stomp_model());
    }

    @Override
    public RenderLayer getRenderType(big_foot_stomp animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {

        stack.scale(1.5f,1.5f,1.5f);
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }

}