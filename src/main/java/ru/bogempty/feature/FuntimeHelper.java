package ru.bogempty.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import ru.bogempty.module.Category;
import ru.bogempty.module.Module;

public class FunTimeHelper extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    // hotkeys handled externally or via settings
    public boolean autoPot = true;
    public boolean autoGap = false;
    public boolean deskSync = true; // simple desync helper

    public FunTimeHelper() {
        super("FunTimeHelper", Category.PLAYER);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;

        if (autoPot && mc.player.getHealth() < 12f) {
            useItem(Items.SPLASH_POTION);
        }
        if (autoGap && mc.player.getHealth() < 8f) {
            useItem(Items.GOLDEN_APPLE);
        }
    }

    private void useItem(net.minecraft.item.Item item) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem() == item) {
                int prev = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = i;
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                mc.player.getInventory().selectedSlot = prev;
                break;
            }
        }
    }
}
