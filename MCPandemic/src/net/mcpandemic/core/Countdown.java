package net.mcpandemic.core;

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

    @Override
    public void run() {
        if (seconds == 0) {
            cancel();
            arena.start();

        }
        if (seconds % 30 == 0 || seconds <=10) {
            if (seconds == 1 ) {
                arena.sendMessage("Game will start in 1 second.");
            } else {
                arena.sendMessage("Game will start in " + seconds + " seconds.");
            }
        }

        if (arena.getPlayers().size() < Config.getRequiredPlayers()) {
            cancel();
            arena.setState(GameState.RECRUITING);
            arena.sendMessage("There are too few players. Countdown stopped.");
            return;
        }

        seconds--;
    }
}
