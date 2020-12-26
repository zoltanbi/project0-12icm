package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;
import static net.mcpandemic.core.kits.humantypes.ItemHandler.setIronBoots;

public class KitD extends Kit {

    //4
    public KitD(UUID uuid) {
        super(uuid, KitType.D);
    }

    @Override
    public void onStart(Player player) {

        //clear
        player.getInventory().clear();
        //sword and shield
        setShield(player);
        setStoneSword(player);

        //grenades

        //food
        setApple(player,2);
        setMelon(player,1);

        //prestige items

        //armor
        setIronHelmet(player);
        setIronChest(player);
        setIronPants(player);
        setIronBoots(player);
    }
}
