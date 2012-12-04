package edu.hm.mineandroidsweeper.test.gamelogic;

import junit.framework.TestCase;
import edu.hm.mineandroidsweeper.gamelogic.Coordinate;

public class CoordinateTest extends TestCase {

	public void testEquals() {
		Coordinate coord1 = new Coordinate(1, 1);
		Coordinate coord2 = new Coordinate(1, 1);

		assertEquals(coord1, coord2);
	}

}
