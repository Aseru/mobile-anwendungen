package edu.hm.androidsweeper.features.highscore;

import junit.framework.TestCase;

public class HighscoreEntryTest extends TestCase {

	private final double value1 = 10.71267;
	private final double value2 = 83.30129;
	
	private HighscoreEntry defaultEntry = new HighscoreEntry();
	private HighscoreEntry emptyEntry = HighscoreEntry.newEmpty();

	private final HighscoreEntry entry1 = new HighscoreEntry("player", value1);
	private final HighscoreEntry entry2 = new HighscoreEntry("player", value2);
	
	

	public void testDefault() {
		assertNull(defaultEntry.getPlayerName());
		assertEquals(Double.MAX_VALUE, defaultEntry.getTime());
	}
	
	public void testEmpty() {
		assertEquals("", emptyEntry.getPlayerName());
		assertEquals(Double.MAX_VALUE, emptyEntry.getTime());
	}
	
	public void testIllegalPlayerName() {
		try {
			new HighscoreEntry(null, 1.0);
			fail("No exception thrown.");
		}
		catch(IllegalArgumentException iae) {
			
		}
	}
	
	public void testIllegalTimeValue() throws IllegalArgumentException {
		try {
			new HighscoreEntry("player", -2.0);
			fail("No exception thrown.");
		}
		catch(IllegalArgumentException iae) {
			
		}
	}

	
	public void testCompare() {
		assertEquals(0, entry1.compareTo(entry1));
		assertEquals(0, entry2.compareTo(entry2));
		
		assertTrue(entry1.compareTo(entry2)>0);
		assertTrue(entry2.compareTo(entry1)<0);
		
		assertTrue(entry1.compareTo(defaultEntry)>0);
		assertTrue(emptyEntry.compareTo(entry2)<0);
	}
	
	public void testEquals() {
		assertTrue(defaultEntry.equals(defaultEntry));
		assertTrue(emptyEntry.equals(emptyEntry));
		assertTrue(defaultEntry.equals(new HighscoreEntry()));
		assertTrue(emptyEntry.equals(HighscoreEntry.newEmpty()));
		assertTrue(entry1.equals(new HighscoreEntry("player", value1)));
		assertFalse(entry2.equals(new HighscoreEntry("other player", value2)));
	}
}
