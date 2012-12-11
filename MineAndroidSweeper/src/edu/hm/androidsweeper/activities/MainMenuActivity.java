package edu.hm.androidsweeper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.persistence.GamePersistenceManager;

public class MainMenuActivity extends Activity {
    
    public static final String TAG = "MainMenuActivity";
    
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    
    @Override
    protected void onPostResume() {
        super.onPostResume();
        View view = findViewById(R.id.textView_resume);
        if (view instanceof TextView) {
            TextView resume = (TextView)view;
            resume.setEnabled(GamePersistenceManager.saveGameAvailable(this));
        }
    }
    
    private void initView() {
        setContentView(R.layout.activity_main);
        
        TextView newGame = (TextView)findViewById(R.id.textView_newGame);
        newGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                onNewGamesClicked();
            }
        });
        
        TextView resume = (TextView)findViewById(R.id.textView_resume);
        resume.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(final View v) {
                onResumeClicked();
            }
        });
        
        TextView highscore = (TextView)findViewById(R.id.textView_highscore);
        highscore.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(final View v) {
                onHighscoreClicked();
            }
        });
    }
    
    // Click Methods
    private void onResumeClicked() {
        Log.d(TAG, getString(R.string.str_dbg_resume_clicked));
        Intent intent = new Intent(this, GameActivity.class);
        Game loadedGame = GamePersistenceManager.loadGame(this);
        if (loadedGame != null) {
            intent.putExtra(Game.EXTRA_NAME, loadedGame);
        }
        startActivity(intent);
    }
    
    private void onNewGamesClicked() {
        Log.d(TAG, getString(R.string.str_dbg_new_game_clicked));
        Intent intent = new Intent(this, DifficultActivity.class);
        startActivity(intent);
    }
    
    private void onHighscoreClicked() {
        Log.d(TAG, getString(R.string.str_dbg_highscore_clicked));
        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }
    
}
