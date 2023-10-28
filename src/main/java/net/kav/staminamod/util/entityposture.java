package net.kav.staminamod.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class entityposture {

    public static final Map<String,Float> entityposture= new HashMap<>();
    public static final List<String> entityname= new ArrayList<>();
    public static float getposture(String string) {

        return entityposture.get(string);
    }

    public static void add(String name, float posture)
    {
        entityposture.put(name,posture);
        entityname.add(name);
    }
    public static void clear()
    {
        entityposture.clear();
        entityname.clear();
    }
}
