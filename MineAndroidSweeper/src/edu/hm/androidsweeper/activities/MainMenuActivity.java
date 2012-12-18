package edu.hm.androidsweeper.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.dialogs.ClearHighscoreDialog;
import edu.hm.androidsweeper.dialogs.DialogUtil;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.persistence.GamePersistenceManager;

/**
 * Activity for the main menu.
 */
public class MainMenuActivity extends Activity {
    
    /**
     * Logging Tag.
     */
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
        View view;
        setContentView(R.layout.activity_main);
        
        view = findViewById(R.id.textView_newGame);
        if (view instanceof TextView) {
            TextView newGame = (TextView)view;
            newGame.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    onNewGamesClicked();
                }
            });
        }
        
        view = findViewById(R.id.textView_resume);
        if (view instanceof TextView) {
            TextView resume = (TextView)view;
            resume.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(final View v) {
                    onResumeClicked();
                }
            });
        }
        
        view = findViewById(R.id.textView_highscore);
        if (view instanceof TextView) {
            TextView highscore = (TextView)view;
            highscore.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(final View v) {
                    onHighscoreClicked();
                }
            });
        }
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
    
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear_highscore:
                Dialog dialog = new ClearHighscoreDialog(this);
                DialogUtil.showDialog(dialog);
                break;
                
            case R.id.menu_settings:
                break;
                
            default:
                break;
        }
        return true;
    }
    
}
