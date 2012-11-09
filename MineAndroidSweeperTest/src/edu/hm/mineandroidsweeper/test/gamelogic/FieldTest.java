package edu.hm.mineandroidsweeper.test.gamelogic;

import edu.hm.mineandroidsweeper.gamelogic.Coordinate;
import edu.hm.mineandroidsweeper.gamelogic.Field;
import junit.framework.TestCase;

public class FieldTest extends TestCase {
	
	public void testEquals(){
		Field field1 = new Field(new Coordinate(1, 1));
		Field field2 = new Field(new Coordinate(1, 1));
		
		assertEquals(field1, field2);
	}

	public void testGetNeighborCoords8() {
		Coordinate coord = new Coordinate(2, 2);
		Field field = new Field(coord);
		Coordinate[] coords = new Coordinate[] { 
				new Coordinate(1, 1), new Coordinate(2, 1), 
				new Coordinate(3, 1), new Coordinate(1, 2), 
				new Coordinate(3, 2), new Coordinate(1, 3), 
				new Coordinate(2, 3), new Coordinate(3, 3) };

		Coordinate[] neighbors = field.getNeighborCoords8();
		
		for(int i = 0; i < coords.length; i++){
			assertEquals(coords[i], neighbors[i]);
		}
	}
	
	public void testIncreaseValue(){
		Field bomb = new Field(new Coordinate(2, 2));
		bomb.setBomb(true);
		Field field = new Field(new Coordinate(1, 1));
		
		assertFalse(bomb.increaseValue());
		assertTrue(field.increaseValue());
	}

}
