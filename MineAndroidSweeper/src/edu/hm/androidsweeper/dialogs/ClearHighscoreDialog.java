package edu.hm.androidsweeper.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.features.highscore.Highscores;

public class ClearHighscoreDialog extends AlertDialog {
    
    private final Context context;
    
    public ClearHighscoreDialog(final Context context) {
        super(context);
        this.context = context;
        init();
    }
    
    private void init() {
        setCancelable(false);
        String title = context.getString(R.string.dialog_clearhighscore_title);
        String message = context.getString(R.string.dialog_clearhighscore_message);
        createButtons();
        setTitle(title);
        setMessage(message);
    }
    
    private void createButtons() {
        setButton(BUTTON_NEGATIVE, context.getText(R.string.dialog_clearhighscore_button_abort),
                new OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }
        });
        
        setButton(BUTTON_POSITIVE, context.getText(R.string.dialog_clearhighscore_button_delete),
                new OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                Highscores.deleteHighscores(context);
                dialog.dismiss();
            }
        });
    }
    
}
