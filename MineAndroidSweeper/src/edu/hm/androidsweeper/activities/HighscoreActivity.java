package edu.hm.androidsweeper.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.features.highscore.Highscores;
import edu.hm.androidsweeper.persistence.HighscorePersistenceManager;



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
