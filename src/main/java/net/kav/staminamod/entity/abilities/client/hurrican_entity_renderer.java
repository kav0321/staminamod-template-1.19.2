package net.kav.staminamod.entity.abilities.client;

import net.kav.staminamod.entity.abilities.foot_stomp;
import net.kav.staminamod.entity.abilities.hurrican_entity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class hurrican_entity_renderer extends GeoProjectilesRenderer<hurrican_entity> {
    public hurrican_entity_renderer(EntityRendererFactory.Context ctx) {
        super(ctx, new hurrican_entity_model());
    }

    @Override
    public RenderLayer getRenderType(hurrican_entity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        stack.scale(1,1,1);
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }


}
