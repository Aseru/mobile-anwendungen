package edu.hm.androidsweeper.gamelogic;

import java.util.Map;

/** Implements the Floodfill algorithm.
 */
public final class FloodFill {
    
    private FloodFill() { }
    
    /** Fills a map with fields calculated using the Floodfill algorithm.
     * 
     * @param map The map used to save the fields.
     * @param start The field to start from.
     */
    public static void fill8(final Map<Coordinate, Field> map, final Field start) {
        if (start == null || start.isBomb() || start.isRevealed()) {
            return;
        }
        start.setRevealed(true);
        if (start.getValue() > 0) {
            return;
        }
        
        Field[] neighborFields = start.getNeighborFields8(map);
        for (Field field : neighborFields) {
            fill8(map, field);
        }
    }
    
}
