package net.mcpandemic.core.shops;

import net.mcpandemic.core.Manager;
import net.mcpandemic.core.gamestates.GameState;
import net.mcpandemic.core.teams.Team;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
<<<<<<< HEAD
import org.bukkit.event.inventory.InventoryAction;
=======
import org.bukkit.event.inventory.ClickType;
>>>>>>> 51e034362e7951fbc76a10b409ba10212cbdd63b
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

import static net.mcpandemic.core.infectedmanager.DatabaseManager.getRankPoints;
import static net.mcpandemic.core.infectedmanager.DatabaseManager.setRankPoints;
import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;

public class ShopListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClick() == ClickType.SHIFT_LEFT ||e.getClick() == ClickType.SHIFT_RIGHT ) {
            e.setCancelled(true);
        }

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains(("Rankpoints"))) {
            if (e.getClick().isShiftClick() || e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                e.setCancelled(true);
            }
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {
                    case IRON_SWORD:
                        p.performCommand("rankup");

                        break;
                    case APPLE:
                        if (Manager.getArena().getState() != GameState.INFECTION) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be in-game to buy that!");
                        } else if (Manager.getArena().getState() == GameState.INFECTION && Manager.getArena().getTeam(p) == Team.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be a " + ChatColor.GREEN + "Human" + ChatColor.RED + " to buy that!");
                        } else {
                            try {
                                int rankPoints = getRankPoints(p.getUniqueId());

                                if (rankPoints >= 20) {
                                    setRankPoints(p.getUniqueId(), -20);
                                    setApple(p, 1);
                                } else {
                                    p.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                            + (20 - rankPoints) + ChatColor.GREEN + " more Rankpoints to buy that!");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }

                        break;
                    case MELON_SLICE:
                        if (Manager.getArena().getState() != GameState.INFECTION) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be in-game to buy that!");
                        } else if (Manager.getArena().getState() == GameState.INFECTION && Manager.getArena().getTeam(p) == Team.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be a " + ChatColor.GREEN + "Human" + ChatColor.RED + " to buy that!");
                        } else {
                            try {
                                int rankPoints = getRankPoints(p.getUniqueId());

                                if (rankPoints >= 10) {
                                    setRankPoints(p.getUniqueId(), -10);
                                    setMelon(p, 1);
                                } else {
                                    p.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                            + (10 - rankPoints) + ChatColor.GREEN + " more Rankpoints to buy that!");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }

                        break;
                    case COOKED_PORKCHOP:
                        if (Manager.getArena().getState() != GameState.INFECTION) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be in-game to buy that!");
                        } else if (Manager.getArena().getState() == GameState.INFECTION && Manager.getArena().getTeam(p) == Team.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be a " + ChatColor.GREEN + "Human" + ChatColor.RED + " to buy that!");
                        } else {
                            try {
                                int rankPoints = getRankPoints(p.getUniqueId());

                                if (rankPoints >= 40) {
                                    setRankPoints(p.getUniqueId(), -40);
                                    setPork(p, 1);
                                } else {
                                    p.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                            + (40 - rankPoints) + ChatColor.GREEN + " more Rankpoints to buy that!");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }

                        break;
                    case COOKED_CHICKEN:
                        if (Manager.getArena().getState() != GameState.INFECTION) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be in-game to buy that!");
                        } else if (Manager.getArena().getState() == GameState.INFECTION && Manager.getArena().getTeam(p) == Team.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be a " + ChatColor.GREEN + "Human" + ChatColor.RED + " to buy that!");
                        } else {
                            try {
                                int rankPoints = getRankPoints(p.getUniqueId());

                                if (rankPoints >= 30) {
                                    setRankPoints(p.getUniqueId(), -30);
                                    setChicken(p, 1);
                                } else {
                                    p.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                            + (30 - rankPoints) + ChatColor.GREEN + " more Rankpoints to buy that!");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }

                        break;
                    case SLIME_BALL:
                        if (Manager.getArena().getState() != GameState.INFECTION) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be in-game to buy that!");
                        } else if (Manager.getArena().getState() == GameState.INFECTION && Manager.getArena().getTeam(p) == Team.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be a " + ChatColor.GREEN + "Human" + ChatColor.RED + " to buy that!");
                        } else {
                            try {
                                int rankPoints = getRankPoints(p.getUniqueId());

                                if (rankPoints >= 35) {
                                    setRankPoints(p.getUniqueId(), -35);
                                    setSlime(p, 1);
                                } else {
                                    p.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                            + (35 - rankPoints) + ChatColor.GREEN + " more Rankpoints to buy that!");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }

                        break;
                    case GHAST_TEAR:
                        if (Manager.getArena().getState() != GameState.INFECTION) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be in-game to buy that!");
                        } else if (Manager.getArena().getState() == GameState.INFECTION && Manager.getArena().getTeam(p) == Team.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be a " + ChatColor.GREEN + "Human" + ChatColor.RED + " to buy that!");
                        } else {
                            try {
                                int rankPoints = getRankPoints(p.getUniqueId());

                                if (rankPoints >= 35) {
                                    setRankPoints(p.getUniqueId(), -35);
                                    setFlash(p, 1);
                                } else {
                                    p.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                            + (35 - rankPoints) + ChatColor.GREEN + " more Rankpoints to buy that!");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }

                        break;
                    case MAGMA_CREAM:
                        if (Manager.getArena().getState() != GameState.INFECTION) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be in-game to buy that!");
                        } else if (Manager.getArena().getState() == GameState.INFECTION && Manager.getArena().getTeam(p) == Team.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be a " + ChatColor.GREEN + "Human" + ChatColor.RED + " to buy that!");
                        } else {
                            try {
                                int rankPoints = getRankPoints(p.getUniqueId());

                                if (rankPoints >= 50) {
                                    setRankPoints(p.getUniqueId(), -50);
                                    setFire(p, 1);
                                } else {
                                    p.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                            + (50 - rankPoints) + ChatColor.GREEN + " more Rankpoints to buy that!");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }

                        break;
                    case EGG:
                        if (Manager.getArena().getState() != GameState.INFECTION) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be in-game to buy that!");
                        } else if (Manager.getArena().getState() == GameState.INFECTION && Manager.getArena().getTeam(p) == Team.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be a " + ChatColor.GREEN + "Human" + ChatColor.RED + " to buy that!");
                        } else {
                            try {
                                int rankPoints = getRankPoints(p.getUniqueId());

                                if (rankPoints >= 50) {
                                    setRankPoints(p.getUniqueId(), -50);
                                    setFrag(p, 1);
                                } else {
                                    p.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                            + (50 - rankPoints) + ChatColor.GREEN + " more Rankpoints to buy that!");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }

                        break;
                    case CHEST:
                        if (Manager.getArena().getState() != GameState.INFECTION) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be in-game to buy that!");
                        } else if (Manager.getArena().getState() == GameState.INFECTION && Manager.getArena().getTeam(p) == Team.ZOMBIE) {
                            p.sendMessage(Manager.getServerTag() + ChatColor.RED + "You need to be a " + ChatColor.GREEN + "Human" + ChatColor.RED + " to buy that!");
                        } else {
                            try {
                                int rankPoints = getRankPoints(p.getUniqueId());

                                if (rankPoints >= 200) {
                                    setRankPoints(p.getUniqueId(), -200);
                                    p.getInventory().addItem(new ItemStack(Material.CHEST));
                                } else {
                                    p.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                            + (200 - rankPoints) + ChatColor.GREEN + " more Rankpoints to buy that!");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
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
