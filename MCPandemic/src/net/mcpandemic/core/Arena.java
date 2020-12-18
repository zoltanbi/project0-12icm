package net.mcpandemic.core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Arena class is responsible for driving the game mode in each
 * instance. Contains:
 *     - id (int)
 *     - players (ArrayList)
 *     - spawn (Location)
 *     - state (GameState)
 *     - countdown (Countdown)
 *     - game (Game)
 */
public class Arena {

    private int id;
    private ArrayList<UUID> players;
    private Location spawn;
    private GameState state;
    private Countdown countdown;
    private Game game;

    public Arena(int id) {
        this.id = id;
        players = new ArrayList<>();
        spawn = Config.getArenaSpawn(id);
        state = GameState.RECRUITING;
        countdown = new Countdown(this);
        game = new Game(this);
    }

    public void start() {
        game.start();
    }

    public void reset() {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).teleport(Config.getLobbySpawn());
        }

        state = GameState.RECRUITING;
        players.clear();
        countdown = new Countdown(this);
        game = new Game(this);
    }

    /**
     * Sends a message to everyone in the Arena.
     * @param message a String.
     */
    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    /**
     * This method adds a Player to the players ArrayList. The Player is
     * then teleported to the Arena's spawn Location.
     * @param player a Player.
     */
    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if (players.size() >= Config.getRequiredPlayers()) {
            countdown.begin();
        }
    }

    /**
     * This method removes a Player from the players ArrayList. The
     * Player is then teleported back to the lobby-spawn Location.
     * @param player a Player.
     */
    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(Config.getLobbySpawn());

        if (players.size() <= Config.getRequiredPlayers() && state.equals(GameState.COUNTDOWN)) {
            reset();
        }

        if (players.size() == 0 && state.equals(GameState.COUNTDOWN)) {
            reset();
        }
    }

    /*
     * GETTERS:
     */

    public int getID() {
        return id;
    }

    public ArrayList<UUID> getPlayers() {
        return players;
    }

    public GameState getState() {
        return state;
    }

    public Game getGame() {
        return game;
    }

    /*
     * SETTERS:
     */

    public void setState(GameState state) {
        this.state = state;
    }

}
