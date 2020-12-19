package net.mcpandemic.core.voting;

import net.mcpandemic.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class VoteGUICommand implements CommandExecutor {
    private Main instance;

    public VoteGUICommand(Main instance) {
        this.instance = instance;
    }

    /**
     * Opens up Voting GUI on /vote command
     *
     * @param sender
     * @param command
     * @param s
     * @param strings
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            applyVoteUI((Player) sender);
        }

        return false;
    }

    /**
     * Creates Voting GUI
     *
     * @param p
     */
    private void applyVoteUI(Player p) {
        //SETUP
        Inventory vGUI = Bukkit.createInventory(
                null, 9, ChatColor.DARK_GRAY + "Vote");

        //LISTS
        List<String> num1Lore = new ArrayList<>();
        num1Lore.add(ChatColor.GRAY + "Click to select this Map.");

        List<String> num2Lore = new ArrayList<>();
        num2Lore.add(ChatColor.GRAY + "Click to select this Map.");

        List<String> num3Lore = new ArrayList<>();
        num3Lore.add(ChatColor.GRAY + "Click to select this Map.");

        List<String> num4Lore = new ArrayList<>();
        num4Lore.add(ChatColor.GRAY + "Click to select this Map.");

        List<String> num5Lore = new ArrayList<>();
        num5Lore.add(ChatColor.GRAY + "Click to select this Map.");


        //ITEMSTACKS

        //Player heads (currently broken)
 /*       ItemStack num1 = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta num1Meta = (SkullMeta) num1.getItemMeta();
        assert num1Meta != null;

        GameProfile num1Profile = new GameProfile(UUID.randomUUID(),
                null);
        num1Profile.getProperties().put("textures", new Property("textures",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFhOTQ2M2ZkM2M0MzNkNWUxZDlmZWM2ZDVkNGIwOWE4M2E5NzBiMGI3NGRkNTQ2Y2U2N2E3MzM0OGNhYWIifX19"));
        Field num1Field;
        try {
            num1Field = num1Meta.getClass().getDeclaredField(
                    "num1Profile");
            num1Field.setAccessible(true);
            num1Field.set(num1Meta, num1Profile);
        } catch (NoSuchFieldException | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
        }

        num1.setItemMeta(num1Meta);

        ItemStack num2 = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta num2Meta = (SkullMeta) num2.getItemMeta();
        assert num2Meta != null;

        GameProfile num2Profile = new GameProfile(UUID.randomUUID(),
                null);
        num2Profile.getProperties().put("textures", new Property("textures",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWNiNDE5ZDk4NGQ4Nzk2MzczYzk2NDYyMzNjN2EwMjY2NGJkMmNlM2ExZDM0NzZkZDliMWM1NDYzYjE0ZWJlIn19fQ=="));
        Field num2Field;
        try {
            num2Field = num2Meta.getClass().getDeclaredField(
                    "num2Profile");
            num2Field.setAccessible(true);
            num2Field.set(num2Meta, num2Profile);
        } catch (NoSuchFieldException | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
        }

        num2.setItemMeta(num2Meta);

        ItemStack num3 = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta num3Meta = (SkullMeta) num3.getItemMeta();
        assert num3Meta != null;

        GameProfile num3Profile = new GameProfile(UUID.randomUUID(),
                null);
        num2Profile.getProperties().put("textures", new Property("textures",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjhlYmFiNTdiNzYxNGJiMjJhMTE3YmU0M2U4NDhiY2QxNGRhZWNiNTBlOGY1ZDA5MjZlNDg2NGRmZjQ3MCJ9fX0="));
        Field num3Field;
        try {
            num3Field = num3Meta.getClass().getDeclaredField(
                    "num3Profile");
            num3Field.setAccessible(true);
            num3Field.set(num3Meta, num3Profile);
        } catch (NoSuchFieldException | IllegalArgumentException
               | IllegalAccessException e) {
            e.printStackTrace();
        }

        num3.setItemMeta(num3Meta);

        ItemStack num4 = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta num4Meta = (SkullMeta) num4.getItemMeta();
        assert num4Meta != null;

        GameProfile num4Profile = new GameProfile(UUID.randomUUID(),
                null);
        num4Profile.getProperties().put("textures", new Property("textures",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjJiZmNmYjQ4OWRhODY3ZGNlOTZlM2MzYzE3YTNkYjdjNzljYWU4YWMxZjlhNWE4YzhhYzk1ZTRiYTMifX19"));
        Field num4Field;
        try {
            num4Field = num4Meta.getClass().getDeclaredField(
                    "num4Profile");
            num4Field.setAccessible(true);
            num4Field.set(num4Meta, num4Profile);
        } catch (NoSuchFieldException | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
        }

        num4.setItemMeta(num4Meta);

        ItemStack num5 = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta num5Meta = (SkullMeta) num5.getItemMeta();
        assert num5Meta != null;

        GameProfile num5Profile = new GameProfile(UUID.randomUUID(),
                null);
        num5Profile.getProperties().put("textures", new Property("textures",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWY0ZWNmMTEwYjBhY2VlNGFmMWRhMzQzZmIxMzZmMWYyYzIxNjg1N2RmZGE2OTYxZGVmZGJlZTdiOTUyOCJ9fX0="));
        Field num5Field;
        try {
            num5Field = num5Meta.getClass().getDeclaredField(
                    "num5Profile");
            num5Field.setAccessible(true);
            num5Field.set(num5Meta, num5Profile);
        } catch (NoSuchFieldException | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
        }

        num5.setItemMeta(num5Meta);*/

        //each item's display name has to be accessed from 5 random Maps
        ItemStack num1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta num1Meta = num1.getItemMeta();
        assert num1Meta != null;
        num1Meta.setDisplayName(ChatColor.RED + "MAP 1");

        ItemStack num2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta num2Meta = num2.getItemMeta();
        assert num2Meta != null;
        num2Meta.setDisplayName(ChatColor.RED + "MAP 2");

        ItemStack num3 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta num3Meta = num3.getItemMeta();
        assert num3Meta != null;
        num3Meta.setDisplayName(ChatColor.RED + "MAP 3");

        ItemStack num4 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta num4Meta = num4.getItemMeta();
        assert num4Meta != null;
        num4Meta.setDisplayName(ChatColor.RED + "MAP 4");

        ItemStack num5 = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        ItemMeta num5Meta = num5.getItemMeta();
        assert num5Meta != null;
        num5Meta.setDisplayName(ChatColor.RED + "MAP 5");


        //ITEM SETTINGS
        vGUI.setItem(0,num1);
        vGUI.setItem(1,num2);
        vGUI.setItem(2,num3);
        vGUI.setItem(3,num4);
        vGUI.setItem(4,num5);

        //FINAL
        p.openInventory(vGUI);
    }
}
