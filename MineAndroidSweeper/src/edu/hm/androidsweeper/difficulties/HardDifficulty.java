/**
 * 
 */
package edu.hm.androidsweeper.difficulties;

public class HardDifficulty implements IDifficulty {
    
    public static final DifficultyDescription DESCRIPTION = DifficultyDescription.HARD;
    
    private static final long serialVersionUID = 8096952251879936437L;
    
    public final static int xSize = 15;
    public final static int ySize = 30;
    public final static int numberOfBombs = 99;
    
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
