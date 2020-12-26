package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;

public class KitC extends Kit {

    //3
    public KitC(UUID uuid) {
        super(uuid, KitType.C);
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

        //prestige items

        //armor
        setIronHelmet(player);
        setIronChest(player);
        setIronPants(player);
        setIronBoots(player);
    }
}
