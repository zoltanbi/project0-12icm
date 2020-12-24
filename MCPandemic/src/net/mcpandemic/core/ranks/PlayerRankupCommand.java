package net.mcpandemic.core.ranks;

import net.mcpandemic.core.Manager;
import net.mcpandemic.core.infectedmanager.DatabaseManager;
import net.mcpandemic.core.infectedmanager.RankupManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.EnumUtils;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class PlayerRankupCommand implements CommandExecutor{

    //rank <player> <rank>

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                try {
                    int rankPoints = DatabaseManager.getRankPoints(player.getUniqueId());
                    InfectedRank playerCurrentRank = DatabaseManager.getInfectedRank(player);
                    InfectedRank nextRank = RankupManager.getNextRank(playerCurrentRank);
                    int nextRankCost = RankupManager.getNextRank(playerCurrentRank).getCost();
                    if (playerCurrentRank == InfectedRank.T) {
                        player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You are MAX rank! If you want to prestige type " + ChatColor.GOLD + "/prestige");
                    }
                    if (rankPoints >= nextRankCost) {
                        DatabaseManager.setRankPoints(player.getUniqueId(), (-nextRankCost));
                        DatabaseManager.setInfectedRank(player, nextRank);
                        Manager.getArena().sendMessage(Manager.getServerTag() + ChatColor.DARK_AQUA + "Player " +
                                ChatColor.GOLD + player.getName() + ChatColor.DARK_AQUA + " ranked up to " + ChatColor.GOLD + nextRank.getName());
                        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0F, 1.0F);
                    } else {
                        player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You need " + ChatColor.GOLD
                                + (nextRankCost - rankPoints) + ChatColor.GREEN + " more Rankpoints to rankup!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                player.sendMessage(ChatColor.RED + "This command has no arguments. Try " + ChatColor.GREEN + "/rankup");
            }

        }
        return false;
    }

}
