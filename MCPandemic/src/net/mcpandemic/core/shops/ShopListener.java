package net.mcpandemic.core.shops;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

import static net.mcpandemic.core.infectedmanager.DatabaseManager.getRankPoints;
import static net.mcpandemic.core.infectedmanager.DatabaseManager.setRankPoints;
import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;

public class ShopListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains(("Shop"))) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {
                    case IRON_SWORD:
                        p.performCommand("rankup");

                        break;
                    case APPLE:
                        try {
                            int rankPoints = getRankPoints(p.getUniqueId());

                            if (rankPoints >= 20) {
                                setRankPoints(p.getUniqueId(), rankPoints - 20);
                                setApple(p, 1);
                            }

                        } catch(SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    case MELON_SLICE:
                        try {
                            int rankPoints = getRankPoints(p.getUniqueId());

                            if (rankPoints >= 10) {
                                setRankPoints(p.getUniqueId(), rankPoints - 10);
                                setMelon(p, 1);
                            }

                        } catch(SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    case PORKCHOP:
                        try {
                            int rankPoints = getRankPoints(p.getUniqueId());

                            if (rankPoints >= 40) {
                                setRankPoints(p.getUniqueId(), rankPoints - 40);
                                setPork(p, 1);
                            }

                        } catch(SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    case CHICKEN:
                        try {
                            int rankPoints = getRankPoints(p.getUniqueId());

                            if (rankPoints >= 30) {
                                setRankPoints(p.getUniqueId(), rankPoints - 30);
                                setChicken(p, 1);
                            }

                        } catch(SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    case SLIME_BALL:
                        try {
                            int rankPoints = getRankPoints(p.getUniqueId());

                            if (rankPoints >= 50) {
                                setRankPoints(p.getUniqueId(), rankPoints - 50);
                                setSlime(p, 1);
                            }

                        } catch(SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    case GHAST_TEAR:
                        try {
                            int rankPoints = getRankPoints(p.getUniqueId());

                            if (rankPoints >= 50) {
                                setRankPoints(p.getUniqueId(), rankPoints - 50);
                                setFlash(p, 1);
                            }

                        } catch(SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    case MAGMA_CREAM:
                        try {
                            int rankPoints = getRankPoints(p.getUniqueId());

                            if (rankPoints >= 50) {
                                setRankPoints(p.getUniqueId(), rankPoints - 50);
                                setFire(p, 1);
                            }

                        } catch(SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    case EGG:
                        try {
                            int rankPoints = getRankPoints(p.getUniqueId());

                            if (rankPoints >= 50) {
                                setRankPoints(p.getUniqueId(), rankPoints - 50);
                                setFrag(p, 1);
                            }

                        } catch(SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    case CHEST:
                        try {
                            int rankPoints = getRankPoints(p.getUniqueId());

                            if (rankPoints >= 200) {
                                setRankPoints(p.getUniqueId(), rankPoints - 200);
                                p.getInventory().addItem(new ItemStack(Material.CHEST));
                            }

                        } catch(SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    default:
                        return;
                }

                p.closeInventory();
            }
        }
    }
}
