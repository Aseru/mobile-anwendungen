package edu.hm.androidsweeper.features.highscore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import edu.hm.androidsweeper.application.App;
import edu.hm.androidsweeper.difficulties.CustomizedDifficulty;
import edu.hm.androidsweeper.difficulties.EasyDifficulty;
import edu.hm.androidsweeper.difficulties.HardDifficulty;
import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.difficulties.MediumDifficulty;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.gamelogic.GameState;
import edu.hm.androidsweeper.persistence.HighscorePersistenceManager;

/**
 * This class contains the highscore tables and provides methods for those.<br>
 * It is implemented as a singleton. There is one table for every basic
 * difficulty.
 */
public final class Highscores implements Serializable {
    
    private static Highscores instance;
    
    private static boolean initialized;
    
    private static final long serialVersionUID = 46357815423849470L;
    
    /**
     * The maximum number of highscore entries that can be stored in a table.
     */
    public static final int HIGHSCORE_TABLE_SIZE = 10;
    
    private final List<HighscoreEntry> easy;
    private final List<HighscoreEntry> medium;
    private final List<HighscoreEntry> hard;
    
    private Highscores() {
        easy = new ArrayList<HighscoreEntry>();
        medium = new ArrayList<HighscoreEntry>();
        hard = new ArrayList<HighscoreEntry>();
    }
    
    /**
     * Loads if needed and returns the Highscore instance for this application.
     * 
     * @return The Highscore instance.
     */
    public static Highscores getInstance() {
        if (!initialized) {
            instance = HighscorePersistenceManager.loadHighscores(App.getContext());
            initialized = true;
        }
        return instance;
    }
    
    /**
     * Deletes all saved Highscore entries.
     * 
     * @param context
     *            The context for this application.
     */
    public static void deleteHighscores(final Context context) {
        initialized = false;
        HighscorePersistenceManager.deleteHighscores(context);
        Highscores.getInstance();
    }
    
    /**
     * Returns whether the given game scored a highscore or not.
     * 
     * @param game
     *            The game to check the score.
     * @return True if a highscore was made, false if not.
     */
    public static boolean isHighscore(final Game game) {
        IDifficulty difficulty = game.getDifficulty();
        if (difficulty instanceof CustomizedDifficulty || game.getUsedHints() != 0
                || game.getState() != GameState.WON) {
            return false;
        }
        List<HighscoreEntry> list = Highscores.getInstance().selectList(difficulty);
        HighscoreEntry entry = new HighscoreEntry("tmp", game.getPlaytimeAsDouble());
        if (list.isEmpty() || list.size() < HIGHSCORE_TABLE_SIZE) {
            return true;
        }
        
        HighscoreEntry lowestHighscore = list.get(list.size() - 1);
        return entry.compareTo(lowestHighscore) > 0;
    }
    
    /**
     * Adds a highscore for the given game and player name.
     * 
     * @param game
     *            The game to add as a highscore entry.
     * @param playerName
     *            The player name for this highscore entry.
     */
    public static void addHighscore(final Game game, final String playerName) {
        IDifficulty difficulty = game.getDifficulty();
        List<HighscoreEntry> list = Highscores.getInstance().selectList(difficulty);
        HighscoreEntry entry = new HighscoreEntry(playerName, game.getPlaytimeAsDouble());
        list.add(entry);
        Collections.sort(list, entry);
        if (list.size() > HIGHSCORE_TABLE_SIZE) {
            list.remove(list.size() - 1);
        }
        HighscorePersistenceManager.saveHighscores(App.getContext(), Highscores.getInstance());
    }
    
    private List<HighscoreEntry> selectList(final IDifficulty difficulty) {
        List<HighscoreEntry> list = null;
        if (difficulty instanceof EasyDifficulty) {
            list = easy;
        }
        else if (difficulty instanceof MediumDifficulty) {
            list = medium;
        }
        else if (difficulty instanceof HardDifficulty) {
            list = hard;
        }
        else {
            throw new IllegalArgumentException(
                    "There is no highscore table for the given difficulty.");
        }
        
        return list;
    }
    
    /**
     * Returns the highscore table for the {@link EasyDifficulty}.
     * 
     * @return A list of highscore entries.
     */
    public List<HighscoreEntry> getEasy() {
        return Collections.unmodifiableList(easy);
    }
    
    /**
     * Returns the highscore table for the {@link MediumDifficulty}.
     * 
     * @return A list of highscore entries.
     */
    public List<HighscoreEntry> getMedium() {
        return Collections.unmodifiableList(medium);
    }
    
    /**
     * Returns the highscore table for the {@link HardDifficulty}.
     * 
     * @return A list of highscore entries.
     */
    public List<HighscoreEntry> getHard() {
        return Collections.unmodifiableList(hard);
    }
    
    /**
     * Creates a new empty Highscores instance.
     * 
     * @return A new Highscores instance.
     */
    public static Highscores emptyHighscores() {
        return new Highscores();
    }
    
    /**
     * Manually sets the main instance to the given instance.
     * 
     * @param inst
     *            The Highscores instance to set.
     */
    protected static void setInstance(final Highscores inst) {
        instance = inst;
        initialized = instance != null;
    }
    
}
