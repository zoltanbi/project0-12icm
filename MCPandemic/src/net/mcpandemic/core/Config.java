package net.mcpandemic.core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * This class is responsible for the config.yml logic. All methods in
 * this class are static.
 */
public class Config {

    private static Main main;

    public Config(Main main) {
        Config.main = main;

        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();
    }

    public static int getRequiredPlayers() {
        return main.getConfig().getInt("required-players");
    }

    public static int getVoteSeconds() {
        return main.getConfig().getInt("vote-seconds");
    }

    public static int getCountdownSeconds() {
        return main.getConfig().getInt("countdown-seconds");
    }

    public static Location getLobbySpawn() {
        World world = Bukkit.getServer().getWorld("world");
        return new Location(world, 77.488, 13.0, 90.475, 0,0);

    }

    public static Location getArenaSpawn(int id) {
        return new Location(
                Bukkit.getWorld(main.getConfig().getString(
                        "arenas." + id + ".world")),
                main.getConfig().getDouble("arenas." + id + ".x"),
                main.getConfig().getDouble("arenas." + id + ".y"),
                main.getConfig().getDouble("arenas." + id + ".z"),
                main.getConfig().getInt("arenas." + id + ".yaw"),
                (float) main.getConfig().getDouble("arenas." + id +
                        ".pitch")
        );

    }

    public static int getArenaAmount() {
        return main.getConfig().getConfigurationSection("arenas.").getKeys(
                false).size();
    }

    public static int getLobbyAmount() {
        return main.getConfig().getConfigurationSection("lobby-spawn.").getKeys(
                false).size();
    }

}
