package edu.hm.mineandroidsweeper.activities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.difficulties.IDifficulty;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.gamelogic.GameState;
import edu.hm.mineandroidsweeper.graphics.FieldViewUtil;
import edu.hm.mineandroidsweeper.graphics.PlaygroundViewUtil;
import edu.hm.mineandroidsweeper.persistence.GameLoader;

public class GameActivity extends Activity {
    
    public final static String TAG = "GameActicity";
    
    private Chronometer mChronometer;
    private Game game = null;
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView(savedInstanceState);
        game.setState(GameState.RUNNING);
    }
    
    private void init() {
        Bundle extras = getIntent().getExtras();
        game = getGameFromExtras(extras);
        if (game == null) {
            game = createNewGame(extras);
        }
    }
    
    /**
     * Starts the DifficultyActivity and creates a new game with the user
     * selected difficulty
     * 
     * @return a new game
     */
    private Game createNewGame(final Bundle extras) {
        IDifficulty difficulty = getDifficultyFromExtras(extras);
        Game newGame = new Game(difficulty);
        newGame.init();
        return newGame;
    }
    
    
    
    /* (non-Javadoc)
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    private Game getGameFromExtras(final Bundle extras){
        if(extras == null) {
            return null;
        }
        Game game = null;
        Serializable serializable = extras.getSerializable(Game.EXTRA_NAME);
        if (serializable != null) {
            game = (Game) serializable;
        }
        return game;
    }
    
    private IDifficulty getDifficultyFromExtras(final Bundle extras){
        if(extras == null) {
            return null;
        }
        IDifficulty difficulty = null;
        Serializable serializable = extras.getSerializable(IDifficulty.EXTRA_NAME);
        if (serializable != null) {
            difficulty = (IDifficulty) serializable;
        }
        return difficulty;
    }
    
    private void initView(final Bundle savedInstanceState) {
        FieldViewUtil.createFieldViews(this, game);
        View view = PlaygroundViewUtil.createPlayGroundView(this, game.getPlayground());
        setContentView(view);
        initChronometer();
    }
    
    private void initChronometer(){
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer
        .setOnChronometerTickListener(new OnChronometerTickListener() {
            
            long base;
            long current;
            long time;
            
            @Override
            public void onChronometerTick(final Chronometer chronometer) {
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
        super.onPause();
        
        mChronometer.stop();
        long base = mChronometer.getBase();
        long current = SystemClock.elapsedRealtime();
        long time = current - base;
        game.setCurrentPlaytime(time);
        
        boolean result = saveTheRunningGame();
        Log.d(TAG, getString(R.string.str_dbg_saving_game) +"... saved: " +result);
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
