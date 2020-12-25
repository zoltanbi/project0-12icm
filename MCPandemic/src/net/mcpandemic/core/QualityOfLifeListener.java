package net.mcpandemic.core;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;


public class QualityOfLifeListener implements Listener {

    public static ItemStack howToPlayBook() {
        ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
        bookMeta.setTitle("How To Play");
        bookMeta.setAuthor("MCPandemic");

        List<String> pages = new ArrayList<String>();
        pages.add("Welcome to " + ChatColor.DARK_RED + "MCPandemic\n" + ChatColor.RESET +
                "-------------------\n" +
                "      How to play:\n" +
                "-------------------\n" + ChatColor.RESET +
                "➣ 2 Teams:\n" +
                "      " + ChatColor.DARK_GREEN +  "Humans" + ChatColor.RESET + " & " + ChatColor.RED + "Zombies\n" +
                "\n" + ChatColor.RESET +
                "➣ If you are a " + ChatColor.RED + "Zombie" + ChatColor.RESET + ", kill humans to infect everyone.\n" +
                "\n" +
                "➣ If you are a" + ChatColor.DARK_GREEN + " Human" + ChatColor.RESET + ", survive and fight off the horde until the"); // Page 1

        pages.add("timer runs out to win.\n" +
                "\n" +
                "➣ Currency is " + ChatColor.BLUE + "Rankpoints" + ChatColor.RESET + ". You get "+ ChatColor.BLUE + "Rankpoints" + ChatColor.RESET + " every time:\n" +
                "  ➡ A " + ChatColor.DARK_GREEN + "Human" + ChatColor.RESET + " dies\n" +
                "  ➡ When you kill the          opposite team\n" +
                "  ➡ On win\n" +
                "\n" +
                "➣ Oh, and you can use these " + ChatColor.BLUE + "Rankpoints" + ChatColor.RESET + " to rankup (" + ChatColor.GOLD + "/rankup" + ChatColor.RESET + ") to "); // Page 2

        pages.add("get better gear as a" + ChatColor.DARK_GREEN + " Human" + ChatColor.RESET + " (such as grenades, a bow, food for healing and some sick netherrite armor)\n" +
                "\n" +
                "➣ This game has " + ChatColor.LIGHT_PURPLE + "Prestiges.\n" + ChatColor.RESET +
                "\n" +
                "➣ " + ChatColor.LIGHT_PURPLE + "Prestiges " + ChatColor.RESET + "are when you reach max rank (Rank 20) and want to reset your progress in return for some"); // Page 3

        pages.add("sick perks such as Tomahawks, new grenades and many others (" + ChatColor.GOLD + "/pshop" + ChatColor.RESET + " for info)"); // Page 4

        bookMeta.setPages(pages);
        writtenBook.setItemMeta(bookMeta);
        return writtenBook;
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFire(BlockIgniteEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

}
