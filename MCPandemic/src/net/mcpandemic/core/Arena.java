package net.mcpandemic.core;

import net.mcpandemic.core.voting.Maps;
import net.mcpandemic.core.voting.VoteCountdown;
import net.mcpandemic.core.voting.VoteMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
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

    private ArrayList<UUID> players;
    private Location spawn;
    private Location mapSpawn;
    private GameState state;
    private VoteMap voteMap;
//    HashMap<Maps, Integer> votableMaps;
//    private VoteCountdown voteCountdown;
    private Countdown countdown;
    private Game game;

    public Arena() {
        players = new ArrayList<>();
        spawn = Config.getLobbySpawn();
        state = GameState.RECRUITING;
        mapSpawn = Config.getArenaSpawn(0);
//        voteMap = new VoteMap();
//        votableMaps = voteMap.getVotableMaps();
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
        //players.clear(); removes from game which we dont want atm
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
        if (state == GameState.RECRUITING || state == GameState.VOTING ||
                state == GameState.COUNTDOWN) {
            player.teleport(spawn);
            if (state == GameState.RECRUITING && players.size() >= Config.getRequiredPlayers()) {
                countdown.begin();
            }
        }
        if (state == GameState.LIVE) {
            player.teleport(mapSpawn);
        }

    }

    /**
     * This method removes a Player from the players ArrayList. The
     * Player is then teleported back to the lobby-spawn Location.
     * @param player a Player.
     */
    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        if (player.isOnline()) {
            player.teleport(Config.getLobbySpawn());
            player.sendMessage("You're not supposed to be out of the game... Contact a staff member");
        }

        if (players.size() <= Config.getRequiredPlayers() && (state.equals(GameState.COUNTDOWN) || state.equals(GameState.VOTING))) {
            reset();
        }

        if (players.size() == 0 && (state.equals(GameState.COUNTDOWN) || state.equals(GameState.VOTING))) {
            reset();
        }
    }

    /*
     * GETTERS:
     */

    public String getID() {
        return "main";
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

    public Location getMapSpawn() {
        return mapSpawn;
    }

    /*
     * SETTERS:
     */

    public void setState(GameState state) {
        this.state = state;
    }

}
