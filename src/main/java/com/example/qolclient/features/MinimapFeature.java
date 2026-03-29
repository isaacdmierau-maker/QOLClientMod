package com.example.qolclient.features;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.BlockPos;
import com.example.qolclient.config.ModConfig;
import java.util.ArrayList;
import java.util.List;

public class MinimapFeature {
    private static boolean isEnabled = false;
    private static final int MINIMAP_SIZE = 100;
    private static final int MINIMAP_X = 10;
    private static final int MINIMAP_Y = 10;
    private static List<Waypoint> waypoints = new ArrayList<>();

    public static class Waypoint {
        public String name;
        public BlockPos pos;
        public int color;

        public Waypoint(String name, BlockPos pos, int color) {
            this.name = name;
            this.pos = pos;
            this.color = color;
        }
    }

    public static void init() {
        isEnabled = ModConfig.isFeatureEnabled("minimap");
        HudRenderCallback.EVENT.register(MinimapFeature::renderMinimap);
    }

    public static void toggle() {
        isEnabled = !isEnabled;
        ModConfig.setFeature("minimap", isEnabled);
    }

    private static void renderMinimap(DrawContext drawContext, float tickDelta) {
        if (!isEnabled) return;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;
        TextRenderer textRenderer = client.textRenderer;
        drawContext.drawTextWithBackdrop(textRenderer, "Minimap", MINIMAP_X + 5, MINIMAP_Y + 5, 0xFFFFFF, 0xFF000000);
    }

    public static void addWaypoint(String name, BlockPos pos, int color) {
        waypoints.add(new Waypoint(name, pos, color));
    }

    public static void removeWaypoint(String name) {
        waypoints.removeIf(w -> w.name.equals(name));
    }

    public static boolean isEnabled() {
        return isEnabled;
    }
}