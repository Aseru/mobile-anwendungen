package edu.hm.mineandroidsweeper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import edu.hm.mineandroidsweeper.R;

public class MainMenuActivity extends Activity {
	
	public static final String TAG = "MainMenu";

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
		Intent intent = new Intent(this, HighscoreActivity.class);
		startActivity(intent);
	}

	private void onHighscoreClicked(View view) {
		Log.d(TAG, getString(R.string.str_dbg_highscore_clicked));
	}

}
