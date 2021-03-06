package net.mcpandemic.core.gamestates;

import net.mcpandemic.core.Arena;
import net.mcpandemic.core.Config;
import net.mcpandemic.core.Main;
import net.mcpandemic.core.Manager;
import net.mcpandemic.core.infectedmanager.DatabaseManager;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

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
        for (UUID uuid : arena.getPlayers()) {
            if (DatabaseManager.getInfectedKit(Bukkit.getPlayer(uuid)) == KitType.MOTHERZOMBIE) {
                DatabaseManager.setInfectedKit(uuid, KitType.ZOMBIE);
            }
        }
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

        if (preGameSeconds >= 50) {
            Manager.turnGamemodeAdventure();
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
