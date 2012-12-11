package edu.hm.androidsweeper.gamelogic;

import java.io.Serializable;

public class Coordinate implements Serializable {
	
	private static final long serialVersionUID = -6415929767292021551L;
	
	private final int x;
	private final int y;
	
	/* No-args constructor, needed for Serialization. */
	protected Coordinate() {
		x = 0;
		y = 0;
	}
	
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Coordinate)){
			return false;
		}
		Coordinate another = (Coordinate) o;
		if(this.x == another.x && this.y == another.y)
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String("(" + x + "," + y + ")");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + x;
		hash = hash * 31 + y;
		return hash;
	}
	
	
	
	

}
