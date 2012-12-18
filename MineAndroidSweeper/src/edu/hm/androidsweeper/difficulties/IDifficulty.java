package edu.hm.androidsweeper.difficulties;

import java.io.Serializable;

/**
 * Interface for the difficulty classes TODO: Document type IDifficulty.
 */
public interface IDifficulty extends Serializable {
    
    /**
     * The string used to set a difficulty as a extra to intents.
     */
    String EXTRA_NAME = "edu.hm.mineandroidsweeper.extra.difficulty";
    
    /**
     * Returns the field width.
     * 
     * @return the size in x direction
     */
    int getWidth();
    
    /**
     * Returns the field heigth.
     * 
     * @return the size in y direction
     */
    int getHeight();
    
    /**
     * Returns the number of bombs.
     * 
     * @return the number of bombs
     */
    int getBombs();
    
    
}
