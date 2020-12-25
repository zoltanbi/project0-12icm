package net.mcpandemic.core;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.HashMap;
import java.util.UUID;


public class Killstreaks {

    public static ItemStack getPotionItemStack(PotionType type, boolean extend, boolean upgraded, String displayName){
        ItemStack potion = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Adrenaline");
        meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0), false);
        //meta.setBasePotionData(new PotionData(type, extend, upgraded));
        potion.setItemMeta(meta);
        return potion;
    }

    public static ItemStack getSplashPotionItemStack(PotionType type, boolean extend, boolean upgraded, String displayName){
        ItemStack potion = new ItemStack(Material.SPLASH_POTION, 1);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN + "Poison O' Zombie");
        meta.setBasePotionData(new PotionData(type, extend, upgraded));
        potion.setItemMeta(meta);
        return potion;
    }

    public static ItemStack getMelon() {
        ItemStack melon = new ItemStack(Material.MELON_SLICE);
        ItemMeta melonMeta = melon.getItemMeta();
        melonMeta.setDisplayName(ChatColor.RED + "Heal 1❤");
        melon.setItemMeta(melonMeta);
        return melon;
    }

    public static ItemStack getApple() {
        ItemStack apple = new ItemStack(Material.APPLE);
        ItemMeta melonMeta = apple.getItemMeta();
        melonMeta.setDisplayName(ChatColor.RED + "Heal 2❤");
        apple.setItemMeta(melonMeta);
        return apple;
    }

    public static ItemStack getChicken() {
        ItemStack chicken = new ItemStack(Material.COOKED_CHICKEN);
        ItemMeta melonMeta = chicken.getItemMeta();
        melonMeta.setDisplayName(ChatColor.RED + "Heal 3❤");
        chicken.setItemMeta(melonMeta);
        return chicken;
    }

    public static ItemStack getPorkchop() {
        ItemStack porkchop = new ItemStack(Material.COOKED_PORKCHOP);
        ItemMeta melonMeta = porkchop.getItemMeta();
        melonMeta.setDisplayName(ChatColor.RED + "Heal 4❤");
        porkchop.setItemMeta(melonMeta);
        return porkchop;
    }

    public static ItemStack getGoldenApple() {
        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta melonMeta = goldenApple.getItemMeta();
        melonMeta.setDisplayName(ChatColor.RED + "Heal FULL❤");
        goldenApple.setItemMeta(melonMeta);
        return goldenApple;
    }

    public static ItemStack getStickyGrenade() {
        ItemStack slime = new ItemStack(Material.SLIME_BALL);
        ItemMeta slimeMeta = slime.getItemMeta();
        slimeMeta.setDisplayName(ChatColor.GREEN + "Sticky Grenade");
        slime.setItemMeta(slimeMeta);
        return slime;
    }

    public static ItemStack getFlashGrenade() {
        ItemStack tear = new ItemStack(Material.GHAST_TEAR);
        ItemMeta tearMeta = tear.getItemMeta();
        tearMeta.setDisplayName(ChatColor.GOLD + "Flash Grenade");
        tear.setItemMeta(tearMeta);
        return tear;
    }

    public static ItemStack getDiamondSword() {
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta tearMeta = diamondSword.getItemMeta();
        tearMeta.setDisplayName(ChatColor.DARK_AQUA + "25 killstreak");
        diamondSword.setItemMeta(tearMeta);
        return diamondSword;
    }

    public static void giveKillstreakReward(Player player, int killstreak) {
        switch(killstreak) {
            case 2:
            case 11:
            case 20:
            case 30:
            case 39:
                player.getInventory().addItem(getMelon());
                break;
            case 3:
            case 12:
            case 21:
            case 31:
            case 40:
                player.getInventory().addItem(getApple());
                break;
            case 4:
            case 13:
            case 22:
            case 32:
            case 41:
                player.getInventory().addItem(getChicken());
                break;
            case 5:
            case 14:
            case 23:
            case 33:
            case 42:
                player.getInventory().addItem(getPorkchop());
                break;
            case 6:
            case 15:
            case 24:
            case 34:
            case 43:
                player.getInventory().addItem(getGoldenApple());
                break;
            case 7:
            case 16:
            case 26:
            case 35:
            case 44:
                player.getInventory().addItem(getStickyGrenade());
                break;
            case 8:
            case 17:
            case 27:
            case 36:
            case 45:
                player.getInventory().addItem(getFlashGrenade());
                break;
            case 9:
            case 18:
            case 28:
            case 37:
            case 46:
                player.getInventory().addItem(getPotionItemStack(PotionType.STRENGTH, false, false, ChatColor.RED + "Strength"));
                break;
            case 10:
            case 19:
            case 29:
            case 38:
            case 47:
                player.getInventory().addItem(getSplashPotionItemStack(PotionType.POISON, false, false, ChatColor.RED + "Poison"));
                break;
            case 25:
                player.getInventory().addItem(getDiamondSword());
        }
    }

}
