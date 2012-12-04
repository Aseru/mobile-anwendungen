package edu.hm.mineandroidsweeper.misc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * TODO: Document type ToastUtil.
 */
@SuppressLint("ShowToast")
public class ToastUtil {
    
    private static Handler handler = null;
    
    private ToastUtil() {}
    
    public static void showShortToast(final Context context, final int messageID) {
        Toast toast = Toast.makeText(context, messageID, Toast.LENGTH_SHORT);
        showToast(toast);
    }
    
    public static void showShortToast(final Context context, final String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        showToast(toast);
    }
    
    private static void showToast(final Toast toast) {
        if (handler == null) {
            handler = new Handler();
        }
        Runnable runnable = new Runnable() {
            
            @Override
            public void run() {
                toast.show();
            }
        };
        handler.post(runnable);
    }
    
}
