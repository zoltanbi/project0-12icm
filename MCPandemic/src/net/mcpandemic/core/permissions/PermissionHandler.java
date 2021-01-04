package net.mcpandemic.core.permissions;

import net.mcpandemic.core.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class PermissionHandler implements Listener {
    private final Main instance = Main.getInstance();
    public HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public void onEnable() {
        instance.getConfig().options().copyDefaults(true);
        instance.saveConfig();

        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    public void onDisable() {
        perms.clear();
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        permSetup(player);
    }

    @EventHandler
    public void leave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        perms.remove(player.getUniqueId());
    }

    public void permSetup(Player player) {
        PermissionAttachment attachment = player.addAttachment(instance);
        perms.put(player.getUniqueId(), attachment);
        permSetter(player.getUniqueId());
    }

    private void permSetter(UUID uuid) {
        PermissionAttachment attachment = this.perms.get(uuid);
        for (String groups : instance.getConfig().getConfigurationSection("Groups").getKeys(false)) {
            for (String permissions : instance.getConfig().getStringList("Groups." + groups + ".permissions")) {
                System.out.print(permissions);
                attachment.setPermission(permissions, true);
            }
        }
    }
}
