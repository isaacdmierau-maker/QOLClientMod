package com.example.qolclient.features;

import net.minecraft.client.MinecraftClient;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import com.example.qolclient.config.ModConfig;

public class InventorySorter {
    private static boolean isEnabled = false;

    public static void init() {
        isEnabled = ModConfig.isFeatureEnabled("inventory_sort");
    }

    public static void sortInventory() {
        if (!isEnabled) return;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        Inventory inventory = client.player.getInventory();

        for (int i = 0; i < inventory.size() - 4; i++) {
            for (int j = i + 1; j < inventory.size() - 4; j++) {
                ItemStack itemI = inventory.getStack(i);
                ItemStack itemJ = inventory.getStack(j);
                if (shouldSwap(itemI, itemJ)) {
                    ItemStack temp = itemI.copy();
                    inventory.setStack(i, itemJ.copy());
                    inventory.setStack(j, temp);
                }
            }
        }
    }

    private static boolean shouldSwap(ItemStack item1, ItemStack item2) {
        if (item1.isEmpty() && !item2.isEmpty()) return true;
        if (item1.isEmpty() || item2.isEmpty()) return false;
        int type1 = getItemPriority(item1);
        int type2 = getItemPriority(item2);
        return type1 > type2;
    }

    private static int getItemPriority(ItemStack item) {
        if (item.getMaxCount() == 64) return 3;
        if (item.isDamageable()) return 2;
        if (item.getItem() == Items.GOLDEN_APPLE || item.getItem() == Items.APPLE) return 1;
        return 0;
    }

    public static boolean isEnabled() {
        return isEnabled;
    }
}