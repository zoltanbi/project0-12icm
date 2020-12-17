package net.mcpandemic.core;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the driver class. It is responsible to put everything that
 * is needed together to drive the game mode.
 */
public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        Main.instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

}
