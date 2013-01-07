package edu.hm.androidsweeper.gamelogic;

/**
 * The different states a game can be in.
 */
public enum GameState {
    
    /**
     * Game state before the game is running.
     */
    STARTING,
    
    /**
     * Game is current running.
     */
    RUNNING,
    
    /**
     * Game is currently paused.
     */
    PAUSED,
    
    /**
     * Game is finished and won.
     */
    WON,
    
    /**
     * Game is finisched and lost.
     */
    LOST,
    
    /**
     * Game is saved to sd-card.
     */
    SAVED
}
