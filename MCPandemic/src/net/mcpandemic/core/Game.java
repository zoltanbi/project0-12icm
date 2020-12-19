package net.mcpandemic.core;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

/**
 * The Game class is responsible for the game mode's logic
 * for infection. Currently being tested for block break events.
 */
public class Game extends BukkitRunnable {

    private Arena arena;
    private HashMap<UUID, Integer> points;
    private int preGameSeconds;

    public Game(Arena arena) {
        this.arena = arena;
        this.points = new HashMap<>();
        this.preGameSeconds = 60;
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.teleportPlayersToArena();
        arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "You have one minute to run away! The "
                + ChatColor.YELLOW + "Zovid-19" + ChatColor.DARK_GREEN + " virus is coming!");

        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }
        this.runTaskTimer(Main.getInstance(), 0, 20);
    }

    @Override
    public void run() {
        if (preGameSeconds == 0) {
            cancel();
            arena.setState(GameState.INFECTION);

        }
        if (preGameSeconds == 30) {
            arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "The virus will spread in " + ChatColor.YELLOW + "30" + ChatColor.DARK_GREEN + " seconds...");
        }
        if (preGameSeconds <= 10) {
            if (preGameSeconds != 0) {
                arena.sendMessage(Manager.getServerTag() + ChatColor.YELLOW + preGameSeconds + ChatColor.DARK_GREEN + "...");
            }
        }

        preGameSeconds--;
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
