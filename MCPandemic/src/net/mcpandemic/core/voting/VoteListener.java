package net.mcpandemic.core.voting;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class VoteListener {
    public VoteListener() {
    }

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
        else if (!e.getView().getTitle().contains(("Prestige"))) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {
                    case IRON_SWORD:
                        //use rankUp command on player

                        break;
                    case APPLE:
                        p.getInventory().addItem(new ItemStack(Material.APPLE));

                        break;
                    case MELON_SLICE:
                        p.getInventory().addItem(new ItemStack(Material.MELON_SLICE));

                        break;
                    case PORKCHOP:
                        p.getInventory().addItem(new ItemStack(Material.PORKCHOP));

                        break;
                    case CHICKEN:
                        p.getInventory().addItem(new ItemStack(Material.CHICKEN));

                        break;
                    case SLIME_BALL:
                        //modify to slow grenade
                        p.getInventory().addItem(new ItemStack(Material.SLIME_BALL));

                        break;
                    case SNOWBALL:
                        //modify to blind grenade
                        p.getInventory().addItem(new ItemStack(Material.SNOWBALL));

                        break;
                    case MAGMA_CREAM:
                        //modify to fire grenade
                        p.getInventory().addItem(new ItemStack(Material.MAGMA_CREAM));

                        break;
                    case EGG:
                        //modify to frag grenade
                        p.getInventory().addItem(new ItemStack(Material.EGG));

                        break;
                    case CHEST:
                        //modify to mystery chest
                        p.getInventory().addItem(new ItemStack(Material.CHEST));

                        break;
                    default:
                        return;
                }

                p.closeInventory();
            }
        }
    }

}
