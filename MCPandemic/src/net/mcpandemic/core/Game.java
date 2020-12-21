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
    private int preGameSeconds;

    public Game(Arena arena) {
        this.arena = arena;
        this.preGameSeconds = 60;
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.teleportPlayersToArena();
        //set humans and set kits
        arena.setHumans();
        arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "You have one minute to run away! The "
                + ChatColor.YELLOW + "Zovid-19" + ChatColor.DARK_GREEN + " virus is coming!");
        this.runTaskTimer(Main.getInstance(), 0, 20);
    }

    public void skipGame() {
        cancel();
        arena.startInfection();
    }

    @Override
    public void run() {
        if (preGameSeconds == 0) {
            cancel();
            arena.startInfection();

        }
        if (preGameSeconds == 30) {
            arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "The virus will spread in " + ChatColor.YELLOW + "30" + ChatColor.DARK_GREEN + " seconds...");
        }
        if (preGameSeconds <= 10) {
            if (preGameSeconds != 0) {
                arena.sendMessage(Manager.getServerTag() + ChatColor.YELLOW + preGameSeconds + ChatColor.DARK_GREEN + "...");
            }
        }

        if (arena.getPlayers().size() < Config.getRequiredPlayers()) {
            cancel();
            arena.sendMessage(Manager.getServerTag() + "There are too few players. Resetting game.");
            arena.reset();
            return;
        }

        preGameSeconds--;
    }

}
