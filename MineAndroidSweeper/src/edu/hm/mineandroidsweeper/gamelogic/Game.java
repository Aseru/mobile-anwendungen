package edu.hm.mineandroidsweeper.gamelogic;

import edu.hm.mineandroidsweeper.difficulties.IDifficulty;

public class Game {
	
	private final IDifficulty difficulty;
	
	private GameState state;
	private Playground playground;
	

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
	
	

}
