package ru.bogempty.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Hand;
import ru.bogempty.module.Category;
import ru.bogempty.module.Module;

public class PveBoost extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    public float range = 4.0f;

    public PveBoost() {
        super("PveBoost", Category.PVE);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        // simple auto-hit nearest mob for farm / balance on FunTime
        Entity best = null;
        double bestDist = range;
        for (Entity e : mc.world.getEntities()) {
            if (!(e instanceof MobEntity mob) || !mob.isAlive()) continue;
            double d = mc.player.distanceTo(e);
            if (d < bestDist) {
                bestDist = d;
                best = e;
            }
        }
        if (best != null && mc.player.getAttackCooldownProgress(0.5f) >= 0.95f) {
            mc.interactionManager.attackEntity(mc.player, best);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
