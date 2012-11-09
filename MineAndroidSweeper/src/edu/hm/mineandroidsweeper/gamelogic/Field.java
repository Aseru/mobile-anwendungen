package edu.hm.mineandroidsweeper.gamelogic;

public class Field {
	
	private final Coordinate coord;
	
	private boolean isBomb;
	private int value;

	public Field(Coordinate coord) {
		this.coord = coord;
		
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
	 * @return the coord
	 */
	public Coordinate getCoord() {
		return coord;
	}


}
