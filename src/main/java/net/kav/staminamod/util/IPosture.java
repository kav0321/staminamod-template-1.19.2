package net.kav.staminamod.util;

import net.minecraft.entity.data.TrackedData;

public interface IPosture {
    TrackedData<Float> getposture();
    float getposture_number();
    void setposture_float(float posture);
    float getmaxposture();
    void incrementposture_float(float posture);
}
