package edu.hm.androidsweeper.persistence;

import java.io.IOException;
import java.io.OptionalDataException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import junit.framework.TestCase;
import android.annotation.SuppressLint;
import edu.hm.androidsweeper.difficulties.EasyDifficulty;
import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.gamelogic.Field;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.gamelogic.GameState;
import edu.hm.androidsweeper.gamelogic.Playground;
import edu.hm.androidsweeper.misc.FileUtil;

public class GameLoaderTest extends TestCase {

	private PipedOutputStream outputStream;
	private PipedInputStream inputStream;
	
	private IDifficulty difficulty;
	private Game game;
	private long gameTestPlaytime = 12749248L;
	
	@SuppressLint("NewApi")
	@Override
	public void setUp() throws Exception {		
		outputStream = new PipedOutputStream();
		inputStream = new PipedInputStream(outputStream, 20000);		// Must use bigger than default buffer!
		
		difficulty = new EasyDifficulty();
		game = new Game(difficulty);
	}
	
	public void testLoadAndSave() throws Exception {
		game.init();

		saveGame();
		loadGame();
	}
	
	private void saveGame() throws IOException {
		game.setState(GameState.SAVED);
		game.setCurrentPlaytime(gameTestPlaytime);
		
		
		FileUtil.saveObject(outputStream, game);
	}
	
	private void loadGame() throws OptionalDataException, ClassNotFoundException, IOException {
		Game loadedGame = (Game) FileUtil.loadObject(inputStream);
		assertNotNull(loadedGame);

		loadedGame.setState(GameState.STARTING);
		
		compareGames(game, loadedGame);
	}
	
	
	public static void compareGames(Game game, Game loadedGame) {
		
		// Test if loadedGame equals game.
		assertEquals(GameState.STARTING, loadedGame.getState());
		assertEquals(game.getDifficulty().getBombs(), loadedGame.getDifficulty().getBombs());
		assertEquals(game.getDifficulty().getWidth(), loadedGame.getDifficulty().getWidth());
		assertEquals(game.getDifficulty().getHeight(), loadedGame.getDifficulty().getHeight());
		
		assertEquals(game.getCurrentPlaytime(), loadedGame.getCurrentPlaytime());
		assertEquals(game.getAvaiableHints(), loadedGame.getAvaiableHints());
		assertEquals(game.getFlagCount(), loadedGame.getFlagCount());
		assertEquals(game.getUsedHints(), loadedGame.getUsedHints());
		
		
		// Test Playground
		Playground gamePlayground = game.getPlayground();
		Playground loadedPlayground = loadedGame.getPlayground();
		
		Field[] gameFields = gamePlayground.getFieldsArray();
		Field[] loadedFields = loadedPlayground.getFieldsArray();
		assertEquals(gameFields.length, loadedFields.length);
		
		for(int i=0; i<gameFields.length;i++) {
			assertEquals(gameFields[i], loadedFields[i]);
		}
		assertEquals(gamePlayground.getFieldsMap(), loadedPlayground.getFieldsMap());
		
	}
}
