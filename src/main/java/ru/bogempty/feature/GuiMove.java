package ru.bogempty.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.option.KeyBinding;
import ru.bogempty.module.Category;
import ru.bogempty.module.Module;

public class GuiMove extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public GuiMove() {
        super("GuiMove", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.currentScreen == null || mc.currentScreen instanceof ChatScreen) return;
        // allow movement keys while in gui (inventory etc)
        KeyBinding[] keys = {
                mc.options.forwardKey, mc.options.backKey,
                mc.options.leftKey, mc.options.rightKey,
                mc.options.jumpKey, mc.options.sprintKey
        };
        for (KeyBinding key : keys) {
            key.setPressed(KeyBinding.isPressed(key.getDefaultKey()));
        }
    }
}
