package net.mcpandemic.core.gamestates;

import net.mcpandemic.core.Arena;
import net.mcpandemic.core.Main;
import net.mcpandemic.core.Manager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * The Game class is responsible for the game mode's logic
 * for infection. Currently being tested for block break events.
 */
public class Endgame extends BukkitRunnable {

    private Arena arena;
    private int endSeconds;

    public Endgame(Arena arena) {
        this.arena = arena;
        this.endSeconds = 9;
    }

    public void start() {
        arena.setState(GameState.ENDGAME);
        this.runTaskTimer(Main.getInstance(), 0, 20);
    }

    @Override
    public void run() {
        Manager.turnGamemodeAdventure();
        if (endSeconds == 0) {
            cancel();
            arena.reset();
            arena.resetParkourReward();
        }

        if (endSeconds == 2) {
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "Loading lobby...");
        }

        endSeconds--;
    }

}
