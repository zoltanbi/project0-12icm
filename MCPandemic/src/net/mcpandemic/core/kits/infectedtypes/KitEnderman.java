package net.mcpandemic.core.kits.infectedtypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class KitEnderman extends Kit {

    public KitEnderman(UUID uuid, KitType type) {
        super(uuid, type);
    }

    @Override
    public void onStart(Player player) {
        //clear
        player.getInventory().clear();

        //weapon
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));


        //grenades

        //prestige items
    }
}
