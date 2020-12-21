//package net.mcpandemic.core.ranks;
//
//import net.mcpandemic.core.Main;
//import net.md_5.bungee.api.ChatColor;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.AsyncPlayerChatEvent;
//import org.bukkit.event.player.PlayerJoinEvent;
//
//public class RankListener implements Listener {
//
//    @EventHandler
//    public void onJoin(PlayerJoinEvent e) {
//
//        if (!e.getPlayer().hasPlayedBefore() ) {
//            Main.getFileManager().setRank(e.getPlayer(), Rank.GUEST);
//            Main.getFileManager().setInfectedRank(e.getPlayer(), InfectedRank.A);
//            Main.getFileManager().setPrestige(e.getPlayer(), Prestige.ONE);
//        }
//
//    }
//
//    @EventHandler
//    public void onChat(AsyncPlayerChatEvent e) {
//
//        Player player = e.getPlayer();
//        Rank rank = Main.getFileManager().getRank(player);
//        InfectedRank infectedRank = Main.getFileManager().getInfectedRank(player);
//        Prestige prestige = Main.getFileManager().getPrestige(player);
//
//        e.setCancelled(true);
//
//        for (Player onlinePlayers : e.getRecipients()) {
//            onlinePlayers.sendMessage(ChatColor.BLACK + "{" + rank.getColor()
//                    + ChatColor.BOLD + rank.getName() + ChatColor.RESET + ChatColor.BLACK + "}" +
//                    ChatColor.BLUE + "<" + ChatColor.YELLOW + prestige.getPrNum() +
//                    ChatColor.GOLD + prestige.getPrSymbol() + ChatColor.BLUE + ">" + ChatColor.GOLD + "[" +
//                    infectedRank.getColor() + infectedRank.getName() + ChatColor.GOLD +
//                    "] " + ChatColor.WHITE + player.getName() + ChatColor.GOLD + " >: "
//                    + ChatColor.WHITE + e.getMessage());
//        }
//
//    }
//
//}
