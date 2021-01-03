package net.mcpandemic.core.gamestates;

import net.mcpandemic.core.Arena;
import net.mcpandemic.core.Config;
import net.mcpandemic.core.Main;
import net.mcpandemic.core.Manager;
import net.mcpandemic.core.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
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
        arena.setMotherZombie();

        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }
        this.runTaskTimer(Main.getInstance(), 0, 20);
    }

    public void skipInfection() {
        cancel();
        arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "Infection phase skipped... Congratulations to these survivors:" + arena.getSurvivors());
        arena.startEndgame();
        try {
            arena.infectedWinBonus(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (UUID uuid : Manager.getArena().getPlayers()) {
            Bukkit.getPlayer(uuid).setLevel(seconds);
            Bukkit.getPlayer(uuid).setExp(0);
        }

        if (seconds == 0) {
            cancel();
            arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "Time limit reached! Congratulations to these survivors:" + arena.getSurvivors());
            try {
                arena.survivorWinBonus(1);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            arena.startEndgame();
        }

        if (!arena.areSurvivors()) {
            cancel();
            arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "All humans had been infected! Zombies have won! ");
            try {
                arena.infectedWinBonus(1);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            arena.startEndgame();
        }

        if (seconds == 180) {
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are " + ChatColor.YELLOW + "3" + ChatColor.AQUA + " minutes left!");
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are currently " + ChatColor.YELLOW + arena.getTeamCount(Team.HUMAN) + ChatColor.AQUA + " humans and " + ChatColor.YELLOW + arena.getTeamCount(Team.ZOMBIE) + ChatColor.AQUA + " zombies!");
        }
        if (seconds == 120) {
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are " + ChatColor.YELLOW + "2" + ChatColor.AQUA + " minutes left!");
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are currently " + ChatColor.YELLOW + arena.getTeamCount(Team.HUMAN) + ChatColor.AQUA + " humans and " + ChatColor.YELLOW + ChatColor.YELLOW + arena.getTeamCount(Team.ZOMBIE) + ChatColor.AQUA + " zombies!");
        }
        if (seconds == 60) {
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There is " + ChatColor.YELLOW + "1" + ChatColor.AQUA + " minute left!");
            arena.sendMessage(Manager.getServerTag() + ChatColor.AQUA + "There are currently " + ChatColor.YELLOW + arena.getTeamCount(Team.HUMAN) + ChatColor.AQUA + " humans and " + ChatColor.YELLOW + ChatColor.YELLOW + arena.getTeamCount(Team.ZOMBIE) + ChatColor.AQUA + " zombies!");
        }
        if (seconds <= 10) {
            if (seconds != 0) {
                for (UUID uuid : Manager.getArena().getPlayers()) {
                    Bukkit.getPlayer(uuid).playSound(Bukkit.getPlayer(uuid).getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);
                }
            }
        }

        if (seconds >= 230) {
            Manager.turnGamemodeAdventure();
        }

        if (arena.getPlayers().size() < Config.getRequiredPlayers()) {
            cancel();
            arena.sendMessage(Manager.getServerTag() + "There are too few players. Resetting game.");
            arena.reset();
            return;
        }


        seconds--;
    }

}
