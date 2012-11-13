package edu.hm.mineandroidsweeper.activities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.difficulties.EasyDifficulty;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.persistence.GameLoader;

public class GameActivity extends Activity {

	public final static String TAG = "GameActicity";

	private long lastPause = SystemClock.elapsedRealtime();

	private Chronometer mChronometer;
	private Game game = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView(savedInstanceState);
		init();
	}

	private void init() {
		getExtras();
		if (game == null) {
			game = createNewGame();
		}
	}

	/**
	 * Starts the DifficultyActivity and creates a new game with the user
	 * selected difficulty
	 * 
	 * @return a new game
	 */
	private Game createNewGame() {
		Game newGame = new Game(new EasyDifficulty());
		// TODO is Stub
		return newGame;
	}

	private void getExtras() {
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		Serializable serializable = extras.getSerializable(Game.EXTRA_NAME);
		if (serializable != null) {
			game = (Game) serializable;
		}
	}

	private void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_game);
		mChronometer = (Chronometer) findViewById(R.id.chronometer);
		mChronometer.setBase(SystemClock.elapsedRealtime());
		mChronometer
				.setOnChronometerTickListener(new OnChronometerTickListener() {

					long base;
					long current;
					long time;

					public void onChronometerTick(Chronometer chronometer) {
						base = chronometer.getBase();
						current = SystemClock.elapsedRealtime();
						time = current - base;
						chronometer.setText(Long.toString(TimeUnit.MILLISECONDS
								.toSeconds(time)));
					}
				});
		mChronometer.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		mChronometer.stop();
		long base = mChronometer.getBase();
		long current = SystemClock.elapsedRealtime();
		long time = current - base;
		game.setCurrentPlaytime(time);
		saveTheRunningGame();
		super.onPause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mChronometer.setBase(SystemClock.elapsedRealtime() - game.getCurrentPlaytime());
		mChronometer.start();
	}

	private boolean saveTheRunningGame() {
		Game tmp = game;

		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = openFileOutput(GameLoader.SAVE_GAME_FILENAME,
					Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			return false;
		}

		boolean success = GameLoader.saveGame(tmp, fileOutputStream);
		return success;
	}

}
