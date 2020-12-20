package net.mcpandemic.core.voting;

import net.mcpandemic.core.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class VoteGUICommand {


    public VoteGUICommand() {
    }

    /**
     * Creates Voting GUI
     *
     * @param p
     */
    public static void applyVoteUI(Player p) {
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
        num1Meta.setDisplayName(ChatColor.RED + Manager.getArena().getMapArray()[0].getMapName());
        num1.setItemMeta(num1Meta);

        ItemStack num2 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta num2Meta = num2.getItemMeta();
        assert num2Meta != null;
        num2Meta.setDisplayName(ChatColor.BLUE + Manager.getArena().getMapArray()[1].getMapName());
        num2.setItemMeta(num2Meta);

        ItemStack num3 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta num3Meta = num3.getItemMeta();
        assert num3Meta != null;
        num3Meta.setDisplayName(ChatColor.GREEN + Manager.getArena().getMapArray()[2].getMapName());
        num3.setItemMeta(num3Meta);

        ItemStack num4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta num4Meta = num4.getItemMeta();
        assert num4Meta != null;
        num4Meta.setDisplayName(ChatColor.YELLOW + Manager.getArena().getMapArray()[3].getMapName());
        num4.setItemMeta(num4Meta);

        ItemStack num5 = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        ItemMeta num5Meta = num5.getItemMeta();
        assert num5Meta != null;
        num5Meta.setDisplayName(ChatColor.LIGHT_PURPLE + Manager.getArena().getMapArray()[4].getMapName());
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
