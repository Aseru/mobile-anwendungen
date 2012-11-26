package edu.hm.mineandroidsweeper.dialogs;

import android.app.Dialog;
import android.os.Handler;

/**
 * TODO: Document type DialogUtil.
 */
public class DialogUtil {
    
    private static Handler handler = null;
    
    public static void showDialog(final Dialog dialog){
        if(handler == null){
            handler = new Handler();
        }
        
        Runnable runnable = new Runnable() {
            
            @Override
            public void run() {
                dialog.show();
            }
        };
        handler.post(runnable);
    }
    
}