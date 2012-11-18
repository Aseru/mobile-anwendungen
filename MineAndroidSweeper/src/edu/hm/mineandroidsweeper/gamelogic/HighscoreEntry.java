package edu.hm.mineandroidsweeper.gamelogic;

import java.io.Serializable;

public class HighscoreEntry implements Serializable, Comparable<HighscoreEntry> {

	private static final long serialVersionUID = -1405935095422710834L;

	private final String playerName;
	private final double time;
	
	/* No-args constructor needed for Serialization. */
	protected HighscoreEntry() {
		playerName = null;
		time = 0;
	}
	
	public HighscoreEntry(String playerName, double time) {
		if(playerName==null || time<0)
			throw new IllegalArgumentException();
		this.playerName = playerName;
		this.time = time;
	}

	public String getPlayerName() {
		return playerName;
	}

	public double getTime() {
		return time;
	}
	
	public static HighscoreEntry newEmpty() {
		return new HighscoreEntry("", 0);
	}
	
	
	public int compareTo(HighscoreEntry o) {
		if(time>o.time)		// Highscore is worse (bigger), then this is smaller in value.
			return -1;
		else if(time<o.time) 	// Highscore is better (smaller), then this is bigger in value.
			return 1;
		else
			return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this==o)
			return true;
		if(!(o instanceof HighscoreEntry))
			return false;
		HighscoreEntry e = (HighscoreEntry) o;
		if(time==e.time && playerName.equals(e.playerName))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result += 31*result +time;
		result += 31*result +playerName.hashCode();
		return result;
	}

}