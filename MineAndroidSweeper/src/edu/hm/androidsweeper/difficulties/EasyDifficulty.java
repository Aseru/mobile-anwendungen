/**
 * 
 */
package edu.hm.androidsweeper.difficulties;

public class EasyDifficulty implements IDifficulty {
    
    public static final DifficultyDescription DESCRIPTION = DifficultyDescription.EASY;
    
    private static final long serialVersionUID = 1952238030186230671L;
    
    public final static int xSize = 9;
    public final static int ySize = 9;
    public final static int numberOfBombs = 12;
    
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
