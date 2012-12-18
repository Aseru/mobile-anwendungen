package edu.hm.androidsweeper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.difficulties.CustomizedDifficulty;
import edu.hm.androidsweeper.difficulties.EasyDifficulty;
import edu.hm.androidsweeper.difficulties.HardDifficulty;
import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.difficulties.MediumDifficulty;

/**
 * Activity where the user can select his desired difficulty.
 */
public class DifficultActivity extends Activity {
    
    /**
     * Logging Tag.
     */
    public static final String TAG = "DifficultActivity";
    
    private SeekBar seekWidth;
    private SeekBar seekHeight;
    private SeekBar seekBombs;
    
    private TextView actualWidth;
    private TextView actualHeight;
    private TextView actualBombs;
    private TextView maxWidth;
    private TextView maxHeight;
    private TextView maxBombs;
    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    
    private void initView() {
        setContentView(R.layout.activity_difficulty);
        addOnClickListeners();
        initTextViews();
        initSeekBars();
        update();
    }
    
    private void initTextViews() {
        initActualViews();
        initMaxViews();
        initMinViews();
        
    }
    
    private void initActualViews() {
        View view = findViewById(R.id.textView_actual_bombs);
        if (view instanceof TextView) {
            actualBombs = (TextView)view;
            actualBombs.setText(Integer.toString(CustomizedDifficulty.DEFAULT_BOMBS));
        }
        view = findViewById(R.id.textView_actual_width);
        if (view instanceof TextView) {
            actualWidth = (TextView)view;
            actualWidth.setText(Integer.toString(CustomizedDifficulty.DEFAULT_SIZE));
        }
        view = findViewById(R.id.textView_actual_height);
        if (view instanceof TextView) {
            actualHeight = (TextView)view;
            actualHeight.setText(Integer.toString(CustomizedDifficulty.DEFAULT_SIZE));
        }
    }
    
    private void initMaxViews() {
        View view = findViewById(R.id.textView_max_bombs);
        if (view instanceof TextView) {
            maxBombs = (TextView)view;
            maxBombs.setText(Integer.toString(calcMaxBombs()));
        }
        view = findViewById(R.id.textView_max_width);
        if (view instanceof TextView) {
            maxWidth = (TextView)view;
            maxWidth.setText(Integer.toString(CustomizedDifficulty.MAX_SIZE));
        }
        view = findViewById(R.id.textView_max_height);
        if (view instanceof TextView) {
            maxHeight = (TextView)view;
            maxHeight.setText(Integer.toString(CustomizedDifficulty.MAX_SIZE));
        }
    }
    
    private void initMinViews() {
        View view = findViewById(R.id.textView_min_width);
        if (view instanceof TextView) {
            TextView minWidth = (TextView)view;
            minWidth.setText(Integer.toString(CustomizedDifficulty.MIN_SIZE));
        }
        view = findViewById(R.id.textView_min_height);
        if (view instanceof TextView) {
            TextView minHeight = (TextView)view;
            minHeight.setText(Integer.toString(CustomizedDifficulty.MIN_SIZE));
        }
    }
    
