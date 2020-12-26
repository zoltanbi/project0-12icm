package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;
import static net.mcpandemic.core.kits.humantypes.ItemHandler.setNetheriteBoots;

public class KitI extends Kit {

    //9
    public KitI(UUID uuid) {
        super(uuid, KitType.I);
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
        setApple(player,4);
        //prestige items
        //armor
        setIronHelmet(player);
        setIronChest(player);
        setIronPants(player);
        setNetheriteBoots(player);
    }
}
