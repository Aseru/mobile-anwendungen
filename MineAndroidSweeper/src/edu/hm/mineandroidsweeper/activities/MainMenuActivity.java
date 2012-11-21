package edu.hm.mineandroidsweeper.activities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.persistence.GameLoader;

public class MainMenuActivity extends Activity {
    
    public static final String TAG = "MainMenuActivity";
    
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    
    private void initView() {
        setContentView(R.layout.activity_main);
        
        TextView newGame = (TextView)findViewById(R.id.textView_newGame);
        newGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                onNewGamesClicked(v);
            }
        });
        
        TextView resume = (TextView)findViewById(R.id.textView_resume);
        resume.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(final View v) {
                onResumeClicked(v);
            }
        });
        
        TextView highscore = (TextView)findViewById(R.id.textView_highscore);
        highscore.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(final View v) {
                onHighscoreClicked(v);
            }
        });
    }
    
    // Click Methods
    private void onResumeClicked(final View view) {
        Log.d(TAG, getString(R.string.str_dbg_resume_clicked));
        Intent intent = new Intent(this, GameActivity.class);
        Game loadedGame = loadGame();
        if (loadedGame != null) {
            intent.putExtra(Game.EXTRA_NAME, loadedGame);
        }
        startActivity(intent);
    }
    
    private void onNewGamesClicked(final View view) {
        Log.d(TAG, getString(R.string.str_dbg_new_game_clicked));
        Intent intent = new Intent(this, DifficultActivity.class);
        startActivity(intent);
    }
    
    private void onHighscoreClicked(final View view) {
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
