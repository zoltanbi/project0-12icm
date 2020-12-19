package com.infected.ranks;

import org.bukkit.ChatColor;

public enum Rank {

    OWNER("OWNER", ChatColor.RED),
    ADMIN("ADMIN", ChatColor.RED),
    MOD("MOD", ChatColor.DARK_GREEN),
    GOATED("GOATED", ChatColor.DARK_PURPLE),
    MATEY("MATEY", ChatColor.GREEN),
    BANGER("BANGER", ChatColor.RED),
    SPLASH("SPLASH", ChatColor.AQUA),
    JAGGER("JAGGER", ChatColor.YELLOW),
    GUEST("", ChatColor.GRAY);

    private String name;
    private ChatColor color;

    Rank(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }
}
