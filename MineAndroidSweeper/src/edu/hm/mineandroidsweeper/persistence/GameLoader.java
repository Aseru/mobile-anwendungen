package edu.hm.mineandroidsweeper.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;

import android.content.Context;
import android.util.Log;
import edu.hm.mineandroidsweeper.gamelogic.Game;
import edu.hm.mineandroidsweeper.gamelogic.GameState;

public final class GameLoader {
    
    public static final String TAG = "GameLoader";
    public static final String SAVE_GAME_FILENAME = "ms_savegame_data.ser";
    
    public static boolean saveGame(final Context context, final Game game) {
        FileOutputStream fileOutputStream = null;
        GameState currentState = game.getState();
        
        try {
            fileOutputStream = context.openFileOutput(GameLoader.SAVE_GAME_FILENAME,
                    Context.MODE_PRIVATE);
            saveObject(fileOutputStream, game);
            game.setState(GameState.SAVED);
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
    
    public static void saveObject(final OutputStream outStream, final Object object)
            throws IOException {
        ObjectOutputStream objectOutputStream = null;
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }
    
    public static Object loadObject(final InputStream inStream) throws OptionalDataException,
    ClassNotFoundException, IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
    
    public static Game loadGame(final Context context) {
        Game loadedGame = null;
        FileInputStream fileInputStream = null;
        
        Object object;
        try {
            fileInputStream = context.openFileInput(SAVE_GAME_FILENAME);
            object = loadObject(fileInputStream);
            loadedGame = (Game) object;
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
        return loadedGame;
    }
}
