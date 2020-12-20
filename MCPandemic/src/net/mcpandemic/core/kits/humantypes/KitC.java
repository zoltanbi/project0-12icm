package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class KitC extends Kit {

    //3
    public KitC(UUID uuid) {
        super(uuid, KitType.C);
    }

    @Override
    public void onStart(Player player) {
        //clear
        player.getInventory().clear();
        //sword and shield
        player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
        player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
        //grenades
        //food
        ItemStack apple = new ItemStack(Material.APPLE, 2);
        ItemMeta appleMeta = apple.getItemMeta();
        appleMeta.setDisplayName(ChatColor.RED + "Heal 2❤");
        apple.setItemMeta(appleMeta);

        ItemStack melon = new ItemStack(Material.MELON_SLICE);
        ItemMeta melonMeta = melon.getItemMeta();
        melonMeta.setDisplayName(ChatColor.RED + "Heal 1❤");
        melon.setItemMeta(melonMeta);

        player.getInventory().addItem(apple);
        //prestige items
        //armor
        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
    }
}