package com.roberto.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("GUI plugin enabled.");
        getCommand("menu").setExecutor(new MenuCommand(this));
    }

    public void shopUI(Player p) {
        //BEGINNING
        Inventory gui = Bukkit.createInventory(
                null,18, ChatColor.DARK_GRAY + "Shop");

        //LISTS

        //ITEMSTACKS
        /*
            sword is rank up
            chicken, porkchop, watermelon, apple is healing
            slimeball is slow grenade
            snowball is blind grenade
            chest is mystery chest
        */
        ItemStack rankUp = new ItemStack(Material.IRON_SWORD);
        ItemMeta rankMeta = rankUp.getItemMeta();
        rankMeta.setDisplayName(ChatColor.GREEN + "Rank Up");
        rankUp.setItemMeta(rankMeta);

        ItemStack heal1 = new ItemStack(Material.APPLE);
        ItemMeta h1Meta = heal1.getItemMeta();
        h1Meta = setDisplayName(ChatColor.GREEN + "Healing 1");
        heal1.setItemMeta(h1Meta);

        ItemStack heal2 = new ItemStack(Material.MELON_SLICE);
        ItemMeta h2Meta = heal2.getItemMeta();
        h2Meta = setDisplayName(ChatColor.GREEN + "Healing 2");
        heal2.setItemMeta(h2Meta);

        ItemStack heal3 = new ItemStack(Material.PORKCHOP);
        ItemMeta h3Meta = heal3.getItemMeta();
        h3Meta = setDisplayName(ChatColor.GREEN + "Healing 3");
        heal3.setItemMeta(h3Meta);

        ItemStack heal4 = new ItemStack(Material.CHICKEN);
        ItemMeta h4Meta = heal4.getItemMeta();
        h4Meta = setDisplayName(ChatColor.GREEN + "Healing 4");
        heal4.setItemMeta(h4Meta);

        ItemStack sGrenade = newItemStack(Material.SLIME_BALL);
        ItemMeta sGrenadeMeta = sGrenade.getItemMeta();
        sGrenadeMeta = setDisplayName(ChatColor.GREEN + "Slow Grenade");
        sGrenade.setItemMeta(sGrenadeMeta);

        ItemStack bGrenade = newItemStack(Material.SNOWBALL);
        ItemMeta bGrenadeMeta = bGrenade.getItemMeta();
        bGrenadeMeta = setDisplayName(ChatColor.GREEN + "Blind Grenade");
        bGrenade.setItemMeta(bGrenadeMeta);

        ItemStack fireGrenade = newItemStack(Material.MAGMA_CREAM);
        ItemMeta fireGrenadeMeta = fireGrenade.getItemMeta();
        fireGrenadeMeta = setDisplayName(ChatColor.GREEN + "Fire Grenade");
        fireGrenade.setItemMeta(fireGrenadeMeta);

        ItemStack fragGrenade = newItemStack(Material.EGG);
        ItemMeta fragGrenadeMeta = fragGrenade.getItemMeta();
        fragGrenadeMeta = setDisplayName(ChatColor.GREEN + "Frag Grenade");
        fragGrenade.setItemMeta(fragGrenadeMeta);

        ItemStack mysChest = newItemStack(Material.CHEST);
        ItemMeta mysChestMeta = mysChest.getItemMeta();
        mysChestMeta = setDisplayName(ChatColor.GREEN + "Mystery Chest");
        mysChest.setItemMeta(mysChestMeta);

        //ITEM SETTINGS

        //FINAL
    }
}
