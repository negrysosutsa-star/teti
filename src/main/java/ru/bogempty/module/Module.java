package ru.bogempty.module;

public abstract class Module {
    private final String name;
    private final Category category;
    private boolean enabled;
    private int key = -1;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public boolean isEnabled() { return enabled; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public void onRender() {}
}
