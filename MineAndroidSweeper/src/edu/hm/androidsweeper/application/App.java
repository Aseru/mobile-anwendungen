package edu.hm.androidsweeper.application;

import android.app.Application;
import android.content.Context;

/**
 * TODO: Document type App.
 */
public class App extends Application {
    
    private static Context mContext;
    
    @Override
    public void onCreate() {
        setContext(this);
        super.onCreate();
    }
    
    private static void setContext(final Context context) {
        App.mContext = context;
    }
    
    public static Context getContext() {
        return mContext;
    }
}
