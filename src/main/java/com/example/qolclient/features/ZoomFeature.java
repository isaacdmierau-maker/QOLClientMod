package com.example.qolclient.features;

import net.minecraft.client.MinecraftClient;
import com.example.qolclient.config.ModConfig;

public class ZoomFeature {
    private static float zoomLevel = 1.0f;
    private static final float ZOOM_INCREMENT = 0.1f;
    private static final float MAX_ZOOM = 10.0f;

    public static void init() { }

    public static void applyZoom() {
        if (!ModConfig.isFeatureEnabled("zoom")) return;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        zoomLevel = Math.min(zoomLevel + ZOOM_INCREMENT, MAX_ZOOM);
    }

    public static void resetZoom() {
        zoomLevel = 1.0f;
    }

    public static float getZoomLevel() {
        return zoomLevel;
    }
}