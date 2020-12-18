package net.mcpandemic.core;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The Manager class is responsible for looking after the Arena class.
 * All methods in this class are static.
 */
public class Manager {

    private static ArrayList<Arena> arenas;

    public Manager() {
        for (int i = 0; i <= (Config.getArenaAmount() - 1); i++) {
            arenas.add(new Arena(i));
        }
    }

    public static List<Arena> getArenas() {
        return arenas;
    }

    /**
     * Checks if a Player is in an Arena.
     * @param player a Player.
     * @return a boolean.
     */
    public static boolean isPlaying(Player player) {
         for (Arena arena : arenas) {
             if (arena.getPlayers().contains(player.getUniqueId())) {
                 return true;
             }
         }
         return false;
    }

    /**
     * Returns the arena by the player in it.
     * @param player a Player.
     * @return an Arena.
     */
    public static Arena getArena(Player player) {
        for (Arena arena : arenas) {
            if (arena.getPlayers().contains(player.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }

    /**
     * Returns the arena by id.
     * @param id an int.
     * @return an Arena.
     */
    public static Arena getArena(int id) {
        for (Arena arena : arenas) {
            if (arena.getID() == id) {
                return arena;
            }
        }
        return null;
    }

}
