package com.walpolerobotics.scouting.matchappneo2019;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.walpolerobotics.scouting.matchappneo2019.util.Match;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_NAME = "main";

    private TextInputLayout mMatchNumberLayout;
    private EditText mMatchNumberInput;
    private TextInputLayout mRobotNumberLayout;
    private EditText mRobotNumberInput;
    private TextInputLayout mScouterNameLayout;
    private EditText mScouterNameInput;
    private TextView mRobotPositionIndicator;

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
        mRobotPositionIndicator = findViewById(R.id.robotPositionIndicator);

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
        int position = prefs.getInt("robotPosition", 0);
        String robotPosition = Match.getPositionDescriptor(Match.getStartingPosition(alliance,
                position));
        String robotPositionIndicatorMessage = getResources()
                .getString(R.string.robot_position_indicator, robotPosition);
        mRobotPositionIndicator.setText(robotPositionIndicatorMessage);
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
        editor.putString("scouterName", scouterName);
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

    public void settings(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.restricted_area)
                .setMessage(R.string.restricted_area_msg)
                .setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }
}
