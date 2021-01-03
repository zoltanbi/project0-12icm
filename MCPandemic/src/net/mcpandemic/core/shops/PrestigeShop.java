package net.mcpandemic.core.shops;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//p2Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+ "Costs 2 Prestige Tokens",
//        "",
//        ChatColor.WHITE + "Perk applies to: " + ChatColor.DARK_GREEN + "Human",
//        "",
//        ));

public class PrestigeShop {
    /**
     * Every time /pshop is called in chat, Prestige Shop UI is applied
     *
     * @param p Player using pshop command
     */
    public void applyPShopUI(Player p) {
        //SETUP
        Inventory pgui = Bukkit.createInventory(
                null,45, ChatColor.LIGHT_PURPLE + "Prestige Shop");

        //Juggernaut
        ItemStack p1 = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta p1Meta = p1.getItemMeta();
        assert p1Meta != null;
        p1Meta.setDisplayName(ChatColor.AQUA + "Juggernaut");
        p1Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+ "Costs 2 Prestige Tokens",
                "",
                ChatColor.WHITE + "Perk applies to: " + ChatColor.DARK_GREEN + "Human" + ChatColor.WHITE + " & " + ChatColor.RED + "Zombie",
                "",
                ChatColor.DARK_GREEN + "As Human: " + ChatColor.GRAY + "Receive a Diamond",
                ChatColor.GRAY + "Chestplate instead of an Iron",
                ChatColor.GRAY + "Chestplate." + ChatColor.RED + " As Zombie:" + ChatColor.GRAY + " receive",
                ChatColor.GRAY + "a leggings upgrade on every",
                ChatColor.GRAY+ "zombie kit.",
                "",
                ChatColor.GOLD + "Clicking this will buy the perk",
                ChatColor.GOLD + "and there is no turning back!"));
        p1.setItemMeta(p1Meta);

        //Throwing Knives
        ItemStack p2 = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta p2Meta = p2.getItemMeta();
        assert p2Meta != null;
        p2Meta.setDisplayName(ChatColor.AQUA + "Throwing Knives");
        p2Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+ "Costs 1 Prestige Token",
                "",
                ChatColor.WHITE + "Perk applies to: " + ChatColor.DARK_GREEN + "Human",
                "",
                ChatColor.DARK_GREEN + "As Human: " + ChatColor.GRAY + "Receive 2 throwing knives",
                ChatColor.GRAY + "in every game. Throwing at a target",
                ChatColor.GRAY + "will damage them for 70% of their",
                ChatColor.GRAY + "remaining health.",
                "",
                ChatColor.GOLD + "Clicking this will buy the perk",
                ChatColor.GOLD + "and there is no turning back!"));
        p2.setItemMeta(p2Meta);

        //Tomahawk
        ItemStack p3 = new ItemStack(Material.IRON_AXE);
        ItemMeta p3Meta = p3.getItemMeta();
        assert p3Meta != null;
        p3Meta.setDisplayName(ChatColor.AQUA + "Tomahawk");
        p3Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+ "Costs 1 Prestige Token",
                "",
                ChatColor.WHITE + "Perk applies to: " + ChatColor.DARK_GREEN + "Human",
                "",
                ChatColor.DARK_GREEN + "As Human: " + ChatColor.GRAY + "Receive a tomahawk every",
                ChatColor.GRAY + "game that can be thrown to one-shot",
                ChatColor.GRAY + "any zombie. Has 1 time use.",
                "",
                ChatColor.GOLD + "Clicking this will buy the perk",
                ChatColor.GOLD + "and there is no turning back!"));
        p3.setItemMeta(p3Meta);

        //Knockback
        ItemStack p4 = new ItemStack(Material.STONE_SWORD);
        ItemMeta p4Meta = p4.getItemMeta();
        assert p4Meta != null;
        p4Meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        p4Meta.setDisplayName(ChatColor.AQUA + "Knockback");
        p4Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+ "Costs 1 Prestige Token",
                "",
                ChatColor.WHITE + "Perk applies to: " + ChatColor.DARK_GREEN + "Human",
                "",
                ChatColor.DARK_GREEN + "As Human: " + ChatColor.GRAY + "Receive a duplicate sword",
                ChatColor.GRAY + "that has a knockback 1 enchantment",
                ChatColor.GRAY + "every game.",
                "",
                ChatColor.GOLD + "Clicking this will buy the perk",
                ChatColor.GOLD + "and there is no turning back!"));
        p4.setItemMeta(p4Meta);

        //Feather Falling
        ItemStack p5 = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta p5Meta = p5.getItemMeta();
        assert p5Meta != null;
        p5Meta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
        p5Meta.setDisplayName(ChatColor.AQUA + "Feather Falling");
        p5Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+ "Costs 2 Prestige Tokens",
                "",
                ChatColor.WHITE + "Perk applies to: " + ChatColor.DARK_GREEN + "Human",
                "",
                ChatColor.DARK_GREEN + "As Human: " + ChatColor.GRAY + "Get Feather Falling IV",
                ChatColor.GRAY + "enchantment on your boots.",
                "",
                ChatColor.GOLD + "Clicking this will buy the perk",
                ChatColor.GOLD + "and there is no turning back!"));
        p5.setItemMeta(p5Meta);

        //Demolitionist
        ItemStack p6 = new ItemStack(Material.SLIME_BALL);
        ItemMeta p6Meta = p6.getItemMeta();
        assert p6Meta != null;
        p6Meta.setDisplayName(ChatColor.AQUA + "Demolitionist");
        p6Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+ "Costs 1 Prestige Token",
                "",
                ChatColor.WHITE + "Perk applies to: " + ChatColor.DARK_GREEN + "Human",
                "",
                ChatColor.DARK_GREEN + "As Human: " + ChatColor.GRAY + "Spawn with a random",
                ChatColor.GRAY + "grenade every game.",
                "",
                ChatColor.GOLD + "Clicking this will buy the perk",
                ChatColor.GOLD + "and there is no turning back!"));
        p6.setItemMeta(p6Meta);

        //zPower
        ItemStack p7 = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta p7Meta = p7.getItemMeta();
        assert p7Meta != null;
        p7Meta.setDisplayName(ChatColor.AQUA + "zPower");
        p7Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+ "Costs 2 Prestige Tokens",
                "",
                ChatColor.WHITE + "Perk applies to: " + ChatColor.RED + "Zombie",
                "",
                ChatColor.RED + "As Zombie: " + ChatColor.GRAY + "Get a weapon upgrade",
                ChatColor.GRAY + "to deal half a heart more damage",
                ChatColor.GRAY + "to humans.",
                "",
                ChatColor.GOLD + "Clicking this will buy the perk",
                ChatColor.GOLD + "and there is no turning back!"));
        p7.setItemMeta(p7Meta);

        //Scavenger
        ItemStack p8 = new ItemStack(Material.APPLE);
        ItemMeta p8Meta = p8.getItemMeta();
        assert p8Meta != null;
        p8Meta.setDisplayName(ChatColor.AQUA + "Scavenger");
        p8Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+ "Costs 2 Prestige Tokens",
                "",
                ChatColor.WHITE + "Perk applies to: " + ChatColor.DARK_GREEN + "Human",
                "",
                ChatColor.DARK_GREEN + "As Human: " + ChatColor.GRAY + "have a chance of scavenging",
                ChatColor.GRAY + "items after killing an infected.",
                "",
                ChatColor.GOLD + "Clicking this will buy the perk",
                ChatColor.GOLD + "and there is no turning back!"));
        p8.setItemMeta(p8Meta);

        //ITEMSETTINGS
        pgui.setItem(0, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(1, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(2, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(3, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(4, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(5, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(6, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(7, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(8, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(9, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(18, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(17, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(26, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(27, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(35, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(36, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(37, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(38, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(39, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(40, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(41, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(42, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(43, new ItemStack(Material.GOLD_INGOT));
        pgui.setItem(44, new ItemStack(Material.GOLD_INGOT));

        ItemStack prestige = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta prestigeMeta = prestige.getItemMeta();
        assert prestigeMeta != null;
        prestigeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Prestige");
        List<String> prestigeLore = new ArrayList<>();
        prestigeLore.add(ChatColor.GOLD + "Click to Prestige!");
        prestigeMeta.setLore(prestigeLore);
        prestige.setItemMeta(prestigeMeta);

        pgui.setItem(13, prestige);

        pgui.setItem(20, p1);
        pgui.setItem(21, p2);
        pgui.setItem(22, p3);
        pgui.setItem(23, p4);
        pgui.setItem(24, p5);
        pgui.setItem(29, p6);
        pgui.setItem(30, p7);
        pgui.setItem(31, p8);

        //FINAL
        p.openInventory(pgui);
    }
}
