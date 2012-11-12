package edu.hm.mineandroidsweeper.activities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.persistence.GameLoader;


public class GameActivity extends Activity {
	
	public final static String TAG = "GameActicity";
	
	
	private Chronometer mChronometer;
	private long lastPause = SystemClock.elapsedRealtime();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView(savedInstanceState);
	}
	
	private void initView(Bundle savedInstanceState){
		setContentView(R.layout.activity_game);
		mChronometer = (Chronometer) findViewById(R.id.chronometer);
		mChronometer.setBase(SystemClock.elapsedRealtime());
		mChronometer.setOnChronometerTickListener(new OnChronometerTickListener() {
			
			long base;
			long current;
			long time;
			
			public void onChronometerTick(Chronometer chronometer) {
				base = chronometer.getBase();
				current = SystemClock.elapsedRealtime();
				time = current - base;
				chronometer.setText(Long.toString(TimeUnit.MILLISECONDS.toSeconds(time)));
			}
		});
		mChronometer.start();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		lastPause = SystemClock.elapsedRealtime();
		mChronometer.stop();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mChronometer.setBase(mChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
		mChronometer.start();
	}

	
	
	private boolean saveTheRunningGame() {
		Game tmp = null;
		
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = openFileOutput(GameLoader.SAVE_GAME_FILENAME, Context.MODE_PRIVATE);
		} 
		catch (FileNotFoundException e) {
			return false;
		}
		
		boolean success = GameLoader.saveGame(tmp, fileOutputStream);
		return success;
	}
	

}
