package edu.hm.androidsweeper.misc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Utility class for showing toast.
 */
@SuppressLint("ShowToast")
public final class ToastUtil {
    
    private static final Object LOCK = new Object();
    
    private static Handler handler;
    
    private ToastUtil() {
        
    }
    
    /**
     * Shows a toast with the given message(Id) for a short duration.
     * 
     * @param context
     *            the activity context
     * @param messageID
     *            the resource id for the message
     */
    public static void showShortToast(final Context context, final int messageID) {
        Toast toast = Toast.makeText(context, messageID, Toast.LENGTH_SHORT);
        showToast(toast);
    }
    
    /**
     * Shows a toast with the given message(Id) for a short duration.
     * 
     * @param context
     *            the activity context
     * @param message
     *            the message string
     */
    public static void showShortToast(final Context context, final String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        showToast(toast);
    }
    
    private static void showToast(final Toast toast) {
        checkHandler();
        Runnable runnable = new Runnable() {
            
            @Override
            public void run() {
                toast.show();
            }
        };
        handler.post(runnable);
    }
    
    private static void checkHandler() {
        synchronized (ToastUtil.LOCK) {
            if (ToastUtil.handler == null) {
                ToastUtil.handler = new Handler();
            }
        }
    }
    
}
