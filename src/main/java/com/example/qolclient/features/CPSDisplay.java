package com.example.qolclient.features;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;
import com.example.qolclient.config.ModConfig;

public class CPSDisplay {
    private static boolean isEnabled = false;
    private static int clickCount = 0;
    private static long lastSecond = 0;
    private static int currentCPS = 0;
    private static final int HUD_X = 10;
    private static final int HUD_Y = 150;
    private static boolean lastLMBState = false;

    public static void init() {
        isEnabled = ModConfig.isFeatureEnabled("cps");
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastSecond >= 1000) {
                currentCPS = clickCount;
                clickCount = 0;
                lastSecond = currentTime;
            }
            long handle = MinecraftClient.getInstance().getWindow().getHandle();
            boolean lmbPressed = GLFW.glfwGetMouseButton(handle, GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS;
            if (lmbPressed && !lastLMBState) {
                clickCount++;
            }
            lastLMBState = lmbPressed;
        });
        HudRenderCallback.EVENT.register(CPSDisplay::renderCPS);
    }

    public static void toggle() {
        isEnabled = !isEnabled;
        ModConfig.setFeature("cps", isEnabled);
    }

    private static void renderCPS(DrawContext drawContext, float tickDelta) {
        if (!isEnabled) return;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.isPaused()) return;
        TextRenderer textRenderer = client.textRenderer;
        String cpsText = "CPS: " + currentCPS;
        drawContext.drawTextWithBackdrop(textRenderer, cpsText, HUD_X, HUD_Y, 0x00FF00, 0xFF000000);
    }

    public static boolean isEnabled() {
        return isEnabled;
    }
}