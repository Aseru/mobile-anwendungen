package edu.hm.mineandroidsweeper.activities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.persistence.GameLoader;

public class MainMenuActivity extends Activity {

	public static final String TAG = "MainMenuActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setContentView(R.layout.activity_main);
		
		TextView newGame = (TextView) findViewById(R.id.textView_newGame);
				newGame.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onNewGamesClicked(v);
			}
		});
		
		TextView resume = (TextView) findViewById(R.id.textView_resume);
		resume.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				onResumeClicked(v);
			}
		});
		
		TextView highscore = (TextView) findViewById(R.id.textView_highscore);
		highscore.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				onHighscoreClicked(v);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void onResumeClicked(View view) {
		Log.d(TAG, getString(R.string.str_dbg_resume_clicked));
	}

	private void onNewGamesClicked(View view) {
		Log.d(TAG, getString(R.string.str_dbg_new_game_clicked));
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}

	private void onHighscoreClicked(View view) {
		Log.d(TAG, getString(R.string.str_dbg_highscore_clicked));
	}
	
	
	private Game loadGame() {
		Game loadedGame;
		
		FileInputStream fileInputStream;
		try {
			fileInputStream = openFileInput(GameLoader.SAVE_GAME_FILENAME);
		}
		catch (FileNotFoundException e) {
			return null;
		}
		
		loadedGame = GameLoader.loadGame(fileInputStream);
		return loadedGame;
	}

}
