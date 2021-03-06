package net.mcpandemic.core;

import net.mcpandemic.core.gamestates.GameState;
import net.mcpandemic.core.infectedmanager.DatabaseManager;
import net.mcpandemic.core.infectedmanager.ZombieManager;
import net.mcpandemic.core.teams.Team;
import net.minecraft.server.v1_16_R3.PacketPlayInClientCommand;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

public class GameListener implements Listener {

    HashMap<Player, Location> newlyInfected = new HashMap<>();
    static HashMap<Player, Integer> playerKillstreaks = new HashMap<>();
    static HashMap<Player, Integer> infectedKillStreaks = new HashMap<>();

    public static void clearPlayerKillStreaks() {
        playerKillstreaks.clear();
    }

    public void clearInfectedKillStreaks() {
        infectedKillStreaks.clear();
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (Manager.getArena().getState() == GameState.RECRUITING || Manager.getArena().getState() == GameState.VOTING ||
                    Manager.getArena().getState() == GameState.COUNTDOWN) {
                e.setCancelled(true);
                if (player.getLocation().getY() < 1) {
                    player.teleport(Manager.getArena().getLobbySpawn());
                }
            }
        }
    }


    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            Player attacked = (Player) e.getEntity();
            Team attackerTeam = Manager.getArena().getTeam(attacker);
            Team attackedTeam = Manager.getArena().getTeam(attacked);


            if (Manager.getArena().getState() == GameState.INFECTION) {
                if (attackerTeam == attackedTeam) {
                    e.setCancelled(true);
                    attacker.sendMessage(Manager.getServerTag() + ChatColor.RED + "You can't attack your teammate!");
                }
            } else {
                e.setCancelled(true);
            }
        } else if(e.getDamager() instanceof Player && e.getEntity() instanceof Player ||
                (e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() instanceof Player)) {
            Player shooter = ((Player) ((Projectile) e.getDamager()).getShooter());
            Player attacked = (Player) e.getEntity();
            Team attackerTeam = Manager.getArena().getTeam(shooter);
            Team attackedTeam = Manager.getArena().getTeam(attacked);
            if (Manager.getArena().getState() == GameState.INFECTION) {
                if (attackerTeam == attackedTeam) {
                    e.setCancelled(true);
                    shooter.sendMessage(Manager.getServerTag() + ChatColor.RED + "You can't attack your teammate!");
                }
            } else {
                e.setCancelled(true);
            }
        }
    }


    //KILL MESSAGE NEEDS TO BE SET ON ONJOIN STILL
    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        Location deathLocation = e.getEntity().getLocation();
        for (PotionEffect effect : killed.getActivePotionEffects()) {
            killed.removePotionEffect(effect.getType());
        }
        if (Manager.getArena().getState() == GameState.INFECTION){
            //if unknown cause of death and human
            //sets to team zombie and kill feed message. Adds human to newly infected hashmap
            if (e.getEntity().getKiller() == null && Manager.getArena().getTeam(killed) == Team.HUMAN) {
                Manager.getArena().setTeam(killed, Team.ZOMBIE);
                Manager.getArena().zombieInfectMessage(e.getEntity());
                newlyInfected.put(killed, deathLocation);
                // death logic
                Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
                    public void run(){
                        if (killed.isDead()){
                            ((CraftPlayer)killed).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                            //logic
                            killed.teleport(newlyInfected.get(killed));
                            ZombieManager.playerZombieSetup(killed);
                            //DisguiseManager.setZombieDisguise(killed);
                            newlyInfected.remove(killed);
                        }

                    }
                });
                try {
                    Manager.getArena().survivorBonus(1);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            // if killer was found
            } else if(e.getEntity().getKiller() != null) {
                Player killer = e.getEntity().getKiller();
                // if the killer was human and killed was zombie
                // //send kill feed message
                if(Manager.getArena().getTeam(killer) == Team.HUMAN && Manager.getArena().getTeam(killed) == Team.ZOMBIE) {
                    Manager.getArena().zombieKillMessage(killer, killed);
                    //death logic
                    Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
                        public void run(){
                            if (killed.isDead()){
                                ((CraftPlayer)killed).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                                //logic
                                killed.teleport(Manager.getArena().getMapSpawn());
                                ZombieManager.playerZombieSetup(killed);
                            }

                        }
                    });
                    //add rankpoints to killer
                    try {
                        Manager.getArena().killBonus(killer, 1);
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                // if the killer was zombie and killed was human
                // send kill feed message. Adds human to newly infected hashmap
                } else if (Manager.getArena().getTeam(killer) == Team.ZOMBIE && Manager.getArena().getTeam(killed) == Team.HUMAN) {
                    Manager.getArena().setTeam(killed, Team.ZOMBIE);
                    Manager.getArena().humanKillMessage(killer, killed);
                    newlyInfected.put(killed, deathLocation);
                    //death logic
                    Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
                        public void run(){
                            if (killed.isDead()){
                                ((CraftPlayer)killed).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                                //logic
                                killed.teleport(newlyInfected.get(killed));
                                ZombieManager.playerZombieSetup(killed);
                                //DisguiseManager.setZombieDisguise(killed);
                                newlyInfected.remove(killed);
                            }

                        }
                    });
                    try {
                        Manager.getArena().killBonus(killer, 1);
                        Manager.getArena().survivorBonus(1);
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
            } else if (Manager.getArena().getTeam(killed) == Team.ZOMBIE) {
                Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
                    public void run(){
                        if (killed.isDead()){
                            ((CraftPlayer)killed).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                            //logic
                            killed.teleport(Manager.getArena().getMapSpawn());
                            ZombieManager.playerZombieSetup(killed);
                            //
                        }

                    }
                });

            }
        // IF GAMESTATE IS IN LIVE
        } else if (Manager.getArena().getState() == GameState.LIVE) {
            Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
                public void run(){
                    if (killed.isDead()){
                        ((CraftPlayer)killed).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                        //logic
                        killed.teleport(Manager.getArena().getMapSpawn());
                        Manager.getArena().getHumanKit(killed).onStart(killed);
                    }

                }
            });
        } else if (Manager.getArena().getState() == GameState.ENDGAME) {
            Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
                public void run(){
                    if (killed.isDead()){
                        ((CraftPlayer)killed).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                        //logic
                        killed.teleport(Manager.getArena().getMapSpawn());
                    }

                }
            });
        // IF IN LOBBY
        } else {
            Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
                public void run(){
                    if (killed.isDead()){
                        ((CraftPlayer)killed).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                        //logic
                        killed.teleport(Manager.getArena().getLobbySpawn());
                    }

                }
            });
        }
    }

    @EventHandler
    public void killStreakHandler(PlayerDeathEvent e) {
        if (Manager.getArena().getState() == GameState.INFECTION && e.getEntity().getKiller() != null) {
            Player killed = e.getEntity();
            Player killer = e.getEntity().getKiller();
            if (Manager.getArena().getTeam(killer) == Team.HUMAN && Manager.getArena().getTeam(killed) == Team.ZOMBIE) {
                if (!playerKillstreaks.containsKey(killer)) {
                    playerKillstreaks.put(killer, 1);
                } else if (playerKillstreaks.containsKey(killer)) {
                    playerKillstreaks.put(killer, (playerKillstreaks.get(killer) + 1));
                    Killstreaks.giveKillstreakReward(killer, playerKillstreaks.get(killer));
                    Manager.getArena().sendMessage(Manager.getServerTag() + ChatColor.AQUA + "Player " + ChatColor.GOLD +
                            killer.getName() + ChatColor.AQUA + " is on a " + ChatColor.GOLD + playerKillstreaks.get(killer) +
                            ChatColor.AQUA + " kills killing spree!");
                }
            }
//            } else if(Manager.getArena().getTeam(killer) == Team.ZOMBIE && Manager.getArena().getTeam(killed) == Team.HUMAN) {
//
//            }
        }
    }


    @EventHandler
    public void onEnderchestReward(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Manager.getArena().getState() == GameState.VOTING || Manager.getArena().getState() == GameState.RECRUITING ||
                    Manager.getArena().getState() == GameState.COUNTDOWN) {
                if (e.getClickedBlock().getType() == Material.ENDER_CHEST) {
                    if (Manager.getArena().canReceiveParkourReward(player)) {
                        Manager.getArena().addToParkourReward(player);
                        player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW +
                                "10 Rankpoints" + ChatColor.GREEN + " for completing the parkour!");
                        try {
                            DatabaseManager.setRankPoints(player.getUniqueId(), 10);
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                    } else {
                        player.sendMessage(Manager.getServerTag() + ChatColor.RED + "You already received this reward!");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onChestClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Objects.requireNonNull(e.getClickedBlock()).getType() == Material.ENDER_CHEST || (Objects.requireNonNull(e.getClickedBlock()).getType() == Material.CHEST)) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Manager.getArena().addPlayer(player);
        player.sendMessage("You are playing MCPandemic");
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            public void run() {
                player.setGameMode(GameMode.ADVENTURE);
            }
        }, 5);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        try {
            Manager.getArena().removePlayer(player);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
