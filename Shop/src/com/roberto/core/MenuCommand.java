package com.roberto.core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {
    private main main;

    public MenuCommand(main m) {
        this.main = m;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            main.applyShopUI((Player) sender);
        }
        else {

        }

        return false;
    }
}
