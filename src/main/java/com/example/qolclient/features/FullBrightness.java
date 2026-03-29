package com.example.qolclient.features;
import net.minecraft.client.MinecraftClient;
import com.example.qolclient.config.ModConfig;

public class FullBrightness {
    private static float originalGamma;
    private static boolean isEnabled = false;

    public static void init() {
        isEnabled = ModConfig.isFeatureEnabled("full_brightness");
    }

    public static void toggle() {
        isEnabled = !isEnabled;
        ModConfig.setFeature("full_brightness", isEnabled);
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options == null) return;
        if (isEnabled) {
            originalGamma = client.options.gamma().getValue().floatValue();
            client.options.gamma().setValue(16.0);
        } else {
            client.options.gamma().setValue((double) originalGamma);
        }
    }

    public static boolean isEnabled() {
        return isEnabled;
    }
}