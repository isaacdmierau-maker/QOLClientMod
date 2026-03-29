package com.example.qolclient;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QOLClientMod implements ModInitializer {
    public static final String MOD_ID = "qolclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("QOL Client Mod initialized!");
    }
}