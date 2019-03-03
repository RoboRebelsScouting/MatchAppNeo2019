package com.walpolerobotics.scouting.matchappneo2019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.walpolerobotics.scouting.matchappneo2019.util.Match;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_NAME = "main";

    private TextInputLayout mMatchNumberLayout;
    private EditText mMatchNumberInput;
    private TextInputLayout mRobotNumberLayout;
    private EditText mRobotNumberInput;
    private TextInputLayout mScouterNameLayout;
    private EditText mScouterNameInput;
    private RadioGroup mAllianceInput;
    private RadioGroup mPositionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EmojiCompat.Config config = new BundledEmojiCompatConfig(getApplicationContext());
        config.setReplaceAll(true);
        EmojiCompat.init(config);

        setContentView(R.layout.activity_main);

        mMatchNumberLayout = findViewById(R.id.matchNumberLayout);
        mMatchNumberInput = findViewById(R.id.matchNumberInput);
        mRobotNumberLayout = findViewById(R.id.robotInputLayout);
        mRobotNumberInput = findViewById(R.id.robotInput);
        mScouterNameLayout = findViewById(R.id.scouterNameInputLayout);
        mScouterNameInput = findViewById(R.id.scouterNameInput);
        mAllianceInput = findViewById(R.id.allianceInput);
        mPositionInput = findViewById(R.id.positionInput);

        mRobotNumberInput.requestFocus();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Read in relevant data from SharedPreferences key-value store
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        mMatchNumberInput.setText(String.valueOf(prefs.getInt("matchNumber", 1)));
        mScouterNameInput.setText(prefs.getString("scouterName", ""));
        int alliance = prefs.getInt("robotAlliance", 0);
        RadioButton allianceRadioButton = (RadioButton) mAllianceInput.getChildAt(alliance);
        allianceRadioButton.setChecked(true);
        int position = prefs.getInt("robotPosition", 0);
        RadioButton positionRadioButton = (RadioButton) mPositionInput.getChildAt(position);
        positionRadioButton.setChecked(true);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save relevant data to SharedPreferences key-value store
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        boolean matchNumberValid = mMatchNumberInput.getText().length() > 0;
        if (matchNumberValid) {
            int matchNumber = Integer.valueOf(mMatchNumberInput.getText().toString());
            editor.putInt("matchNumber", matchNumber);
        }

        String scouterName = mScouterNameInput.getText().toString();
        int alliance = mAllianceInput.indexOfChild(findViewById(
                mAllianceInput.getCheckedRadioButtonId()));
        int position = mPositionInput.indexOfChild(findViewById(
                mPositionInput.getCheckedRadioButtonId()));

        editor.putString("scouterName", scouterName);
        editor.putInt("robotAlliance", alliance);
        editor.putInt("robotPosition", position);
        editor.apply();
    }

    public void begin(View view) {
        boolean matchNumberError = mMatchNumberInput.getText().length() == 0;
        if (matchNumberError) {
            mMatchNumberLayout.setErrorEnabled(true);
            mMatchNumberLayout.setError(getResources().getString(R.string.match_number_error));
        } else {
            mMatchNumberLayout.setErrorEnabled(false);
        }

        boolean robotNumberError = mRobotNumberInput.getText().length() == 0;
        if (robotNumberError) {
            mRobotNumberLayout.setErrorEnabled(true);
            mRobotNumberLayout.setError(getResources().getString(R.string.robot_number_error));
        } else {
            mRobotNumberLayout.setErrorEnabled(false);
        }

        boolean scouterNameError = mScouterNameInput.getText().length() == 0;
        if (scouterNameError) {
            mScouterNameLayout.setErrorEnabled(true);
            mScouterNameLayout.setError(getResources().getString(R.string.scouter_name_error));
        } else {
            mScouterNameLayout.setErrorEnabled(false);
        }

        if (matchNumberError || robotNumberError || scouterNameError) {
            return;
        }

        int robotNumber = Integer.valueOf(mRobotNumberInput.getText().toString());

        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra("robotNumber", robotNumber);

        startActivity(intent);
    }
}
