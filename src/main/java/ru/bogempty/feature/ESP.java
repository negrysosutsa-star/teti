package ru.bogempty.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import ru.bogempty.module.Category;
import ru.bogempty.module.Module;

public class ESP extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public ESP() {
        super("ESP", Category.VISUALS);
    }

    @Override
    public void onRender() {
        // render handled via mixin / world render event in full build
    }

    public void render(MatrixStack matrices, VertexConsumerProvider consumers) {
        if (mc.world == null || mc.player == null) return;
        for (Entity e : mc.world.getEntities()) {
            if (!(e instanceof PlayerEntity) || e == mc.player) continue;
            Box box = e.getBoundingBox().offset(-mc.getEntityRenderDispatcher().camera.getPos().x,
                    -mc.getEntityRenderDispatcher().camera.getPos().y,
                    -mc.getEntityRenderDispatcher().camera.getPos().z);
            // simple box outline via RenderSystem in real render pass
        }
    }
}
