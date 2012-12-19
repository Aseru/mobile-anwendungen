package edu.hm.androidsweeper.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.activities.DifficultActivity;
import edu.hm.androidsweeper.activities.HighscoreActivity;
import edu.hm.androidsweeper.activities.MainMenuActivity;

/**
 * The dialog which informs the user of the game state if the game is finished.<br>
 * The user can retry the game, show the highscore or go back to the main menu.
 */
public class GameFinishedDialog extends AlertDialog {
    
    /**
     * TODO: Document type GoToMainAction.
     */
    private static final class GoToMainAction implements OnClickListener {
        
        private final Context context;
        
        public GoToMainAction(final Context context) {
            this.context = context;
        }
        
        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            Intent intent = new Intent(context, MainMenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            dialog.dismiss();
        }
    }
    
    /**
     * TODO: Document type RestartGameAction.
     */
    private static final class RestartGameAction implements OnClickListener {
        
        private final Context context;
        
        public RestartGameAction(final Context context) {
            this.context = context;
        }
        
        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            Intent intent = new Intent(context, DifficultActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
            dialog.dismiss();
        }
    }
    
    /**
     * TODO: Document type OpenHighscoreAction.
     */
    private static final class OpenHighscoreAction implements OnClickListener {
        
        private final Context context;
        
        public OpenHighscoreAction(final Context context) {
            this.context = context;
        }
        
        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            Intent mainIntent = new Intent(context, MainMenuActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Intent highscoreIntent = new Intent(context, HighscoreActivity.class);
            context.startActivity(mainIntent);
            context.startActivity(highscoreIntent);
            dialog.dismiss();
        }
    }
    
    private final Context context;
    
    /**
     * Creates a new instance of {@link GameFinishedDialog}.
     * 
     * @param context
     *            the activity context
     * @param isWon
     *            the state is the game was won or lose
     * @param time
     *            the total playtime for this game
     */
    public GameFinishedDialog(final Context context, final boolean isWon, final double time) {
        super(context);
        this.context = context;
        init(isWon, time);
    }
    
    private void init(final boolean isWon, final double time) {
        setCancelable(true);
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
                new GoToMainAction(context));
        setButton(BUTTON_NEUTRAL, context.getString(R.string.dialog_finished_button_highscore),
                new OpenHighscoreAction(context));
        setButton(BUTTON_POSITIVE, context.getString(R.string.dialog_finished_button_restart),
                new RestartGameAction(context));
    }
    
}
