package net.mcpandemic.core;

import net.mcpandemic.core.teams.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {

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
