package edu.hm.androidsweeper.difficulties;

/**
 * Class to represent the medium predefined difficulty.
 */
public class MediumDifficulty implements IDifficulty {
    
    /**
     * Description for this difficulty.
     */
    public static final DifficultyDescription DESCRIPTION = DifficultyDescription.MEDIUM;
    
    private static final long serialVersionUID = -885855746218919308L;
    
    /**
     * Defined field width.
     */
    public static final int WIDTH = 15;
    
    /**
     * Defined field height.
     */
    public static final int HEIGTH = 15;
    
    /**
     * Defined number of bombs.
     */
    public static final int BOMBS = 40;
    
    /**
     * Defined the number of available hints.
     */
    public static final int HINTS = 4;
    
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
