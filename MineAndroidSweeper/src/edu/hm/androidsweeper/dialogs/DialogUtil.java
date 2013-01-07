package edu.hm.androidsweeper.dialogs;

import android.app.Dialog;
import android.os.Handler;

/**
 * Utility class for showing dialogs.
 */
public final class DialogUtil {
    
    private static final Object LOCK = new Object();
    
    private static Handler handler;
    
    private DialogUtil() { }
    
    /**
     * Show a passed dialog.
     * 
     * @param dialog
     *            the dialog to show
     */
    public static void showDialog(final Dialog dialog) {
        checkHandler();
        Runnable runnable = new Runnable() {
            
            @Override
            public void run() {
                dialog.show();
            }
        };
        handler.post(runnable);
    }
    
    private static void checkHandler() {
        synchronized (LOCK) {
            if (handler == null) {
                handler = new Handler();
            }
        }
    }
    
}
