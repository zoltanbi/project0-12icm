package net.mcpandemic.core.grenades;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import me.libraryaddict.disguise.utilities.packets.packethandlers.PacketHandlerEntityStatus;
import net.mcpandemic.core.Main;
import net.mcpandemic.core.Manager;
import net.mcpandemic.core.gamestates.GameState;
import net.mcpandemic.core.teams.Team;
import net.minecraft.server.v1_16_R2.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
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

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlindGrenade implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (Manager.getArena().getState() == GameState.INFECTION) {
            if (e.getMaterial() == Material.GHAST_TEAR) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {

                    p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);

                    final Item grenade = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.GHAST_TEAR));
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

                                        ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 9));
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
