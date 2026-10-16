package ru.bogempty.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import ru.bogempty.BogEmpty;
import ru.bogempty.module.Category;
import ru.bogempty.module.Module;
import ru.bogempty.module.ModuleManager;
import ru.bogempty.util.Theme;

import java.util.List;

public class ClickGui extends Screen {
    private Category current = Category.COMBAT;
    private int panelX = 40;
    private int panelY = 40;

    public ClickGui() {
        super(Text.literal("BogEmpty"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // blur simulation + dark overlay
        context.fill(0, 0, this.width, this.height, 0x99000000);

        Theme theme = BogEmpty.INSTANCE.theme;
        int accent = theme.accent;
        int bg = theme.background;

        // main panel
        context.fill(panelX, panelY, panelX + 420, panelY + 320, bg);
        context.fill(panelX, panelY, panelX + 420, panelY + 24, accent);

        context.drawText(this.textRenderer, "BOGEMPTY", panelX + 8, panelY + 7, 0xFFFFFFFF, true);

        // tabs
        int tabX = panelX + 8;
        for (Category cat : Category.values()) {
            boolean selected = cat == current;
            int color = selected ? accent : 0xFFAAAAAA;
            context.drawText(this.textRenderer, cat.name, tabX, panelY + 32, color, false);
            if (mouseX >= tabX && mouseX <= tabX + this.textRenderer.getWidth(cat.name) + 8
                    && mouseY >= panelY + 30 && mouseY <= panelY + 44) {
                // hover
            }
            tabX += this.textRenderer.getWidth(cat.name) + 16;
        }

        // modules
        List<Module> mods = ModuleManager.getByCategory(current);
        int y = panelY + 55;
        for (Module m : mods) {
            int color = m.isEnabled() ? accent : 0xFFCCCCCC;
            context.drawText(this.textRenderer, m.getName(), panelX + 12, y, color, false);
            y += 16;
        }

        // theme switcher
        context.drawText(this.textRenderer, "Theme: " + BogEmpty.INSTANCE.theme.name(), panelX + 12, panelY + 300, 0xFFAAAAAA, false);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // tabs
        int tabX = panelX + 8;
        for (Category cat : Category.values()) {
            int w = this.textRenderer.getWidth(cat.name) + 8;
            if (mouseX >= tabX && mouseX <= tabX + w && mouseY >= panelY + 30 && mouseY <= panelY + 44) {
                current = cat;
                return true;
            }
            tabX += this.textRenderer.getWidth(cat.name) + 16;
        }

        // modules toggle
        List<Module> mods = ModuleManager.getByCategory(current);
        int y = panelY + 55;
        for (Module m : mods) {
            if (mouseX >= panelX + 12 && mouseX <= panelX + 200 && mouseY >= y && mouseY <= y + 14) {
                if (button == 0) m.toggle();
                return true;
            }
            y += 16;
        }

        // theme cycle
        if (mouseX >= panelX + 12 && mouseX <= panelX + 150 && mouseY >= panelY + 298 && mouseY <= panelY + 312) {
            Theme[] themes = Theme.values();
            int idx = (BogEmpty.INSTANCE.theme.ordinal() + 1) % themes.length;
            BogEmpty.INSTANCE.theme = themes[idx];
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
