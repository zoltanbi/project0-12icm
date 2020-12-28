package net.mcpandemic.core.shops;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PShopListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains(("Prestige"))) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {
                    case LEATHER_CHESTPLATE:
                        //final stand

                        break;
                    case TRIPWIRE_HOOK:
                        //throwing knives

                        break;
                    case MELON_SLICE:
                        //hardline

                        break;
                    case WOODEN_SWORD:
                        //knockback

                        break;
                    case IRON_BOOTS:
                        //Feather falling

                        break;
                    case STONE_SWORD:
                        //zpower

                        break;
                    case APPLE:
                        //Scavenger

                        break;
                    case IRON_DOOR:
                        //Blast Shield

                        break;
                    case SLIME_BALL:
                        //Demolitionist

                        break;
                    case CHAINMAIL_CHESTPLATE:
                        //Juggernaut

                        break;
                    case ARROW:
                        //Double Tap

                        break;
                    default:
                        return;
                }

                p.closeInventory();
            }
        }
    }
}
