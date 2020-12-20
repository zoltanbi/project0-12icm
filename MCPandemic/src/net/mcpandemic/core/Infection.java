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
public class Infection extends BukkitRunnable {

    private Arena arena;
    private HashMap<UUID, Integer> points;
    private int seconds;

    public Infection(Arena arena) {
        this.arena = arena;
        this.points = new HashMap<>();
        this.seconds = 240;
    }

    public void start() {
        arena.setState(GameState.INFECTION);
        arena.sendMessage(Manager.getServerTag() + ChatColor.RED + "Player is the mother zombie!");

        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }
        this.runTaskTimer(Main.getInstance(), 0, 20);
    }

    @Override
    public void run() {
        if (seconds == 0) {
            cancel();
            arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "Time limit reached! Congratulations to these survivors:" + ChatColor.YELLOW + "player");
            arena.reset();

        }
        if (seconds == 180) {
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are " + ChatColor.YELLOW + "3" + ChatColor.AQUA + " minutes left!");
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are currently " + ChatColor.YELLOW + "x" + ChatColor.AQUA + " humans and " + ChatColor.YELLOW + "x" + ChatColor.AQUA + " zombies!");
        }
        if (seconds == 120) {
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are " + ChatColor.YELLOW + "2" + ChatColor.AQUA + " minutes left!");
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are currently " + ChatColor.YELLOW + "x" + ChatColor.AQUA + " humans and " + ChatColor.YELLOW + "x" + ChatColor.AQUA + " zombies!");
        }
        if (seconds == 60) {
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There is " + ChatColor.YELLOW + "1" + ChatColor.AQUA + " minute left!");
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are currently " + ChatColor.YELLOW + "x" + ChatColor.AQUA + " humans and " + ChatColor.YELLOW + "x" + ChatColor.AQUA + " zombies!");
        }
        if (seconds <= 10) {
            if (seconds != 0) {
                arena.sendMessage(Manager.getServerTag() + ChatColor.YELLOW + seconds + ChatColor.DARK_GREEN + "...");
            }
        }

        seconds--;
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