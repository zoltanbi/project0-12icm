package net.mcpandemic.core.gamestates;

import net.mcpandemic.core.*;
import net.mcpandemic.core.gamestates.Countdown;
import net.mcpandemic.core.gamestates.GameState;
import net.mcpandemic.core.voting.Maps;
import net.mcpandemic.core.voting.VoteMap;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * The Countdown class is responsible for the countdown logic. Announces
 * at certain periods to let players know when the GameState's are
 * changing.
 */
public class VoteCountdown extends BukkitRunnable {

    private Arena arena;
    private VoteMap voteMap;
    private Countdown countdown;
    private int voteSeconds;
    private Maps map;

    public VoteCountdown(Arena arena) {
        this.arena = arena;
        this.voteSeconds = Config.getVoteSeconds();
    }

    public void startVote() {
        Manager.turnGamemodeAdventure();
        arena.setState(GameState.VOTING);
        arena.promptVotableMaps();
        this.runTaskTimer(Main.getInstance(), 0, 20);
    }

    public void skipVote() {
        cancel();
        map = arena.getHighestVoted();
        arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "Voting has ended! The winner is " + ChatColor.YELLOW + map.getMapName());
        arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_AQUA + "Loading map " + ChatColor.YELLOW + map.getMapName() + ChatColor.DARK_AQUA + " ...");
        //set the winning arena's spawnpoint
        arena.setMapSpawn(map.getLocation());
        //start game countdown
        arena.startCountdown();
    }

    @Override
    public void run() {
        if (voteSeconds == 0) {
            cancel();
            map = arena.getHighestVoted();
            arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "Voting has ended! The winner is " + ChatColor.YELLOW + map.getMapName());
            arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_AQUA + "Loading map " + ChatColor.YELLOW + map.getMapName() + ChatColor.DARK_AQUA + " ...");
            //set the winning arena's spawnpoint
            arena.setMapSpawn(map.getLocation());

            //start game countdown
            arena.startCountdown();

        }
        if (voteSeconds % 45 == 0 || voteSeconds <=10) {
            if (voteSeconds != 0) {
                arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "Voting ends in " + voteSeconds + "...");
            }
        }

        if (arena.getPlayers().size() < Config.getRequiredPlayers()) {
            cancel();
            arena.setState(GameState.RECRUITING);
            arena.sendMessage("There are too few players. Countdown stopped.");
            return;
        }

        voteSeconds--;
    }
}
