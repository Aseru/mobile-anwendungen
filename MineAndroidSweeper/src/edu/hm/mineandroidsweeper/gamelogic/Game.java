package edu.hm.mineandroidsweeper.gamelogic;

import edu.hm.mineandroidsweeper.difficulties.IDifficulty;

public class Game {
	
	private final IDifficulty difficulty;
	private Playground playground;
	

	public Game(IDifficulty difficulty) {
		this.difficulty = difficulty;
		init();
	}
	
	private void init(){
		playground = new Playground(difficulty);
	}

}
