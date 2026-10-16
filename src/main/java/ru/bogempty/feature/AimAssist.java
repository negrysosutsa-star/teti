package ru.bogempty.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import ru.bogempty.module.Category;
import ru.bogempty.module.Module;

public class AimAssist extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    public float range = 4.5f;
    public float speed = 0.35f;
    public boolean playersOnly = true;

    public AimAssist() {
        super("AimAssist", Category.COMBAT);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.currentScreen != null) return;
        LivingEntity target = findTarget();
        if (target == null) return;

        Vec3d eyes = mc.player.getEyePos();
        Vec3d targetPos = target.getPos().add(0, target.getHeight() * 0.85, 0);
        Vec3d diff = targetPos.subtract(eyes);

        double dist = diff.length();
        if (dist > range) return;

        float yaw = (float) Math.toDegrees(Math.atan2(diff.z, diff.x)) - 90f;
        float pitch = (float) -Math.toDegrees(Math.atan2(diff.y, Math.sqrt(diff.x * diff.x + diff.z * diff.z)));

        float currentYaw = mc.player.getYaw();
        float currentPitch = mc.player.getPitch();

        float deltaYaw = MathHelper.wrapDegrees(yaw - currentYaw);
        float deltaPitch = pitch - currentPitch;

        mc.player.setYaw(currentYaw + deltaYaw * speed);
        mc.player.setPitch(MathHelper.clamp(currentPitch + deltaPitch * speed, -90f, 90f));
    }

    private LivingEntity findTarget() {
        LivingEntity best = null;
        double bestDist = range;
        for (Entity e : mc.world.getEntities()) {
            if (!(e instanceof LivingEntity living) || e == mc.player || !living.isAlive()) continue;
            if (playersOnly && !(e instanceof PlayerEntity)) continue;
            double d = mc.player.distanceTo(e);
            if (d < bestDist) {
                bestDist = d;
                best = living;
            }
        }
        return best;
    }
}
