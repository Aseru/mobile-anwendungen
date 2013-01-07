package edu.hm.androidsweeper.gamelogic;

import java.io.Serializable;

import android.util.Log;
import android.view.View;
import edu.hm.androidsweeper.activities.IGameActivity;
import edu.hm.androidsweeper.difficulties.IDifficulty;

/** Represents a started game.
 * Contains all game data.
 */
public class Game implements Serializable {
    
    /** Extra package info for this class. */
    public static final String EXTRA_NAME = "edu.hm.mineandroidsweeper.extra.game";
    /** Class tag. */
    public static final String TAG = "Game";
    
    private static final long serialVersionUID = 7823809805228633799L;
    
    private final IDifficulty difficulty;
    
    private long currentPlaytime;
    private GameState state;
    private Playground playground;
    private transient IGameActivity activity;
    private int flagCount;
    private int usedHints;
    
    /** No-args constructor needed for Serialization. */
    protected Game() {
        difficulty = null;
    }
    
    /** Constructs a new game with the given difficulty.
     * @param difficulty The difficulty for this game.
     */
    public Game(final IDifficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    /** Initializes the game and loads the playground.
     * Must be called before using the game.
     */
    public void init() {
        setState(GameState.STARTING);
        playground = new Playground(this, difficulty);
        playground.init();
    }
    
    /** Toggles the flag on the field that is associated to the given view.
     * @param view The view of the field.
     */
    public void setFlag(final View view) {
        if (state != GameState.RUNNING) {
            return;
        }
        Object tag = view.getTag();
        Field field;
        if (tag instanceof Field) {
            field = (Field)tag;
            if (field.isRevealed()) {
                return;
            }
            if (field.isFlag()) {
                field.setFlag(false);
                flagCount--;
            }
            else {
                field.setFlag(true);
                flagCount++;
            }
            if (activity != null) {
                activity.updateFlagCount();
            }
        }
    }
    
    /** Reveals a field.
     * @param view The view of the field to reveal.
     */
    public void reveal(final View view) {
        if (state != GameState.RUNNING) {
            return;
        }
        Object tag = view.getTag();
        Field field;
        if (tag instanceof Field) {
            field = (Field)tag;
            if (!field.isRevealed() && !field.isFlag()) {
                playground.reveal(field);
            }
        }
    }
    
    /** Returns the game state.
     * @return the state value
     */
    public GameState getState() {
        return state;
    }
    
    /** Sets the game state.
     * @param state the state to set
     */
    public void setState(final GameState state) {
        this.state = state;
        Log.d(TAG, "set state to: " + state);
        if ((state == GameState.WON || state == GameState.LOSE) && activity != null) {
            activity.handleGameEnd();
        }
    }
    
    /** Returns the difficulty for this game.
     * @return the difficulty value
     */
    public IDifficulty getDifficulty() {
        return difficulty;
    }
    
    /** Returns the current played time.
     * @return the currentPlaytime value
     */
    public long getCurrentPlaytime() {
        return currentPlaytime;
    }
    
    /** Sets the current played time.
     * @param currentPlaytime the currentPlaytime to set
     */
    public void setCurrentPlaytime(final long currentPlaytime) {
        this.currentPlaytime = currentPlaytime;
    }
    
    /** Returns the playground for this game.
     * @return the playground value
     */
    public Playground getPlayground() {
        return playground;
    }
    
    /**
     * Sets the activity to the specified value.
     * 
     * @param activity
     *            the value to set
     */
    public void setActivity(final IGameActivity activity) {
        this.activity = activity;
    }
    
    /**
     * Returns the flagCount.
     * 
     * @return the flagCount value
     */
    public int getFlagCount() {
        return flagCount;
    }
    
    /** Returns whether using a hint is allowed or not.
     * @return True if there are hints left, false if not.
     */
    public boolean askForHint() {
        if (usedHints < difficulty.getHints() && state == GameState.RUNNING) {
            playground.revealRandomField();
            usedHints++;
            return true;
        }
        return false;
    }
    
    /**
     * Returns the amount of available hints left.
     * @return the usedHints value
     */
    public int getAvaiableHints() {
        return difficulty.getHints() - usedHints;
    }
    
    /**
     * Returns the amount of used hints.
     * 
     * @return the usedHints
     */
    public int getUsedHints() {
        return usedHints;
    }
    
    /** Returns the currently played time as a double value.
     * @return TODO: Define return value unit
     */
    public double getPlaytimeAsDouble() {
        long timeLong = currentPlaytime;
        return timeLong / 1000d;
    }
    
}
