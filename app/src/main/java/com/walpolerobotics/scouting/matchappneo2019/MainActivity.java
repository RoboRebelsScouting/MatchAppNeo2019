package com.walpolerobotics.scouting.matchappneo2019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.walpolerobotics.scouting.matchappneo2019.util.Match;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_NAME = "main";

    private EditText mMatchNumberInput;
    private EditText mRobotNumberInput;
    private EditText mScouterNameInput;
    private RadioGroup mAllianceInput;
    private RadioGroup mPositionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMatchNumberInput = findViewById(R.id.matchNumberInput);
        mRobotNumberInput = findViewById(R.id.robotInput);
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

        int matchNumber = Integer.valueOf(mMatchNumberInput.getText().toString());
        String scouterName = mScouterNameInput.getText().toString();
        int alliance = mAllianceInput.indexOfChild(findViewById(
                mAllianceInput.getCheckedRadioButtonId()));
        int position = mPositionInput.indexOfChild(findViewById(
                mPositionInput.getCheckedRadioButtonId()));

        // Save relevant data to SharedPreferences key-value store
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("matchNumber", matchNumber);
        editor.putString("scouterName", scouterName);
        editor.putInt("robotAlliance", alliance);
        editor.putInt("robotPosition", position);
        editor.apply();
    }

    public void begin(View view) {
        int robotNumber = Integer.valueOf(mRobotNumberInput.getText().toString());

        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra("robotNumber", robotNumber);

        startActivity(intent);
    }
}
