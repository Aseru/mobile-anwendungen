package edu.hm.androidsweeper.persistence;

import java.util.List;

import android.test.AndroidTestCase;
import edu.hm.androidsweeper.difficulties.EasyDifficulty;
import edu.hm.androidsweeper.difficulties.HardDifficulty;
import edu.hm.androidsweeper.difficulties.MediumDifficulty;
import edu.hm.androidsweeper.features.highscore.HighscoreEntry;
import edu.hm.androidsweeper.features.highscore.Highscores;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.gamelogic.GameState;

public class HighscorePersistenceManagerTest extends AndroidTestCase {
	
	private HighscoreEntry entryEasy;
	private HighscoreEntry entryMedium;
	private HighscoreEntry entryHard;

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		entryEasy = new HighscoreEntry("testPlayer1", 0.123D);
		entryMedium = new HighscoreEntry("testPlayer2", 0.223D);
		entryHard = new HighscoreEntry("testPlayer3", 0.323D);
		Highscores.deleteHighscores(getContext());
		Game game = new Game(new EasyDifficulty());
		game.setState(GameState.WON);
		game.setCurrentPlaytime(123L);
		Highscores.addHighscore(game, "testPlayer1");
		game = new Game(new MediumDifficulty());
		game.setCurrentPlaytime(223L);
		game.setState(GameState.WON);
		Highscores.addHighscore(game, "testPlayer2");
		game = new Game(new HardDifficulty());
		game.setCurrentPlaytime(323L);
		game.setState(GameState.WON);
		Highscores.addHighscore(game, "testPlayer3");
	}
	
	public void testSaveAndLoad(){
		HighscorePersistenceManager.saveHighscores(getContext(), Highscores.getInstance());
		Highscores highscores = HighscorePersistenceManager.loadHighscores(getContext());

		List<HighscoreEntry> easy = highscores.getEasy();
		List<HighscoreEntry> medium = highscores.getMedium();
		List<HighscoreEntry> hard = highscores.getHard();
		
		for(HighscoreEntry entry : easy){
			assertTrue(entry.equals(entryEasy));
		}
		
		for(HighscoreEntry entry : medium){
			assertEquals(entry, entryMedium);
		}
		
		for(HighscoreEntry entry : hard){
			assertEquals(entry, entryHard);
		}
	}
	
	

}
