package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;
import static net.mcpandemic.core.kits.humantypes.ItemHandler.setNetheriteBoots;

public class KitS extends Kit {

    //19
    public KitS(UUID uuid) {
        super(uuid, KitType.S);
    }

    @Override
    public void onStart(Player player) {

        //clear
        player.getInventory().clear();
        //sword and shield
        setShield(player);
        setStoneSword(player);

        //grenades
        setSlime(player, 2);
        setFlash(player, 2);

        //food
        setApple(player,6);
        //prestige items

        //bow
        setBow(player);
        setArrows(player, 20);
        //armor
        setNetheriteHelmet(player);
        setNetheriteChest(player);
        setNetheritePants(player);
        setNetheriteBoots(player);
    }
}
