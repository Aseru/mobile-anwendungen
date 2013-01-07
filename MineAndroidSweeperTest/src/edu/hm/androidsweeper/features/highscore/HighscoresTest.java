package edu.hm.androidsweeper.features.highscore;

import junit.framework.TestCase;
import edu.hm.androidsweeper.difficulties.EasyDifficulty;
import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.gamelogic.GameState;

public class HighscoresTest extends TestCase {

	private Highscores highscores;

	private IDifficulty difficulty = new EasyDifficulty();

	private HighscoreEntry defaultEntry = new HighscoreEntry();
	private HighscoreEntry emptyEntry = HighscoreEntry.newEmpty();
	private HighscoreEntry entry1 = new HighscoreEntry("entry1", 1.2);
	private HighscoreEntry entry2 = new HighscoreEntry("entry2", 20.33);
	private HighscoreEntry entry3 = new HighscoreEntry("entry3", 38.5);
	private HighscoreEntry entry4 = new HighscoreEntry("entry4", 42);
	private HighscoreEntry entry5 = new HighscoreEntry("entry5", 70.04);
	private HighscoreEntry entry6 = new HighscoreEntry("entry6", 85.44);
	private HighscoreEntry entry7 = new HighscoreEntry("entry7", 120.9);
	private HighscoreEntry entry8 = new HighscoreEntry("entry8", 130.12);
	private HighscoreEntry entry9 = new HighscoreEntry("entry9", 160.29);
	private HighscoreEntry entry10 = new HighscoreEntry("entry10", 192.54);
	private HighscoreEntry entry11 = new HighscoreEntry("entry11", 241.39);
	private HighscoreEntry entry12 = new HighscoreEntry("entry12", 491.24);

	private HighscoreEntry[] entries = { entry1, entry2, entry3, entry4,
			entry5, entry6, entry7, entry8, entry9, entry10, entry11, entry12 };

	@Override
	protected void setUp() throws Exception {
		highscores = Highscores.emptyHighscores();
		Highscores.setInstance(highscores);
	}

	public void testGetInstance() {
		assertSame(highscores, Highscores.getInstance());
	}

	public void testIsHighscore() {
		Game game = null;
		for(HighscoreEntry he: entries) {
			game = getHEAsGame(he);
			game.setState(GameState.WON);
			assertTrue(Highscores.isHighscore(game));
		}
		game = getHEAsGame(defaultEntry);
		game.setState(GameState.WON);
		assertTrue(Highscores.isHighscore(game));
		
		game = getHEAsGame(emptyEntry);
		game.setState(GameState.WON);
		assertTrue(Highscores.isHighscore(game));
		
		for(HighscoreEntry he: entries) {
			Highscores.addHighscore(getHEAsGame(he), he.getPlayerName());
		}
		
		assertFalse(highscores.getEasy().contains(entry11));
		assertFalse(highscores.getEasy().contains(entry12));
		assertFalse(Highscores.isHighscore(getHEAsGame(defaultEntry)));
		assertFalse(Highscores.isHighscore(getHEAsGame(emptyEntry)));
	}

	public void testAddHighscore() {
		assertTrue(highscores.getEasy().isEmpty());

		HighscoreEntry he;
		for (int i = 0; i < 10; i++) {
			he = entries[i];
			Highscores.addHighscore(getHEAsGame(he), he.getPlayerName());
			assertTrue(highscores.getEasy().contains(he));
		}

	}

	public Game getHEAsGame(HighscoreEntry he) {
		Game g = new Game(difficulty);
		long playtime = Double.compare(he.getTime(), Double.MAX_VALUE) == 0 ? Integer.MAX_VALUE
				: Math.round(he.getTime() * 1000d);
		g.setCurrentPlaytime(playtime);
		return g;
	}
}
