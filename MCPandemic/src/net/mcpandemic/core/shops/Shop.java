package net.mcpandemic.core.shops;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    /**
     * Every time /shop is called in chat, Shop UI is applied
     *
     * @param p Player using shop command
     */
    public void applyShopUI(Player p) {
        //SETUP
        Inventory gui = Bukkit.createInventory(
                null,18, ChatColor.DARK_GRAY + "Shop");

        //LISTS
        List<String> rankLore = new ArrayList<>();
        rankLore.add(ChatColor.GRAY + "Click to rank up!");

        List<String> h1Lore = new ArrayList<>();
        h1Lore.add(ChatColor.GRAY + "Click to get Healing Apple!");

        List<String> h2Lore = new ArrayList<>();
        h2Lore.add(ChatColor.GRAY + "Click to get Healing Melon Slice!");

        List<String> h3Lore = new ArrayList<>();
        h3Lore.add(ChatColor.GRAY + "Click to get Healing Porkchop!");

        List<String> h4Lore = new ArrayList<>();
        h4Lore.add(ChatColor.GRAY + "Click to get Healing Chicken!");

        List<String> sLore = new ArrayList<>();
        sLore.add(ChatColor.GRAY + "Click to get Slow Grenade!");

        List<String> bLore = new ArrayList<>();
        bLore.add(ChatColor.GRAY + "Click to get Blind Grenade!");

        List<String> fireLore = new ArrayList<>();
        fireLore.add(ChatColor.GRAY + "Click to get Fire Grenade!");

        List<String> fragLore = new ArrayList<>();
        fragLore.add(ChatColor.GRAY + "Click to get Frag Grenade!");

        List<String> chestLore = new ArrayList<>();
        chestLore.add(ChatColor.GRAY + "Click to get Mystery Chest!");

        //ITEMSTACKS
        ItemStack rankUp = new ItemStack(Material.IRON_SWORD);
        ItemMeta rankMeta = rankUp.getItemMeta();
        assert rankMeta != null;
        rankMeta.setDisplayName(ChatColor.GREEN + "Rank Up");
        rankUp.setItemMeta(rankMeta);

        ItemStack heal1 = new ItemStack(Material.APPLE);
        ItemMeta h1Meta = heal1.getItemMeta();
        assert h1Meta != null;
        h1Meta.setDisplayName(ChatColor.GREEN + "Heal 2");
        heal1.setItemMeta(h1Meta);

        ItemStack heal2 = new ItemStack(Material.MELON_SLICE);
        ItemMeta h2Meta = heal2.getItemMeta();
        assert h2Meta != null;
        h2Meta.setDisplayName(ChatColor.GREEN + "Heal 1");
        heal2.setItemMeta(h2Meta);

        ItemStack heal3 = new ItemStack(Material.PORKCHOP);
        ItemMeta h3Meta = heal3.getItemMeta();
        assert h3Meta != null;
        h3Meta.setDisplayName(ChatColor.GREEN + "Heal 1.5");
        heal3.setItemMeta(h3Meta);

        ItemStack heal4 = new ItemStack(Material.CHICKEN);
        ItemMeta h4Meta = heal4.getItemMeta();
        assert h4Meta != null;
        h4Meta.setDisplayName(ChatColor.GREEN + "Heal 3");
        heal4.setItemMeta(h4Meta);

        ItemStack sGrenade = new ItemStack(Material.SLIME_BALL);
        ItemMeta sGrenadeMeta = sGrenade.getItemMeta();
        assert sGrenadeMeta != null;
        sGrenadeMeta.setDisplayName(ChatColor.GREEN + "Slow Grenade");
        sGrenade.setItemMeta(sGrenadeMeta);

        ItemStack bGrenade = new ItemStack(Material.GHAST_TEAR);
        ItemMeta bGrenadeMeta = bGrenade.getItemMeta();
        assert bGrenadeMeta != null;
        bGrenadeMeta.setDisplayName(ChatColor.GREEN + "Blind Grenade");
        bGrenade.setItemMeta(bGrenadeMeta);

        ItemStack fireGrenade = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta fireGrenadeMeta = fireGrenade.getItemMeta();
        assert fireGrenadeMeta != null;
        fireGrenadeMeta.setDisplayName(ChatColor.GREEN + "Fire Grenade");
        fireGrenade.setItemMeta(fireGrenadeMeta);

        ItemStack fragGrenade = new ItemStack(Material.EGG);
        ItemMeta fragGrenadeMeta = fragGrenade.getItemMeta();
        assert fragGrenadeMeta != null;
        fragGrenadeMeta.setDisplayName(ChatColor.GREEN + "Frag Grenade");
        fragGrenade.setItemMeta(fragGrenadeMeta);

        ItemStack mysChest = new ItemStack(Material.CHEST);
        ItemMeta mysChestMeta = mysChest.getItemMeta();
        assert mysChestMeta != null;
        mysChestMeta.setDisplayName(ChatColor.GREEN + "Mystery Chest");
        mysChest.setItemMeta(mysChestMeta);

        //ITEM SETTINGS
        gui.setItem(4,rankUp);
        gui.setItem(9,heal1);
        gui.setItem(10,heal2);
        gui.setItem(11,heal3);
        gui.setItem(12,heal4);
        gui.setItem(13,sGrenade);
        gui.setItem(14,bGrenade);
        gui.setItem(15,fireGrenade);
        gui.setItem(16,fragGrenade);
        gui.setItem(17,mysChest);

        //FINAL
        p.openInventory(gui);
    }
}