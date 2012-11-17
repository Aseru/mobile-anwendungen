package edu.hm.mineandroidsweeper.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.difficulties.CustomizedDifficulty;

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
		addOnClickListenersForRadioGroup();
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

	private void addOnClickListenersForRadioGroup() {
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
	}

}
