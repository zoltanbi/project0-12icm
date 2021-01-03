package net.mcpandemic.core.shops;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PShopListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains(("Prestige"))) {
            if (e.getClick().isShiftClick() || e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                e.setCancelled(true);
            }
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {
                    case GOLD_BLOCK:
                        //prestige

                        break;
                    case DIAMOND_CHESTPLATE:
                        //juggernaut

                        break;
                    case TRIPWIRE_HOOK:
                        //throwing knives

                        break;
                    case IRON_AXE:
                        //tomahawk

                        break;
                    case STONE_SWORD:
                        //knockback

                        break;
                    case NETHERITE_BOOTS:
                        //Feather falling

                        break;
                    case SLIME_BALL:
                        //demolotionist

                        break;
                    case NETHERITE_AXE:
                        //zPower

                        break;
                    case APPLE:
                        //scavenger

                        break;
                    default:
                        return;
                }

                p.closeInventory();
            }
        }
    }
}
