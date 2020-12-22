package net.mcpandemic.core;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.entity.Player;

public class DisguiseManager {

    public DisguiseManager() {}

    public static void setZombieDisguise(Player player) {
        MobDisguise mobDisguise = new MobDisguise(DisguiseType.ZOMBIE);
        mobDisguise.setEntity(player);
        mobDisguise.setViewSelfDisguise(false);
        mobDisguise.setHideArmorFromSelf(true);
        mobDisguise.startDisguise();
    }

    public static void removeDisguise(Player player) {
        Disguise d = DisguiseAPI.getDisguise(player);
        d.removeDisguise();
    }
}