package edu.hm.androidsweeper.activities;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.application.App;
import edu.hm.androidsweeper.dialogs.ClearHighscoreDialog;
import edu.hm.androidsweeper.dialogs.DialogUtil;
import edu.hm.androidsweeper.difficulties.EasyDifficulty;
import edu.hm.androidsweeper.difficulties.HardDifficulty;
import edu.hm.androidsweeper.difficulties.MediumDifficulty;
import edu.hm.androidsweeper.features.highscore.HighscoreEntry;
import edu.hm.androidsweeper.features.highscore.Highscores;
import edu.hm.androidsweeper.misc.SharedMenu;

public class HighscoreActivity extends Activity {
    
    public static final String TAG = "HighscoreActivity";
    public static final String EXTRA_NAME = "HighscoreDifficulty";
    
    private Highscores myHighscores;
    
    private TextView easy;
    private TextView medium;
    private TextView hard;
    
    private View highscoreView;
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Object o = extras.get(EXTRA_NAME);
            if (o instanceof String) {
                String difficulty = (String)o;
                if (difficulty.equals(EasyDifficulty.NAME)) {
                    onEasyClick();
                    return;
                }
                if (difficulty.equals(MediumDifficulty.NAME)) {
                    onMediumClick();
                    return;
                }
                if (difficulty.equals(HardDifficulty.NAME)) {
                    onHardClick();
                    return;
                }
                
            }
        }
        else {
            onEasyClick();
        }
    }
    
    private void init() {
        View view;
        highscoreView = getLayoutInflater().inflate(R.layout.activity_highscore, null);
        myHighscores = Highscores.getInstance();
        
        view = highscoreView.findViewById(R.id.textViewEasy);
        if (view instanceof TextView) {
            easy = (TextView)view;
            easy.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    onEasyClick();
                }
            });
        }
        view = highscoreView.findViewById(R.id.textViewMedium);
        if (view instanceof TextView) {
            medium = (TextView)view;
            medium.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    onMediumClick();
                }
            });
        }
        
        view = highscoreView.findViewById(R.id.textViewHard);
        if (view instanceof TextView) {
            hard = (TextView)view;
            hard.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    onHardClick();
                }
            });
        }
    }
    
    private void setScores(final List<HighscoreEntry> highscoreSet) {
        TableLayout highscoreTable = (TableLayout)highscoreView.findViewById(R.id.highscoreTable);
        highscoreTable.removeAllViews();
        
        int rowContentPadding = Math.round(App.getContext().getResources()
                .getDimension(R.dimen.tablerow_contents_padding));
        
        HighscoreEntry he = null;
        Iterator<HighscoreEntry> it = highscoreSet.iterator();
        
        for (int i = 1; i <= highscoreSet.size(); i++) {
            if (it.hasNext()) {
                he = it.next();
            }
            
            TableRow tr = new TableRow(this);
            tr.setPadding(
                    0,
                    Math.round(App.getContext().getResources()
                            .getDimension(R.dimen.tablerow_padding_top)), 0, 0);
            
            TextView index = new TextView(this);
            index.setPadding(rowContentPadding, rowContentPadding, rowContentPadding,
                    rowContentPadding);
            index.setWidth(Math.round(App.getContext().getResources()
                    .getDimension(R.dimen.index_width)));
            // index.setHeight()
            index.setText(Integer.toString(i));
            
            tr.addView(index, 0);
            
            TextView name = new TextView(this);
            name.setPadding(rowContentPadding, rowContentPadding, rowContentPadding,
                    rowContentPadding);
            name.setWidth(Math.round(App.getContext().getResources()
                    .getDimension(R.dimen.name_width)));
            name.setText(he.getPlayerName());
            tr.addView(name, 1);
            
            TextView score = new TextView(this);
            score.setPadding(rowContentPadding, rowContentPadding, rowContentPadding,
                    rowContentPadding);
            score.setWidth(Math.round(App.getContext().getResources()
                    .getDimension(R.dimen.score_width)));
            score.setText(Double.toString(he.getTime()));
            tr.addView(score, 2);
            
            highscoreTable.addView(tr);
        }
        
    }
    
    private void setView(final List<HighscoreEntry> highscoreList) {
        setScores(highscoreList);
        setContentView(highscoreView);
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
    
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        SharedMenu.onCreateOptionMenu(menu, getApplicationContext(), inflater);
        inflater.inflate(R.menu.menu_highscore, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!SharedMenu.onOptionItemSelected(item, this)) {
            switch (item.getItemId()) {
                case R.id.menu_clear_highscore:
                    Dialog dialog = new ClearHighscoreDialog(this);
                    DialogUtil.showDialog(dialog);
                    break;
                default:
                    break;
                    
            }
        }
        return super.onOptionsItemSelected(item);
    }
    
}
