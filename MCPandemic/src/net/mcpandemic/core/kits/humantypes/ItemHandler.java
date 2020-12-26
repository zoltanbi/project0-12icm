package net.mcpandemic.core.kits.humantypes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemHandler {

    //weapons
    public static void setStoneSword(Player player) {
        ItemStack weapon = new ItemStack(Material.STONE_SWORD);
        ItemMeta weaponMeta = weapon.getItemMeta();
        weaponMeta.setUnbreakable(true);
        weapon.setItemMeta(weaponMeta);

        player.getInventory().addItem(weapon);
    }
    public static void setIronSword(Player player) {
        ItemStack weapon = new ItemStack(Material.IRON_SWORD);
        ItemMeta weaponMeta = weapon.getItemMeta();
        weaponMeta.setUnbreakable(true);
        weapon.setItemMeta(weaponMeta);

        player.getInventory().addItem(weapon);
    }

    public static void setBow(Player player) {
        ItemStack weapon = new ItemStack(Material.BOW);
        ItemMeta weaponMeta = weapon.getItemMeta();
        weaponMeta.setUnbreakable(true);
        weapon.setItemMeta(weaponMeta);

        player.getInventory().addItem(weapon);
    }

    public static void setArrows(Player player, int n) {
        ItemStack arrows = new ItemStack(Material.ARROW, n);
        ItemMeta arrMeta = arrows.getItemMeta();
        arrMeta.setUnbreakable(true);
        arrows.setItemMeta(arrMeta);

        player.getInventory().addItem(arrows);
    }

    public static void setShield(Player player) {
        ItemStack shield = new ItemStack(Material.SHIELD);
        ItemMeta shieldMeta = shield.getItemMeta();
        shieldMeta.setUnbreakable(true);
        shield.setItemMeta(shieldMeta);

        player.getInventory().setItemInOffHand(shield);
    }

    //armor
    public static void setLeatherHelmet(Player player) {
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.setUnbreakable(true);
        helmet.setItemMeta(helmetMeta);

        player.getInventory().setHelmet(helmet);
    }
    public static void setLeatherChest(Player player) {
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setUnbreakable(true);
        chest.setItemMeta(chestMeta);

        player.getInventory().setChestplate(chest);
    }
    public static void setLeatherPants(Player player) {
        ItemStack pants = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta pantsMeta = pants.getItemMeta();
        pantsMeta.setUnbreakable(true);
        pants.setItemMeta(pantsMeta);

        player.getInventory().setLeggings(pants);
    }
    public static void setLeatherBoots(Player player) {
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.setUnbreakable(true);
        boots.setItemMeta(bootsMeta);

        player.getInventory().setBoots(boots);
    }

    public static void setChainHelmet(Player player) {
        ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.setUnbreakable(true);
        helmet.setItemMeta(helmetMeta);

        player.getInventory().setHelmet(helmet);
    }
    public static void setChainChest(Player player) {
        ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setUnbreakable(true);
        chest.setItemMeta(chestMeta);

        player.getInventory().setChestplate(chest);
    }
    public static void setChainPants(Player player) {
        ItemStack pants = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta pantsMeta = pants.getItemMeta();
        pantsMeta.setUnbreakable(true);
        pants.setItemMeta(pantsMeta);

        player.getInventory().setLeggings(pants);
    }
    public static void setChainBoots(Player player) {
        ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.setUnbreakable(true);
        boots.setItemMeta(bootsMeta);

        player.getInventory().setBoots(boots);
    }

    public static void setIronHelmet(Player player) {
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.setUnbreakable(true);
        helmet.setItemMeta(helmetMeta);

        player.getInventory().setHelmet(helmet);
    }
    public static void setIronChest(Player player) {
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setUnbreakable(true);
        chest.setItemMeta(chestMeta);

        player.getInventory().setChestplate(chest);
    }
    public static void setIronPants(Player player) {
        ItemStack pants = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta pantsMeta = pants.getItemMeta();
        pantsMeta.setUnbreakable(true);
        pants.setItemMeta(pantsMeta);

        player.getInventory().setLeggings(pants);
    }
    public static void setIronBoots(Player player) {
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.setUnbreakable(true);
        boots.setItemMeta(bootsMeta);

        player.getInventory().setBoots(boots);
    }

    public static void setNetheriteHelmet(Player player) {
        ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.setUnbreakable(true);
        helmet.setItemMeta(helmetMeta);

        player.getInventory().setHelmet(helmet);
    }
    public static void setNetheriteChest(Player player) {
        ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setUnbreakable(true);
        chest.setItemMeta(chestMeta);

        player.getInventory().setChestplate(chest);
    }
    public static void setNetheritePants(Player player) {
        ItemStack pants = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta pantsMeta = pants.getItemMeta();
        pantsMeta.setUnbreakable(true);
        pants.setItemMeta(pantsMeta);

        player.getInventory().setLeggings(pants);
    }
    public static void setNetheriteBoots(Player player) {
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.setUnbreakable(true);
        boots.setItemMeta(bootsMeta);

        player.getInventory().setBoots(boots);
    }

    //food and grenades
    public static void setSlime(Player player, int n) {
        ItemStack slime = new ItemStack(Material.SLIME_BALL, n);
        ItemMeta slimeMeta = slime.getItemMeta();
        slimeMeta.setDisplayName(ChatColor.GREEN + "Sticky Grenade");
        slime.setItemMeta(slimeMeta);

        player.getInventory().addItem(slime);
    }

    public static void setFlash(Player player, int n) {
        ItemStack tear = new ItemStack(Material.GHAST_TEAR, n);
        ItemMeta tearMeta = tear.getItemMeta();
        tearMeta.setDisplayName(ChatColor.GOLD + "Flash Grenade");
        tear.setItemMeta(tearMeta);

        player.getInventory().addItem(tear);
    }

    public static void setApple(Player player, int n) {
        ItemStack apple = new ItemStack(Material.APPLE, n);
        ItemMeta appleMeta = apple.getItemMeta();
        appleMeta.setDisplayName(ChatColor.RED + "Heal 2❤");
        apple.setItemMeta(appleMeta);

        player.getInventory().addItem(apple);
    }

    public static void setMelon(Player player, int n) {
        ItemStack melon = new ItemStack(Material.MELON_SLICE, n);
        ItemMeta melonMeta = melon.getItemMeta();
        melonMeta.setDisplayName(ChatColor.RED + "Heal 1❤");
        melon.setItemMeta(melonMeta);

        player.getInventory().addItem(melon);
    }
}
