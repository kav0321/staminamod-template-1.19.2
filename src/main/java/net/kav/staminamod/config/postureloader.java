package net.kav.staminamod.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.util.entityposture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//thx to https://github.com/Globox1997/LevelZ/blob/1.19/src/main/java/net/levelz/data/LevelLoader.java#L23
public class postureloader implements SimpleSynchronousResourceReloadListener {
    @Override
    public Identifier getFabricId() {
        return new Identifier(StaminaMod.MODID,"postureloader");
    }

    @Override
    public void reload(ResourceManager manager) {
        entityposture.clear();
        manager.findResources("entityposture", id -> id.getPath().endsWith(".json")).forEach((id, resourceRef) -> {

            InputStream stream = null;
            try {
                stream = resourceRef.getInputStream();
                JsonObject data = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
                entityposture.add(data.get("name").getAsString(),data.get("posture").getAsFloat());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });
    }



}
