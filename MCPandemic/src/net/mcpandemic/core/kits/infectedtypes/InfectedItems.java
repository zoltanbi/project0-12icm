package net.mcpandemic.core.kits.infectedtypes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InfectedItems {

    //weapons
    public static void setMotherZombieWeapon(Player player) {
        ItemStack weapon = new ItemStack(Material.GOLDEN_AXE);
        ItemMeta weaponMeta = weapon.getItemMeta();
        weaponMeta.setUnbreakable(true);
        weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        weapon.setItemMeta(weaponMeta);

        player.getInventory().addItem(weapon);
    }

    public static void setZombieWeapon(Player player) {
        ItemStack weapon = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta weaponMeta = weapon.getItemMeta();
        weaponMeta.setUnbreakable(true);
        weapon.setItemMeta(weaponMeta);

        player.getInventory().addItem(weapon);
    }

    public static void setSkeletonWeapon(Player player) {
        ItemStack weapon = new ItemStack(Material.WOODEN_AXE);
        ItemMeta weaponMeta = weapon.getItemMeta();
        weaponMeta.setUnbreakable(true);
        weapon.setItemMeta(weaponMeta);

        ItemStack weapon2 = new ItemStack(Material.BOW);
        ItemMeta weapon2Meta = weapon2.getItemMeta();
        weapon2Meta.setUnbreakable(true);
        weapon2Meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        weapon2.setItemMeta(weapon2Meta);

        player.getInventory().addItem(weapon);
        player.getInventory().addItem(weapon2);
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

}
