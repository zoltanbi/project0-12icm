package net.mcpandemic.core;

import net.mcpandemic.core.gamestates.GameState;
import net.mcpandemic.core.infectedmanager.ZombieManager;
import net.mcpandemic.core.teams.Team;
import net.minecraft.server.v1_16_R2.PacketPlayInClientCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;
import java.util.HashMap;

public class GameListener implements Listener {

    HashMap<Player, Location> newlyInfected = new HashMap<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player player = e.getPlayer();

        if (Manager.isPlaying(player) && Manager.getArena(player).getState()
                .equals((GameState.INFECTION))) {

            player.sendMessage(ChatColor.GOLD + "+1 Point!");

            Manager.getArena(player).getInfection().addPoint(player);
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
        }
    }

//    @EventHandler
//    public void RespawnScreen(PlayerDeathEvent e){
//        final Player p = e.getEntity();
//        Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
//            public void run(){
//                if (p.isDead()){
//                    ((CraftPlayer)p).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
//                }
//            }
//        });
//    }

    //KILL MESSAGE NEEDS TO BE SET ON ONJOIN STILL
    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        Location deathLocation = e.getEntity().getLocation();
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
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Manager.getArena().addPlayer(player);
        player.sendMessage("You are playing MCPandemic");
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
