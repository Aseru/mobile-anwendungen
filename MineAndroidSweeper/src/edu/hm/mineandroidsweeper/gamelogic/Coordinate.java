package edu.hm.mineandroidsweeper.gamelogic;

public class Coordinate {
	
	private final int x;
	private final int y;
	
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
