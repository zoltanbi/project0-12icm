package net.mcpandemic.core.voting;

import net.mcpandemic.core.*;
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

    public VoteCountdown(Arena arena) {
        this.arena = arena;
        this.voteSeconds = Config.getVoteSeconds();
    }

    public void startVote() {
        arena.setState(GameState.VOTING);
        arena.promptVotableMaps();
        this.runTaskTimer(Main.getInstance(), 0, 20);
    }

    @Override
    public void run() {
        if (voteSeconds == 0) {
            cancel();
            arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_GREEN + "Voting has ended! The winner is " + ChatColor.YELLOW + arena.getHighestVoted().getMapName());
            arena.sendMessage(Manager.getServerTag() + ChatColor.DARK_AQUA + "Loading map " + ChatColor.YELLOW + arena.getHighestVoted().getMapName() + ChatColor.DARK_AQUA + " ...");
            //set the winning arena's spawnpoint
            arena.setMapSpawn(arena.getHighestVoted().getLocation());

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
