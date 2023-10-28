package net.kav.staminamod.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class toolparry {
    public static final Map<String,Float> toolparry= new HashMap<>();
    public static final List<String> toolname= new ArrayList<>();
    public static float getposture(String string) {

        return toolparry.get(string);
    }

    public static void add(String name, float posture)
    {
        toolparry.put(name,posture);
        toolname.add(name);
    }
    public static void clear()
    {
        entityposture.clear();
        toolname.clear();
    }
}
