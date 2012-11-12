/**
 * 
 */
package edu.hm.mineandroidsweeper.difficulties;

public class EasyDifficulty implements IDifficulty {
	
	private static final long serialVersionUID = 1952238030186230671L;
	
	public static final DifficultyDescription description = DifficultyDescription.EASY;

	public final static int xSize = 15;
	public final static int ySize = 15;
	public final static int numberOfBombs = 20;

	/* (non-Javadoc)
	 * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getXSize()
	 */
	public int getXSize() {
		return xSize;
	}

	/* (non-Javadoc)
	 * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getYSize()
	 */
	public int getYSize() {
		return ySize;
	}

	/* (non-Javadoc)
	 * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getNumberOfBombs()
	 */
	public int getNumberOfBombs() {
		// TODO Auto-generated method stub
		return numberOfBombs;
	}

	public DifficultyDescription getDescription() {
		return description;
	}

}
