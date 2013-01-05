package edu.hm.androidsweeper.test.gamelogic;

import junit.framework.TestCase;
import edu.hm.androidsweeper.gamelogic.Coordinate;

public class CoordinateTest extends TestCase {

	public void testEquals() {
		Coordinate coord1 = new Coordinate(1, 1);
		Coordinate coord2 = new Coordinate(1, 1);

		assertEquals(coord1, coord2);
	}

}
