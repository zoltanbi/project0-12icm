package com.infected.ranks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static FileManager fileManager;

    @Override
    public void onEnable() {

        fileManager = new FileManager(this);
        Bukkit.getPluginManager().registerEvents(new RankListener(), this);
        getCommand("setRank").setExecutor(new RankCommand());
        getCommand("setInfectedRank").setExecutor(new InfectedRankCommand());
        getCommand("setPrestige").setExecutor(new SetPrestigeCommand());

    }

    public static FileManager getFileManager() {
        return fileManager;
    }

}
