package net.kav.staminamod.event.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.screen.AbilityScreen;
import net.kav.staminamod.screen.AbiltyGui;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_KAV_SOUL = "key.category.staminamod.category";
    public static final String KEY_ABILITY1 = "key.staminamod.ability";
    public static final String KEY_ABILITY2 = "key.staminamod.ability1";
    public static final String KEY_ABILITY3 = "key.staminamod.ability2";
    public static KeyBinding ability1;
    public static KeyBinding ability2;
    public static KeyBinding ability3;
    public static KeyBinding test;
    public static KeyBinding test2;
    private static int tick = 20;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            tick--;
            if (tick <= 0) {
                tick = 0;
            }
            usingabilities();
            if(test.isPressed())
            {
                MinecraftClient.getInstance().setScreen(new AbilityScreen(new AbiltyGui()));
            }
            if(test2.isPressed())
            {
                //AbilityData.addAbility(MinecraftClient.getInstance().player);
            }
        });
    }

    public static boolean isabilitybeingpress() {
        return ability1.isPressed() || ability2.isPressed() || ability3.isPressed();
    }

    public static void register() {
        ability1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ABILITY1,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                KEY_CATEGORY_KAV_SOUL
        ));


        ability2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ABILITY2,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                KEY_CATEGORY_KAV_SOUL
        ));

        ability3 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ABILITY3,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                KEY_CATEGORY_KAV_SOUL
        ));
        test = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "TEST",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                KEY_CATEGORY_KAV_SOUL
        ));
        test2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "TEST2",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                KEY_CATEGORY_KAV_SOUL
        ));

        registerKeyInputs();
    }
    public static void usingabilities()
    {
        if(ability1.isPressed())
        {
            if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().contains("ability1") && Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability1")!=0)
            {
             int id=    AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability1")).ID;
             String name  =AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability1")).animationname;
                float stamina  =AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability1")).stamina;
                if(client_tick.getTick==-1)
                {
                    client_tick.getTick=AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability1")).cooldown;
                }
                if(client_tick.getTick<=0)
                {
                    client_tick.getTick=-1;
                    ClientPlayNetworking.send(
                            Packets.AbilityAni.ID,
                            new Packets.AbilityAni(MinecraftClient.getInstance().player.getId(), id).write());
                    AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability1")).staminaconsume();
                }
            }
            else {

            }
        }
        else if(ability2.isPressed())
        {

        }else if(ability3.isPressed())
        {

        }
    }
}
