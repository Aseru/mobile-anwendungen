package edu.hm.androidsweeper.persistence;

import java.io.IOException;
import java.io.OptionalDataException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import junit.framework.TestCase;
import edu.hm.androidsweeper.difficulties.EasyDifficulty;
import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.gamelogic.GameState;
import edu.hm.androidsweeper.misc.FileUtil;

public class GameLoaderTest extends TestCase {

	private PipedOutputStream outputStream;
	private PipedInputStream inputStream;
	
	private IDifficulty difficulty;
	private Game game;
	
	@Override
	public void setUp() throws Exception {		
		outputStream = new PipedOutputStream();
		inputStream = new PipedInputStream(outputStream);
		difficulty = new EasyDifficulty();
		game = new Game(difficulty);
		game.setState(GameState.STARTING);
	}
	
	public void testLoadAndSave() throws Exception {
			saveGame();
			loadGame();
	}
	
	private void saveGame() throws IOException {
		FileUtil.saveObject(outputStream, game);
	}
	
	private void loadGame() throws OptionalDataException, ClassNotFoundException, IOException {
		Game loadedGame = (Game) FileUtil.loadObject(inputStream);
		assertNotNull(loadedGame);
		
		// Test if loadedGame equals game.
		assertEquals(GameState.STARTING, loadedGame.getState());
		assertEquals(difficulty.getBombs(), loadedGame.getDifficulty().getBombs());
		assertEquals(difficulty.getWidth(), loadedGame.getDifficulty().getWidth());
		assertEquals(difficulty.getHeight(), loadedGame.getDifficulty().getHeight());
		
		// ...Playground not tested yet.
	}
	
}
