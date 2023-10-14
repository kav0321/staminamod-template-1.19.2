package net.kav.staminamod.entity.abilities.client;


import net.kav.staminamod.entity.abilities.big_foot_stomp;
import net.kav.staminamod.entity.abilities.bigger_foot_stomp;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class bigger_foot_stomp_renderer extends GeoProjectilesRenderer<bigger_foot_stomp> {
    public bigger_foot_stomp_renderer(EntityRendererFactory.Context ctx) {
        super(ctx, new bigger_foot_stomp_model());
    }

    @Override
    public RenderLayer getRenderType(bigger_foot_stomp animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {

        stack.scale(2,2,2);
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }

}