package net.mcpandemic.core.ranks;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.ChatColor;

public enum InfectedRank {

    A("PVT-1", ChatColor.GRAY, 0, KitType.A),
    B("PFC-2", ChatColor.GRAY, 100, KitType.B),
    C("CPL-3", ChatColor.GRAY, 250, KitType.C),
    D("SPC-4", ChatColor.GRAY, 400, KitType.D),
    E("SGT-5", ChatColor.GRAY, 550, KitType.E),
    F("SSG-6", ChatColor.YELLOW, 700, KitType.F),
    G("SFC-7", ChatColor.YELLOW, 850, KitType.G),
    H("MSG-8", ChatColor.YELLOW, 1000, KitType.H),
    I("SGM-9", ChatColor.YELLOW, 1150, KitType.I),
    J("CSM-10", ChatColor.YELLOW, 1300, KitType.J),
    K("2LT-11", ChatColor.GREEN, 1450, KitType.K),
    L("1LT-12", ChatColor.GREEN, 1600, KitType.L),
    M("CPT-13", ChatColor.GREEN, 1750, KitType.M),
    N("MAJ-14", ChatColor.GREEN, 2900, KitType.N),
    O("LTC-15", ChatColor.GREEN, 2050, KitType.O),
    P("COL-16", ChatColor.AQUA, 2200, KitType.P),
    Q("BG-17", ChatColor.AQUA, 2350, KitType.Q),
    R("MG-18", ChatColor.AQUA, 2500, KitType.R),
    S("LTG-19", ChatColor.AQUA, 2650, KitType.S),
    T("GEN-20", ChatColor.AQUA, 2800, KitType.T);

    private String name;
    private ChatColor color;
    private int cost;
    private KitType kitType;

    InfectedRank(String name, ChatColor color, int cost, KitType kitType) {
        this.name = name;
        this.color = color;
        this.cost = cost;
        this.kitType = kitType;
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

    public KitType getKitType() {
        return kitType;
    }
}
