package edu.hm.mineandroidsweeper.difficulties;

import java.io.Serializable;

public interface IDifficulty extends Serializable{
	
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
	
	
	public DifficultyDescription getDescription();

}
