package net.mcpandemic.core.kits.infectedtypes;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class KitMotherZombie extends Kit {

    private static DisguiseType disguise;

    public KitMotherZombie(UUID uuid) {
        super(uuid, KitType.MOTHERZOMBIE);
        disguise = DisguiseType.ZOMBIFIED_PIGLIN;
    }

    @Override
    public void onStart(Player player) {
        //clear
        player.getInventory().clear();

        //weapon
        player.getInventory().addItem(new ItemStack(Material.NETHERITE_AXE));


        //grenades

        //prestige items

        //armor
        player.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
    }

    public static DisguiseType getDisguise() {
        return disguise;
    }
}
