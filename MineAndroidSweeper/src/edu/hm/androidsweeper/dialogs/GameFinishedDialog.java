package edu.hm.androidsweeper.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
    private final Highscores highscores;
    private final Game game;
    private boolean isNewHighscore;
    private EditText playerNameInput;
    
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
    public GameFinishedDialog(final Context context, final Game game) {
        super(context);
        this.context = context;
        this.game = game;
        highscores = Highscores.getInstance();
        init();
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
    
    @Override
    public void dismiss() {
        if (isNewHighscore) {
            System.out.println("Dismiss");
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
        }
    }
    
    /**
     * TODO: Document type OpenHighscoreAction.
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
