package edu.hm.mineandroidsweeper.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import edu.hm.mineandroidsweeper.R;

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
    
    public void onResumeClicked(View view){
    	System.out.println("Resume clicked");
    }
    
    public void onNewGamesClicked(View view){
    	System.out.println("Start clicked");
    }
    
    public void onHighscoreClicked(View view){
    	System.out.println("Highscore clicked");
    }
    
    
}
