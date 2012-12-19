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
import edu.hm.androidsweeper.persistence.HighscorePersistenceManager;

public final class Highscores implements Serializable {
    
    private static Highscores instance;
    
    private static final long serialVersionUID = 46357815423849470L;
    
    public static final int HIGHSCORE_TABLE_SIZE = 10;
    
    private final List<HighscoreEntry> easy;
    private final List<HighscoreEntry> medium;
    private final List<HighscoreEntry> hard;
    
    public static Highscores getInstance() {
        if (instance == null) {
            instance = HighscorePersistenceManager.loadHighscores(App.getContext());
        }
        return instance;
    }
    
    public static void deleteHighscores(final Context context) {
        instance = null;
        HighscorePersistenceManager.deleteHighscores(context);
        Highscores.getInstance();
    }
    
    private Highscores() {
        easy = new ArrayList<HighscoreEntry>();
        medium = new ArrayList<HighscoreEntry>();
        hard = new ArrayList<HighscoreEntry>();
    }
    
    public static boolean isHighscore(final Game game) {
        IDifficulty difficulty = game.getDifficulty();
        long time = game.getCurrentPlaytime();
        double value = time / 1000d;
        if (difficulty instanceof CustomizedDifficulty || game.getUsedHints() != 0) {
            return false;
        }
        List<HighscoreEntry> list = Highscores.getInstance().selectList(difficulty);
        HighscoreEntry entry = new HighscoreEntry("tmp", value);
        if (list.isEmpty() || list.size() < HIGHSCORE_TABLE_SIZE) {
            addHighscore(entry, difficulty);
            return true;
        }
        
        HighscoreEntry lowestHighscore = list.get(list.size() - 1);
        if (entry.compareTo(lowestHighscore) < 0) {
            addHighscore(entry, difficulty);
            return true;
        }
        return false;
    }
    
    public static void addHighscore(final HighscoreEntry entry, final IDifficulty difficulty) {
        List<HighscoreEntry> list = Highscores.getInstance().selectList(difficulty);
        list.add(entry);
        Collections.sort(list);
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
    
    public List<HighscoreEntry> getEasy() {
        return Collections.unmodifiableList(easy);
    }
    
    public List<HighscoreEntry> getMedium() {
        return Collections.unmodifiableList(medium);
    }
    
    public List<HighscoreEntry> getHard() {
        return Collections.unmodifiableList(hard);
    }
    
    public static Highscores emptyHighscores() {
        return new Highscores();
    }
    
    public static Highscores loadHighscores() {
        return null;
    }
    
}
