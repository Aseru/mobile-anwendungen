package edu.hm.mineandroidsweeper.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.features.highscore.Highscores;
import edu.hm.mineandroidsweeper.persistence.HighscorePersistenceManager;



public class HighscoreActivity extends Activity {
    
    public static final String TAG = "HighscoreActivity";
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        View highscoreLayout = initView();
        
        //setContentView(highscoreLayout);
    }
    
    
    private View initView() {
        View highscoreView = getLayoutInflater().inflate(R.layout.activity_highscore, null);
        
        Highscores highscores = HighscorePersistenceManager.loadHighscores(this);
        
        highscoreView.findViewById(R.id.name1);
        
        
        return highscoreView;
    }
    
    
}
