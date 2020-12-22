package net.mcpandemic.core;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DisguiseManager {

    public DisguiseManager() {}

    public static void setZombieDisguise(Player player) {
        MobDisguise mobDisguise = new MobDisguise(DisguiseType.ZOMBIE);
        mobDisguise.setEntity(player);
        mobDisguise.setViewSelfDisguise(false);
        FlagWatcher watcher = mobDisguise.getWatcher();
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack[] itemStack = {air, air, air, air};
        watcher.setArmor(itemStack);
        //Actionabe
        mobDisguise.setNotifyBar(DisguiseConfig.NotifyBar.NONE);
        mobDisguise.startDisguise();
    }

    public static void removeDisguise(Player player) {
        Disguise d = DisguiseAPI.getDisguise(player);
        d.removeDisguise();
    }
}
