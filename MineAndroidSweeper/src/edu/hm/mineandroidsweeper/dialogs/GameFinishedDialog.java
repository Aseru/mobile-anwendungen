package edu.hm.mineandroidsweeper.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import edu.hm.mineandroidsweeper.R;

/**
 * TODO: Document type GameFinishedDialog.
 */
public class GameFinishedDialog extends AlertDialog {
    
    
    private final Context context;
    
    public GameFinishedDialog(final Context context, final boolean isWon, final long time) {
        super(context);
        this.context = context;
        init(isWon, time);
    }
    
    private void init(final boolean isWon, final long time) {
        setCancelable(true);
        String title;
        String message;
        if(isWon){
            title = context.getString(R.string.dialog_finished_won_title);
            message = context.getString(R.string.dialog_finished_won_message, time);
            setIcon(android.R.drawable.btn_plus);
        }
        else {
            title = context.getString(R.string.dialog_finished_lose_title);
            message = context.getString(R.string.dialog_finished_lose_message, time);
            setIcon(android.R.drawable.btn_minus);
        }
        setTitle(title);
        setMessage(message);
    }
    
}

