package ru.bogempty.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.bogempty.BogEmpty;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // watermark + replace title feel
        int color = 0xFFAA00FF; // purple
        String text = "BOGEMPTY";
        int x = this.width / 2 - this.textRenderer.getWidth(text) * 2 / 2; // larger
        int y = 30;
        // draw larger by scaling conceptually (real scale needs matrix)
        context.drawText(this.textRenderer, text, x, y, color, true);
        context.drawText(this.textRenderer, "v1.0", x + this.textRenderer.getWidth(text) + 6, y + 2, 0xFFCC88FF, false);
    }
}
