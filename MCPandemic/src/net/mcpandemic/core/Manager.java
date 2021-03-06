package net.mcpandemic.core;

import net.mcpandemic.core.gamestates.GameState;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * The Manager class is responsible for looking after the Arena class.
 * All methods in this class are static.
 */
public class Manager {

    private static Arena arena;

    public Manager() {
        arena = new Arena();

    }

    public static Arena getArenas() {
        return arena;
    }

    /**
     * Checks if a Player is in an Arena.
     * @param player a Player.
     * @return a boolean.
     */
    public static boolean isPlaying(Player player) {
        return arena.getPlayers().contains(player.getUniqueId());
    }

    /**
     * Returns the arena by the player in it.
     * @param player a Player.
     * @return an Arena.
     */
    public static Arena getArena(Player player) {
        if (arena.getPlayers().contains(player.getUniqueId())) {
            return arena;
        }
        return null;
    }

    /**
     * Returns the arena by id.
     * @return an Arena.
     */
    public static Arena getArena() {
        return arena;
    }

    public static boolean isRecruiting() {
        return getArena().getState() == GameState.RECRUITING;
    }

    public static String getServerTag() {
        String tag = ChatColor.DARK_RED + "[MCPandemic] " + ChatColor.RESET;
        return tag;
    }

    public static void turnGamemodeAdventure() {
        for (Player player : Main.getInstance().getServer().getOnlinePlayers()) {
            if (player.getGameMode() != GameMode.ADVENTURE) {
                player.setGameMode(GameMode.ADVENTURE);
            }
        }
    }

}
