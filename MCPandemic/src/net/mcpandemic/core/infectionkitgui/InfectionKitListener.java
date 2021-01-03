package net.mcpandemic.core.infectionkitgui;

import net.mcpandemic.core.Manager;
import net.mcpandemic.core.kits.KitType;
import net.mcpandemic.core.infectedmanager.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import static net.mcpandemic.core.infectedmanager.DatabaseManager.getInfectedKit;

public class InfectionKitListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains(("Infected Kits"))) {
            if (e.getClick().isShiftClick() || e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                e.setCancelled(true);
            }
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {
                    case ZOMBIE_HEAD:
                        if (DatabaseManager.getInfectedKit(p) == KitType.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag()
                                    + ChatColor.DARK_GREEN + "Zombie Kit Already Equipped!");
                        } else {
                            p.sendMessage(Manager.getServerTag()
                                    + ChatColor.DARK_GREEN + "Zombie Kit Equipped.");
                            DatabaseManager.setInfectedKit(p, KitType.ZOMBIE);
                        }

                        p.closeInventory();
                        break;
                    case SKELETON_SKULL:
                        if (getInfectedKit(p) == KitType.SKELETON) {
                            p.sendMessage(Manager.getServerTag()
                                    + ChatColor.DARK_GRAY + "Skeleton Kit Already Equipped!");
                        } else {
                            p.sendMessage(Manager.getServerTag()
                                    + ChatColor.DARK_GRAY + "Skeleton Kit Equipped.");
                            DatabaseManager.setInfectedKit(p, KitType.SKELETON);
                        }
                        p.closeInventory();
                        break;
                    default:
                        return;
                }

                p.closeInventory();
            }
        }
    }
}
