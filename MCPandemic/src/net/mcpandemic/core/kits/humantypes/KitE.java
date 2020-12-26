package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;

public class KitE extends Kit {

    //5
    public KitE(UUID uuid) {
        super(uuid, KitType.E);
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
        //armor
        setIronHelmet(player);
        setIronChest(player);
        setIronPants(player);
        setNetheriteBoots(player);
    }
}
