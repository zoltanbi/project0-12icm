package net.mcpandemic.core.infectedmanager;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DisguiseManager {

    public DisguiseManager() {}

    public static void setZombieDisguise(Player player) {
        KitType kitType = DatabaseManager.getInfectedKit(player);
        DisguiseType disguise;
        switch(kitType) {
            case ZOMBIE:
                disguise = DisguiseType.ZOMBIE;
                break;
            case MOTHERZOMBIE:
                disguise = DisguiseType.ZOMBIFIED_PIGLIN;
                break;
            case SKELETON:
                disguise = DisguiseType.SKELETON;
                break;
            default:
                disguise = DisguiseType.ZOMBIE;
        }
        MobDisguise mobDisguise = new MobDisguise(disguise);
        mobDisguise.setEntity(player);
        mobDisguise.setViewSelfDisguise(false);
        FlagWatcher watcher = mobDisguise.getWatcher();
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack[] itemStack = {air, air, air, air};
        watcher.setArmor(itemStack);
        //Actionabe
        mobDisguise.setNotifyBar(DisguiseConfig.NotifyBar.NONE);
        //player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("You are now INFECTED!"));
        mobDisguise.startDisguise();
    }


    public static void removeDisguise(Player player) {
        Disguise d = DisguiseAPI.getDisguise(player);
        d.removeDisguise();
    }
}
