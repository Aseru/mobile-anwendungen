package edu.hm.androidsweeper.misc;

import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import edu.hm.androidsweeper.R;

/**
 * TODO: Document type SharedMenu.
 */
public class SharedMenu {
    
    public static void onCreateOptionMenu(final Menu menu, final Context context, final MenuInflater inflater) {
        inflater.inflate(R.menu.menu_shared, menu);
    }
    
    public static boolean onOptionItemSelected(final MenuItem item, final Activity activity){
        switch (item.getItemId()) {
            case R.id.menu_settings:
                return true;
                
            default:
                return false;
        }
    }
    
}
