/**
 * 
 */
package edu.hm.mineandroidsweeper.difficulties;

public class HardDifficulty implements IDifficulty {

	private static final long serialVersionUID = 8096952251879936437L;

	public static final DifficultyDescription description = DifficultyDescription.HARD;

	public final static int xSize = 15;
	public final static int ySize = 15;
	public final static int numberOfBombs = 100;

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

	public DifficultyDescription getDescription() {
		return description;
	}

}
