package com.mygdx.library;

public class Parameters {
    private static float aspectRatio;
    private static float inverseAspectRatio;

    public static void setAspectRatio(float width, float height) {
        aspectRatio = width / height;
        inverseAspectRatio = 1.0f / aspectRatio; // height / width
    }

    public static float getAspectRatio() {
        return aspectRatio;
    }

    public static float getInverseAspectRatio() {
        return inverseAspectRatio;
    }
}
