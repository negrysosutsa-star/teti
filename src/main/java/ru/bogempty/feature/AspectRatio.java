package ru.bogempty.feature;

import ru.bogempty.module.Category;
import ru.bogempty.module.Module;

public class AspectRatio extends Module {
    public float ratio = 1.6f; // stretched

    public AspectRatio() {
        super("AspectRatio", Category.VISUALS);
    }
}
