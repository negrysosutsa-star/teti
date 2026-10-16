package ru.bogempty.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import ru.bogempty.module.Category;
import ru.bogempty.module.Module;

public class TriggerBot extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    public float range = 3.2f;
    public boolean playersOnly = true;

    public TriggerBot() {
        super("TriggerBot", Category.COMBAT);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        if (mc.crosshairTarget == null) return;

        Entity target = null;
        if (mc.crosshairTarget.getType() == net.minecraft.util.hit.HitResult.Type.ENTITY) {
            target = ((net.minecraft.util.hit.EntityHitResult) mc.crosshairTarget).getEntity();
        }
        if (!(target instanceof LivingEntity living) || !living.isAlive()) return;
        if (playersOnly && !(target instanceof PlayerEntity)) return;
        if (mc.player.distanceTo(target) > range) return;
        if (mc.player.getAttackCooldownProgress(0.5f) < 0.9f) return;

        mc.interactionManager.attackEntity(mc.player, target);
        mc.player.swingHand(Hand.MAIN_HAND);
    }
}
