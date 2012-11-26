package edu.hm.mineandroidsweeper.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OptionalDataException;

import android.content.Context;
import android.util.Log;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.gamelogic.GameState;
import edu.hm.mineandroidsweeper.misc.FileUtil;

public final class GamePersistanceManager {
    
    public static final String TAG = "GameLoader";
    public static final String SAVE_GAME_FILENAME = "ms_savegame_data.ser";
    
    public static boolean saveGame(final Context context, final Game game) {
        FileOutputStream fileOutputStream = null;
        GameState currentState = game.getState();
        if (currentState != GameState.RUNNING) {
            deleteSaveGame(context);
            return false;
        }
        try {
            fileOutputStream = context.openFileOutput(GamePersistanceManager.SAVE_GAME_FILENAME,
                    Context.MODE_PRIVATE);
            game.setState(GameState.SAVED);
            FileUtil.saveObject(fileOutputStream, game);
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "Exception:", e);
            game.setState(currentState);
            return false;
        }
        catch (IOException e) {
            Log.e(TAG, "Exception:", e);
            game.setState(currentState);
            return false;
        }
        return true;
    }
    
    public static boolean deleteSaveGame(final Context context) {
        boolean deleted = context.deleteFile(SAVE_GAME_FILENAME);
        Log.d(TAG, context.getString(R.string.str_dbg_delete_save_game, deleted));
        return deleted;
    }
    
    public static boolean saveGameAvailable(final Context context) {
        try {
            FileInputStream inStream = context.openFileInput(SAVE_GAME_FILENAME);
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }
    
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
            Log.e(TAG, "Exception:", e);
        }
        catch (ClassNotFoundException e) {
            Log.e(TAG, "Exception:", e);
        }
        catch (IOException e) {
            Log.e(TAG, "Exception:", e);
        }
        return null;
    }
}
