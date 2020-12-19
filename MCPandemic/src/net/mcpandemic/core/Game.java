package net.mcpandemic.core;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * The Game class is responsible for the game mode's logic
 * for infection. Currently being tested for block break events.
 */
public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points;

    public Game(Arena arena) {
        this.arena = arena;
        this.points = new HashMap<>();
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.teleportPlayersToArena();

        arena.sendMessage("Game has started! Your objective is to be the " +
                "first player to break 20 blocks.");

        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }
    }

    public void addPoint(Player player) {
        int p = points.get(player.getUniqueId()) + 1;

        if (p == 20) {
            arena.sendMessage(player.getName() + " WINS!!");

            arena.reset();
            return;
        }

        points.replace(player.getUniqueId(), p);
    }

}
