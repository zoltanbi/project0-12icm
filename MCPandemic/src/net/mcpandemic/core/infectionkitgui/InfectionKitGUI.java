package net.mcpandemic.core.infectionkitgui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InfectionKitGUI implements CommandExecutor {

    public InfectionKitGUI() {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            applyInfectionGUI((Player) commandSender);
        }

        return false;
    }

    public static void applyInfectionGUI(Player player) {
        //SETUP
        Inventory infectedKitGUI = Bukkit.createInventory(
                null, 9, ChatColor.DARK_GRAY + "Infected Kits");

        //ITEMSTACKS
        ItemStack zombieKit = new ItemStack(Material.ZOMBIE_HEAD);
        ItemMeta zombieMeta = zombieKit.getItemMeta();
        assert zombieMeta != null;
        zombieMeta.setDisplayName(ChatColor.DARK_GREEN + "Zombie Kit (Default)");
        zombieKit.setItemMeta(zombieMeta);

        ItemStack skeleKit = new ItemStack(Material.SKELETON_SKULL);
        ItemMeta skeleMeta = skeleKit.getItemMeta();
        assert skeleMeta != null;
        skeleMeta.setDisplayName(ChatColor.DARK_GRAY + "Skeleton Kit");
        skeleKit.setItemMeta(skeleMeta);

        //ITEM SETTINGS
        infectedKitGUI.setItem(0,zombieKit);
        infectedKitGUI.setItem(1,skeleKit);

        //FINAL
        player.openInventory(infectedKitGUI);
    }
}
