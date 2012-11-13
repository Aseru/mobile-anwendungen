package edu.hm.mineandroidsweeper.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import edu.hm.mineandroidsweeper.gamelogic.Highscores;

public final class HighscorePersistence {


	/* Highscores for different difficulties.
	 * Stored in Shared preferences or in file system?
	 */
	
	public static final String SAVE_HIGHSCORES_FILENAME = "ms_savehighscores_data.ser";	
	
	public static boolean savehighscores(Highscores highscores, OutputStream outputStream) {
		ObjectOutputStream objectOutputStream = null;
		
		try {
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(highscores);
		}
		catch (IOException e) {
			return false;
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) { }
		}
		return true;
	}
	
	
	public static Highscores loadHighscores(InputStream inputStream) {
		Highscores highscores;
		ObjectInputStream objectInputStream = null;
		try {
			objectInputStream = new ObjectInputStream(inputStream);
			
			highscores = (Highscores) objectInputStream.readObject();
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
		
		return highscores;
	}
	
}
