/**
 * 
 */
package edu.hm.mineandroidsweeper.difficults;

public class HardDifficulty implements IDifficulty {

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
		// TODO Auto-generated method stub
		return numberOfBombs;
	}

}
