package edu.hm.androidsweeper.misc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.activities.MyPreferenceActivity;

/**
 * Utility class to handle a shared menu.
 */
public final class SharedMenu {
    
    private SharedMenu() {
        
    }
    
    /**
     * loads the shared menu.
     * 
     * @param menu
     *            the current activity menu
     * @param context
     *            the currenty activity context
     * @param inflater
     *            the menu inflater
     */
    public static void onCreateOptionMenu(final Menu menu, final Context context,
            final MenuInflater inflater) {
        inflater.inflate(R.menu.menu_shared, menu);
    }
    
    /**
     * onOptionItemSelected implementation for.
     * 
     * @param item
     *            the selected option item
     * @param activity
     *            the current activity
     * @return true if option item was handled
     */
    public static boolean onOptionItemSelected(final MenuItem item, final Activity activity) {
        if (item.getItemId() == R.id.menu_settings) {
            activity.startActivity(new Intent(activity, MyPreferenceActivity.class));
            return true;
        }
        return false;
    }
}
