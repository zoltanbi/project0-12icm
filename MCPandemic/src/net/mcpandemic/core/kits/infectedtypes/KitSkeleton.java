package net.mcpandemic.core.kits.infectedtypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class KitSkeleton extends Kit {

    public KitSkeleton(UUID uuid, KitType type) {
        super(uuid, type);
    }

    @Override
    public void onStart(Player player) {
        //clear
        player.getInventory().clear();

        //weapon
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_AXE));
        player.getInventory().addItem(new ItemStack(Material.BOW));
        player.getInventory().addItem(new ItemStack(Material.ARROW));

        //grenades

        //prestige items

        //armor
        player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
    }
}