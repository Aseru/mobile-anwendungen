package edu.hm.androidsweeper.gamelogic;

import java.io.Serializable;

/** Represents a point on a two dimensional plane.
 * The first point of the plane is (0,0).
 */
public class Coordinate implements Serializable {
    
    private static final long serialVersionUID = -6415929767292021551L;
    
    private final int x;
    private final int y;
    
    /* No-args constructor, needed for Serialization. */
    /** Creates a new instance of {@link Coordinate} at the point (0,0).
     */
    protected Coordinate() {
        x = 0;
        y = 0;
    }
    
    /** Constructor for a coordinate from a given position.
     * @param x The horizontal position on the plane.
     * @param y The vertical position on the plane.
     */
    public Coordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    /** Returns the horizontal position.
     * @return x >= 0
     */
    public int getX() {
        return x;
    }
    
    /** Returns the vertical position.
     * @return y >= 0
     */
    public int getY() {
        return y;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate another = (Coordinate) o;
        return x == another.x && y == another.y;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
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
