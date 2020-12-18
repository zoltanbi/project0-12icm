package net.mcpandemic.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the driver class. It is responsible to put everything that
 * is needed together to drive the game mode.
 */
public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        System.out.println("ENABLING MCPANDEMIC");
        System.out.println("ENABLING MCPANDEMIC");
        System.out.println("ENABLING MCPANDEMIC");
        Main.instance = this;

        new Config(this);

        new Manager();

        getCommand("arena").setExecutor(new ArenaCommand());

        Bukkit.getPluginManager().registerEvents(new GameListener(), this);
    }

    public static Main getInstance() {
        return instance;
    }

}
