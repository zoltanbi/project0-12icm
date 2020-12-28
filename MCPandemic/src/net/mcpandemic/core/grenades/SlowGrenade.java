package net.mcpandemic.core.grenades;

import net.mcpandemic.core.Main;
import net.mcpandemic.core.Manager;
import net.mcpandemic.core.gamestates.GameState;
import net.mcpandemic.core.teams.Team;
import net.minecraft.server.v1_16_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class SlowGrenade implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (Manager.getArena().getState() == GameState.INFECTION) {
            if(e.getMaterial() == Material.SLIME_BALL) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {

                    p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);

                    final Item grenade = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.SLIME_BALL));
                    grenade.setVelocity(p.getLocation().getDirection().multiply(0.8D));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            Location grenadeCoords = grenade.getLocation();
                            grenade.getWorld().createExplosion(grenade.getLocation().getX(), grenade.getLocation().getY(), grenade.getLocation().getZ(), 0.01F, false, false);
                            List<Entity> entities = (List<Entity>) grenadeCoords.getWorld().getNearbyEntities(grenadeCoords, 5, 5, 5);
                            for (Entity e : entities) {
                                if (e instanceof Player) {
                                    if (Manager.getArena().getTeam(((Player) e)) == Team.ZOMBIE) {
                                        PacketPlayOutAnimation damage = new PacketPlayOutAnimation(((CraftPlayer) e).getHandle(), 1);
                                        ReflectionUtils.sendPacket((Player) e, damage);

                                        ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 2));
                                    }
                                }
                            }
                            grenade.remove();
                        }
                    }, 40);
                }
            }
        }
    }

}
