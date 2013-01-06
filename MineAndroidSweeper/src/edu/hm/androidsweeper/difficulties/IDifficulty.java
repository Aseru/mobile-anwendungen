package edu.hm.androidsweeper.difficulties;

import java.io.Serializable;

/**
 * Interface for the game difficulty.
 */
public interface IDifficulty extends Serializable {
    
    /**
     * The string used to set a difficulty as a extra to intents.
     */
    String EXTRA_NAME = "edu.hm.mineandroidsweeper.extra.difficulty";
    
    /** Returns the playground width.
     * 
     * @return The horizontal size.
     */
    int getWidth();
    
    /** Returns the playground height.
     * 
     * @return The vertical size.
     */
    int getHeight();
    
    /**
     * Returns the number of bombs for this difficulty.
     * 
     * @return the number of bombs
     */
    int getBombs();
    
    
    /**
     * Returns the maximum number of hints for this difficulty.
     * 
     * @return the number of available hints.
     */
    int getHints();
    
    /**
     * Returns the name of this difficulty.
     * 
     * @return the difficulty name.
     */
    String getDifficultyName();
    
}
