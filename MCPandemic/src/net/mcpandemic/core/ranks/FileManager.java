//package net.mcpandemic.core.ranks;
//
//import net.mcpandemic.core.Main;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.entity.Player;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//public class FileManager {
//
//    private static Main main;
//    private File file;
//    private YamlConfiguration config;
//
//    // UUID: rank
//
//
//    public FileManager(Main main) {
//        FileManager.main = main;
//
//        file = new File(main.getDataFolder(), "data.yml");
//
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch(IOException e) {
//                e.printStackTrace();
//            }
//        }
//        config = YamlConfiguration.loadConfiguration(file);
//    }
//
//    /**
//     * Method used to set Rank of player using UUID. Used in
//     * RankListener class.
//     * @param player a Player.
//     * @param rank a Rank.
//     */
//    public void setRank(Player player, Rank rank) {
//        config.set(player.getUniqueId().toString() + ".Rank", rank.name());
//        save();
//    }
//
//    /**
//     * Method used to set Rank of player using UUID. Used in RankCommand
//     * class.
//     * @param uuid a UUID.
//     * @param rank a Rank.
//     */
//    public void setRank(UUID uuid, Rank rank) {
//        config.set(uuid.toString() + ".Rank", rank.name());
//        save();
//    }
//
//    /**
//     * Method used to get Rank of player. Used in RankListener class.
//     * @param player a Player.
//     * @return Rank enum of player.
//     */
//    public Rank getRank(Player player) {
//        return Rank.valueOf(config.getString(player.getUniqueId().toString() + ".Rank"));
//    }
//
//    /**
//     * Method used to set InfectedRank of player using UUID. Used in
//     * RankListener class.
//     * @param player a Player.
//     * @param rank an InfectedRank.
//     */
//    public void setInfectedRank(Player player, InfectedRank rank) {
//        config.set(player.getUniqueId().toString() + ".InfectedRank", rank.name());
//        save();
//    }
//
//    public void setInfectedRank(UUID uuid, InfectedRank rank) {
//        config.set(uuid.toString() + ".InfectedRank", rank.name());
//        save();
//    }
//
//    public InfectedRank getInfectedRank(Player player) {
//        return InfectedRank.valueOf(config.getString(player.getUniqueId().toString() + ".InfectedRank"));
//    }
//
//    public void setPrestige(Player player, Prestige prestige) {
//        config.set(player.getUniqueId().toString() + ".PrestigeRank", prestige.name());
//        save();
//    }
//
//    public void setPrestige(UUID uuid, Prestige prestige) {
//        config.set(uuid.toString() + ".PrestigeRank", prestige.name());
//        save();
//    }
//
//    public Prestige getPrestige(Player player) {
//        return Prestige.valueOf(config.getString(player.getUniqueId().toString() + ".PrestigeRank"));
//    }
//
//    public void setRankPoints(Player player, int amount) {
//        config.set(player.getUniqueId().toString() + ".Currency.Rankpoints", amount);
//        save();
//    }
//
//    public void setRankPoints(UUID uuid, int amount) {
//        config.set(uuid.toString() + ".Currency.Rankpoints", amount);
//        save();
//    }
//
//    public void setPrestigeTokens(Player player, int amount) {
//        config.set(player.getUniqueId().toString() + ".Currency.PrestigeTokens", amount);
//        save();
//    }
//
//    public void setPrestigeTokens(UUID uuid, int amount) {
//        config.set(uuid.toString() + ".Currency.PrestigeTokens", amount);
//        save();
//    }
//
//    public void setZombieKit(Player player, int amount) {
//        config.set(player.getUniqueId().toString() + ".Currency.PrestigeTokens", amount);
//        save();
//    }
//
//    public void setZombieKit(UUID uuid, int amount) {
//        config.set(uuid.toString() + ".Currency.PrestigeTokens", amount);
//        save();
//    }
//
//    private void save() {
//        try {
//            config.save(file);
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
