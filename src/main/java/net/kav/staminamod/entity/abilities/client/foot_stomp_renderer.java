package net.kav.staminamod.entity.abilities.client;

import net.kav.staminamod.entity.abilities.foot_stomp;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class foot_stomp_renderer extends GeoProjectilesRenderer<foot_stomp> {
    public foot_stomp_renderer(EntityRendererFactory.Context ctx) {
        super(ctx, new foot_stomp_model());
    }

    @Override
    public RenderLayer getRenderType(foot_stomp animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }


}
