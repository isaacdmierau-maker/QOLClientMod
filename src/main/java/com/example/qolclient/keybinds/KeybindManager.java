package com.example.qolclient.keybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.example.qolclient.features.*;

public class KeybindManager {
    public static KeyBinding fullBrightnessKey;
    public static KeyBinding zoomKey;
    public static KeyBinding keystrokesToggleKey;
    public static KeyBinding cpsToggleKey;
    public static KeyBinding inventorySortKey;
    public static KeyBinding minimapToggleKey;

    public static void registerKeybinds() {
        fullBrightnessKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.qolclient.full_brightness", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, "QOL Client"));
        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.qolclient.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z, "QOL Client"));
        keystrokesToggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.qolclient.keystrokes_toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, "QOL Client"));
        cpsToggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.qolclient.cps_toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "QOL Client"));
        inventorySortKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.qolclient.inventory_sort", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_I, "QOL Client"));
        minimapToggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.qolclient.minimap_toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "QOL Client"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (fullBrightnessKey.wasPressed()) FullBrightness.toggle();
            if (zoomKey.isPressed()) ZoomFeature.applyZoom();
            if (keystrokesToggleKey.wasPressed()) KeystrokesDisplay.toggle();
            if (cpsToggleKey.wasPressed()) CPSDisplay.toggle();
            if (inventorySortKey.wasPressed()) InventorySorter.sortInventory();
            if (minimapToggleKey.wasPressed()) MinimapFeature.toggle();
        });
    }
}