package net.mcpandemic.core.shops;

import net.mcpandemic.core.Arena;
import net.mcpandemic.core.gamestates.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mcpandemic.core.Manager.getArenas;

public class ShopCommand implements CommandExecutor {
    private Shop instance;
    private Arena arenaInstance;

    public ShopCommand(Shop s) {
        this.instance = s;
        this.arenaInstance = getArenas();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player && arenaInstance.getState() == GameState.INFECTION) {
            instance.applyShopUI((Player) sender);
        }

        return false;
    }
}
