package net.mcpandemic.core;

import com.sun.istack.internal.NotNull;
import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import net.mcpandemic.core.kits.humantypes.*;
import net.mcpandemic.core.voting.Maps;
import net.mcpandemic.core.voting.VoteCountdown;
import net.mcpandemic.core.voting.VoteMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * The Arena class is responsible for driving the game mode in each
 * instance. Contains:
 *     - id (int)
 *     - players (ArrayList)
 *     - spawn (Location)
 *     - state (GameState)
 *     - countdown (Countdown)
 *     - game (Game)
 */
public class Arena {

    private ArrayList<UUID> players;
    private Location spawn;
    private Location mapSpawn;
    private GameState state;
    private VoteMap voteMap;
    LinkedHashMap<Maps, Integer> votableMaps;
    private Maps[] mapArray;
    private ArrayList<UUID> votedPlayers;
    private VoteCountdown voteCountdown;
    private Countdown countdown;
    private Game game;

    public Arena() {
        //initial setup
        players = new ArrayList<>();
        World world = Bukkit.getServer().getWorld("lobby");
        spawn = new Location(world, 77.4, 13.0, 90.47, 0, 0);
        //gamestate
        state = GameState.RECRUITING;
        //voting
        voteMap = new VoteMap();
        votableMaps = voteMap.getVotableMaps();
        mapArray = new Maps[5];
        votedPlayers = new ArrayList<>();
        voteCountdown = new VoteCountdown(this);
        //rest
        countdown = new Countdown(this);
        game = new Game(this);
    }

    public void startCountdown() {
        countdown.begin();
    }

    public void startGame() {
        game.start();
    }

    public void teleportPlayersToArena() {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).teleport(mapSpawn);
        }
    }

    public void reset() {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).getInventory().clear();
            Bukkit.getPlayer(uuid).teleport(spawn);
        }

        voteMap = new VoteMap();
        votableMaps = voteMap.getVotableMaps();
        mapArray = new Maps[5];
        votedPlayers = new ArrayList<>();
        voteCountdown = new VoteCountdown(this);
        //rest
        countdown = new Countdown(this);
        game = new Game(this);
        if(players.size() >= Config.getRequiredPlayers()) {
            voteCountdown.startVote();
        }
    }

    /**
     * Sends a message to everyone in the Arena.
     * @param message a String.
     */
    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    /**
     * This method adds a Player to the players ArrayList. The Player is
     * then teleported to the Arena's spawn Location.
     * @param player a Player.
     */
    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        if (state == GameState.RECRUITING || state == GameState.VOTING ||
                state == GameState.COUNTDOWN) {
            player.teleport(spawn);
            if (state == GameState.RECRUITING && players.size() >= Config.getRequiredPlayers()) {
                voteCountdown.startVote();
            }
            if (state == GameState.VOTING) {
                player.sendMessage(Manager.getServerTag() + ChatColor.DARK_AQUA + "Hello, " + ChatColor.YELLOW
                        + player.getName() + ChatColor.DARK_AQUA + ", vote for the next map! " + ChatColor.YELLOW + "(/vote)");
            }
        }
        if (state == GameState.LIVE) {
            player.teleport(mapSpawn);
        }

    }

    /**
     * This method removes a Player from the players ArrayList. The
     * Player is then teleported back to the lobby-spawn Location.
     * @param player a Player.
     */
    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        votedPlayers.remove(player.getUniqueId());
        if (player.isOnline()) {
            player.teleport(spawn);
            player.sendMessage(ChatColor.RED + "You're not supposed to be out of the game... Contact a staff member");
        }

        if (players.size() <= Config.getRequiredPlayers() && (state.equals(GameState.COUNTDOWN) || state.equals(GameState.VOTING))) {
            reset();
        }

        if (players.size() == 0 && (state.equals(GameState.COUNTDOWN) || state.equals(GameState.VOTING))) {
            reset();
        }
    }

    public void promptVotableMaps() {
        StringBuilder prompt = new StringBuilder();
        prompt.append(Manager.getServerTag() + ChatColor.DARK_GREEN + "Vote for the next map! " + ChatColor.YELLOW + "(/vote <id>)");
        int i = 1;
        for (Maps map : votableMaps.keySet()) {
            mapArray[i-1] = map;
            prompt.append(  "\n" + Manager.getServerTag() + ChatColor.DARK_GREEN + i + ". " + ChatColor.AQUA + map.getMapName());
            i++;
        }
        sendMessage(prompt.toString());
    }

    public void castVote(Player player, int id) {
        votedPlayers.add(player.getUniqueId());
        votableMaps.put(mapArray[id-1], votableMaps.get(mapArray[id-1]) + 1);
        sendMessage(Manager.getServerTag() + ChatColor.DARK_AQUA + "Player " + ChatColor.YELLOW + player.getName() + ChatColor.DARK_AQUA + " voted for " + ChatColor.YELLOW + mapArray[id-1].getMapName());
    }

    /*
     * GETTERS:
     */

    public String getID() {
        return "main";
    }

    public ArrayList<UUID> getPlayers() {
        return players;
    }

    public GameState getState() {
        return state;
    }

    public Game getGame() {
        return game;
    }

    public Location getMapSpawn() {
        return mapSpawn;
    }

    public LinkedHashMap<Maps, Integer> getVotableMaps() {
        return votableMaps;
    }

    public ArrayList<UUID> getVotedPlayers() {
        return votedPlayers;
    }

    public Maps[] getMapArray() {
        return mapArray;
    }

    public VoteCountdown getVoteCountdown() {
        return voteCountdown;
    }

    public Kit getHumanKit(Player player) {

        switch (Main.getFileManager().getInfectedRank(player).getKitType()) {
            case A:
                return new KitA(player.getUniqueId());
            case B:
                return new KitB(player.getUniqueId());
            case C:
                return new KitC(player.getUniqueId());
            case D:
                return new KitD(player.getUniqueId());
            case E:
                return new KitE(player.getUniqueId());
            case F:
                return new KitF(player.getUniqueId());
            case G:
                return new KitG(player.getUniqueId());
            case H:
                return new KitH(player.getUniqueId());
            case I:
                return new KitI(player.getUniqueId());
            case J:
                return new KitJ(player.getUniqueId());
            case K:
                return new KitK(player.getUniqueId());
            case L:
                return new KitL(player.getUniqueId());
            case M:
                return new KitM(player.getUniqueId());
            case N:
                return new KitN(player.getUniqueId());
            case O:
                return new KitO(player.getUniqueId());
            case P:
                return new KitP(player.getUniqueId());
            case Q:
                return new KitQ(player.getUniqueId());
            case R:
                return new KitR(player.getUniqueId());
            case S:
                return new KitS(player.getUniqueId());
            case T:
                return new KitT(player.getUniqueId());


        }
        return new KitA(player.getUniqueId());
    }


    public Maps getHighestVoted() {
        Random random = new Random();
        int randSelect = random.nextInt(4) + 1;//generates values 0 - 4, hence + 1
        Maps chosenMap = mapArray[randSelect];
        for (Maps map : votableMaps.keySet()) {
            if (votableMaps.get(map) > votableMaps.get(chosenMap)) {
                chosenMap = map;
            }
        }
        return chosenMap;
    }

    /*
     * SETTERS:
     */

    public void setState(GameState state) {
        this.state = state;
    }

    public void setMapSpawn(Location mapSpawn) {
        this.mapSpawn = mapSpawn;
    }

    public void setHumanKits() {
        for (UUID uuid : players) {
            getHumanKit(Bukkit.getPlayer(uuid)).onStart(Bukkit.getPlayer(uuid));
        }
    }

}
