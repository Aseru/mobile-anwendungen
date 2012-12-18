package edu.hm.androidsweeper.difficulties;

/**
 * Class to represent the hardest predefined difficulty.
 */
public class HardDifficulty implements IDifficulty {
    
    /**
     * Description for this difficulty.
     */
    public static final DifficultyDescription DESCRIPTION = DifficultyDescription.HARD;
    
    private static final long serialVersionUID = 8096952251879936437L;
    
    /**
     * Defined field width.
     */
    public static final int WIDTH = 15;
    
    /**
     * Defined field height.
     */
    public static final int HEIGHT = 30;
    
    /**
     * Defined number of bombs.
     */
    public static final int BOMBS = 99;
    
    /**
     * Defined number of available hints.
     */
    public static final int HINTS = 5;
    
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
        return HEIGHT;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getNumberOfBombs()
     */
    @Override
    public int getBombs() {
        return BOMBS;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getDescription
     */
    @Override
    public DifficultyDescription getDescription() {
        return DESCRIPTION;
    }
    
    @Override
    public int getHints() {
        return HINTS;
    }
    
}
