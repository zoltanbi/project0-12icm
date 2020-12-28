package net.mcpandemic.core.ranks;

import net.mcpandemic.core.kits.KitType;
import org.bukkit.ChatColor;

public enum InfectedRank {

    A("Private", ChatColor.GRAY, 0, KitType.A),
    B("Specialist", ChatColor.GRAY, 100, KitType.B),
    C("Corporal", ChatColor.GRAY, 250, KitType.C),
    D("Sergeant", ChatColor.GRAY, 400, KitType.D),
    E("Staff Sergeant", ChatColor.GRAY, 550, KitType.E),
    F("Master Sergeant", ChatColor.YELLOW, 700, KitType.F),
    G("1st Sergeant", ChatColor.YELLOW, 850, KitType.G),
    H("Sergeant Major", ChatColor.YELLOW, 1000, KitType.H),
    I("Command Sgt Major", ChatColor.YELLOW, 1150, KitType.I),
    J("2nd Lieutenant", ChatColor.YELLOW, 1300, KitType.J),
    K("1st Lieutenant", ChatColor.GREEN, 1450, KitType.K),
    L("Captain", ChatColor.GREEN, 1600, KitType.L),
    M("Major", ChatColor.GREEN, 1750, KitType.M),
    N("Lt. Colonel", ChatColor.GREEN, 2900, KitType.N),
    O("Colonel", ChatColor.GREEN, 2050, KitType.O),
    P("Brigadier General", ChatColor.AQUA, 2200, KitType.P),
    Q("Major General", ChatColor.AQUA, 2350, KitType.Q),
    R("Lt. General", ChatColor.AQUA, 2500, KitType.R),
    S("General", ChatColor.AQUA, 2650, KitType.S),
    T("Commander", ChatColor.LIGHT_PURPLE, 2800, KitType.T);

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
