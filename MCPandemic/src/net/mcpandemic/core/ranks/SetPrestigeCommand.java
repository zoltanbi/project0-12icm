package net.mcpandemic.core.ranks;

import net.mcpandemic.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.EnumUtils;
import org.bukkit.entity.Player;

public class SetPrestigeCommand implements CommandExecutor{

    //rank <player> <rank>

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (player.isOp()) {
            if (args.length == 2) {
                if (Bukkit.getOfflinePlayer(args[0]).isOnline() ||
                        Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()) {
                    if (EnumUtils.isValidEnum(Prestige.class, args[1].toUpperCase())) {
                        DatabaseManager.setPrestigeRank(
                                Bukkit.getOfflinePlayer(args[0]).getUniqueId(),
                                Prestige.valueOf(args[1].toUpperCase()));
                        player.sendMessage(ChatColor.DARK_GREEN + "You changed this prestige!");

                        if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
                            Bukkit.getOfflinePlayer(args[0]).getPlayer().sendMessage(
                                    player.getName() + " just changed your prestige to " +
                                            args[1] + ".");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "THIS RANK DOESNT EXIST");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "THEY NEVER PLAYED BEFORE");
                }
            } else {
                player.sendMessage(ChatColor.RED + "INVALID USAGE");
            }
        } else {
            player.sendMessage(ChatColor.RED + "NO PERMISSION");
        }

        return false;
    }

}
