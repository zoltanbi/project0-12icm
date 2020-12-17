package com.roberto.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class main extends JavaPlugin {

    /**
     * Runs on plugin start
     */
    @Override
    public void onEnable() {
        System.out.println("Shop GUIS plugin enabled.");
        getCommand("shop").setExecutor(new ShopCommand(this));
        getCommand("pshop").setExecutor(new PShopCommand(this));
    }

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
        h1Meta.setDisplayName(ChatColor.GREEN + "Healing 1");
        heal1.setItemMeta(h1Meta);

        ItemStack heal2 = new ItemStack(Material.MELON_SLICE);
        ItemMeta h2Meta = heal2.getItemMeta();
        assert h2Meta != null;
        h2Meta.setDisplayName(ChatColor.GREEN + "Healing 2");
        heal2.setItemMeta(h2Meta);

        ItemStack heal3 = new ItemStack(Material.PORKCHOP);
        ItemMeta h3Meta = heal3.getItemMeta();
        assert h3Meta != null;
        h3Meta.setDisplayName(ChatColor.GREEN + "Healing 3");
        heal3.setItemMeta(h3Meta);

        ItemStack heal4 = new ItemStack(Material.CHICKEN);
        ItemMeta h4Meta = heal4.getItemMeta();
        assert h4Meta != null;
        h4Meta.setDisplayName(ChatColor.GREEN + "Healing 4");
        heal4.setItemMeta(h4Meta);

        ItemStack sGrenade = new ItemStack(Material.SLIME_BALL);
        ItemMeta sGrenadeMeta = sGrenade.getItemMeta();
        assert sGrenadeMeta != null;
        sGrenadeMeta.setDisplayName(ChatColor.GREEN + "Slow Grenade");
        sGrenade.setItemMeta(sGrenadeMeta);

        ItemStack bGrenade = new ItemStack(Material.SNOWBALL);
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

    /**
     * Every time /pshop is called in chat, Prestige Shop UI is applied
     *
     * @param p Player using pshop command
     */
    public void applyPShopUI(Player p) {
        //SETUP
        Inventory pgui = Bukkit.createInventory(
                null,27, ChatColor.DARK_GRAY + "Prestige Shop");


        //LISTS
        List<String> fStandLore = new ArrayList<>();
        fStandLore.add(ChatColor.GRAY + "Click to get Final Stand!");

        List<String> tKnivesLore = new ArrayList<>();
        tKnivesLore.add(ChatColor.GRAY + "Click to get Throwing Knives!");

        List<String> hLineLore = new ArrayList<>();
        hLineLore.add(ChatColor.GRAY + "Click to get Hardline!");

        List<String> kBackLore = new ArrayList<>();
        kBackLore.add(ChatColor.GRAY + "Click to get KickBack!");

        List<String> fFallLore = new ArrayList<>();
        fFallLore.add(ChatColor.GRAY + "Click to get Feather Falling!");

        List<String> zLore = new ArrayList<>();
        zLore.add(ChatColor.GRAY + "Click to get zPower!");

        List<String> scavLore = new ArrayList<>();
        scavLore.add(ChatColor.GRAY + "Click to get Scavenger!");

        List<String> blastLore = new ArrayList<>();
        blastLore.add(ChatColor.GRAY + "Click to get Blast Shield!");

        List<String> demoLore = new ArrayList<>();
        demoLore.add(ChatColor.GRAY + "Click to get Demolitionist!");

        List<String> juggLore = new ArrayList<>();
        juggLore.add(ChatColor.GRAY + "Click to get Juggernaut!");

        List<String> dTapLore = new ArrayList<>();
        dTapLore.add(ChatColor.GRAY + "Click to get Double Tap!");

        //ITEMSTACKS
        ItemStack juggernaut = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta juggMeta = juggernaut.getItemMeta();
        assert juggMeta != null;
        juggMeta.setDisplayName(ChatColor.BLUE + "Juggernaut");
        juggernaut.setItemMeta(juggMeta);

        ItemStack hardline = new ItemStack(Material.MELON_SLICE);
        ItemMeta hardlineMeta = hardline.getItemMeta();
        assert hardlineMeta != null;
        hardlineMeta.setDisplayName(ChatColor.BLUE + "HardLine");
        hardline.setItemMeta(hardlineMeta);

        ItemStack knockback = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta knockMeta = juggernaut.getItemMeta();
        assert knockMeta != null;
        knockMeta.setDisplayName(ChatColor.BLUE + "Knockback");
        knockback.setItemMeta(knockMeta);

        ItemStack fFalling = new ItemStack(Material.IRON_BOOTS);
        ItemMeta fallingMeta = fFalling.getItemMeta();
        assert fallingMeta != null;
        fallingMeta.setDisplayName(ChatColor.BLUE + "Feather Falling");
        fFalling.setItemMeta(fallingMeta);

        ItemStack zPower = new ItemStack(Material.STONE_SWORD);
        ItemMeta zMeta = zPower.getItemMeta();
        assert zMeta != null;
        zMeta.setDisplayName(ChatColor.BLUE + "ZPower");
        zPower.setItemMeta(zMeta);

        ItemStack scavenger = new ItemStack(Material.APPLE);
        ItemMeta scavengerMeta = scavenger.getItemMeta();
        assert scavengerMeta != null;
        scavengerMeta.setDisplayName(ChatColor.BLUE + "Scavenger");
        scavenger.setItemMeta(scavengerMeta);

        ItemStack bShield = new ItemStack(Material.IRON_DOOR);
        ItemMeta bShieldMeta = bShield.getItemMeta();
        assert bShieldMeta != null;
        bShieldMeta.setDisplayName(ChatColor.BLUE + "Blast Shield");
        bShield.setItemMeta(bShieldMeta);

        ItemStack demolitionist = new ItemStack(Material.SLIME_BALL);
        ItemMeta dMeta = demolitionist.getItemMeta();
        assert dMeta != null;
        dMeta.setDisplayName(ChatColor.BLUE + "Demolitionist");
        demolitionist.setItemMeta(dMeta);

        ItemStack doubleTap = new ItemStack(Material.ARROW);
        ItemMeta dTapMeta = doubleTap.getItemMeta();
        assert fallingMeta != null;
        dTapMeta.setDisplayName(ChatColor.BLUE + "Double Tap");
        doubleTap.setItemMeta(dTapMeta);

        ItemStack finalStand = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta fStandMeta = finalStand.getItemMeta();
        assert fStandMeta != null;
        fStandMeta.setDisplayName(ChatColor.BLUE + "Final Stand");
        finalStand.setItemMeta(fStandMeta);

        ItemStack tKnives = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta tKMeta = tKnives.getItemMeta();
        assert tKMeta != null;
        tKMeta.setDisplayName(ChatColor.BLUE + "Throwing Knives");
        tKnives.setItemMeta(tKMeta);

        //ITEMSETTINGS
        pgui.setItem(2,finalStand);
        pgui.setItem(3,tKnives);
        pgui.setItem(4,hardline);
        pgui.setItem(5,knockback);
        pgui.setItem(6,fFalling);
        pgui.setItem(11,zPower);
        pgui.setItem(12,scavenger);
        pgui.setItem(13,bShield);
        pgui.setItem(14,demolitionist);
        pgui.setItem(15,juggernaut);
        pgui.setItem(20,doubleTap);

        //filler gold blocks for prestige shop aesthetic
        pgui.setItem(0,new ItemStack(Material.GOLD_BLOCK));
        pgui.setItem(8,new ItemStack(Material.GOLD_BLOCK));
        pgui.setItem(9,new ItemStack(Material.GOLD_BLOCK));
        pgui.setItem(17,new ItemStack(Material.GOLD_BLOCK));
        pgui.setItem(18,new ItemStack(Material.GOLD_BLOCK));
        pgui.setItem(26,new ItemStack(Material.GOLD_BLOCK));

        //FINAL
        p.openInventory(pgui);
    }
}
