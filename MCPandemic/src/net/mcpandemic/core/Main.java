package net.mcpandemic.core;

import net.mcpandemic.core.foodhealing.FoodHealer;
import net.mcpandemic.core.grenades.BlindGrenade;
import net.mcpandemic.core.grenades.FireGrenade;
import net.mcpandemic.core.grenades.FragGrenade;
import net.mcpandemic.core.grenades.SlowGrenade;
import net.mcpandemic.core.infectionkitgui.InfectionKitGUI;
import net.mcpandemic.core.infectionkitgui.InfectionKitListener;
import net.mcpandemic.core.mysterychest.MysteryChestListener;
import net.mcpandemic.core.permissions.PermissionHandler;
import net.mcpandemic.core.ranks.*;
import net.mcpandemic.core.shops.*;
import net.mcpandemic.core.voting.VoteCommand;
import net.mcpandemic.core.voting.VoteListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is the driver class. It is responsible to put everything that
 * is needed together to drive the game mode.
 */
public class Main extends JavaPlugin {

    private static Main instance;
    //private static FileManager fileManager;

    private static Connection connection;
    private String host, database, username, password;
    private int port;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new QualityOfLifeListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        System.out.println("ENABLING MCPANDEMIC");
        host = "na03-sql.pebblehost.com";
        port = 3306;
        database = "customer_156347_mcpandemic";
        username = "customer_156347_mcpandemic";
        password = "07Zoltan#11";

        try {
            openConnection();
            System.out.println("Connected to database!");
        } catch(SQLException exception) {
            exception.printStackTrace();
        }


        Main.instance = this;

        //fileManager = new FileManager(this);
        new Config(this);

        new Manager();

        getCommand("arena").setExecutor(new ArenaCommand());
        getCommand("vote").setExecutor(new VoteCommand());
        //Bukkit.getPluginManager().registerEvents(new RankListener(), this);
        getCommand("setRank").setExecutor(new RankCommand());
        getCommand("setInfectedRank").setExecutor(new InfectedRankCommand());
        getCommand("setPrestige").setExecutor(new SetPrestigeCommand());
        getCommand("kit").setExecutor(new InfectionKitGUI());
        getCommand("rankup").setExecutor(new PlayerRankupCommand());
        getCommand("shop").setExecutor(new ShopCommand(new Shop()));
        getCommand("pshop").setExecutor(new PShopCommand(new PrestigeShop()));

        Bukkit.getPluginManager().registerEvents(
                new TeleportFix(this), this);
        Bukkit.getPluginManager().registerEvents(
                new GameListener(), this);
        Bukkit.getPluginManager().registerEvents(new VoteListener(),this);
        //grenades
        Bukkit.getPluginManager().registerEvents(new FragGrenade(),this);
        Bukkit.getPluginManager().registerEvents(new SlowGrenade(),this);
        Bukkit.getPluginManager().registerEvents(new BlindGrenade(),this);
        Bukkit.getPluginManager().registerEvents(new FireGrenade(),this);

        Bukkit.getPluginManager().registerEvents(new InfectionKitListener(),this);
        Bukkit.getPluginManager().registerEvents(new FoodHealer(),this);
        Bukkit.getPluginManager().registerEvents(new MysteryChestListener(),this);
        Bukkit.getPluginManager().registerEvents(new ShopListener(), this);
        Bukkit.getPluginManager().registerEvents(new PShopListener(), this);
        Bukkit.getPluginManager().registerEvents(new PermissionHandler(), this);
    }


    private void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://" +
                this.host + ":" +
                this.port + "/" +
                this.database, this.username, this.password);
    }

    public static PreparedStatement prepareStatement(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static Main getInstance() {
        return instance;
    }

//    public static FileManager getFileManager() {
//        return fileManager;
//    }

}
