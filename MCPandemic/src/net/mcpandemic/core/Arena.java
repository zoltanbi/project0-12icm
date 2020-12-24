package net.mcpandemic.core;

import net.mcpandemic.core.gamestates.*;
import net.mcpandemic.core.infectedmanager.DisguiseManager;
import net.mcpandemic.core.infectedmanager.ZombieManager;
import net.mcpandemic.core.kits.Kit;
import net.mcpandemic.core.kits.KitType;
import net.mcpandemic.core.kits.humantypes.*;
import net.mcpandemic.core.infectedmanager.DatabaseManager;
import net.mcpandemic.core.teams.Team;
import net.mcpandemic.core.voting.Maps;
import net.mcpandemic.core.gamestates.VoteCountdown;
import net.mcpandemic.core.voting.VoteMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.sql.SQLException;
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

    /**
     * Initialize arena.
     */

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

    /**
     * Reset arena.
     */

    public void reset() {
        GameListener.clearPlayerKillStreaks();
        for (UUID uuid : teams.keySet()) {
            if (teams.get(uuid) == Team.ZOMBIE) {
                DisguiseManager.removeDisguise(Bukkit.getPlayer(uuid));
                if (DatabaseManager.getInfectedKit(Bukkit.getPlayer(uuid)) == KitType.MOTHERZOMBIE) {
                    DatabaseManager.setInfectedKit(uuid, KitType.ZOMBIE);
                }
            }
        }
        Bukkit.getScheduler().cancelTasks(Main.getInstance());
        teams.clear();
        teams = new HashMap<>();
        state = GameState.RECRUITING;
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).getInventory().clear();
            Bukkit.getPlayer(uuid).teleport(spawn);
            Bukkit.getPlayer(uuid).setHealth(20.0);
        }

        voteMap = new VoteMap();
        votableMaps = voteMap.getVotableMaps();
        mapArray = new Maps[5];
        votedPlayers.clear();
        votedPlayers = new ArrayList<>();
        voteCountdown = new VoteCountdown(this);
        countdown = new Countdown(this);
        game = new Game(this);
        infection = new Infection(this);
        endgame = new Endgame(this);
        //BUG
        if(players.size() >= Config.getRequiredPlayers()) {
            voteCountdown.startVote();
        }
    }

    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    /**
     * Bukkit runnable methods (Game states)
     */

    public void setState(GameState state) {
        this.state = state;
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

    /**
     * Methods that add and remove players.
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
            if (DatabaseManager.getInfectedKit(player) == KitType.MOTHERZOMBIE) {
                DatabaseManager.setInfectedKit(player.getUniqueId(), KitType.ZOMBIE);
            }
        }
        if (state == GameState.INFECTION) {
            setTeam(player, Team.ZOMBIE);
            player.teleport(mapSpawn);
            ZombieManager.playerZombieSetup(player);
            zombieInfectMessage(player);
        }

    }

    public void removePlayer(Player player) {
        if (teams.get(player.getUniqueId()) == Team.ZOMBIE && state.equals(GameState.INFECTION) && getTeamCount(Team.ZOMBIE) < 2) {
            setBackupMotherZombie();
        }
        players.remove(player.getUniqueId());
        teams.remove(player.getUniqueId());

        votedPlayers.remove(player.getUniqueId());
        if (player.isOnline()) {
            player.teleport(spawn);
            player.sendMessage(ChatColor.RED + "You're not supposed to be out of the game... Contact a staff member");
        }

        if (players.size() <= Config.getRequiredPlayers() && (state.equals(GameState.COUNTDOWN) || state.equals(GameState.VOTING))) {
            sendMessage(Manager.getServerTag() + "There are too few players. Resetting game.");
            reset();
        }

        if (players.size() == 0 && (state.equals(GameState.COUNTDOWN) || state.equals(GameState.VOTING))) {
            sendMessage(Manager.getServerTag() + "There are too few players. Resetting game.");
            reset();
        }

    }

    /**
     * Mother zombie methods.
     */
    public void setMotherZombie() {
        int infAmount = 1 + (teams.size() / 7);
        Random random = new Random();
        ArrayList<UUID> unique = new ArrayList<UUID>();
        while (unique.size() < infAmount) {
            int a = random.nextInt(players.size());
            UUID temp = players.get(a);
            if (!unique.contains(temp)) {
                unique.add(temp);
                setTeam(Bukkit.getPlayer(temp), Team.ZOMBIE);
                //setup mother zombie
                DatabaseManager.setInfectedKit(temp, KitType.MOTHERZOMBIE);
                ZombieManager.playerZombieSetup(Bukkit.getPlayer(temp));
                sendMessage(Manager.getServerTag() + ChatColor.RED + "Player " + ChatColor.YELLOW + Bukkit.getPlayer(temp).getName() + ChatColor.RED + " is the mother zombie!");
            }
        }
    }

    public void setBackupMotherZombie() {
        Random random = new Random();
        int a = random.nextInt(players.size());
        UUID temp = players.get(a);
        setTeam(Bukkit.getPlayer(temp), Team.ZOMBIE);
        //setup mother zombie
        DatabaseManager.setInfectedKit(temp, KitType.MOTHERZOMBIE);
        ZombieManager.playerZombieSetup(Bukkit.getPlayer(temp));
        Bukkit.getPlayer(temp).sendMessage(Manager.getServerTag() + ChatColor.LIGHT_PURPLE + "you were picked as a new mother zombie! Go eat some human brains!");
        sendMessage(Manager.getServerTag() + ChatColor.RED + "Player " + ChatColor.YELLOW + Bukkit.getPlayer(temp).getName() + ChatColor.RED + " became a mother zombie!");
    }

    /**
     * Team methods.
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

    /**
     * Location methods.
     */

    public void setMapSpawn(Location mapSpawn) {
        this.mapSpawn = mapSpawn;
    }

    public Location getLobbySpawn() {
        return spawn;
    }

    public void teleportPlayersToArena() {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).teleport(mapSpawn);
        }
    }

    /**
     * Condition check methods,
     */

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

    /**
     * Vote methods.
     */

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

    /**
     * Useful getters.
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

    /**
     * Human methods.
     */

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

    public void setHumans() {
        for (UUID uuid : players) {
            setTeam(Bukkit.getPlayer(uuid), Team.HUMAN);
            getHumanKit(Bukkit.getPlayer(uuid)).onStart(Bukkit.getPlayer(uuid));
        }
    }

    public void setHuman(UUID uuid) {
        setTeam(Bukkit.getPlayer(uuid), Team.HUMAN);
        getHumanKit(Bukkit.getPlayer(uuid)).onStart(Bukkit.getPlayer(uuid));
    }

    /**
     * Kill messages
     */

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
                ChatColor.GOLD + " tore " + ChatColor.YELLOW + killed.getName() + ChatColor.RED + " into pieces!";
        String msg2 = Manager.getServerTag() + ChatColor.YELLOW + killer.getName() +
                ChatColor.GOLD + " destroyed " + ChatColor.YELLOW + killed.getName() + ChatColor.RED + "!";
        String msg3 = Manager.getServerTag() + ChatColor.YELLOW + killer.getName() +
                ChatColor.GOLD + " killed " + ChatColor.YELLOW + killed.getName() + ChatColor.RED + "!";
        String[] arr = {msg1, msg2, msg3};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        sendMessage(arr[select]);
    }

    /**
     * Rank points currency methods
     */

    public void survivorBonus(int multiplier) throws SQLException {
        for (UUID uuid : teams.keySet()) {
            if (teams.get(uuid) == Team.HUMAN) {
                Bukkit.getPlayer(uuid).sendMessage(ChatColor.GREEN + "A fellow human died! Survival bonus! +" + (2*multiplier) + " Rankpoints");
                DatabaseManager.setRankPoints(uuid, (2*multiplier));
            } else if (teams.get(uuid) == Team.ZOMBIE) {
                Bukkit.getPlayer(uuid).sendMessage(ChatColor.GREEN + "A human got infected! Spread bonus! +" + (multiplier) + " Rankpoints");
                DatabaseManager.setRankPoints(uuid, (multiplier));
            }
        }
    }

    public void killBonus(Player player, int multiplier) throws SQLException {
        if (teams.get(player.getUniqueId()) == Team.HUMAN) {
            player.sendMessage(ChatColor.GREEN + "You killed an infected! Kill bonus! +" + (2*multiplier) + " Rankpoints");
            DatabaseManager.setRankPoints(player.getUniqueId(), (2*multiplier));
        } else if (teams.get(player.getUniqueId()) == Team.ZOMBIE) {
            player.sendMessage(ChatColor.GREEN + "You infected a human! Kill bonus! +" + (2*multiplier) + " Rankpoints");
            DatabaseManager.setRankPoints(player.getUniqueId(), (2*multiplier));
        }
    }

    public void survivorWinBonus(int multiplier) throws SQLException {
        for (UUID uuid : teams.keySet()) {
            if (teams.get(uuid) == Team.HUMAN) {
                Bukkit.getPlayer(uuid).sendMessage(ChatColor.GREEN + "Win bonus! +" + (30*multiplier) + " Rankpoints");
                DatabaseManager.setRankPoints(uuid, (30*multiplier));
            }
        }
    }

    public void infectedWinBonus(int multiplier) throws SQLException {
        for (UUID uuid : teams.keySet()) {
            if (teams.get(uuid) == Team.ZOMBIE) {
                Bukkit.getPlayer(uuid).sendMessage(ChatColor.GREEN + "Win bonus! +" + (15*multiplier) + " Rankpoints");
                DatabaseManager.setRankPoints(uuid, (15*multiplier));
            }
        }
    }

}
