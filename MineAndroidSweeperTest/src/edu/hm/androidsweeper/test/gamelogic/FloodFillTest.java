package edu.hm.androidsweeper.test.gamelogic;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import edu.hm.androidsweeper.gamelogic.Coordinate;
import edu.hm.androidsweeper.gamelogic.Field;
import edu.hm.androidsweeper.gamelogic.FloodFill;

public class FloodFillTest extends TestCase {
	

	public void testFloodFill8() throws Exception {
		Map<Coordinate, Field> map = new HashMap<Coordinate, Field>();
		
		Field[] expectedfields = new Field[] { 
				new Field(new Coordinate(1, 1)),
				new Field(new Coordinate(2, 1)),
				new Field(new Coordinate(3, 1)),
				new Field(new Coordinate(1, 2)),
				new Field(new Coordinate(2, 2)),
				new Field(new Coordinate(3, 2)),
				new Field(new Coordinate(1, 3)),
				new Field(new Coordinate(2, 3)),
				new Field(new Coordinate(3, 3)) };
		
		expectedfields[0].setBomb(true);
		expectedfields[1].increaseValue();
		expectedfields[3].increaseValue();
		expectedfields[4].increaseValue();
		
		for (Field f : expectedfields) {
			map.put(f.getCoord(), f);
		}
		
		FloodFill.fill8(map, expectedfields[expectedfields.length-1]);
		
		assertTrue(expectedfields[1].isRevealed());
		assertTrue(expectedfields[2].isRevealed());
		assertTrue(expectedfields[3].isRevealed());
		assertTrue(expectedfields[4].isRevealed());
		assertTrue(expectedfields[5].isRevealed());
		assertTrue(expectedfields[6].isRevealed());
		assertTrue(expectedfields[7].isRevealed());
		assertTrue(expectedfields[8].isRevealed());
		
		assertFalse(expectedfields[0].isRevealed());
	}

	
	

}
