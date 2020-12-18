package net.mcpandemic.core;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Arena class is responsible for driving the game mode in each
 * instance. Contains:
 *     - an id (int)
 *     - players (ArrayList)
 *     - spawn (Location)
 *     - state (GameState)
 */
public class Arena {

    private int id;
    private ArrayList<UUID> players;
    private Location spawn;
    private GameState state;

    public Arena(int id) {
        this.id = id;
        players = new ArrayList<>();
        spawn = Config.getArenaSpawn(id);
        state = GameState.RECRUITING;
    }

    /**
     * This method adds a Player to the players ArrayList. The Player is
     * then teleported to the Arena's spawn Location.
     * @param player a Player.
     */
    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);
    }

    /**
     * This method removes a Player from the players ArrayList. The
     * Player is then teleported back to the lobby-spawn Location.
     * @param player a Player.
     */
    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(Config.getLobbySpawn());
    }

    /*
     * GETTERS:
     */

    public int getID() {
        return id;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public GameState getState() {
        return state;
    }

}
