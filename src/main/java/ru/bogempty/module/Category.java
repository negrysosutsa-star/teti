package ru.bogempty.module;

public enum Category {
    COMBAT("Combat"),
    VISUALS("Visuals"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    PVE("PVE"),
    MISC("Misc");

    public final String name;
    Category(String name) { this.name = name; }
}
