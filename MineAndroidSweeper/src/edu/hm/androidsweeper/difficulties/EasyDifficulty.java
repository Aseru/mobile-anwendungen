/**
 * 
 */
package edu.hm.androidsweeper.difficulties;

public class EasyDifficulty implements IDifficulty {
	
	public static final DifficultyDescription DESCRIPTION = DifficultyDescription.EASY;
	
	private static final long serialVersionUID = 1952238030186230671L;

	public final static int xSize = 10;
	public final static int ySize = 10;
	public final static int numberOfBombs = 10;

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
		return numberOfBombs;
	}

	/* (non-Javadoc)
	 * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getDescription
	 */
	public DifficultyDescription getDescription() {
		return DESCRIPTION;
	}

}
