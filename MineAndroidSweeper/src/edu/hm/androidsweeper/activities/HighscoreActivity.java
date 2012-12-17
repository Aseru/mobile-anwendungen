package edu.hm.androidsweeper.activities;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.features.highscore.HighscoreEntry;
import edu.hm.androidsweeper.features.highscore.Highscores;
import edu.hm.androidsweeper.persistence.HighscorePersistenceManager;



public class HighscoreActivity extends Activity {
    
    public static final String TAG = "HighscoreActivity";
    
    private static int[] viewNames = {R.id.name1, R.id.name2, R.id.name3, R.id.name4, R.id.name5, R.id.name6, R.id.name7, R.id.name8, R.id.name9, R.id.name10};
    private static int[] viewScores = {R.id.score1, R.id.score2,  R.id.score3, R.id.score4, R.id.score5, R.id.score6, R.id.score7, R.id.score8, R.id.score9, R.id.score10};
    
    private Highscores myHighscores;
    
    private TextView easy;
    private TextView medium;
    private TextView hard;
    
    private View highscoreView;
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        init();
    }
    
    private void init() {
        highscoreView = getLayoutInflater().inflate(R.layout.activity_highscore, null);
        loadHighscores();
        //setContentView(highscoreView);
        
        easy = (TextView)highscoreView.findViewById(R.id.textViewEasy);
        easy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                onEasyClick();
            }
        });
        
        medium = (TextView)highscoreView.findViewById(R.id.textViewMedium);
        medium.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                onMediumClick();
            }
        });
        
        hard = (TextView)highscoreView.findViewById(R.id.textViewHard);
        hard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                onHardClick();
            }
        });
        
        
        onEasyClick();
    }
    
    private void setView(final Set<HighscoreEntry> highscoreSet) {
        View highscoreLayout = getHighscoreView(highscoreSet);        
        setContentView(highscoreLayout);
    }
    
    private View getHighscoreView(final Set<HighscoreEntry> highscoreSet) {
        
        
        Iterator<HighscoreEntry> it = highscoreSet.iterator();
        HighscoreEntry he = null;
        for(int i=0;i<viewNames.length;i++) {
            
            if(it.hasNext()) {
                he = it.next();
            }
            
            TextView nameView = (TextView)highscoreView.findViewById(viewNames[i]);
            String playerName = he.getPlayerName();
            if(playerName.equals("")) {
                playerName = "empty";
            }
            nameView.setText(playerName);
            
            TextView scoreView = (TextView)highscoreView.findViewById(viewScores[i]);
            String playerScore = Double.toString(he.getTime());
            if(he.getTime()==0) {
                playerScore = "-";
            }
            scoreView.setText(playerScore);
        }
        
        
        return highscoreView;
    }
    
    private void loadHighscores() {
        myHighscores = HighscorePersistenceManager.loadHighscores(this);
        if(myHighscores==null) {
            myHighscores = HighscorePersistenceManager.initNewHighscores(this);
        }
    }
    
    private void onEasyClick() {
        easy.setTypeface(null, Typeface.BOLD);
        medium.setTypeface(null, Typeface.NORMAL);
        hard.setTypeface(null, Typeface.NORMAL);
        setView(myHighscores.getEasy());
    }
    
    private void onMediumClick() {
        easy.setTypeface(null, Typeface.NORMAL);
        medium.setTypeface(null, Typeface.BOLD);
        hard.setTypeface(null, Typeface.NORMAL);
        setView(myHighscores.getMedium());
    }
    private void onHardClick() {
        easy.setTypeface(null, Typeface.NORMAL);
        medium.setTypeface(null, Typeface.NORMAL);
        hard.setTypeface(null, Typeface.BOLD);
        setView(myHighscores.getHard());
    }
}

