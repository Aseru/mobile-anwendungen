package edu.hm.mineandroidsweeper.activities;

import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.R.layout;
import edu.hm.mineandroidsweeper.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