    private void initSeekBars() {
        View view = findViewById(R.id.seekBar_width);
        if (view instanceof SeekBar) {
            seekWidth = (SeekBar)view;
            if (seekWidth.getProgress() == 0) {
                seekWidth.setProgress(Integer.valueOf(CustomizedDifficulty.DEFAULT_SIZE));
            }
            seekWidth.setMax(CustomizedDifficulty.MAX_SIZE - CustomizedDifficulty.MIN_SIZE);
            seekWidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(final SeekBar seekBar) {
                    // No implementation necessary
                }
                
                @Override
                public void onStartTrackingTouch(final SeekBar seekBar) {
                    // No implementation necessary
                }
                
                @Override
                public void onProgressChanged(final SeekBar seekBar, final int progress,
                        final boolean fromUser) {
                    update();
                }
            });
        }
        view = findViewById(R.id.seekBar_height);
        if (view instanceof SeekBar) {
            seekHeight = (SeekBar)view;
            if (seekHeight.getProgress() == 0) {
                seekHeight.setProgress(Integer.valueOf(CustomizedDifficulty.DEFAULT_SIZE));
            }
            seekHeight.setMax(CustomizedDifficulty.MAX_SIZE - CustomizedDifficulty.MIN_SIZE);
            seekHeight.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(final SeekBar seekBar) {
                    // No implementation necessary
                }
                
                @Override
                public void onStartTrackingTouch(final SeekBar seekBar) {
                    // No implementation necessary
                }
                
                @Override
                public void onProgressChanged(final SeekBar seekBar, final int progress,
                        final boolean fromUser) {
                    update();
                }
            });
        }
        view = findViewById(R.id.seekBar_bombs);
        if (view instanceof SeekBar) {
            seekBombs = (SeekBar)view;
            if (seekBombs.getProgress() == 0) {
                seekBombs.setProgress(Integer.valueOf(CustomizedDifficulty.DEFAULT_BOMBS
                        - CustomizedDifficulty.MIN_SIZE));
            }
            seekBombs.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(final SeekBar seekBar) {
                    // No implementation necessary
                }
                
                @Override
                public void onStartTrackingTouch(final SeekBar seekBar) {
                    // No implementation necessary
                }
                
                @Override
                public void onProgressChanged(final SeekBar seekBar, final int progress,
                        final boolean fromUser) {
                    update();
                }
            });
        }
    }
    
    private void update() {
        int width = seekWidth.getProgress() + CustomizedDifficulty.MIN_SIZE;
        int height = seekHeight.getProgress() + CustomizedDifficulty.MIN_SIZE;
        int maximumBombs = width * height - 1;
        if (maximumBombs > CustomizedDifficulty.MAX_BOMBS) {
            maximumBombs = CustomizedDifficulty.MAX_BOMBS;
        }
        seekBombs.setMax(maximumBombs - 1);
        maxBombs.setText(Integer.toString(maximumBombs));
        int bombs = seekBombs.getProgress() + 1;
        actualWidth.setText(Integer.toString(width));
        actualHeight.setText(Integer.toString(height));
        actualBombs.setText(Integer.toString(bombs));
    }
    
    private void addOnClickListeners() {
        View view = findViewById(R.id.radio_customized);
        RadioButton rButton;
        if (view instanceof RadioButton) {
            rButton = (RadioButton)view;
            rButton.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(final View v) {
                    View custom = findViewById(R.id.layout_customized_difficulty);
                    custom.setVisibility(View.VISIBLE);
                }
            });
        }
        view = findViewById(R.id.radio_easy);
        if (view instanceof RadioButton) {
            rButton = (RadioButton)view;
            rButton.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(final View v) {
                    View custom = findViewById(R.id.layout_customized_difficulty);
                    custom.setVisibility(View.GONE);
                }
            });
        }
        view = findViewById(R.id.radio_medium);
        if (view instanceof RadioButton) {
            rButton = (RadioButton)view;
            rButton.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(final View v) {
                    View custom = findViewById(R.id.layout_customized_difficulty);
                    custom.setVisibility(View.GONE);
                }
            });
        }
        view = findViewById(R.id.radio_hard);
        if (view instanceof RadioButton) {
            rButton = (RadioButton)view;
            rButton.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(final View v) {
                    View custom = findViewById(R.id.layout_customized_difficulty);
                    custom.setVisibility(View.GONE);
                }
            });
        }
        view = findViewById(R.id.button_start);
        if (view instanceof Button) {
            Button button = (Button)view;
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    startGame();
                }
            });
        }
    }
    
    private int calcMaxBombs() {
        int width = Integer.parseInt((String)actualWidth.getText());
        int height = Integer.parseInt((String)actualHeight.getText());
        return width * height;
    }
    
    private void startGame() {
        IDifficulty difficulty = getDifficulty();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(IDifficulty.EXTRA_NAME, difficulty);
        startActivity(intent);
    }
    
    private IDifficulty getDifficulty() {
        IDifficulty difficulty = null;
        View view = findViewById(R.id.radioGroup_difficulty);
        if (view instanceof RadioGroup) {
            RadioGroup rGroup = (RadioGroup)view;
            int selectedButtonID = rGroup.getCheckedRadioButtonId();
            switch (selectedButtonID) {
                case R.id.radio_easy:
                    difficulty = new EasyDifficulty();
                    break;
                case R.id.radio_medium:
                    difficulty = new MediumDifficulty();
                    break;
                case R.id.radio_hard:
                    difficulty = new HardDifficulty();
                    break;
                case R.id.radio_customized:
                    int xSize = Integer.parseInt((String)actualWidth.getText());
                    int ySize = Integer.parseInt((String)actualHeight.getText());
                    int numberOfBombs = Integer.parseInt((String)actualBombs.getText());
                    difficulty = new CustomizedDifficulty(xSize, ySize, numberOfBombs);
                    break;
                default:
                    break;
            }
        }
        return difficulty;
    }
}