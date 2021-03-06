package edu.hm.androidsweeper.activities;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.dialogs.DialogUtil;
import edu.hm.androidsweeper.dialogs.GameFinishedDialog;
import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.features.highscore.Highscores;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.gamelogic.GameState;
import edu.hm.androidsweeper.graphics.FieldViewUtil;
import edu.hm.androidsweeper.graphics.PlaygroundViewUtil;
import edu.hm.androidsweeper.misc.SharedMenu;
import edu.hm.androidsweeper.persistence.GamePersistenceManager;

/**
 * Activity for the game <br>
 * creates the all views and handles the game.
 */
public class GameActivity extends Activity implements IGameActivity {
    
    /**
     * Tag used for logging.
     */
    public static final String TAG = "GameActicity";
    
    private Chronometer mChronometer;
    private Game game;
    private Dialog gameEndDialog;
    
    private void init() {
        if (game == null) {
            Bundle extras = getIntent().getExtras();
            game = getGameFromExtras(extras);
            if (game == null) {
                game = createNewGame(extras);
            }
            game.setState(GameState.RUNNING);
        }
    }
    
    /**
     * Starts the DifficultyActivity and creates a new game with the user
     * selected difficulty.
     * 
     * @param extras
     *            the extras bundle
     * @return a new game
     */
    private Game createNewGame(final Bundle extras) {
        IDifficulty difficulty = getDifficultyFromExtras(extras);
        Game newGame = new Game(difficulty);
        newGame.init();
        return newGame;
    }
    
    private Game getGameFromExtras(final Bundle extras) {
        if (extras == null) {
            return null;
        }
        Game tmpGame = null;
        Serializable serializable = extras.getSerializable(Game.EXTRA_NAME);
        if (serializable instanceof Game) {
            tmpGame = (Game)serializable;
        }
        return tmpGame;
    }
    
    private IDifficulty getDifficultyFromExtras(final Bundle extras) {
        if (extras == null) {
            return null;
        }
        IDifficulty difficulty = null;
        Serializable serializable = extras.getSerializable(IDifficulty.EXTRA_NAME);
        if (serializable != null) {
            difficulty = (IDifficulty)serializable;
        }
        return difficulty;
    }
    
    private void initView() {
        FieldViewUtil.createFieldViews(this, game);
        View view = PlaygroundViewUtil.createPlayGroundView(this, game.getPlayground());
        setContentView(view);
        initChronometer();
        initInfoBar();
    }
    
    private void setFlagCount(final int count) {
        View view = findViewById(R.id.txt_flags);
        if (view instanceof TextView) {
            TextView textView = (TextView)view;
            textView.setText(Integer.toString(count));
        }
    }
    
    private void initInfoBar() {
        TextView textView = null;
        View view = findViewById(R.id.txt_bombs);
        if (view instanceof TextView) {
            textView = (TextView)view;
            textView.setText(Integer.toString(game.getDifficulty().getBombs()));
        }
        setFlagCount(game.getFlagCount());
    }
    
    private void initChronometer() {
        View view = findViewById(R.id.chronometer);
        if (view instanceof Chronometer) {
            mChronometer = (Chronometer)view;
            mChronometer.setBase(SystemClock.elapsedRealtime());
            mChronometer.setOnChronometerTickListener(new OnChronometerTickListener() {
                
                @Override
                public void onChronometerTick(final Chronometer chronometer) {
                    setChronometerTime(getChronometerTimeInMillis());
                }
            });
        }
    }
    
    private long getChronometerTimeInMillis() {
        long base = mChronometer.getBase();
        long current = SystemClock.elapsedRealtime();
        return current - base;
    }
    
    private void setChronometerTime(final long time) {
        long timeInSecs = TimeUnit.MILLISECONDS.toSeconds(time);
        mChronometer.setText(Long.toString(timeInSecs));
    }
    
    private void openGameEndDialog() {
        gameEndDialog = new GameFinishedDialog(this, game);
        DialogUtil.showDialog(gameEndDialog);
    }
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, getString(R.string.str_dbg_on_create));
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            game = (Game)savedInstanceState.getSerializable(Game.TAG);
        }
        setContentView(R.layout.activity_game);
    }
    
    @Override
    protected void onStart() {
        Log.d(TAG, getString(R.string.str_dbg_on_start));
        init();
        initView();
        game.setActivity(this);
        if (game.getState() == GameState.SAVED) {
            game.setState(GameState.RUNNING);
        }
        super.onStart();
    }
    
    @Override
    protected void onStop() {
        Log.d(TAG, getString(R.string.str_dbg_on_stop));
        mChronometer.stop();
        game.setActivity(null);
        if (game.getState() == GameState.RUNNING) {
            game.setCurrentPlaytime(getChronometerTimeInMillis());
            GamePersistenceManager.saveGame(this, game);
        }
        if (gameEndDialog != null && gameEndDialog.isShowing()) {
            gameEndDialog.dismiss();
        }
        super.onStop();
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        Log.d(TAG, getString(R.string.str_dbg_on_save_instance));
        outState.putSerializable(Game.TAG, game);
        super.onSaveInstanceState(outState);
    }
    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        Log.d(TAG, getString(R.string.str_dbg_on_resume));
        super.onResume();
        mChronometer.setBase(SystemClock.elapsedRealtime() - game.getCurrentPlaytime());
        setChronometerTime(game.getCurrentPlaytime());
        if (game.getState() == GameState.RUNNING) {
            mChronometer.start();
        }
        else {
            openGameEndDialog();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        SharedMenu.onCreateOptionMenu(menu, getApplicationContext(), inflater);
        inflater.inflate(R.menu.menu_game, menu);
        MenuItem hint = menu.findItem(R.id.menu_hint);
        hint.setTitle(getString(R.string.menu_get_hint, game.getAvaiableHints(), game
                .getDifficulty().getHints()));
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!SharedMenu.onOptionItemSelected(item, this) && item.getItemId() == R.id.menu_hint && game.askForHint()) {
            item.setTitle(getString(R.string.menu_get_hint, game.getAvaiableHints(), game
                    .getDifficulty().getHints()));
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void handleGameEnd() {
        Log.d(TAG, getString(R.string.str_dbg_handle_game_end));
        GamePersistenceManager.deleteSaveGame(this);
        mChronometer.stop();
        long time = getChronometerTimeInMillis();
        setChronometerTime(time);
        game.setCurrentPlaytime(time);
        Highscores.isHighscore(game);
        openGameEndDialog();
    }
    
    @Override
    public void updateFlagCount() {
        int flagCount = game.getFlagCount();
        setFlagCount(flagCount);
    }
    
}
