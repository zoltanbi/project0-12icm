package net.mcpandemic.core.grenades;

import net.mcpandemic.core.Main;
import net.mcpandemic.core.Manager;
import net.mcpandemic.core.gamestates.GameState;
import net.mcpandemic.core.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FragGrenade implements Listener {
    private final Main instance;

    public FragGrenade(Main m) {
        this.instance = m;
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getMaterial() == Material.EGG) {
            p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);

            final Item grenade = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.EGG));
            grenade.setVelocity(p.getLocation().getDirection().multiply(0.8D));

            Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
                @Override
                public void run() {
                    Location grenadeCoords = grenade.getLocation();
                    grenade.getWorld().createExplosion(grenade.getLocation().getX(), grenade.getLocation().getY(), grenade.getLocation().getZ(), 3, false, false);

                    List<Entity> entities = (List<Entity>) grenadeCoords.getWorld().getNearbyEntities(grenadeCoords, 3,3,3);
                    for (Entity e : entities) {
                        if (e instanceof Player) {
                            if (((Player) e).getHealth() <= 3.5 && Manager.getArena().getTeam((Player)e) == Team.ZOMBIE && Manager.getArena().getState() == GameState.INFECTION) {
                                ((Player) e).setHealth(0);
                            }
                            else if(Manager.getArena().getTeam((Player)e) == Team.ZOMBIE && Manager.getArena().getState() == GameState.INFECTION) {
                                ((Player) e).setHealth(((Player) e).getHealth() - 7);
                            }
                        }
                    }

                }
            }, 20*2);
        }

    }

    @EventHandler
    public void onFoodLevelChange(PlayerEggThrowEvent e)
    {
        e.setHatching(false);
    }

}