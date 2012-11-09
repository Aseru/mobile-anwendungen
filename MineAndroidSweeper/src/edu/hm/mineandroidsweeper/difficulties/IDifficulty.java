package edu.hm.mineandroidsweeper.difficulties;

public interface IDifficulty {
	
	/**
	 * 
	 * @return the size in x direction
	 */
	public int getXSize();
	
	/**
	 * 
	 * @return the size in y direction
	 */
	public int getYSize();
	
	/**
	 * 
	 * @return the number of bombs
	 */
	public int getNumberOfBombs();

}
