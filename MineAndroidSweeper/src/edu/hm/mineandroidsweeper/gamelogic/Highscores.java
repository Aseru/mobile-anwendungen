package edu.hm.mineandroidsweeper.gamelogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.hm.mineandroidsweeper.difficulties.DifficultyDescription;
import edu.hm.mineandroidsweeper.difficulties.IDifficulty;

public final class Highscores implements Serializable {

	private static final long serialVersionUID = 46357815423849470L;

	public static final int HIGHSCORE_TABLE_SIZE = 5;
	
	private final List<Highscore> easy;
	private final List<Highscore> medium;
	private final List<Highscore> hard;
	
	protected Highscores() {
		easy = new ArrayList<Highscore>(HIGHSCORE_TABLE_SIZE);
		medium = new ArrayList<Highscore>(HIGHSCORE_TABLE_SIZE);
		hard = new ArrayList<Highscore>(HIGHSCORE_TABLE_SIZE);
		for(int i=0;i<HIGHSCORE_TABLE_SIZE;i++) {
			easy.add(Highscore.newEmpty());
			medium.add(Highscore.newEmpty());
			hard.add(Highscore.newEmpty());
		}
	}
	
	public boolean addHighscore(double value, IDifficulty difficulty) {
		List<Highscore> list = selectList(difficulty);
		
		Highscore thisValue = new Highscore("", value);
		Highscore lowestHighscore = list.get(list.size()-1);
		if(thisValue.compareTo(lowestHighscore)>0) {
			list.remove(lowestHighscore);
			// Find out position of value in the highscore.
			// list.add(thisValue);
			return true;
		}
		
		return false;
	}
	
	public Highscore getHighscoreAtPosition(int position, IDifficulty difficulty) {
		List<Highscore> list = selectList(difficulty);
		
		if(position<1 || position>HIGHSCORE_TABLE_SIZE)
			throw new IllegalArgumentException("Position is out of bounds.");
		
		Highscore result = list.get(position);
		return result;
	}
	
	
	private List<Highscore> selectList(IDifficulty difficulty) {
		List<Highscore> list = null;
		DifficultyDescription desc = difficulty.getDescription();
		if(desc==DifficultyDescription.EASY)
			list = easy;
		else if(desc==DifficultyDescription.MEDIUM)
			list = medium;
		else if(desc==DifficultyDescription.HARD)
			list = hard;
		else
			throw new IllegalArgumentException("There is no highscore table for the given difficulty.");
		
		return list;
	}
	
	
	
	public static Highscores emptyHighscores() {
		return new Highscores();
	}
	
	public static Highscores loadHighscores() {
		return null;
	}
	
}
