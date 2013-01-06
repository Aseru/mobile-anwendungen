package edu.hm.androidsweeper.application;

import android.app.Application;
import android.content.Context;

/** Extended Application class.
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
    
    /** Returns the application context.
     * @return The context.
     */
    public static Context getContext() {
        return mContext;
    }
}
