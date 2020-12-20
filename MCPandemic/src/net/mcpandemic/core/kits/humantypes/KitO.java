package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.Manager;
import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class KitO extends Kit {

    //15
    public KitO(UUID uuid) {
        super(uuid, KitType.O);
    }

    @Override
    public void onStart(Player player) {

        //clear
        player.getInventory().clear();
        //sword and shield
        player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
        player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
        //grenades
        ItemStack slime = new ItemStack(Material.SLIME_BALL, 1);
        ItemMeta slimeMeta = slime.getItemMeta();
        slimeMeta.setDisplayName(ChatColor.GREEN + "Sticky Grenade");
        slime.setItemMeta(slimeMeta);

        ItemStack tear = new ItemStack(Material.GHAST_TEAR, 1);
        ItemMeta tearMeta = tear.getItemMeta();
        tearMeta.setDisplayName(ChatColor.GOLD + "Flash Grenade");
        tear.setItemMeta(tearMeta);

        player.getInventory().addItem(tear);
        player.getInventory().addItem(slime);
        //food
        ItemStack apple = new ItemStack(Material.APPLE, 5);
        ItemMeta appleMeta = apple.getItemMeta();
        appleMeta.setDisplayName(ChatColor.RED + "Heal 2❤");
        apple.setItemMeta(appleMeta);

        ItemStack melon = new ItemStack(Material.MELON_SLICE);
        ItemMeta melonMeta = melon.getItemMeta();
        melonMeta.setDisplayName(ChatColor.RED + "Heal 1❤");
        melon.setItemMeta(melonMeta);

        player.getInventory().addItem(apple);
        //prestige items
        //bow
        player.getInventory().addItem(new ItemStack(Material.BOW));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 5));
        //armor
        player.getInventory().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
    }
}
