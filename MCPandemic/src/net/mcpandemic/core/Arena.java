package net.mcpandemic.core;

import net.mcpandemic.core.teams.Team;
import net.mcpandemic.core.voting.Maps;
import net.mcpandemic.core.voting.VoteCountdown;
import net.mcpandemic.core.voting.VoteMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
    private HashMap<UUID, Team> teams;

    public Arena() {
        //initial setup
        players = new ArrayList<>();
        World world = Bukkit.getServer().getWorld("lobby");
        spawn = new Location(world, 77.4, 13.0, 90.47, 0, 0);
        teams = new HashMap<>();
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
            Bukkit.getPlayer(uuid).teleport(spawn);
        }

        voteMap = new VoteMap();
        votableMaps = voteMap.getVotableMaps();
        mapArray = new Maps[5];
        votedPlayers = new ArrayList<>();
        voteCountdown = new VoteCountdown(this);
        //rest
        teams.clear();
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
            //setting all players to HUMAN at first
            setTeam(player, Team.HUMAN);

            player.teleport(mapSpawn);
        }

        if (state == GameState.INFECTION) {
            //setting 1 in 5 players to ZOMBIE
            int zombieCount = players.size()/5;
            if (zombieCount == 0) {
                zombieCount++;
            }

            for (int i = 0; i < zombieCount; i++) {
                int randomIndex = ThreadLocalRandom.current().nextInt(
                        0, players.size());

                setTeam(Bukkit.getPlayer(players.get(randomIndex)),Team.ZOMBIE);
            }
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

        teams.remove(player);
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
     * TEAM ADDING/REMOVING
     */
    public void setTeam(Player p, Team t) {
        removeTeam(p);
        teams.put(p.getUniqueId(), t);
    }

    public void removeTeam(Player p) {
        if (teams.containsKey(p.getUniqueId())) {
            teams.remove(p.getUniqueId());
        }
    }

    public int getTeamCount(Team t) {
        int amount = 0;
        for (Team team : teams.values()) {
            if (team.equals(t)) {
                amount++;
            }
        }

        return amount;
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
}
