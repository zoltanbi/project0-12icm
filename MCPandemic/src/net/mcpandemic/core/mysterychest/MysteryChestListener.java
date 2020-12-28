package net.mcpandemic.core.mysterychest;

import net.mcpandemic.core.Manager;
import net.mcpandemic.core.gamestates.GameState;
import net.mcpandemic.core.infectedmanager.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.Random;

public class MysteryChestListener implements Listener {

    public MysteryItems[] items = {MysteryItems.CAKE, MysteryItems.PORKCHOP, MysteryItems.GOLDEN_APPLE,
            MysteryItems.FIRE_ASPECT, MysteryItems.BOW_V1, MysteryItems.BOW_V2, MysteryItems.FIRE_GRENADES,
            MysteryItems.FRAG_GRENADES, MysteryItems.FLASH_GRENADES, MysteryItems.STICKY_GRENADES, MysteryItems.RANKPOINTS};

    public void recieveItem(MysteryItems item, Player player) {
        switch(item) {
            case CAKE:
                ItemStack cake = new ItemStack(Material.CAKE);
                ItemMeta cakeMeta = cake.getItemMeta();
                cakeMeta.setDisplayName(ChatColor.RED + "Heal 8❤");
                cake.setItemMeta(cakeMeta);
                player.getInventory().addItem(cake);
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "a Cake" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case PORKCHOP:
                ItemStack pork = new ItemStack(Material.PORKCHOP, 2);
                ItemMeta porkMeta = pork.getItemMeta();
                porkMeta.setDisplayName(ChatColor.RED + "Heal 4❤");
                pork.setItemMeta(porkMeta);
                player.getInventory().addItem(pork);
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "2 Porkchops" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case GOLDEN_APPLE:
                ItemStack goldApple = new ItemStack(Material.GOLDEN_APPLE);
                ItemMeta goldAppleMeta = goldApple.getItemMeta();
                goldAppleMeta.setDisplayName(ChatColor.RED + "Heal FULL❤");
                goldApple.setItemMeta(goldAppleMeta);
                player.getInventory().addItem(goldApple);
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "a Golden Apple" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case FIRE_ASPECT:
                ItemStack woodSword = new ItemStack(Material.WOODEN_SWORD);
                ItemMeta woodSwordMeta = woodSword.getItemMeta();
                woodSwordMeta.setDisplayName(ChatColor.GOLD + "Fire Sword");
                woodSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
                woodSword.setItemMeta(woodSwordMeta);
                player.getInventory().addItem(woodSword);
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "Fire Sword" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case BOW_V1:
                ItemStack powerBow = new ItemStack(Material.BOW);
                ItemMeta powerBowMeta = powerBow.getItemMeta();
                powerBowMeta.setDisplayName(ChatColor.BLUE + "Power Bow");
                powerBowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                powerBow.setItemMeta(powerBowMeta);
                player.getInventory().addItem(powerBow);
                player.getInventory().addItem(new ItemStack(Material.ARROW, 10));
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "a Power Bow" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case BOW_V2:
                ItemStack punchBow = new ItemStack(Material.BOW);
                ItemMeta punchBowMeta = punchBow.getItemMeta();
                punchBowMeta.setDisplayName(ChatColor.DARK_AQUA + "Punch Bow");
                punchBowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
                punchBow.setItemMeta(punchBowMeta);
                player.getInventory().addItem(punchBow);
                player.getInventory().addItem(new ItemStack(Material.ARROW, 10));
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "a Punch Bow" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case FIRE_GRENADES:
                ItemStack fireGrenade = new ItemStack(Material.MAGMA_CREAM, 3);
                ItemMeta fireGrenadeMeta = fireGrenade.getItemMeta();
                fireGrenadeMeta.setDisplayName(ChatColor.RED + "Fire Grenade");
                fireGrenade.setItemMeta(fireGrenadeMeta);
                player.getInventory().addItem(fireGrenade);
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "3 Fire Grenades" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case FRAG_GRENADES:
                ItemStack fragGrenade = new ItemStack(Material.EGG, 3);
                ItemMeta fragGrenadeMeta = fragGrenade.getItemMeta();
                fragGrenadeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Frag Grenade");
                fragGrenade.setItemMeta(fragGrenadeMeta);
                player.getInventory().addItem(fragGrenade);
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "3 Frag Grenades" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case FLASH_GRENADES:
                ItemStack tear = new ItemStack(Material.GHAST_TEAR, 3);
                ItemMeta tearMeta = tear.getItemMeta();
                tearMeta.setDisplayName(ChatColor.GOLD + "Flash Grenade");
                tear.setItemMeta(tearMeta);
                player.getInventory().addItem(tear);
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "3 Flash Grenades" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case STICKY_GRENADES:
                ItemStack slime = new ItemStack(Material.SLIME_BALL, 3);
                ItemMeta slimeMeta = slime.getItemMeta();
                slimeMeta.setDisplayName(ChatColor.GREEN + "Sticky Grenade");
                slime.setItemMeta(slimeMeta);
                player.getInventory().addItem(slime);
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "3 Sticky Grenades" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                break;
            case RANKPOINTS:
                Manager.getArena().sendMessage(Manager.getServerTag() + ChatColor.GREEN + player.getName() + ChatColor.LIGHT_PURPLE +
                        " found " + ChatColor.GREEN + "1000 Rankpoints" + ChatColor.LIGHT_PURPLE + " in a " + ChatColor.GREEN + "Mystery Chest!");
                player.sendMessage(Manager.getServerTag() + ChatColor.GREEN + "You got " + ChatColor.YELLOW + "1000 Rankpoints" +
                        ChatColor.GREEN + " from your Mystery Chest!");
                try {
                    DatabaseManager.setRankPoints(player.getUniqueId(), 1000);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                break;
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (Manager.getArena().getState() == GameState.INFECTION) {
            if (e.getMaterial() == Material.CHEST) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                    Random random = new Random();
                    int a = random.nextInt(items.length);
                    MysteryItems temp = items[a];
                    recieveItem(temp, player);
                }
            }
        }
    }

}
