package com.roberto.core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * PShopCommand class creates Shop GUI on /shop command
 */
public class ShopCommand implements CommandExecutor {
    private main main;

    public ShopCommand(main m) {
        this.main = m;
    }

    /**
     * Applies Shop GUI on command
     *
     * @param sender
     * @param command
     * @param s
     * @param strings
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            main.applyShopUI((Player) sender);
        }

        return false;
    }
}
