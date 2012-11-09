package edu.hm.mineandroidsweeper.difficulties;

public class CustomizedDifficulty implements IDifficulty {

	public final int xSize;
	public final int ySize;
	public final int numberOfBombs;

	public CustomizedDifficulty(int xSize, int ySize, int numberOfBombs) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.numberOfBombs = numberOfBombs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getXSize()
	 */
	public int getXSize() {
		return xSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getYSize()
	 */
	public int getYSize() {
		return ySize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getNumberOfBombs()
	 */
	public int getNumberOfBombs() {
		// TODO Auto-generated method stub
		return numberOfBombs;
	}

}
