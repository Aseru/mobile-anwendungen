package edu.hm.androidsweeper.difficulties;

public class MediumDifficulty implements IDifficulty {
    
    public static final DifficultyDescription DESCRIPTION = DifficultyDescription.MEDIUM;
    
    private static final long serialVersionUID = -885855746218919308L;
    
    public final static int xSize = 15;
    public final static int ySize = 15;
    public final static int numberOfBombs = 40;
    
    /* (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getXSize()
     */
    @Override
    public int getXSize() {
        return xSize;
    }
    
    /* (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getYSize()
     */
    @Override
    public int getYSize() {
        return ySize;
    }
    
    /* (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getNumberOfBombs()
     */
    @Override
    public int getNumberOfBombs() {
        return numberOfBombs;
    }
    
    /* (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getDescription
     */
    @Override
    public DifficultyDescription getDescription() {
        return DESCRIPTION;
    }
    
}
