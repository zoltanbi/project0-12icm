package net.mcpandemic.core;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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
         if (arena.getPlayers().contains(player.getUniqueId())) {
             return true;
         }
         return false;
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
        String tag = ChatColor.RED + "[MCPandemic] " + ChatColor.RESET;
        return tag;
    }

}
