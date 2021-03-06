package edu.hm.androidsweeper.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OptionalDataException;

import android.content.Context;
import android.util.Log;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.gamelogic.GameState;
import edu.hm.androidsweeper.misc.FileUtil;

/**
 * Class to manage the save game persistence.
 */
public final class GamePersistenceManager {
    
    /**
     * 
     * Creates a new instance of {@link GamePersistenceManager}.
     * This constructor should never be called.
     */
    private GamePersistenceManager() { }
    
    /**
     * Tag used for logging.
     */
    public static final String TAG = "GamePersistenceManager";
    
    /**
     * File name for save game.
     */
    public static final String SAVE_GAME_FILENAME = "ms_savegame_data.ser";
    
    
    /**
     * Saves the passed game to internal storage. <br>
     * uses private mode.
     * 
     * @param context
     *            the context which calls this method.
     * @param game
     *            the game object to save.
     */
    public static void saveGame(final Context context, final Game game) {
        FileOutputStream fileOutputStream = null;
        GameState currentState = game.getState();
        boolean success = false;
        try {
            fileOutputStream = context.openFileOutput(GamePersistenceManager.SAVE_GAME_FILENAME,
                    Context.MODE_PRIVATE);
            game.setState(GameState.SAVED);
            game.setActivity(null);
            FileUtil.saveObject(fileOutputStream, game);
            success = true;
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, context.getString(R.string.str_dbg_exception), e);
            game.setState(currentState);
        }
        catch (IOException e) {
            Log.e(TAG, context.getString(R.string.str_dbg_exception), e);
            game.setState(currentState);
        }
        finally {
            Log.d(TAG, context.getString(R.string.str_dbg_saving_game, success));
        }
    }
    
    /**
     * Deletes the save game if one is available.
     * 
     * @param context
     *            which calls this method.
     * @return true if save game is deleted.
     */
    public static boolean deleteSaveGame(final Context context) {
        boolean deleted = context.deleteFile(SAVE_GAME_FILENAME);
        Log.d(TAG, context.getString(R.string.str_dbg_delete_save_game, deleted));
        return deleted;
    }
    
    /**
     * Checks if there is a save game available.
     * 
     * @param context
     *            which calls this method.
     * @return true if there is a save game.
     */
    public static boolean saveGameAvailable(final Context context) {
        boolean available = false;
        try {
            context.openFileInput(SAVE_GAME_FILENAME);
            available = true;
        }
        catch (FileNotFoundException exception) {
            available = false;
        }
        Log.d(TAG, context.getString(R.string.str_dbg_save_game_available, available));
        return available;
    }
    
    /**
     * Loads a game from internal storage.
     * 
     * @param context
     *            which calls this method.
     * @return the loaded save game or null if no game is available or couldn't
     *         loaded.
     */
    public static Game loadGame(final Context context) {
        Game loadedGame = null;
        FileInputStream fileInputStream = null;
        Object object;
        try {
            fileInputStream = context.openFileInput(SAVE_GAME_FILENAME);
            long fileSize = fileInputStream.getChannel().size();
            object = FileUtil.loadObject(fileInputStream);
            loadedGame = (Game)object;
            Log.d(TAG, context.getString(R.string.str_dbg_loaded_save_game, fileSize));
            if (loadedGame.getState() == GameState.SAVED) {
                return loadedGame;
            }
        }
        catch (OptionalDataException e) {
            Log.e(TAG, context.getString(R.string.str_dbg_exception), e);
        }
        catch (ClassNotFoundException e) {
            Log.e(TAG, context.getString(R.string.str_dbg_exception), e);
        }
        catch (IOException e) {
            Log.e(TAG, context.getString(R.string.str_dbg_exception), e);
        }
        return null;
    }
    
    
    
}
