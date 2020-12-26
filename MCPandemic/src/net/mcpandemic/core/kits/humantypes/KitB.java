package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;

import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;

import java.util.UUID;

public class KitB extends Kit {

    //2
    public KitB(UUID uuid) {
        super(uuid, KitType.B);
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
        setApple(player,1);
        setMelon(player,1);

        //prestige items

        //armor
        setIronHelmet(player);
        setIronChest(player);
        setIronPants(player);
        setIronBoots(player);
    }
}
