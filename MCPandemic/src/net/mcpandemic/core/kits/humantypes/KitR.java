package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;
import static net.mcpandemic.core.kits.humantypes.ItemHandler.setNetheriteBoots;

public class KitR extends Kit {

    //18
    public KitR(UUID uuid) {
        super(uuid, KitType.R);
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
        setArrows(player, 13);
        //armor
        setNetheriteHelmet(player);
        setIronChest(player);
        setNetheritePants(player);
        setNetheriteBoots(player);
    }
}
