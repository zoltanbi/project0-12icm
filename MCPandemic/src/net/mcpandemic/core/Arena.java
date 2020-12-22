package net.mcpandemic.core;

import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.humantypes.*;
import net.mcpandemic.core.kits.infectedtypes.KitMotherZombie;
import net.mcpandemic.core.kits.infectedtypes.KitSkeleton;
import net.mcpandemic.core.kits.infectedtypes.KitZombie;
import net.mcpandemic.core.ranks.DatabaseManager;
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
//import java.util.concurrent.ThreadLocalRandom;

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
    private HashMap<UUID, Team> teams;
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
    private Infection infection;
    private Endgame endgame;


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
        infection = new Infection(this);
        endgame = new Endgame(this);
    }

    public Location getLobbySpawn() {
        return spawn;
    }

    public void startCountdown() {
        countdown.begin();
    }

    public void startGame() {
        game.start();
    }

    public void startInfection() {
        infection.start();
    }

    public void startEndgame() {
        endgame.start();
    }

    public void teleportPlayersToArena() {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).teleport(mapSpawn);
        }
    }

    public void reset() {
        state = GameState.RECRUITING;
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).getInventory().clear();
            Bukkit.getPlayer(uuid).teleport(spawn);
        }

        voteMap = new VoteMap();
        votableMaps = voteMap.getVotableMaps();
        mapArray = new Maps[5];
        votedPlayers.clear();
        votedPlayers = new ArrayList<>();
        voteCountdown = new VoteCountdown(this);
        //rest
        teams.clear();
        teams = new HashMap<>();
        countdown = new Countdown(this);
        game = new Game(this);
        infection = new Infection(this);
        endgame = new Endgame(this);
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
        player.getInventory().clear();
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
            //setting all players to HUMAN at first and settong kit
            setHuman(player.getUniqueId());

            player.teleport(mapSpawn);
        }
        if (state == GameState.INFECTION) {
            setTeam(player, Team.ZOMBIE);
            player.teleport(mapSpawn);
            setZombieKit(player);
        }

    }
    /**
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

    public Team getTeam(Player player) {
        return teams.get(player.getUniqueId());
    }

    public int getTeamCount(Team team) {
        int amount = 0;
        for (Team t : teams.values()) {
            if (t.equals(team)) {
                amount++;
            }
        }
        return amount;
    }

    public boolean areSurvivors() {
        for (UUID uuid : teams.keySet()) {
            if (teams.get(uuid) == Team.HUMAN) {
                return true;
            }
        }
        System.out.println("No more humans left");
        return false;
    }

    public String getSurvivors() {
        StringBuilder output = new StringBuilder();
        for (UUID uuid : teams.keySet()) {
            if (teams.get(uuid) == Team.HUMAN) {
                output.append(ChatColor.YELLOW + " " + Bukkit.getPlayer(uuid).getName() + ChatColor.DARK_GREEN + ",");
            }
        }
        output.setLength(output.length() - 1);
        return output.toString();
    }

    public void setMotherZombie() {
        int infAmount = 1 + (teams.size() / 7);
        Random random = new Random();
        ArrayList<Integer> unique = new ArrayList<Integer>();
        while (unique.size() < infAmount) {
            int a = random.nextInt(players.size());
            if (!unique.contains(a)) {
                unique.add(a);
                setTeam(Bukkit.getPlayer(players.get(a)), Team.ZOMBIE);
                new KitMotherZombie(players.get(a)).onStart(Bukkit.getPlayer(players.get(a)));
                sendMessage(Manager.getServerTag() + ChatColor.RED + "Player " + ChatColor.YELLOW + Bukkit.getPlayer(players.get(a)).getName() + ChatColor.RED + " is the mother zombie!");
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
        teams.remove(player.getUniqueId());

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

    public Infection getInfection() {
        return infection;
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

    public Countdown getCountdown() {
        return countdown;
    }


    public Kit getHumanKit(Player player) {

        switch (DatabaseManager.getInfectedRank(player).getKitType()) {
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

    public void setHuman(UUID uuid) {
        setTeam(Bukkit.getPlayer(uuid), Team.HUMAN);
        getHumanKit(Bukkit.getPlayer(uuid)).onStart(Bukkit.getPlayer(uuid));
    }

    /**
     * Set human team and apply kits
     */
    public void setHumans() {
        for (UUID uuid : players) {
            setTeam(Bukkit.getPlayer(uuid), Team.HUMAN);
            getHumanKit(Bukkit.getPlayer(uuid)).onStart(Bukkit.getPlayer(uuid));
        }
    }



    public void setZombieKit(Player player) {
        switch(DatabaseManager.getInfectedKit(player)) {
            case ZOMBIE:
                System.out.println("INFECTED KIT SET");
                new KitZombie(player.getUniqueId()).onStart(player);
                break;
            case SKELETON:
                new KitSkeleton(player.getUniqueId()).onStart(player);
                break;
        }
//        for (UUID uuid : teams.keySet()) {
//            if (teams.get(player.getUniqueId()) == Team.ZOMBIE) {
//                new KitZombie(uuid).onStart(player);
//            }
//        }
    }

    public void zombieInfectMessage(Player player) {
        String msg1 = Manager.getServerTag() + ChatColor.YELLOW + player.getName() + ChatColor.RED + " got infected!";
        String msg2 = Manager.getServerTag() + ChatColor.YELLOW + player.getName() + ChatColor.RED + " is now a zombie";
        String msg3 = Manager.getServerTag() + ChatColor.RED + "The virus got " + ChatColor.YELLOW + player.getName() + ChatColor.RED + "!";
        String[] arr = {msg1, msg2, msg3};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        sendMessage(arr[select]);
    }

    public void humanKillMessage(Player killer, Player killed) {
        String msg1 = Manager.getServerTag() + ChatColor.RED + "Om nom nom nom nom..." +
                ChatColor.YELLOW + killer.getName() + ChatColor.RED + " just made a sandwich out of " +
                ChatColor.YELLOW + killed.getName() + ChatColor.RED + "'s brain!";

        String msg2 = Manager.getServerTag() + ChatColor.YELLOW + killer.getName() + ChatColor.RED + " got " +
                ChatColor.YELLOW + killed.getName() + ChatColor.RED + "!";

        String msg3 = Manager.getServerTag() + ChatColor.YELLOW + killer.getName() + ChatColor.RED + " bit " +
                ChatColor.YELLOW + killed.getName() + ChatColor.RED + "! He bit him!";
        String[] arr = {msg1, msg2, msg3};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        sendMessage(arr[select]);
    }

    public void zombieKillMessage(Player killer, Player killed) {
        String msg1 = Manager.getServerTag() + ChatColor.YELLOW + killer.getName() +
                ChatColor.GOLD + " tore " + ChatColor.YELLOW + killed + ChatColor.RED + " into pieces!";
        String msg2 = Manager.getServerTag() + ChatColor.YELLOW + killer.getName() +
                ChatColor.GOLD + " destroyed " + ChatColor.YELLOW + killed + ChatColor.RED + "!";
        String msg3 = Manager.getServerTag() + ChatColor.YELLOW + killer.getName() +
                ChatColor.GOLD + " killed " + ChatColor.YELLOW + killed + ChatColor.RED + "!";
        String[] arr = {msg1, msg2, msg3};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        sendMessage(arr[select]);
    }



}
