package net.mcpandemic.core;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * The Countdown class is responsible for the countdown logic. Announces
 * at certain periods to let players know when the GameState's are
 * changing.
 */
public class Countdown extends BukkitRunnable {

    private Arena arena;
    private int seconds;

    public Countdown(Arena arena) {
        this.arena = arena;
        this.seconds = Config.getCountdownSeconds();
    }

    public void begin() {
        arena.setState(GameState.COUNTDOWN);
        this.runTaskTimer(Main.getInstance(), 0, 20);

    }

    public void skipCountdown() {
        cancel();
        arena.startGame();
    }

    @Override
    public void run() {
        if (seconds == 0) {
            cancel();
            arena.startGame();

        }
        if (seconds % 30 == 0 || seconds <= 10) {
            if (seconds == 1 ) {
                arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "Game will start in 1 second.");
            } else if (seconds != 0) {
                arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "Game will start in " + seconds + " seconds.");
            }
        }

        if (arena.getPlayers().size() < Config.getRequiredPlayers()) {
            cancel();
            arena.setState(GameState.RECRUITING);
            arena.sendMessage(Manager.getServerTag() + "There are too few players. Countdown stopped.");
            return;
        }

        seconds--;
    }
}
