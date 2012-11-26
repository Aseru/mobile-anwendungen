package edu.hm.mineandroidsweeper.gamelogic;

import java.io.Serializable;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import edu.hm.mineandroidsweeper.activities.GameActivity;
import edu.hm.mineandroidsweeper.dialogs.DialogUtil;
import edu.hm.mineandroidsweeper.dialogs.GameFinishedDialog;
import edu.hm.mineandroidsweeper.difficulties.IDifficulty;

public class Game implements Serializable {
    
    public static final String EXTRA_NAME = "edu.hm.mineandroidsweeper.extra.game";
    public static final String TAG = "Game";
    
    private static final long serialVersionUID = 7823809805228633799L;
    
    private final IDifficulty difficulty;
    
    private long currentPlaytime;
    private GameState state;
    private Playground playground;
    private GameActivity activity;
    private int flagCount;
    
    
    /* No-args constructor needed for Serialization. */
    protected Game() {
        difficulty = null;
    }
    
    public Game(final IDifficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    public void init() {
        setState(GameState.STARTING);
        playground = new Playground(this, difficulty);
        playground.init();
    }
    
    public void setFlag(final View view) {
        if (state != GameState.RUNNING) {
            return;
        }
        Object tag = view.getTag();
        Field field;
        if (tag instanceof Field) {
            field = (Field)tag;
            if(field.isFlag()) {
                field.setFlag(false);
                flagCount--;
            }
            else{
                field.setFlag(true);
                flagCount++;
            }
            activity.setFlagCount(flagCount);
        }
    }
    
    public void reveal(final View view) {
        if (state != GameState.RUNNING) {
            return;
        }
        Object tag = view.getTag();
        Field field;
        if (tag instanceof Field) {
            field = (Field)tag;
            if (!field.isRevealed()) {
                playground.reveal(field);
            }
        }
    }
    
    public void lose(final Context context) {
        setCurrentPlaytime(activity.getChronometerTimeInMillis());
        double d = currentPlaytime / 1000d;
        Dialog loseDialog = new GameFinishedDialog(context, false, d);
        DialogUtil.showDialog(loseDialog);
    }
    
    /**
     * @return the state
     */
    public GameState getState() {
        return state;
    }
    
    /**
     * @param state
     *            the state to set
     */
    public void setState(final GameState state) {
        this.state = state;
        Log.d(TAG, "set state to: " + state);
    }
    
    /**
     * @return the difficulty
     */
    public IDifficulty getDifficulty() {
        return difficulty;
    }
    
    /**
     * @return the currentPlaytime
     */
    public long getCurrentPlaytime() {
        return currentPlaytime;
    }
    
    /**
     * @param currentPlaytime
     *            the currentPlaytime to set
     */
    public void setCurrentPlaytime(final long currentPlaytime) {
        this.currentPlaytime = currentPlaytime;
    }
    
    /**
     * @return the playground
     */
    public Playground getPlayground() {
        return playground;
    }
    
    /**
     * Sets the activity to the specified value.
     * 
     * @param activity the value to set
     */
    public void setActivity(final GameActivity activity) {
        this.activity = activity;
    }
    
    /**
     * Returns the flagCount.
     * 
     * @return the flagCount
     */
    public int getFlagCount() {
        return flagCount;
    }
    
    
    
}
