package edu.hm.mineandroidsweeper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.difficulties.CustomizedDifficulty;
import edu.hm.mineandroidsweeper.difficulties.EasyDifficulty;
import edu.hm.mineandroidsweeper.difficulties.HardDifficulty;
import edu.hm.mineandroidsweeper.difficulties.IDifficulty;
import edu.hm.mineandroidsweeper.difficulties.MediumDifficulty;

public class DifficultActivity extends Activity {

	public static final String TAG = "DifficultActivity";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setContentView(R.layout.activity_difficulty);
		addOnClickListeners();
		setDefaultValuesForCustomizedDifficulty();
	}

	private void setDefaultValuesForCustomizedDifficulty() {
		EditText edit = (EditText) findViewById(R.id.editNumber_length);
		if (edit.getText().length() == 0) {
			edit.setText(Integer.valueOf(CustomizedDifficulty.DEFAULT_SIZE)
					.toString());
		}
		edit = (EditText) findViewById(R.id.editNumber_width);
		if (edit.getText().length() == 0) {
			edit.setText(Integer.valueOf(CustomizedDifficulty.DEFAULT_SIZE)
					.toString());
		}
		edit = (EditText) findViewById(R.id.editNumber_bombs);
		if (edit.getText().length() == 0) {
			edit.setText(Integer.valueOf(CustomizedDifficulty.DEFAULT_SIZE)
					.toString());
		}
	}

	private void addOnClickListeners() {
		RadioButton rButton = (RadioButton) findViewById(R.id.radio_customized);
		rButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				View custom = findViewById(R.id.layout_customized_difficulty);
				custom.setVisibility(View.VISIBLE);
			}
		});

		rButton = (RadioButton) findViewById(R.id.radio_easy);
		rButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				View custom = findViewById(R.id.layout_customized_difficulty);
				custom.setVisibility(View.GONE);
			}
		});

		rButton = (RadioButton) findViewById(R.id.radio_medium);
		rButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				View custom = findViewById(R.id.layout_customized_difficulty);
				custom.setVisibility(View.GONE);
			}
		});

		rButton = (RadioButton) findViewById(R.id.radio_hard);
		rButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				View custom = findViewById(R.id.layout_customized_difficulty);
				custom.setVisibility(View.GONE);
			}
		});

		Button button = (Button) findViewById(R.id.button_start);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					startGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void startGame() throws Exception {
		IDifficulty difficulty = getDifficulty();
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra(IDifficulty.EXTRA_NAME, difficulty);
		startActivity(intent);
	}

	private IDifficulty getDifficulty() throws Exception {
		RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroup_difficulty);
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
			EditText edit = (EditText) findViewById(R.id.editNumber_length);
			int xSize = Integer.parseInt(edit.getText().toString());
			edit = (EditText) findViewById(R.id.editNumber_width);
			int ySize = Integer.parseInt(edit.getText().toString());
			edit = (EditText) findViewById(R.id.editNumber_bombs);
			int numberOfBombs = Integer.parseInt(edit.getText().toString());
			difficulty = new CustomizedDifficulty(xSize, ySize, numberOfBombs);
			break;
		}
		return difficulty;
	}

}
