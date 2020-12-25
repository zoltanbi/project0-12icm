package net.mcpandemic.core.ranks;

import net.mcpandemic.core.Main;
import net.mcpandemic.core.QualityOfLifeListener;
import net.mcpandemic.core.infectedmanager.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;

/*
FOR REFERENCE:
        try {
            Main.prepareStatement("INSERT INTO player_info(" +
                    "uuid, server_rank, infected_rank, prestige_rank, " +
                    "rank_points, prestige_tokens, join_date, infected_kit) " +
                    "VALUES ('" + player.getUniqueId() + "'," +
                    "DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT);").executeUpdate();

            // updating data
            Main.prepareStatement("UPDATE player_info SET rank_points = '10', server_rank = 'ADMIN' WHERE uuid = '" + player.getUniqueId() + "';").executeUpdate();

            //retrieving data
            Main.prepareStatement("SELECT * FROM player_info WHERE uuid = '" + player.getUniqueId() + "';").executeQuery();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
 */

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        try {
            ResultSet rs = Main.prepareStatement("SELECT COUNT(uuid) FROM player_info WHERE uuid = '" + player.getUniqueId().toString() + "';").executeQuery();
            rs.next();

            if (rs.getInt(1) == 0) { //is not in system
                Main.prepareStatement("INSERT INTO player_info(" +
                        "uuid, server_rank, infected_rank, prestige_rank, " +
                        "rank_points, prestige_tokens, join_date, infected_kit) " +
                        "VALUES ('" + player.getUniqueId() + "'," +
                        "DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT);").executeUpdate();
            } else { //is already in system
                ResultSet rs2 = Main.prepareStatement("SELECT * FROM player_info WHERE uuid = '" + player.getUniqueId() + "';").executeQuery();
                rs2.next();

                String serverRank = rs2.getString("server_rank");
                int rankPoints = rs2.getInt("rank_points");
                Timestamp ts = rs2.getTimestamp("join_date");

                System.out.println(serverRank);
                System.out.println(rankPoints);
                System.out.println(ts);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player player = e.getPlayer();
        Rank rank = DatabaseManager.getServerRank(player);
        InfectedRank infectedRank = DatabaseManager.getInfectedRank(player);
        Prestige prestige = DatabaseManager.getPrestigeRank(player);

        e.setCancelled(true);

        for (Player onlinePlayers : e.getRecipients()) {
            onlinePlayers.sendMessage(ChatColor.BLACK + "{" + rank.getColor()
                    + ChatColor.BOLD + rank.getName() + ChatColor.RESET + ChatColor.BLACK + "}" +
                    ChatColor.BLUE + "<" + ChatColor.YELLOW + prestige.getPrNum() +
                    ChatColor.GOLD + prestige.getPrSymbol() + ChatColor.BLUE + ">" + ChatColor.GOLD + "[" +
                    infectedRank.getColor() + infectedRank.getName() + ChatColor.GOLD +
                    "] " + ChatColor.WHITE + player.getName() + ChatColor.GOLD + " >: "
                    + ChatColor.WHITE + e.getMessage());
        }

    }

}
