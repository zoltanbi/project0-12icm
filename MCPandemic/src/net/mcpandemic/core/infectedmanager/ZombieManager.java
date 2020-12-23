package net.mcpandemic.core.infectedmanager;

import net.mcpandemic.core.kits.infectedtypes.KitMotherZombie;
import net.mcpandemic.core.kits.infectedtypes.KitSkeleton;
import net.mcpandemic.core.kits.infectedtypes.KitZombie;
import org.bukkit.entity.Player;

public class ZombieManager {

    public static void playerZombieSetup(Player player) {
        switch(DatabaseManager.getInfectedKit(player)) {
            case ZOMBIE:
                new KitZombie(player.getUniqueId()).onStart(player);
                DisguiseManager.setZombieDisguise(player);
                break;
            case MOTHERZOMBIE:
                new KitMotherZombie(player.getUniqueId()).onStart(player);
                DisguiseManager.setZombieDisguise(player);
                break;
            case SKELETON:
                new KitSkeleton(player.getUniqueId()).onStart(player);
                DisguiseManager.setZombieDisguise(player);
                break;
        }
    }

}
