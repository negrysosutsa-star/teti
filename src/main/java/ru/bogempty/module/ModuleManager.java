package ru.bogempty.module;

import ru.bogempty.feature.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static final List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new AimAssist());
        modules.add(new ESP());
        modules.add(new AspectRatio());
        modules.add(new NoRender());
        modules.add(new GuiMove());
        modules.add(new TriggerBot());
        modules.add(new Sprint());
        modules.add(new FunTimeHelper());
        modules.add(new PveBoost());
    }

    public static void onTick() {
        for (Module m : modules) {
            if (m.isEnabled()) m.onTick();
        }
    }

    public static List<Module> getByCategory(Category cat) {
        List<Module> list = new ArrayList<>();
        for (Module m : modules) {
            if (m.getCategory() == cat) list.add(m);
        }
        return list;
    }
}
