package edu.hm.mineandroidsweeper.difficulties;

public class CustomizedDifficulty implements IDifficulty {

	public static final DifficultyDescription DESCRIPTION = DifficultyDescription.CUSTOM;
	public static final int DEFAULT_SIZE = 15;
	public static final int DEFAULT_BOMBS = 15;
	
	private static final long serialVersionUID = 6850885455181308430L;
	
	public final int xSize;
	public final int ySize;
	public final int numberOfBombs;

	/* No-args constructor, needed for Serialization. */
	protected CustomizedDifficulty() { 
		xSize = DEFAULT_SIZE;
		ySize = DEFAULT_SIZE;
		numberOfBombs = DEFAULT_BOMBS;
	}
	
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
		return numberOfBombs;
	}
	
	/* (non-Javadoc)
	 * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getDescription
	 */
	public DifficultyDescription getDescription() {
		return DESCRIPTION;
	}

}
