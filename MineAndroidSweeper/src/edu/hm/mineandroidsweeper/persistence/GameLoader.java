package edu.hm.mineandroidsweeper.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.gamelogic.GameState;

public final class GameLoader {
	
	public static final String SAVE_GAME_FILENAME = "ms_savegame_data.ser";	

	
	public static boolean saveGame(Game gameToSave, OutputStream outputStream) {
		GameState currentState = gameToSave.getState();
		
		gameToSave.setState(GameState.SAVED);
		ObjectOutputStream objectOutputStream = null;
		
		try {
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(gameToSave);
		}
		catch (IOException e) {
			gameToSave.setState(currentState);
			return false;
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) { }
		}
		return true;
	}
	
	public static Game loadGame(InputStream inputStream) {
		Game loadedGame;
		ObjectInputStream objectInputStream = null;
		try {
			objectInputStream = new ObjectInputStream(inputStream);
			
			loadedGame = (Game) objectInputStream.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
		finally {
			try {
				objectInputStream.close();
			} catch (IOException e) {}
		}
		
		loadedGame.setState(GameState.STARTING);
		return loadedGame;
	}
	
}
