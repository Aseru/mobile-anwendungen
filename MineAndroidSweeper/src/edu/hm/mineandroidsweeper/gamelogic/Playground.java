package edu.hm.mineandroidsweeper.gamelogic;

import java.util.Map;

public class Playground {
	
	private final int xSize;
	private final int ySize;
	
	private Map<Coordinate, Field> fieldsMap;

	public Playground(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}

}
