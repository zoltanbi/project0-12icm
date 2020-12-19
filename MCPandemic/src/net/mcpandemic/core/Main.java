package net.mcpandemic.core;

import net.mcpandemic.core.voting.VoteCommand;
import net.mcpandemic.core.voting.VoteGUICommand;
import net.mcpandemic.core.voting.VoteListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
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
        getCommand("vote").setExecutor(new VoteGUICommand());

        Bukkit.getPluginManager().registerEvents(new GameListener(), this);
        Bukkit.getPluginManager().registerEvents(
                (Listener) new VoteListener(),this);
    }

    public static Main getInstance() {
        return instance;
    }

}
