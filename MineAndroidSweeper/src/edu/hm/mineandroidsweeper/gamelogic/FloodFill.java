package edu.hm.mineandroidsweeper.gamelogic;

import java.util.Map;

public class FloodFill {

	public static void fill8(Map<Coordinate, Field> map, Field start) {
		if (start == null || start.isBomb() || start.isRevealed()) {
			return;
		}
		start.setRevealed(true);
		if (start.getValue() > 0) {
			return;
		}
		
		Field[] neighborFields = start.getNeighborFields8(map);
		for(Field field : neighborFields){
			fill8(map, field);
		}
	}

}
