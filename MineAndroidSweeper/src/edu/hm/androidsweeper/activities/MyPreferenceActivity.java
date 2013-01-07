package edu.hm.androidsweeper.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import edu.hm.androidsweeper.R;

/**
 * Preference activity.
 */
public class MyPreferenceActivity extends PreferenceActivity {
    
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
