package net.kav.staminamod.api;

public enum dodging {
    FORWARD(0),
    BACKWARD(1),
    LEFT(2),
    RIGHT(3);

    private final int value;

    dodging(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
