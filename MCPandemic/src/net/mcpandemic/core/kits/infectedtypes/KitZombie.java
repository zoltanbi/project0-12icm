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

public class KitZombie extends Kit {

    private static DisguiseType disguise;

    public KitZombie(UUID uuid) {
        super(uuid, KitType.ZOMBIE);
        disguise = DisguiseType.ZOMBIE;
    }

    @Override
    public void onStart(Player player) {
        //clear
        player.getInventory().clear();

        //weapon
        setZombieWeapon(player);
        //grenades

        //prestige items

        //armor
        setChainHelmet(player);
        setLeatherChest(player);
        setChainPants(player);
        setChainBoots(player);
    }

    public static DisguiseType getDisguise() {
        return disguise;
    }
}
