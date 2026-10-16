package ru.bogempty.feature;

import net.minecraft.client.MinecraftClient;
import ru.bogempty.module.Category;
import ru.bogempty.module.Module;

public class Sprint extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.forwardSpeed > 0 && !mc.player.isSneaking() && !mc.player.isUsingItem()) {
            mc.player.setSprinting(true);
        }
    }
}
