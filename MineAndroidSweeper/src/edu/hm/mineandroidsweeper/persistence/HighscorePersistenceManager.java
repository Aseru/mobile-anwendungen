package edu.hm.mineandroidsweeper.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.features.highscore.Highscores;
import edu.hm.mineandroidsweeper.misc.FileUtil;

/**
 * Class to manage the highscore persistence.
 */
public final class HighscorePersistenceManager {
    
    
    /**
     * Tag used for logging.
     */
    public static final String TAG = "HighscorePersistenceManager";
    
    /**
     * File name for highscores.
     */
    public static final String HIGHSCORES_FILENAME = "ms_highscore_data.ser";
    
    /**
     * Loads the Highscores from the internal storage.
     * 
     * @param context The context of this application.
     * @return The Highscores loaded.
     */
    public static Highscores loadHighscores(final Context context) {
        Highscores loadedHighscores = null;
        FileInputStream fileInputStream = null;
        Object object;
        try {
            fileInputStream = context.openFileInput(HIGHSCORES_FILENAME);
            object = FileUtil.loadObject(fileInputStream);
            loadedHighscores = (Highscores)object;
            Log.d(TAG, context.getString(R.string.str_dbg_loaded_highscores));
            return loadedHighscores;
        }
        catch (IOException ioe) {
            Log.e(TAG, context.getString(R.string.str_dbg_exception), ioe);
        }
        catch (ClassNotFoundException e) {
            Log.e(TAG, context.getString(R.string.str_dbg_exception), e);
        }
        return null;
    }
    
    
    
    /**
     * Saves the Highscores to internal storage.
     * 
     * @param context The context of this application.
     * @param highscores The Highscores object to save.
     */
    public static void saveHighscores(final Context context, final Highscores highscores) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(HighscorePersistenceManager.HIGHSCORES_FILENAME, Context.MODE_PRIVATE);
            FileUtil.saveObject(fileOutputStream, highscores);
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, context.getString(R.string.str_dbg_exception), e);
        }
        catch (IOException e) {
            Log.e(TAG, context.getString(R.string.str_dbg_exception), e);
        }
        
    }
    
}
