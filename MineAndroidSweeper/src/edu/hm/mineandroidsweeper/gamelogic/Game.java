package edu.hm.mineandroidsweeper.gamelogic;

import java.io.Serializable;

import edu.hm.mineandroidsweeper.difficulties.IDifficulty;

public class Game implements Serializable {
	
	public static final String EXTRA_NAME = "edu.hm.mineandroidsweeper.extra.game";
	
	private static final long serialVersionUID = 7823809805228633799L;

	private final IDifficulty difficulty;
	
	private GameState state;
	private Playground playground;
	private long currentPlaytime;
	
	/* No-args constructor needed for Serialization. */
	protected Game() {
		difficulty = null;
	}
	
	public Game(IDifficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public void init(){
		state = GameState.STARTING;
		playground = new Playground(this, difficulty);
		playground.init();
	}
	
	public void performClick(){
		if(state != GameState.RUNNING){
			return;
		}
		
	}

	/**
	 * @return the state
	 */
	public GameState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(GameState state) {
		this.state = state;
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
	 * @param currentPlaytime the currentPlaytime to set
	 */
	public void setCurrentPlaytime(long currentPlaytime) {
		this.currentPlaytime = currentPlaytime;
	}
	
	
	
	

}
