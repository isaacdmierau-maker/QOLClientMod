package com.example.qolclient.features;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;
import com.example.qolclient.config.ModConfig;

public class KeystrokesDisplay {
    private static boolean isEnabled = false;
    private static final int HUD_X = 10;
    private static final int HUD_Y = 100;

    public static void init() {
        isEnabled = ModConfig.isFeatureEnabled("keystrokes");
        HudRenderCallback.EVENT.register(KeystrokesDisplay::renderKeystrokes);
    }

    public static void toggle() {
        isEnabled = !isEnabled;
        ModConfig.setFeature("keystrokes", isEnabled);
    }

    private static void renderKeystrokes(DrawContext drawContext, float tickDelta) {
        if (!isEnabled) return;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.isPaused()) return;
        TextRenderer textRenderer = client.textRenderer;
        boolean w = isKeyPressed(GLFW.GLFW_KEY_W);
        boolean a = isKeyPressed(GLFW.GLFW_KEY_A);
        boolean s = isKeyPressed(GLFW.GLFW_KEY_S);
        boolean d = isKeyPressed(GLFW.GLFW_KEY_D);
        String wState = w ? "§cW" : "W";
        String aState = a ? "§cA" : "A";
        String sState = s ? "§cS" : "S";
        String dState = d ? "§cD" : "D";
        drawContext.drawTextWithBackdrop(textRenderer, wState, HUD_X, HUD_Y, 0xFFFFFF, 0xFF000000);
        drawContext.drawTextWithBackdrop(textRenderer, aState, HUD_X, HUD_Y + 12, 0xFFFFFF, 0xFF000000);
        drawContext.drawTextWithBackdrop(textRenderer, sState, HUD_X + 12, HUD_Y + 12, 0xFFFFFF, 0xFF000000);
        drawContext.drawTextWithBackdrop(textRenderer, dState, HUD_X + 24, HUD_Y + 12, 0xFFFFFF, 0xFF000000);
    }

    private static boolean isKeyPressed(int keyCode) {
        long handle = MinecraftClient.getInstance().getWindow().getHandle();
        return GLFW.glfwGetKey(handle, keyCode) == GLFW.GLFW_PRESS;
    }

    public static boolean isEnabled() {
        return isEnabled;
    }
}