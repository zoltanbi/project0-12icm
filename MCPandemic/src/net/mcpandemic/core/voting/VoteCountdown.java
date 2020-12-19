package net.mcpandemic.core.voting;

import net.mcpandemic.core.*;
import net.mcpandemic.core.voting.VoteMap;
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

    public VoteCountdown(Arena arena, VoteMap voteMap) {
        this.arena = arena;
        this.voteMap = voteMap;
        this.voteSeconds = Config.getVoteSeconds();
    }

    public void startVote() {
        arena.setState(GameState.VOTING);
        this.runTaskTimer(Main.getInstance(), 0, 20);
    }

    @Override
    public void run() {
        if (voteSeconds == 0) {
            cancel();
            countdown.begin();

        }
        if (voteSeconds % 60 == 0 || voteSeconds <=10) {
            if (voteSeconds == 1 ) {
                arena.sendMessage("Voting will end in 1 second.");
            } else {
                arena.sendMessage("Voting will end in  " + voteSeconds + " seconds.");
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
