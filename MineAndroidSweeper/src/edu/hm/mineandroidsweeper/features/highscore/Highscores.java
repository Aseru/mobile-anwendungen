package edu.hm.mineandroidsweeper.features.highscore;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import edu.hm.mineandroidsweeper.difficulties.DifficultyDescription;
import edu.hm.mineandroidsweeper.difficulties.IDifficulty;

public final class Highscores implements Serializable {

	private static final long serialVersionUID = 46357815423849470L;

	public static final int HIGHSCORE_TABLE_SIZE = 5;
	
	private final Set<HighscoreEntry> easy;
	private final Set<HighscoreEntry> medium;
	private final Set<HighscoreEntry> hard;
	
	protected Highscores() {
		easy = new TreeSet<HighscoreEntry>();
		medium = new TreeSet<HighscoreEntry>();
		hard = new TreeSet<HighscoreEntry>();
		
		for(int i=0;i<HIGHSCORE_TABLE_SIZE;i++) {
			easy.add(HighscoreEntry.newEmpty());
			medium.add(HighscoreEntry.newEmpty());
			hard.add(HighscoreEntry.newEmpty());
		}
	}
	
	public boolean isHighscore(double value, IDifficulty difficulty) {
		TreeSet<HighscoreEntry> list = selectList(difficulty);
		
		HighscoreEntry thisValue = new HighscoreEntry("", value);
		HighscoreEntry lowestHighscore = list.last();
		if(thisValue.compareTo(lowestHighscore)>0)
			return true;
		return false;
	}
	
	public boolean addHighscore(HighscoreEntry highscore, IDifficulty difficulty) {
		TreeSet<HighscoreEntry> list = selectList(difficulty);

		if(isHighscore(highscore.getTime(), difficulty)) {
			list.remove(list.last());
			list.add(highscore);
			return true;
		}
		
		return false;
	}
	
	
	private TreeSet<HighscoreEntry> selectList(IDifficulty difficulty) {
		Set<HighscoreEntry> list = null;
		DifficultyDescription desc = difficulty.getDescription();
		if(desc==DifficultyDescription.EASY)
			list = easy;
		else if(desc==DifficultyDescription.MEDIUM)
			list = medium;
		else if(desc==DifficultyDescription.HARD)
			list = hard;
		else
			throw new IllegalArgumentException("There is no highscore table for the given difficulty.");
		
		return (TreeSet<HighscoreEntry>) list;
	}
	
	
	
	public Set<HighscoreEntry> getEasy() {
		return Collections.unmodifiableSet(easy);
	}

	public Set<HighscoreEntry> getMedium() {
		return Collections.unmodifiableSet(medium);
	}

	public Set<HighscoreEntry> getHard() {
		return Collections.unmodifiableSet(hard);
	}

	
	public static Highscores emptyHighscores() {
		return new Highscores();
	}
	
	public static Highscores loadHighscores() {
		return null;
	}
	
}
