package edu.hm.androidsweeper.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.activities.HighscoreActivity;
import edu.hm.androidsweeper.features.highscore.Highscores;

/** Dialog class for deleting the Highscores.
 */
public class ClearHighscoreDialog extends AlertDialog {
    
    /** Class for abort action.
     */
    private static final class AbortAction implements OnClickListener {
        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            dialog.dismiss();
        }
    }
    
    /** Class for delete action.
     */
    private static final class DeleteHighscoreAction implements OnClickListener {
        
        private final Context context;
        
        public DeleteHighscoreAction(final Context context) {
            this.context = context;
        }
        
        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            Highscores.deleteHighscores(context);
            Intent intent = new Intent(context, HighscoreActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
            dialog.dismiss();
        }
    }
    
    private final Context context;
    
    /**
     * Creates a new instance of {@link ClearHighscoreDialog}.
     * @param context The application context.
     */
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
                new AbortAction());
        
        setButton(BUTTON_POSITIVE, context.getText(R.string.dialog_clearhighscore_button_delete),
                new DeleteHighscoreAction(context));
    }
    
}
