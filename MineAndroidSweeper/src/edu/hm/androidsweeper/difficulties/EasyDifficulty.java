package edu.hm.androidsweeper.difficulties;

/**
 * Class to represent the easiest predefined difficulty.
 */
public class EasyDifficulty implements IDifficulty {
    
    /**
     * Description for this difficulty.
     */
    public static final DifficultyDescription DESCRIPTION = DifficultyDescription.EASY;
    
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
    
}
