package edu.hm.androidsweeper.features.highscore;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import android.content.Context;
import edu.hm.androidsweeper.application.App;
import edu.hm.androidsweeper.difficulties.CustomizedDifficulty;
import edu.hm.androidsweeper.difficulties.EasyDifficulty;
import edu.hm.androidsweeper.difficulties.HardDifficulty;
import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.difficulties.MediumDifficulty;
import edu.hm.androidsweeper.persistence.HighscorePersistenceManager;

public final class Highscores implements Serializable {
    
    private static Highscores instance;
    
    private static final long serialVersionUID = 46357815423849470L;
    
    public static final int HIGHSCORE_TABLE_SIZE = 10;
    
    private final Set<HighscoreEntry> easy;
    private final Set<HighscoreEntry> medium;
    private final Set<HighscoreEntry> hard;
    
    public static Highscores getInstance() {
        if (instance == null) {
            instance = HighscorePersistenceManager.loadHighscores(App.getContext());
        }
        return instance;
    }
    
    public static void deleteHighscores(final Context context){
        instance = null;
        HighscorePersistenceManager.deleteHighscores(context);
        Highscores.getInstance();
    }
    
    private Highscores() {
        easy = new TreeSet<HighscoreEntry>();
        medium = new TreeSet<HighscoreEntry>();
        hard = new TreeSet<HighscoreEntry>();
        
        initHighscoreSets();
    }
    
    private void initHighscoreSets() {
        
        // random values
        for (int i = 1; i <= 3; i++) {
            easy.add(new HighscoreEntry("player"+i, i*83.3));
            medium.add(new HighscoreEntry("player"+(i+24), i*23.64));
            hard.add(new HighscoreEntry("player"+(i+72), i*39.12));
        }
    }
    
    public static boolean isHighscore(final double value, final IDifficulty difficulty) {
        if(difficulty instanceof CustomizedDifficulty) {
            return false;
        }
        TreeSet<HighscoreEntry> list = Highscores.getInstance().selectList(difficulty);
        
        HighscoreEntry thisValue = new HighscoreEntry("", value);
        HighscoreEntry lowestHighscore = list.last();
        if (thisValue.compareTo(lowestHighscore) > 0) {
            return true;
        }
        return false;
    }
    
    public static boolean addHighscore(final HighscoreEntry highscore, final IDifficulty difficulty) {
        TreeSet<HighscoreEntry> list = Highscores.getInstance().selectList(difficulty);
        
        if (isHighscore(highscore.getTime(), difficulty)) {
            list.remove(list.last());
            list.add(highscore);
            return true;
        }
        
        return false;
    }
    
    private TreeSet<HighscoreEntry> selectList(final IDifficulty difficulty) {
        Set<HighscoreEntry> list = null;
        Class<? extends IDifficulty> difClass = difficulty.getClass();
        if (difClass == EasyDifficulty.class) {
            list = easy;
        }
        else if (difClass == MediumDifficulty.class) {
            list = medium;
        }
        else if (difClass == HardDifficulty.class) {
            list = hard;
        }
        else {
            throw new IllegalArgumentException(
                    "There is no highscore table for the given difficulty.");
        }
        
        return (TreeSet<HighscoreEntry>)list;
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
