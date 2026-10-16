package ru.bogempty.feature;

import ru.bogempty.module.Category;
import ru.bogempty.module.Module;

public class NoRender extends Module {
    public boolean fire = true;
    public boolean underWater = true;
    public boolean bossBar = false;
    public boolean scoreboard = false;

    public NoRender() {
        super("NoRender", Category.VISUALS);
    }
}
