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

public class PrestigeShop {
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
