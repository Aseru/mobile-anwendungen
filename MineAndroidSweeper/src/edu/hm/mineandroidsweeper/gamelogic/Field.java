package edu.hm.mineandroidsweeper.gamelogic;

public class Field {

	private final Coordinate coord;

	private boolean isBomb;
	private int value;

	public Field(Coordinate coord) {
		this.coord = coord;

		isBomb = false;
		value = 0;
	}

	/**
	 * @return the isBomb
	 */
	public boolean isBomb() {
		return isBomb;
	}

	/**
	 * @param isBomb
	 *            the isBomb to set
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
	 * @param value
	 *            the value to set
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

	public boolean increaseValue() {
		if (isBomb) {
			return false;
		} else {
			value++;
			return true;
		}
	}

	public Coordinate[] getNeighborCoords8() {
		Coordinate[] neighborCoords = new Coordinate[8];
		int x = coord.getX();
		int y = coord.getY();

		neighborCoords[0] = new Coordinate(x - 1, y - 1);
		neighborCoords[1] = new Coordinate(x, y - 1);
		neighborCoords[2] = new Coordinate(x + 1, y - 1);
		neighborCoords[3] = new Coordinate(x - 1, y);
		neighborCoords[4] = new Coordinate(x + 1, y);
		neighborCoords[5] = new Coordinate(x - 1, y + 1);
		neighborCoords[6] = new Coordinate(x, y + 1);
		neighborCoords[7] = new Coordinate(x + 1, y + 1);

		return neighborCoords;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Field)) {
			return false;
		}
		Field another = (Field) o;
		if (coord.equals(another.getCoord()))
			return true;
		else
			return false;
	}

}
