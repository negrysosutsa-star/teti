package ru.bogempty.util;

public enum Theme {
    PURPLE(0xFFAA00FF, 0xFF220033),
    BLUE(0xFF00AAFF, 0xFF001122),
    GREEN(0xFF00FF88, 0xFF002211),
    RED(0xFFFF3355, 0xFF220011),
    DARK(0xFFCCCCCC, 0xFF111111);

    public final int accent;
    public final int background;

    Theme(int accent, int background) {
        this.accent = accent;
        this.background = background;
    }
}
