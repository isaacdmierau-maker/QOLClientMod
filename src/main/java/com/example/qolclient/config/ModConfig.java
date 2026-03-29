package com.example.qolclient.config;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ModConfig {
    private static final String CONFIG_FILE = "config/qolclient.cfg";
    private static final Map<String, Boolean> features = new HashMap<>();

    static {
        features.put("full_brightness", false);
        features.put("zoom", true);
        features.put("keystrokes", false);
        features.put("cps", false);
        features.put("inventory_sort", true);
        features.put("minimap", true);
    }

    public static void loadConfig() {
        File file = new File(CONFIG_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("#")) continue;
                    String[] parts = line.split("=");
                    if (parts.length == 2) {
                        features.put(parts[0].trim(), Boolean.parseBoolean(parts[1].trim()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveConfig();
        }
    }

    public static void saveConfig() {
        File dir = new File("config");
        if (!dir.exists()) dir.mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(CONFIG_FILE)))) {
            writer.write("# QOL Client Configuration\n");
            writer.write("# Toggle features on/off (true/false)\n\n");
            for (Map.Entry<String, Boolean> entry : features.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toggleFeature(String featureName) {
        features.put(featureName, !features.getOrDefault(featureName, false));
        saveConfig();
    }

    public static boolean isFeatureEnabled(String featureName) {
        return features.getOrDefault(featureName, false);
    }

    public static void setFeature(String featureName, boolean enabled) {
        features.put(featureName, enabled);
        saveConfig();
    }

    public static Map<String, Boolean> getFeatures() {
        return new HashMap<>(features);
    }
}