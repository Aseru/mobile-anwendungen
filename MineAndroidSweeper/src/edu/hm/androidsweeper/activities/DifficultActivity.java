package edu.hm.androidsweeper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import edu.hm.androidsweeper.difficulties.InvalidConfigException;
import edu.hm.androidsweeper.difficulties.MediumDifficulty;
import edu.hm.androidsweeper.misc.ToastUtil;

public class DifficultActivity extends Activity {
    
    public static final String TAG = "DifficultActivity";
    
    private SeekBar seekLength;
    private SeekBar seekWidth;
    private SeekBar seekBombs;
    
    private TextView actualLength;
    private TextView actualWidth;
    private TextView actualBombs;
    private TextView maxLength;
    private TextView maxWidth;
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
        View view = findViewById(R.id.textView_actual_bombs);
        if (view instanceof TextView) {
            actualBombs = (TextView)view;
            actualBombs.setText(Integer.toString(CustomizedDifficulty.DEFAULT_BOMBS));
        }
        view = findViewById(R.id.textView_actual_length);
        if (view instanceof TextView) {
            actualLength = (TextView)view;
            actualLength.setText(Integer.toString(CustomizedDifficulty.DEFAULT_SIZE));
        }
        view = findViewById(R.id.textView_actual_width);
        if (view instanceof TextView) {
            actualWidth = (TextView)view;
            actualWidth.setText(Integer.toString(CustomizedDifficulty.DEFAULT_SIZE));
        }
        view = findViewById(R.id.textView_max_bombs);
        if (view instanceof TextView) {
            maxBombs = (TextView)view;
            maxBombs.setText(Integer.toString(calcMaxBombs()));
        }
        view = findViewById(R.id.textView_max_length);
        if (view instanceof TextView) {
            maxLength = (TextView)view;
            maxLength.setText(Integer.toString(CustomizedDifficulty.MAX_SIZE));
        }
        view = findViewById(R.id.textView_max_width);
        if (view instanceof TextView) {
            maxWidth = (TextView)view;
            maxWidth.setText(Integer.toString(CustomizedDifficulty.MAX_SIZE));
        }
    }
    
    private void initSeekBars() {
        View view = findViewById(R.id.seekBar_length);
        if (view instanceof SeekBar) {
            seekLength = (SeekBar)view;
            if (seekLength.getProgress() == 0) {
                seekLength.setProgress(Integer.valueOf(CustomizedDifficulty.DEFAULT_SIZE));
            }
            seekLength.setMax(CustomizedDifficulty.MAX_SIZE - 1);
            seekLength.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(final SeekBar seekBar) {}
                
                @Override
                public void onStartTrackingTouch(final SeekBar seekBar) {}
                
                @Override
                public void onProgressChanged(final SeekBar seekBar, final int progress,
                        final boolean fromUser) {
                    update();
                }
            });
        }
        view = findViewById(R.id.seekBar_width);
        if (view instanceof SeekBar) {
            seekWidth = (SeekBar)view;
            if (seekWidth.getProgress() == 0) {
                seekWidth.setProgress(Integer.valueOf(CustomizedDifficulty.DEFAULT_SIZE));
            }
            seekWidth.setMax(CustomizedDifficulty.MAX_SIZE - 1);
            seekWidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(final SeekBar seekBar) {}
                
                @Override
                public void onStartTrackingTouch(final SeekBar seekBar) {}
                
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
                seekBombs.setProgress(Integer.valueOf(CustomizedDifficulty.DEFAULT_BOMBS - 1));
            }
            seekBombs.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(final SeekBar seekBar) {}
                
                @Override
                public void onStartTrackingTouch(final SeekBar seekBar) {}
                
                @Override
                public void onProgressChanged(final SeekBar seekBar, final int progress,
                        final boolean fromUser) {
                    update();
                }
            });
        }
    }
    
    private void update() {
        int xSize = seekLength.getProgress() + 1;
        int ySize = seekWidth.getProgress() + 1;
        seekBombs.setMax(Math.min(CustomizedDifficulty.MAX_BOMBS - 1, (xSize * ySize) - 2));
        maxBombs.setText(Integer.toString((xSize * ySize) - 1));
        int bombs = seekBombs.getProgress() + 1;
        actualLength.setText(Integer.toString(xSize));
        actualWidth.setText(Integer.toString(ySize));
        actualBombs.setText(Integer.toString(bombs));
    }
    
    private void addOnClickListeners() {
        RadioButton rButton = (RadioButton)findViewById(R.id.radio_customized);
        rButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(final View v) {
                View custom = findViewById(R.id.layout_customized_difficulty);
                custom.setVisibility(View.VISIBLE);
            }
        });
        
        rButton = (RadioButton)findViewById(R.id.radio_easy);
        rButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(final View v) {
                View custom = findViewById(R.id.layout_customized_difficulty);
                custom.setVisibility(View.GONE);
            }
        });
        
        rButton = (RadioButton)findViewById(R.id.radio_medium);
        rButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(final View v) {
                View custom = findViewById(R.id.layout_customized_difficulty);
                custom.setVisibility(View.GONE);
            }
        });
        
        rButton = (RadioButton)findViewById(R.id.radio_hard);
        rButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(final View v) {
                View custom = findViewById(R.id.layout_customized_difficulty);
                custom.setVisibility(View.GONE);
            }
        });
        
        Button button = (Button)findViewById(R.id.button_start);
        button.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(final View v) {
                try {
                    startGame();
                }
                catch (InvalidConfigException e) {
                    ToastUtil.showShortToast(DifficultActivity.this, e.getToastText());
                    Log.e(TAG, getString(R.string.str_dbg_exception), e);
                }
            }
        });
    }
    
    private int calcMaxBombs() {
        int lenght = Integer.parseInt((String)actualLength.getText());
        int width = Integer.parseInt((String)actualWidth.getText());
        return lenght * width;
    }
    
    private void startGame() throws InvalidConfigException {
        IDifficulty difficulty = getDifficulty();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(IDifficulty.EXTRA_NAME, difficulty);
        startActivity(intent);
    }
    
    private IDifficulty getDifficulty() throws InvalidConfigException {
        RadioGroup rGroup = (RadioGroup)findViewById(R.id.radioGroup_difficulty);
        int selectedButtonID = rGroup.getCheckedRadioButtonId();
        IDifficulty difficulty = null;
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
                int xSize = Integer.parseInt((String)actualLength.getText());
                int ySize = Integer.parseInt((String)actualWidth.getText());
                int numberOfBombs = Integer.parseInt((String)actualBombs.getText());
                difficulty = new CustomizedDifficulty(xSize, ySize, numberOfBombs);
                break;
        }
        return difficulty;
    }
}