package edu.hm.androidsweeper.features.highscore;

import java.io.Serializable;
import java.util.Comparator;

/** This class represents one single highscore entry, with a player name and a time value.
 * Note that, the smaller the time value is, the better the entry is considered.
 */
public class HighscoreEntry implements Serializable, Comparable<HighscoreEntry>, Comparator<HighscoreEntry> {
    
    private static final long serialVersionUID = -1405935095422710834L;
    
    private final String playerName;
    private final double time;
    
    /* No-args constructor needed for Serialization. */
    /**
     * Creates a new instance of {@link HighscoreEntry}.
     * The object will have no player name and the default maximum time value assigned.
     */
    protected HighscoreEntry() {
        playerName = null;
        time = Double.MAX_VALUE;
    }
    
    /**
     * Creates a new instance of {@link HighscoreEntry}.
     * @param playerName The player name for the entry.
     * @param time The time value for the entry.
     * @throws IllegalArgumentException if playerName is null or time is less than zero.
     */
    public HighscoreEntry(final String playerName, final double time) {
        if (playerName == null || time < 0) {
            throw new IllegalArgumentException();
        }
        this.playerName = playerName;
        this.time = time;
    }
    
    /** Returns the player name for this entry.
     * @return The player name.
     */
    public String getPlayerName() {
        return playerName;
    }
    
    /** Returns the time for this entry.
     * @return The time value.
     */
    public double getTime() {
        return time;
    }
    
    /** Returns a new instance empty instance.
     * The object will have no player name and the maximum time value assigned.
     * @return A new empty {@link HighscoreEntry}.
     */
    public static HighscoreEntry newEmpty() {
        return new HighscoreEntry("", Double.MAX_VALUE);
    }
    
    @Override
    public int compareTo(final HighscoreEntry o) {
        int comp = Double.compare(time, o.time);
        if (comp < 0) {
            // in value.
            return 1;
        }
        else if (comp > 0) {
            // bigger in value.
            return -1;
        }
        
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HighscoreEntry)) {
            return false;
        }
        HighscoreEntry e = (HighscoreEntry)o;
        if (!(Double.compare(time, e.time) == 0)) {
            return false;
        }
        
        if (playerName == null) {
            return e.playerName == null;
        }
        else {
            return playerName.equals(e.playerName);
        }
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result += 31 * result + time;
        result += 31 * result + playerName.hashCode();
        return result;
    }
    
    @Override
    public int compare(final HighscoreEntry arg0, final HighscoreEntry arg1) {
        return arg1.compareTo(arg0);
    }
    
}
