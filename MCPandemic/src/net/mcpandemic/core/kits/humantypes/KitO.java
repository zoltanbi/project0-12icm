package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;
import static net.mcpandemic.core.kits.humantypes.ItemHandler.setNetheriteBoots;

public class KitO extends Kit {

    //15
    public KitO(UUID uuid) {
        super(uuid, KitType.O);
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
        setFlash(player, 1);

        //food
        setApple(player,5);
        //prestige items

        //bow
        setBow(player);
        setArrows(player, 5);
        //armor
        setNetheriteHelmet(player);
        setIronChest(player);
        setNetheritePants(player);
        setNetheriteBoots(player);
    }
}
