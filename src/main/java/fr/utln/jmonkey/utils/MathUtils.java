package fr.utln.jmonkey.utils;

public class MathUtils {

    public static float clamp(float min, float max, float currentValue) {
        return Math.min(max, Math.max(min, currentValue));
    }

}
