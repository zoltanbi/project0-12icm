package net.mcpandemic.core.shops;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PShopCommand implements CommandExecutor {
    private PrestigeShop instance;

    public PShopCommand(PrestigeShop s) {
        this.instance = s;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            instance.applyPShopUI((Player) sender);
        }

        return false;
    }
}
