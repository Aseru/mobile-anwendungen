package edu.hm.mineandroidsweeper.difficulties;

public class CustomizedDifficulty implements IDifficulty {

	public final int xSize;
	public final int ySize;
	public final int numberOfBombs;

	public CustomizedDifficulty(int xSize, int ySize, int numberOfBombs)
			throws Exception {
		this.xSize = xSize;
		this.ySize = ySize;
		this.numberOfBombs = numberOfBombs;
		checkConfig();
	}

	private void checkConfig() throws Exception {
		int numberOfFields = xSize * ySize;
		if (numberOfFields < numberOfBombs) {
			throw new Exception("More bombs as fields. Not possible.");
		}
		if (numberOfBombs <= 0) {
			throw new Exception("Minimum one bomb must be selected.");
		}
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
