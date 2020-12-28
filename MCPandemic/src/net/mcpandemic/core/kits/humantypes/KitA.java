package net.mcpandemic.core.kits.humantypes;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import org.bukkit.entity.Player;
import java.util.UUID;
import static net.mcpandemic.core.kits.humantypes.ItemHandler.*;

public class KitA extends Kit {

    //1
    public KitA(UUID uuid) {
        super(uuid, KitType.A);
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

        //prestige items

        //armor
        setIronHelmet(player);
        setIronChest(player);
        setIronPants(player);
        setIronBoots(player);
    }
}