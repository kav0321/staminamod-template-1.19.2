package net.kav.staminamod.event.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.api.multiple_animations;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.data.cooldowndata;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.screen.AbilityScreen;
import net.kav.staminamod.screen.AbiltyGui;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_KAV_SOUL = "key.category.staminamod.category";
    public static final String KEY_ABILITY1 = "key.staminamod.ability";
    public static final String KEY_ABILITY2 = "key.staminamod.ability1";
    public static final String KEY_ABILITY3 = "key.staminamod.ability2";
    public static final String MENU = "key.staminamod.menu";
    public static KeyBinding ability1;
    public static KeyBinding ability2;
    public static KeyBinding ability3;
    public static KeyBinding test;
    public static KeyBinding test2;


   // private static int tick = 20;
    private static final Map<KeyBinding, Boolean> keyStateMap = new HashMap<>();
    public static void registerKeyInputs() {
        ClientTickEvents.END_WORLD_TICK.register(client -> {

            //updateKeyState();

            //  usingabilities();
            checkKeyPress(test2);
            checkKeyPress(ability2);
            checkKeyPress(ability3);

            if(test.wasPressed())
            {

                MinecraftClient.getInstance().setScreen(new AbilityScreen(new AbiltyGui()));
            }



        });
    }
    private static void updateKeyState(KeyBinding key) {
        boolean currentState = key.isPressed();

    }

    private static void checkKeyPress(KeyBinding key) {
        boolean currentState = key.isPressed();
        if(!keyStateMap.getOrDefault(key, false) && currentState) {
            onKeyPressed(key);
        }
        keyStateMap.put(key, currentState);
    }
    private static void onKeyPressed(KeyBinding key) {
        // your current logic remains the same here
        if (key == test2) {
            // Ability1 key was released
            if (AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability1")) != null) {
                int id = AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability1")).ID;
                if (id != 0) {

                    send_ability(id, "ability1");
                }


            }
        }
        if(key==ability2)
        {
            if (AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability2")) != null) {
                int id = AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability2")).ID;
                if (id != 0) {

                    send_ability(id, "ability2");
                }


            }
        }
        if(key==ability3)
        {
            if (AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability3")) != null) {
                int id = AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability3")).ID;
                if (id != 0) {

                    send_ability(id, "ability3");
                }


            }
        }
    }
    private static void onKeyReleased(KeyBinding key) {
        if (key == test2) {
            // Ability1 key was released
            if (AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability1")) != null) {
                int id = AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability1")).ID;
                if (id != 0) {

                    send_ability(id, "ability1");
                }


            }
        }
        if(key==ability2)
        {
            if (AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability2")) != null) {
                int id = AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability2")).ID;
                if (id != 0) {

                    send_ability(id, "ability2");
                }


            }
        }
        if(key==ability3)
        {
            if (AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability3")) != null) {
                int id = AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability3")).ID;
                if (id != 0) {

                    send_ability(id, "ability3");
                }


            }
        }
        // ... handle other keys
    }
   public static boolean isabilitybeingpress() {
        return test2.isPressed() || ability2.isPressed() || ability3.isPressed();
    }

    public static void register() {



        ability2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ABILITY2,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                KEY_CATEGORY_KAV_SOUL
        ));

        ability3 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ABILITY3,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                KEY_CATEGORY_KAV_SOUL
        ));
        test = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                MENU,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                KEY_CATEGORY_KAV_SOUL
        ));
        test2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ABILITY1,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                KEY_CATEGORY_KAV_SOUL
        ));
        keyStateMap.put(test2, false);
        keyStateMap.put(ability2, false);
        keyStateMap.put(ability3, false);

        registerKeyInputs();
    }
    private static void send_ability(int id, String ability_slot)
    {
        if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().contains(ability_slot) && Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),ability_slot)!=0)
        {
            if(client_tick.getGetTick(ability_slot) ==0)
            {

                if(AbilityManager.abiltyregister.get(id).conditions(MinecraftClient.getInstance().player))
                {
                    AbilityManager.abiltyregister.get(id).ClientSideExecution();
                    String name;
                    if(AbilityManager.abiltyregister.get(id) instanceof multiple_animations)
                    {
                        multiple_animations  mu = (multiple_animations) AbilityManager.abiltyregister.get(id);
                        name = mu.getanimation_number();
                    }
                    else
                    {
                        name =AbilityManager.abiltyregister.get(id).animationname;
                    }


                    client_tick.setGetTick(ability_slot,0);
                    ClientPlayNetworking.send(
                            ModMessages.ANIMATION,
                            new Packets.AbilityAni(MinecraftClient.getInstance().player.getId(), id, name).write());
                    AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),ability_slot)).staminaconsume();

                    client_tick.setGetTick(ability_slot,AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),ability_slot)).cooldown+1);
                    cooldowndata.setCooldown((IEntityDataSaver) MinecraftClient.getInstance().player,ability_slot,AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),ability_slot)).cooldown);
                    ClientPlayNetworking.send(ModMessages.COOLDOWN,new Packets.cooldown(AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),ability_slot)).cooldown,ability_slot).write());
                }


            }


        }
        else {

        }
    }

    public static void usingabilities() {

        if (test2.isPressed()) {

            if (AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability1")) != null) {
                int id = AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability1")).ID;
                if (id != 0) {

                    send_ability(id, "ability1");
                }


            }
        }
        if (ability2.isPressed() ) {
            if (AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability2")) != null) {
                int id = AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability2")).ID;
                if (id != 0) {

                    send_ability(id, "ability2");
                }


            }
        }


        if (ability3.isPressed()) {
                if (AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability3")) != null) {
                    int id = AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player), "ability3")).ID;
                    if (id != 0) {

                        send_ability(id, "ability3");
                    }


                }


            }
        }
    }

/*if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().contains("ability2") && Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability2")!=0)
            {
                int id=    AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability2")).ID;
                String name  =AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability2")).animationname;
                float stamina  =AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability2")).stamina;
                if(client_tick.getTick2 ==-1)
                {
                    client_tick.getTick2 =AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability2")).cooldown;
                }
                if(client_tick.getTick2 <=0)
                {
                    AbilityManager.abiltyregister.get(id).ClientSideExecution();
                    client_tick.getTick2 =-1;
                    ClientPlayNetworking.send(
                            Packets.AbilityAni.ID,
                            new Packets.AbilityAni(MinecraftClient.getInstance().player.getId(), id).write());
                    AbilityManager.abiltyregister.get(Equipdata.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"ability2")).staminaconsume();
                }
            }
            else {

            }*/