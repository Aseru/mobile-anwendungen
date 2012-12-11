package edu.hm.androidsweeper.difficulties;

import java.io.Serializable;

public interface IDifficulty extends Serializable{
	
	public static final String EXTRA_NAME = "edu.hm.mineandroidsweeper.extra.difficulty";
	
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
	
	/**
	 * 
	 * @return the description
	 */
	public DifficultyDescription getDescription();

}
