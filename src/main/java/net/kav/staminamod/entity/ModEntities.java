package net.kav.staminamod.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.entity.abilities.big_foot_stomp;
import net.kav.staminamod.entity.abilities.bigger_foot_stomp;
import net.kav.staminamod.entity.abilities.client.big_foot_stomp_renderer;
import net.kav.staminamod.entity.abilities.client.bigger_foot_stomp_renderer;
import net.kav.staminamod.entity.abilities.client.foot_stomp_renderer;
import net.kav.staminamod.entity.abilities.client.hurrican_entity_renderer;
import net.kav.staminamod.entity.abilities.foot_stomp;
import net.kav.staminamod.entity.abilities.hurrican_entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.GeckoLib;

public class ModEntities {

    public static EntityType<hurrican_entity> HITBOX = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(StaminaMod.MODID, "hurrican_s"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, hurrican_entity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 2f)).build());
    public static EntityType<foot_stomp> STOMP = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(StaminaMod.MODID, "stomp_attack"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, foot_stomp::new)
                    .dimensions(EntityDimensions.fixed(1.4f, 0.4f)).build());

    public static EntityType<big_foot_stomp> STOMP2 = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(StaminaMod.MODID, "stomp_attack2"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, big_foot_stomp::new)
                    .dimensions(EntityDimensions.fixed(1.8f, 0.5f)).build());
    public static EntityType<bigger_foot_stomp> STOMP3 = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(StaminaMod.MODID, "stomp_attack3"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, bigger_foot_stomp::new)
                    .dimensions(EntityDimensions.fixed(2.5f, 0.6f)).build());
    ///public static final EntityType<foot_stomp> BOAT = register("boat", EntityType.Builder.create(foot_stomp::new, SpawnGroup.MISC).setDimensions(1.375f, 0.5625f).maxTrackingRange(10));

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, id, type.build(id));
    }

    public static void server()
    {
        GeckoLib.initialize();
    }


    public static void registerEntityclient()
    {
        EntityRendererRegistry.register(ModEntities.STOMP, foot_stomp_renderer::new);
        EntityRendererRegistry.register(ModEntities.STOMP2, big_foot_stomp_renderer::new);
        EntityRendererRegistry.register(ModEntities.STOMP3, bigger_foot_stomp_renderer::new);
        EntityRendererRegistry.register(ModEntities.HITBOX, hurrican_entity_renderer::new);
    }
}
