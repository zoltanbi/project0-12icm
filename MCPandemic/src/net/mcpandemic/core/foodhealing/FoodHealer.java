package net.mcpandemic.core.foodhealing;

import net.mcpandemic.core.Manager;
import net.mcpandemic.core.gamestates.GameState;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.Objects;

public class FoodHealer implements Listener {

    public boolean removeItem(final Inventory inventory, final ItemStack itemStack, int quantity) {
        for (int i = inventory.getContents().length - 1; i >= 0 && quantity > 0; --i) {
            final ItemStack stack = inventory.getItem(i);
            if (stack != null && itemStack.isSimilar(stack)) {
                final int amount = stack.getAmount();
                if (amount <= quantity) {
                    inventory.setItem(i, new ItemStack(Material.AIR));
                    quantity -= amount;
                }
                else {
                    stack.setAmount(stack.getAmount() - quantity);
                    quantity -= quantity;
                }
            }
        }
        return quantity <= 0;
    }

    @EventHandler
    public void playerEat(final PlayerInteractEvent e) {
        if (Manager.getArena().getState() == GameState.LIVE || Manager.getArena().getState() == GameState.INFECTION &&
                e.getItem() != null && Food.getFood(e.getItem()) != null) {
            final Player player = e.getPlayer();
            if (e.getPlayer().getHealth() < 20.0) {
                e.setCancelled(true);
                e.setUseItemInHand(Event.Result.DENY);
                player.setHealth(Math.min(20.0, e.getPlayer().getHealth() + Objects.requireNonNull(Food.getFood(e.getItem())).getHeal()));
                removeItem((Inventory)player.getInventory(), e.getItem(), 1);
                //Potion potion = new Potion(PotionType.STRENGTH);
            }
        }
    }



    @EventHandler
    public void playerConsume(final PlayerItemConsumeEvent e) {
        if (!e.isCancelled() && Manager.getArena().getState() == GameState.LIVE || Manager.getArena().getState() == GameState.INFECTION &&
                e.getItem() != null && Food.getFood(e.getItem()) != null) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e)
    {
        e.setCancelled(true);
    }

}
