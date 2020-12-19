package com.roberto.core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * PShopCommand class creates Prestige Shop GUI on /pshop command
 */
public class PShopCommand implements CommandExecutor {
    private main main;

    public PShopCommand(main m) {
        this.main = m;
    }

    /**
     * Applies Prestige Shop GUI on command
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
            main.applyPShopUI((Player) sender);
        }

        return false;
    }
}
