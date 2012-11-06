package edu.hm.mineandroidsweeper.activities;

import edu.hm.mineandroidsweeper.R;
import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
	
	public static final String TAG = "GameActicity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView(savedInstanceState);
	}
	
	private void initView(Bundle savedInstanceState){
		setContentView(R.layout.activity_game);
	}

}
