package edu.hm.mineandroidsweeper.activities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.persistence.GameLoader;

public class MainMenuActivity extends Activity {

	public static final String TAG = "MainMenuActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	
	// Click Methods
	public void onResumeClicked(View view) {
		Log.d(TAG, getString(R.string.str_dbg_resume_clicked));
		Intent intent = new Intent(this, GameActivity.class);
		Game loadedGame = loadGame();
		if (loadedGame != null) {
			intent.putExtra(Game.EXTRA_NAME, loadedGame);
		}
		startActivity(intent);
	}

	public void onNewGamesClicked(View view) {
		Log.d(TAG, getString(R.string.str_dbg_new_game_clicked));
		Intent intent = new Intent(this, DifficultActivity.class);
		startActivity(intent);
	}

	public void onHighscoreClicked(View view) {
		Log.d(TAG, getString(R.string.str_dbg_highscore_clicked));
	}

	
	private Game loadGame() {
		Game loadedGame;

		FileInputStream fileInputStream;
		try {
			fileInputStream = openFileInput(GameLoader.SAVE_GAME_FILENAME);
		} catch (FileNotFoundException e) {
			return null;
		}

		loadedGame = GameLoader.loadGame(fileInputStream);
		return loadedGame;
	}

}
