package net.mcpandemic.core.voting;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class VoteListener implements Listener {

    /**
     * Handles click events on Vote GUI.
     *
     * @param e
     */
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains(("Vote"))) {
            if (e.getClick().isShiftClick() || e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                e.setCancelled(true);
            }
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {
                    case RED_STAINED_GLASS_PANE:
                        //vote map 1
                        System.out.println(p.toString() + "voted for map 1");
                        p.performCommand("vote 1");
                        p.closeInventory();
                        break;
                    case LIGHT_BLUE_STAINED_GLASS_PANE:
                        //vote map 2
                        System.out.println(p.toString() + "voted for map 2");
                        p.performCommand("vote 2");
                        p.closeInventory();
                        break;
                    case LIME_STAINED_GLASS_PANE:
                        //vote map 3
                        System.out.println(p.toString() + "voted for map 3");
                        p.performCommand("vote 3");
                        p.closeInventory();
                        break;
                    case YELLOW_STAINED_GLASS_PANE:
                        //vote map 4
                        System.out.println(p.toString() + "voted for map 4");
                        p.performCommand("vote 4");
                        p.closeInventory();
                        break;
                    case PINK_STAINED_GLASS_PANE:
                        //vote map 5
                        System.out.println(p.toString() + "voted for map 5");
                        p.performCommand("vote 5");
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
