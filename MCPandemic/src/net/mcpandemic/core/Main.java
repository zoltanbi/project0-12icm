package net.mcpandemic.core;

import net.mcpandemic.core.ranks.*;
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
    private static FileManager fileManager;

    @Override
    public void onEnable() {
        System.out.println("ENABLING MCPANDEMIC");
        System.out.println("ENABLING MCPANDEMIC");
        System.out.println("ENABLING MCPANDEMIC");
        Main.instance = this;

        fileManager = new FileManager(this);
        new Config(this);

        new Manager();

        getCommand("arena").setExecutor(new ArenaCommand());
        getCommand("vote").setExecutor(new VoteCommand());
        Bukkit.getPluginManager().registerEvents(new RankListener(), this);
        getCommand("setRank").setExecutor(new RankCommand());
        getCommand("setInfectedRank").setExecutor(new InfectedRankCommand());
        getCommand("setPrestige").setExecutor(new SetPrestigeCommand());

        Bukkit.getPluginManager().registerEvents(new GameListener(), this);
        Bukkit.getPluginManager().registerEvents(
                (Listener) new VoteListener(),this);
    }

    public static Main getInstance() {
        return instance;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

}
