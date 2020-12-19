package net.mcpandemic.core;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player player = e.getPlayer();

        if (Manager.isPlaying(player) && Manager.getArena(player).getState()
                .equals((GameState.LIVE))) {

            player.sendMessage(ChatColor.GOLD + "+1 Point!");

            Manager.getArena(player).getGame().addPoint(player);
        }

    }

//    @EventHandler
//    public void onJoin(PlayerJoinEvent e) {
//        Player player = e.getPlayer();
//        Manager.getArena().addPlayer(player);
//        player.sendMessage("You are playing MCPandemic");
//
//
//    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player player = e.getPlayer();

        if (Manager.isPlaying(player)) {
            Manager.getArena(player).removePlayer((player));
        }

    }

}
