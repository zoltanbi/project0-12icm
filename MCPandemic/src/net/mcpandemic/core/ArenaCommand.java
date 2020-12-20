package net.mcpandemic.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("list")) {
                player.sendMessage(ChatColor.GREEN + "These are the available arenas: ");
                player.sendMessage(Manager.getArenas().getID());
            } else if (args[0].equalsIgnoreCase("leave")) {
                if (Manager.isPlaying(player)) {
                    Manager.getArena(player).removePlayer(player);
                } else {
                    player.sendMessage(ChatColor.RED + "You are not in an arena!");
                }
            } else if (args[0].equalsIgnoreCase("join")) {
                if (!Manager.getArena().getPlayers().contains(player.getUniqueId())) {
                    Manager.getArena().addPlayer(player);
                    player.sendMessage("You are now playing.");
                } else {
                    player.sendMessage("You are already playing!");
                }
            } else if (args[0].equalsIgnoreCase("skipvote")){
                if (Manager.getArena().getState() == GameState.VOTING) {
                    Manager.getArena().getVoteCountdown().skipVote();
                } else {
                    player.sendMessage("Game-state is not voting!");
                }
            } else if (args[0].equalsIgnoreCase("skipcount")){
                if (Manager.getArena().getState() == GameState.COUNTDOWN) {
                    Manager.getArena().getCountdown().skipCountdown();
                } else {
                    player.sendMessage("Game-state is not countdown!");
                }
            } else if (args[0].equalsIgnoreCase("skipgame")){
                if (Manager.getArena().getState() == GameState.LIVE) {
                    Manager.getArena().getGame().skipGame();
                } else {
                    player.sendMessage("Game-state is not live!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage - these " +
                        "are the options:");
                player.sendMessage(ChatColor.RED + " - /arena list");
                player.sendMessage(ChatColor.RED + " - /arena join [id]");
                player.sendMessage(ChatColor.RED + " - /arena leave");
            }

        } else {
            System.out.println("You cant use this from the console!");
        }

        return false;
    }

}
