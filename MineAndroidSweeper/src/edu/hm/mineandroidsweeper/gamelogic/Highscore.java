package edu.hm.mineandroidsweeper.gamelogic;

import java.io.Serializable;

public class Highscore implements Serializable, Comparable<Highscore> {

	private static final long serialVersionUID = -1405935095422710834L;

	private final String playerName;
	private final double time;
	
	/* No-args constructor needed for Serialization. */
	protected Highscore() {
		playerName = null;
		time = 0;
	}
	
	public Highscore(String playerName, double time) {
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
	
	public static Highscore newEmpty() {
		return new Highscore("", 0);
	}
	
	
	public int compareTo(Highscore o) {
		if(time>o.time)
			return 1;
		else if(time<o.time)
			return -1;
		else
			return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		return o==this;		// Not yet implemented.
	}
	
	@Override
	public int hashCode() {
		return 1;		// Not yet implemented.
	}

}
