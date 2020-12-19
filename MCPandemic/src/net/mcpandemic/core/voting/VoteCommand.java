package net.mcpandemic.core.voting;

import net.mcpandemic.core.GameState;
import net.mcpandemic.core.Manager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Manager.getArena().getState() == GameState.VOTING) {
                if (!Manager.getArena().getVotedPlayers().contains(player.getUniqueId())) {
                    if (args.length == 0) {
                        VoteGUICommand.applyVoteUI(player);
                    } else if (args[0].equalsIgnoreCase("1")) {
                        Manager.getArena().castVote(player, 1);
                    } else if (args[0].equalsIgnoreCase("2")) {
                        Manager.getArena().castVote(player, 2);
                    } else if (args[0].equalsIgnoreCase("3")) {
                        Manager.getArena().castVote(player, 3);
                    } else if (args[0].equalsIgnoreCase("4")) {
                        Manager.getArena().castVote(player, 4);
                    } else if (args[0].equalsIgnoreCase("5")) {
                        Manager.getArena().castVote(player, 5);
                    }
                } else {
                    player.sendMessage(Manager.getServerTag() + ChatColor.DARK_AQUA + "You already voted!");
                }

            } else {
                player.sendMessage(Manager.getServerTag() + ChatColor.DARK_AQUA + "You can't vote right now!");
            }

        } else {
            System.out.println("You cant use this from the console!");
        }

        return false;
    }
}
