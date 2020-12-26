package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;
import static net.mcpandemic.core.kits.humantypes.ItemHandler.setNetheriteBoots;

public class KitH extends Kit {

    //8
    public KitH(UUID uuid) {
        super(uuid, KitType.H);
    }

    @Override
    public void onStart(Player player) {

        //clear
        player.getInventory().clear();
        //sword and shield
        setShield(player);
        setStoneSword(player);

        //grenades
        setSlime(player, 1);

        //food
        setApple(player,3);
        setMelon(player,1);
        //prestige items
        //armor
        setIronHelmet(player);
        setIronChest(player);
        setIronPants(player);
        setNetheriteBoots(player);
    }
}
