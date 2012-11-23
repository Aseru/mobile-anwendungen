package edu.hm.mineandroidsweeper.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OptionalDataException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import junit.framework.TestCase;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.test.mock.MockContext;
import edu.hm.mineandroidsweeper.activities.GameActivity;
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
		game.setState(GameState.STARTING);
	}
	
	public void testLoadAndSave() throws Exception {
			saveGame();
			loadGame();
	}
	
	private void saveGame() throws IOException {
		GameLoader.saveObject(outputStream, game);
	}
	
	private void loadGame() throws OptionalDataException, ClassNotFoundException, IOException {
		Game loadedGame = (Game) GameLoader.loadObject(inputStream);
		assertNotNull(loadedGame);
		
		// Test if loadedGame equals game.
		assertEquals(GameState.STARTING, loadedGame.getState());
		assertEquals(difficulty.getNumberOfBombs(), loadedGame.getDifficulty().getNumberOfBombs());
		assertEquals(difficulty.getXSize(), loadedGame.getDifficulty().getXSize());
		assertEquals(difficulty.getYSize(), loadedGame.getDifficulty().getYSize());
		
		// ...Playground not tested yet.
	}
	
}
