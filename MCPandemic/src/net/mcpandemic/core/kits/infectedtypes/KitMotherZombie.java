package net.mcpandemic.core.kits.infectedtypes;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import static net.mcpandemic.core.kits.infectedtypes.InfectedItems.*;

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
        setMotherZombieWeapon(player);


        //grenades

        //prestige items

        //armor
        setChainHelmet(player);
        setChainChest(player);
        setChainPants(player);
        setChainBoots(player);
    }

    public static DisguiseType getDisguise() {
        return disguise;
    }
}
