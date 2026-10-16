package ru.bogempty;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.bogempty.gui.ClickGui;
import ru.bogempty.module.ModuleManager;
import ru.bogempty.util.Theme;

public class BogEmpty implements ClientModInitializer {
    public static BogEmpty INSTANCE;
    public static final String NAME = "BogEmpty";
    public KeyBinding openGui;
    public ClickGui clickGui;
    public Theme theme = Theme.PURPLE;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        ModuleManager.init();
        openGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.bogempty.gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.bogempty"
        ));
        clickGui = new ClickGui();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGui.wasPressed()) {
                client.setScreen(clickGui);
            }
            ModuleManager.onTick();
        });
    }
}
