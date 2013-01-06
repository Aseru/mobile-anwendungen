package edu.hm.androidsweeper.difficulties;

/**
 * Class to represent the easiest predefined difficulty.
 */
public class EasyDifficulty implements IDifficulty {
    
    /**
     * Name of this difficulty used for extras.
     */
    public static final String NAME = "easy";
    
    private static final long serialVersionUID = 1952238030186230671L;
    
    /**
     * Defined field width.
     */
    public static final int WIDTH = 9;
    
    /**
     * Defined field height.
     */
    public static final int HEIGTH = 9;
    
    /**
     * Defined number of bombs.
     */
    public static final int BOMBS = 12;
    
    /**
     * Defined number of available hints.
     */
    public static final int HINTS = 3;
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getXSize()
     */
    @Override
    public int getWidth() {
        return WIDTH;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getYSize()
     */
    @Override
    public int getHeight() {
        return HEIGTH;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getNumberOfBombs()
     */
    @Override
    public int getBombs() {
        return BOMBS;
    }
    
    
    @Override
    public int getHints() {
        return HINTS;
    }
    
    @Override
    public String getDifficultyName() {
        return NAME;
    }
    
}
