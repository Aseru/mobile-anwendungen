package edu.hm.mineandroidsweeper.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import edu.hm.mineandroidsweeper.R;

/**
 * TODO: Document type GameFinishedDialog.
 */
public class GameFinishedDialog extends AlertDialog {
    
    private final Context context;
    
    public GameFinishedDialog(final Context context, final boolean isWon, final double time) {
        super(context);
        this.context = context;
        init(isWon, time);
    }
    
    private void init(final boolean isWon, final double time) {
        setCancelable(false);
        String title;
        String message;
        if (isWon) {
            title = context.getString(R.string.dialog_finished_won_title);
            message = context.getString(R.string.dialog_finished_won_message, time);
            setIcon(R.drawable.smily_happy);
        }
        else {
            title = context.getString(R.string.dialog_finished_lose_title);
            message = context.getString(R.string.dialog_finished_lose_message, time);
            setIcon(R.drawable.smily_sad);
        }
        
        createButtons();
        setTitle(title);
        setMessage(message);
    }
    
    private void createButtons() {
        setButton(BUTTON_NEGATIVE, context.getString(R.string.dialog_finished_button_back),
                new OnClickListener() {
            
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }
        });
        setButton(BUTTON_NEUTRAL, context.getString(R.string.dialog_finished_button_highscore),
                new OnClickListener() {
            
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                // TODO Auto-generated method stub
                
            }
        });
        setButton(BUTTON_POSITIVE, context.getString(R.string.dialog_finished_button_restart),
                new OnClickListener() {
            
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                // TODO Auto-generated method stub
                
            }
        });
    }
    
}
