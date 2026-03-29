package com.example.qolclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import com.example.qolclient.config.ModConfig;
import com.example.qolclient.keybinds.KeybindManager;
import com.example.qolclient.features.*;

@Environment(EnvType.CLIENT)
public class QOLClientModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfig.loadConfig();
        KeybindManager.registerKeybinds();
        FullBrightness.init();
        ZoomFeature.init();
        KeystrokesDisplay.init();
        CPSDisplay.init();
        InventorySorter.init();
        MinimapFeature.init();
    }
}