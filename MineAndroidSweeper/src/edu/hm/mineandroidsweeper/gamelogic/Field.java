package edu.hm.mineandroidsweeper.gamelogic;

public class Field {
	
	private final int xCoord;
	private final int yCoord;
	
	private boolean isBomb;
	private int value;

	public Field(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
		
		isBomb = false;
		value = -1;
	}

	/**
	 * @return the isBomb
	 */
	public boolean isBomb() {
		return isBomb;
	}

	/**
	 * @param isBomb the isBomb to set
	 */
	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}
	
	

}
