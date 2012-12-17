package edu.hm.androidsweeper.activities;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.features.highscore.HighscoreEntry;
import edu.hm.androidsweeper.features.highscore.Highscores;
import edu.hm.androidsweeper.persistence.HighscorePersistenceManager;

public class HighscoreActivity extends Activity {
    
    public static final String TAG = "HighscoreActivity";
    
    private static int[] viewNames = {R.id.name1,R.id.name2,R.id.name3,R.id.name4,R.id.name5};
    private static int[] viewScores = {R.id.score1,R.id.score2,R.id.score3,R.id.score4,R.id.score5};
    
    private Highscores myHighscores;
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        loadHighscores();
        View highscoreLayout = initView();
        
        setContentView(highscoreLayout);
    }
    
    private View initView() {
        View view;
        View highscoreView = getLayoutInflater().inflate(R.layout.activity_highscore, null);
        
        Set<HighscoreEntry> easyHighscores = myHighscores.getEasy();
        
        Iterator<HighscoreEntry> it = easyHighscores.iterator();
        HighscoreEntry he = null;
        for (int i = 0; i < viewNames.length; i++) {
            
            if (it.hasNext()) {
                he = it.next();
            }
            view = highscoreView.findViewById(viewNames[i]);
            if (view instanceof TextView) {
                TextView nameView = (TextView)view;
                String playerName = he.getPlayerName();
                if (playerName.equals("")) {
                    playerName = "empty";
                }
                nameView.setText(playerName);
            }
            view = highscoreView.findViewById(viewScores[i]);
            if (view instanceof TextView) {
                TextView scoreView = (TextView)view;
                String playerScore = Double.toString(he.getTime());
                if (he.getTime() == 0) {
                    playerScore = "-";
                }
                scoreView.setText(playerScore);
            }
        }
        
        return highscoreView;
    }
    
    private void loadHighscores() {
        myHighscores = HighscorePersistenceManager.loadHighscores(this);
        if (myHighscores == null) {
            myHighscores = HighscorePersistenceManager.initNewHighscores(this);
        }
    }
    
}
