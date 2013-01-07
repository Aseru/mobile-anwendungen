package edu.hm.androidsweeper.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.activities.DifficultActivity;
import edu.hm.androidsweeper.activities.HighscoreActivity;
import edu.hm.androidsweeper.activities.MainMenuActivity;
import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.features.highscore.Highscores;
import edu.hm.androidsweeper.gamelogic.Game;
import edu.hm.androidsweeper.gamelogic.GameState;

/**
 * The dialog which informs the user of the game state if the game is finished.<br>
 * The user can retry the game, show the highscore or go back to the main menu.
 */
public class GameFinishedDialog extends AlertDialog {
    
    private final Context context;
    private final Game game;
    private boolean isNewHighscore;
    private EditText playerNameInput;
    
    /**
     * Creates a new instance of {@link GameFinishedDialog}.
     * 
     * @param context
     *            The activity context.
     * @param game
     *            The game object.
     */
    public GameFinishedDialog(final Context context, final Game game) {
        super(context);
        this.context = context;
        this.game = game;
        init();
        setDefaultPlayerName();
    }
    
    private void init() {
        setCancelable(true);
        setMessageNTitle();
        createButtons();
        View playerNameLayout = LayoutInflater.from(context).inflate(R.layout.dialog_gamefinished,
                null);
        View view = playerNameLayout.findViewById(R.id.player_name);
        if (view instanceof EditText) {
            playerNameInput = (EditText)view;
        }
        setView(playerNameLayout);
        isNewHighscore = Highscores.isHighscore(game);
        if (isNewHighscore) {
            playerNameLayout.setVisibility(View.VISIBLE);
        }
    }
    
    private void setDefaultPlayerName() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String playerName = sharedPrefs.getString(
                context.getString(R.string.prefs_default_player_name_key),
                context.getString(R.string.prefs_default_player_name));
        playerNameInput.setText(playerName);
    }
    
    @Override
    public void dismiss() {
        if (isNewHighscore) {
            String playerName = playerNameInput.getText().toString();
            Highscores.addHighscore(game, playerName);
        }
        super.dismiss();
    }
    
    private void setMessageNTitle() {
        String title;
        String message;
        GameState state = game.getState();
        double time = game.getPlaytimeAsDouble();
        if (state == GameState.WON) {
            title = context.getString(R.string.dialog_finished_won_title);
            message = context.getString(R.string.dialog_finished_won_message, time);
            setIcon(R.drawable.smily_happy);
        }
        else {
            title = context.getString(R.string.dialog_finished_lose_title);
            message = context.getString(R.string.dialog_finished_lose_message, time);
            setIcon(R.drawable.smily_sad);
        }
        setTitle(title);
        setMessage(message);
    }
    
    private void createButtons() {
        setButton(BUTTON_NEGATIVE, context.getString(R.string.dialog_finished_button_back),
                new GoToMainAction(context));
        setButton(BUTTON_NEUTRAL, context.getString(R.string.dialog_finished_button_highscore),
                new OpenHighscoreAction(context, game));
        setButton(BUTTON_POSITIVE, context.getString(R.string.dialog_finished_button_restart),
                new RestartGameAction(context));
    }
    
    /**
     * Class for the <go to main menu> action.
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
        }
    }
    
    /**
     * Class for the <restart game> action.
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
        }
    }
    
    /**
     * Class for the <open highscores> action.
     */
    private static final class OpenHighscoreAction implements OnClickListener {
        
        private final Context context;
        private final Game game;
        
        public OpenHighscoreAction(final Context context, final Game game) {
            this.context = context;
            this.game = game;
        }
        
        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            Intent mainIntent = new Intent(context, MainMenuActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Intent highscoreIntent = new Intent(context, HighscoreActivity.class);
            IDifficulty difficulty = game.getDifficulty();
            String difficultyName = difficulty.getDifficultyName();
            if (difficultyName != null) {
                highscoreIntent.putExtra(HighscoreActivity.EXTRA_NAME, difficultyName);
            }
            context.startActivity(mainIntent);
            context.startActivity(highscoreIntent);
        }
    }
    
}
