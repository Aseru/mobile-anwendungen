package edu.hm.mineandroidsweeper.activities;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.dialogs.DialogUtil;
import edu.hm.mineandroidsweeper.dialogs.GameFinishedDialog;
import edu.hm.mineandroidsweeper.difficulties.IDifficulty;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.gamelogic.GameState;
import edu.hm.mineandroidsweeper.graphics.FieldViewUtil;
import edu.hm.mineandroidsweeper.graphics.PlaygroundViewUtil;
import edu.hm.mineandroidsweeper.persistence.GamePersistenceManager;

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
            textView.setText(Integer.toString(game.getDifficulty().getNumberOfBombs()));
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
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, getString(R.string.str_dbg_on_create));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
    
    @Override
    protected void onStart() {
        Log.d(TAG, getString(R.string.str_dbg_on_start));
        init();
        initView();
        game.setActivity(this);
        super.onStart();
    }
    
    @Override
    protected void onStop() {
        Log.d(TAG, getString(R.string.str_dbg_on_stop));
        mChronometer.stop();
        if (game.getState() == GameState.RUNNING) {
            game.setCurrentPlaytime(getChronometerTimeInMillis());
            GamePersistenceManager.saveGame(this, game);
        }
        super.onStop();
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
    }
    
    @Override
    public void handleGameEnd() {
        Log.d(TAG, getString(R.string.str_dbg_handle_game_end));
        GamePersistenceManager.deleteSaveGame(this);
        GameState state = game.getState();
        boolean won = state == GameState.WON;
        mChronometer.stop();
        long time = getChronometerTimeInMillis();
        setChronometerTime(time);
        game.setCurrentPlaytime(time);
        double d = time / 1000d;
        Dialog loseDialog = new GameFinishedDialog(this, won, d);
        DialogUtil.showDialog(loseDialog);
    }
    
    @Override
    public void updateFlagCount() {
        int flagCount = game.getFlagCount();
        setFlagCount(flagCount);
    }
    
}
