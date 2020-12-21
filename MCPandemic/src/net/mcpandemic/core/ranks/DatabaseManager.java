package net.mcpandemic.core.ranks;

import net.mcpandemic.core.Main;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

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

public class DatabaseManager {

    /**
     * Server Rank Getters and Setters.
     */
    public static void setServerRank(Player player, Rank rank) {
        try {
            Main.prepareStatement("UPDATE player_info SET server_rank = '" + rank.name() + "' WHERE uuid = '" + player.getUniqueId() + "';").executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void setServerRank(UUID uuid, Rank rank) {
        try {
            Main.prepareStatement("UPDATE player_info SET server_rank = '" + rank.name() + "' WHERE uuid = '" + uuid + "';").executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static Rank getServerRank(Player player) {
        try {
            ResultSet rs = Main.prepareStatement("SELECT server_rank FROM player_info WHERE uuid = '" + player.getUniqueId() + "';").executeQuery();
            rs.next();
            String rank = rs.getString("server_rank");
            return Rank.valueOf(rank);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Infected Rank Getters and Setters.
     */
    public static void setInfectedRank(Player player, InfectedRank rank) {
        try {
            Main.prepareStatement("UPDATE player_info SET infected_rank = '" + rank.name() + "' WHERE uuid = '" + player.getUniqueId() + "';").executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void setInfectedRank(UUID uuid, InfectedRank rank) {
        try {
            Main.prepareStatement("UPDATE player_info SET infected_rank = '" + rank.name() + "' WHERE uuid = '" + uuid + "';").executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static InfectedRank getInfectedRank(Player player) {
        try {
            ResultSet rs = Main.prepareStatement("SELECT infected_rank FROM player_info WHERE uuid = '" + player.getUniqueId() + "';").executeQuery();
            rs.next();
            String infectedRank = rs.getString("infected_rank");
            return InfectedRank.valueOf(infectedRank);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Prestige Rank Getters and Setters.
     */
    public static void setPrestigeRank(Player player, Prestige rank) {
        try {
            Main.prepareStatement("UPDATE player_info SET prestige_rank = '" + rank.name() + "' WHERE uuid = '" + player.getUniqueId() + "';").executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void setPrestigeRank(UUID uuid, Prestige rank) {
        try {
            Main.prepareStatement("UPDATE player_info SET prestige_rank = '" + rank.name() + "' WHERE uuid = '" + uuid + "';").executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static Prestige getPrestigeRank(Player player) {
        try {
            ResultSet rs = Main.prepareStatement("SELECT prestige_rank FROM player_info WHERE uuid = '" + player.getUniqueId() + "';").executeQuery();
            rs.next();
            String prestige = rs.getString("prestige_rank");
            return Prestige.valueOf(prestige);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Rank Points Getters and Setters.
     */
    public static void setRankPoints(UUID uuid, int amount) throws SQLException{
        Main.prepareStatement("UPDATE player_info SET rank_points = '" + (getRankPoints(uuid) + amount) + "' WHERE uuid = '" + uuid + "';").executeUpdate();
    }

    public static int getRankPoints(UUID uuid) throws SQLException {
            ResultSet rs = Main.prepareStatement("SELECT rank_points FROM player_info WHERE uuid = '" + uuid + "';").executeQuery();
            rs.next();
            return rs.getInt("rank_points");
    }

    /**
     * Prestige Tokens Getters and Setters.
     */
    public static void setPrestigeTokens(UUID uuid, int amount) throws SQLException{
        Main.prepareStatement("UPDATE player_info SET prestige_tokens = '" + (getPrestigeTokens(uuid) + amount) + "' WHERE uuid = '" + uuid + "';").executeUpdate();
    }

    public static int getPrestigeTokens(UUID uuid) throws SQLException {
        ResultSet rs = Main.prepareStatement("SELECT prestige_tokens FROM player_info WHERE uuid = '" + uuid + "';").executeQuery();
        rs.next();
        return rs.getInt("prestige_tokens");
    }

    /**
     * Infected Kit Getters and Setters.
     */
    public static void setInfectedKit(Player player, KitType kit) {
        try {
            Main.prepareStatement("UPDATE player_info SET infected_kit = '" + kit.name() + "' WHERE uuid = '" + player.getUniqueId() + "';").executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void setInfectedKit(UUID uuid, KitType kit) {
        try {
            Main.prepareStatement("UPDATE player_info SET infected_kit = '" + kit.name() + "' WHERE uuid = '" + uuid + "';").executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static KitType getInfectedKit(Player player) {
        try {
            ResultSet rs = Main.prepareStatement("SELECT infected_kit FROM player_info WHERE uuid = '" + player.getUniqueId() + "';").executeQuery();
            rs.next();
            String prestige = rs.getString("infected_kit");
            return KitType.valueOf(prestige);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


}
