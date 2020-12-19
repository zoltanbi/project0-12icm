package com.infected.ranks;

import org.bukkit.ChatColor;

public enum InfectedRank {

    A("PVT-1", ChatColor.GRAY, 0),
    B("PFC-2", ChatColor.GRAY, 100),
    C("CPL-3", ChatColor.GRAY, 250),
    D("SPC-4", ChatColor.GRAY, 400),
    E("SGT-5", ChatColor.GRAY, 550),
    F("SSG-6", ChatColor.YELLOW, 700),
    G("SFC-7", ChatColor.YELLOW, 850),
    H("MSG-8", ChatColor.YELLOW, 1000),
    I("1SG-9", ChatColor.YELLOW, 1150),
    J("SGM-10", ChatColor.YELLOW, 1300),
    K("CSM-11", ChatColor.GREEN, 1450),
    L("SMA-12", ChatColor.GREEN, 1600),
    M("WO-13", ChatColor.GREEN, 1750),
    N("CWO-14", ChatColor.GREEN, 2900),
    O("2LT-15", ChatColor.GREEN, 2050),
    P("1LT-16", ChatColor.AQUA, 2200),
    Q("CPT-17", ChatColor.AQUA, 2350),
    R("MAJ-18", ChatColor.AQUA, 2500),
    S("LTC-19", ChatColor.AQUA, 2650),
    T("COL-20", ChatColor.AQUA, 2800),
    U("BG-21", ChatColor.RED, 2950),
    V("MG-22", ChatColor.RED, 3100),
    W("LTG-23", ChatColor.RED, 3250),
    X("GEN-24", ChatColor.RED, 3400),
    Y("CMD-25", ChatColor.LIGHT_PURPLE, 3550),;

    private String name;
    private ChatColor color;
    private int cost;

    InfectedRank(String name, ChatColor color, int cost) {
        this.name = name;
        this.color = color;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public int getCost() {
        return cost;
    }
}
