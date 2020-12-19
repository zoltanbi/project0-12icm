package net.mcpandemic.core.voting;

import net.mcpandemic.core.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class VoteListener implements Listener {
    public VoteListener(Main main) {

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains(("Vote"))) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {
                    case RED_STAINED_GLASS_PANE:
                        System.out.println("1st Map Picked");
                        e.setCancelled(true);
                        break;
                    case YELLOW_STAINED_GLASS_PANE:
                        System.out.println("2nd Map Picked");
                        e.setCancelled(true);
                        break;
                    case LIME_STAINED_GLASS_PANE:
                        System.out.println("3rd Map Picked");
                        e.setCancelled(true);
                        break;
                    case LIGHT_BLUE_STAINED_GLASS_PANE:
                        System.out.println("4th Map Picked");
                        e.setCancelled(true);
                        break;
                    case PINK_STAINED_GLASS_PANE:
                        System.out.println("5th Map Picked");
                        e.setCancelled(true);
                        break;
                    default:
                        return;
                }

                p.closeInventory();
            }
        }
    }

}
