package net.mcpandemic.core;

/**
 * This enum shows what game-state the game mode is currently at.
 * Contains:
 *    - RECRUITING
 *        Used when the game is waiting for enough players to join.
 *    - COUNTDOWN
 *        Used when the game is about to start.
 *    - LIVE
 *        Used when the game is in-play.
 */
public enum GameState {

    RECRUITING,
    VOTING,
    COUNTDOWN,
    LIVE,
    INFECTION;

}
