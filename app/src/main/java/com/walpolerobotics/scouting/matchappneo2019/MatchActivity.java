package com.walpolerobotics.scouting.matchappneo2019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.walpolerobotics.scouting.matchappneo2019.util.Match;
import com.walpolerobotics.scouting.matchappneo2019.util.MatchFile;
import com.walpolerobotics.scouting.matchappneo2019.widget.Counter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.walpolerobotics.scouting.matchappneo2019.MainActivity.SHARED_PREFERENCES_NAME;

public class MatchActivity extends AppCompatActivity
        implements EndGameFragment.OnSubmitClickedListener {

    private Toolbar mToolbar;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private SandstormFragment mSandstormFragment;
    private TeleOpFragment mTeleOpFragment;
    private EndGameFragment mEndGameFragment;

    private Counter mCUpInput;
    private Counter mCDownInput;
    private Counter mHUpInput;
    private Counter mHDownInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mPager = findViewById(R.id.pager);
        mPagerAdapter = new MatchPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mCUpInput = findViewById(R.id.cUpInput);
        mCDownInput = findViewById(R.id.cDownInput);
        mHUpInput = findViewById(R.id.hUpInput);
        mHDownInput = findViewById(R.id.hDownInput);
    }

    @Override
    public void onSubmitClicked() {
        Match match = new Match();
        populateMatchData(match);
        mSandstormFragment.populateMatchData(match);
        mTeleOpFragment.populateMatchData(match);
        mEndGameFragment.populateMatchData(match);

        new SaveFileTask().execute(match);
    }

    private void setTheme() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        int alliance = prefs.getInt("robotAlliance", 0);
        if (alliance == Match.ALLIANCE_BLUE) {
            setTheme(R.style.BlueAllianceTheme);
        } else {
            setTheme(R.style.RedAllianceTheme);
        }
    }

    private class MatchPagerAdapter extends FragmentPagerAdapter {

        private static final int NUM_PAGES = 3;

        public MatchPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                default:
                    return null;
                case 0:
                    if (mSandstormFragment == null) {
                        mSandstormFragment = new SandstormFragment();
                    }
                    return mSandstormFragment;
                case 1:
                    if (mTeleOpFragment == null) {
                        mTeleOpFragment = new TeleOpFragment();
                    }
                    return mTeleOpFragment;
                case 2:
                    if (mEndGameFragment == null) {
                        mEndGameFragment = new EndGameFragment();
                    }
                    return mEndGameFragment;
            }
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public Match populateMatchData(Match in) {
        // Read in some global variables from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        in.matchNumber = prefs.getInt("matchNumber", 1);
        // TODO: Remove event hardcoding
        in.eventName = "WPI";
        in.scouterName = prefs.getString("scouterName", "");
        int alliance = prefs.getInt("robotAlliance", 0);
        int position = prefs.getInt("robotPosition", 0);
        in.startingPosition = Match.getStartingPosition(alliance, position);

        in.robotNumber = getIntent().getIntExtra("robotNumber", 0);

        in.cargoPickedUp = mCUpInput.getValue();
        in.cargoDropped = mCDownInput.getValue();
        in.hPickedUp = mHUpInput.getValue();
        in.hDropped = mHDownInput.getValue();

        return in;
    }

    private class SaveFileTask extends AsyncTask<Match, Double, Boolean> {

        protected Boolean doInBackground(Match... matchDataIn) {
            Match matchData = matchDataIn[0];
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                String position = Match.getPositionDescriptor(matchData.startingPosition);
                File dataFile = MatchFile.getCurrentMatchFile(matchData.eventName, position);
                if (dataFile != null) {
                    try {
                        PrintWriter os = new PrintWriter(new FileWriter(dataFile, true));

                        // TODO: Condense this
                        os.print(matchData.matchNumber);
                        os.print(',');
                        os.print(matchData.robotNumber);
                        os.print(',');
                        os.print(matchData.eventName);
                        os.print(',');
                        os.print(matchData.scouterName);
                        os.print(',');
                        os.print(matchData.startingPosition);
                        os.print(',');
                        os.print(matchData.crossHAB);
                        os.print(',');
                        os.print(matchData.sandstormCargoShipCargo);
                        os.print(',');
                        os.print(matchData.scr1);
                        os.print(',');
                        os.print(matchData.scr2);
                        os.print(',');
                        os.print(matchData.scr3);
                        os.print(',');
                        os.print(matchData.sandstormCargoShipHatches);
                        os.print(',');
                        os.print(matchData.shr1);
                        os.print(',');
                        os.print(matchData.shr2);
                        os.print(',');
                        os.print(matchData.shr3);
                        os.print(',');
                        os.print(matchData.cargoPickedUp);
                        os.print(',');
                        os.print(matchData.cargoDropped);
                        os.print(',');
                        os.print(matchData.hPickedUp);
                        os.print(',');
                        os.print(matchData.hDropped);
                        os.print(',');
                        os.print(matchData.teleopCargoShipCargo);
                        os.print(',');
                        os.print(matchData.tcr1);
                        os.print(',');
                        os.print(matchData.tcr2);
                        os.print(',');
                        os.print(matchData.tcr3);
                        os.print(',');
                        os.print(matchData.teleopCargoShipHatches);
                        os.print(',');
                        os.print(matchData.thr1);
                        os.print(',');
                        os.print(matchData.thr2);
                        os.print(',');
                        os.print(matchData.thr3);
                        os.print(',');
                        os.print(matchData.climb);
                        os.print(',');
                        os.print(matchData.numFouls);
                        os.print(',');
                        os.print(matchData.playedDefense);
                        os.print(',');
                        os.print(matchData.brokenHatchCollector);
                        os.print(',');
                        os.print(matchData.brokenCargoCollector);
                        os.print(',');
                        os.print(matchData.brokenDrivetrain);
                        os.print(',');
                        os.println(matchData.brokenClimber);

                        os.close();

                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return false;
        }

        protected void onProgressUpdate(Double... progress) {

        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                // Increment match number by 1
                SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME,
                        MODE_PRIVATE);
                int currentMatchNumber = prefs.getInt("matchNumber", 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("matchNumber", currentMatchNumber + 1);
                editor.apply();

                startActivity(new Intent(MatchActivity.this, MainActivity.class));
            }
        }
    }
}
