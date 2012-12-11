package edu.hm.androidsweeper.activities;

/**
 * Provides callback methods to handle the game end and to update the flag
 * count.
 */
public interface IGameActivity {
    
    /**
     * Callback method to handle the game end.
     */
    void handleGameEnd();
    
    /**
     * Callback method to update the flag count.
     */
    void updateFlagCount();
    
}
