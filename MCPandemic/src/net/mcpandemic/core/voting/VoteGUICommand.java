package net.mcpandemic.core.voting;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
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
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VoteGUICommand implements CommandExecutor {

    public VoteGUICommand() {}

    /**
     * Opens up Voting GUI on /vote command
     *
     * @param sender
     * @param command
     * @param s
     * @param strings
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s,
                             String[] strings) {
        if (sender instanceof Player) {
            applyVoteUI((Player) sender);
        }
        else {
            System.out.println("Console can't use this command!");
        }

        return false;
    }

    /**
     * Creates Voting GUI
     *
     * @param p
     */
    private void applyVoteUI(Player p) {
        //SETUP
        Inventory vGUI = Bukkit.createInventory(
                null, 9, ChatColor.DARK_GRAY + "Vote");

        //LISTS
        List<String> num1Lore = new ArrayList<>();
        num1Lore.add(ChatColor.GRAY + "Click to select this Map.");

        List<String> num2Lore = new ArrayList<>();
        num2Lore.add(ChatColor.GRAY + "Click to select this Map.");

        List<String> num3Lore = new ArrayList<>();
        num3Lore.add(ChatColor.GRAY + "Click to select this Map.");

        List<String> num4Lore = new ArrayList<>();
        num4Lore.add(ChatColor.GRAY + "Click to select this Map.");

        List<String> num5Lore = new ArrayList<>();
        num5Lore.add(ChatColor.GRAY + "Click to select this Map.");


        //ITEMSTACKS
        ItemStack num1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta num1Meta = num1.getItemMeta();
        assert num1Meta != null;
        num1Meta.setDisplayName(ChatColor.RED + "Map 1");
        num1.setItemMeta(num1Meta);

        ItemStack num2 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta num2Meta = num2.getItemMeta();
        assert num2Meta != null;
        num2Meta.setDisplayName(ChatColor.BLUE + "Map 2");
        num2.setItemMeta(num2Meta);

        ItemStack num3 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta num3Meta = num3.getItemMeta();
        assert num3Meta != null;
        num3Meta.setDisplayName(ChatColor.GREEN + "Map 3");
        num3.setItemMeta(num3Meta);

        ItemStack num4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta num4Meta = num4.getItemMeta();
        assert num4Meta != null;
        num4Meta.setDisplayName(ChatColor.YELLOW + "Map 4");
        num4.setItemMeta(num4Meta);

        ItemStack num5 = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        ItemMeta num5Meta = num5.getItemMeta();
        assert num5Meta != null;
        num5Meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Map 5");
        num5.setItemMeta(num5Meta);

        //ITEM SETTINGS
        vGUI.setItem(0,num1);
        vGUI.setItem(1,num2);
        vGUI.setItem(2,num3);
        vGUI.setItem(3,num4);
        vGUI.setItem(4,num5);

        //FINAL
        p.openInventory(vGUI);
    }
}
