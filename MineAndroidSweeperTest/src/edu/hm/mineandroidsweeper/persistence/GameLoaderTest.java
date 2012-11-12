package edu.hm.mineandroidsweeper.persistence;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import junit.framework.TestCase;
import edu.hm.mineandroidsweeper.difficulties.EasyDifficulty;
import edu.hm.mineandroidsweeper.difficulties.IDifficulty;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.gamelogic.GameState;

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
	}
	
	public void testLoadAndSave() {
		saveGame();
		loadGame();
	}
	
	public void saveGame() {
		boolean success = GameLoader.saveGame(game, outputStream);
		assertTrue(success);
	}
	
	public void loadGame() {
		Game loadedGame = GameLoader.loadGame(inputStream);
		assertNotNull(loadedGame);
		
		// Test if loadedGame equals game.
		assertEquals(GameState.STARTING, loadedGame.getState());
		assertEquals(difficulty.getNumberOfBombs(), loadedGame.getDifficulty().getNumberOfBombs());
		assertEquals(difficulty.getXSize(), loadedGame.getDifficulty().getXSize());
		assertEquals(difficulty.getYSize(), loadedGame.getDifficulty().getYSize());
		
		// ...Playground not tested yet.
	}
	
}
