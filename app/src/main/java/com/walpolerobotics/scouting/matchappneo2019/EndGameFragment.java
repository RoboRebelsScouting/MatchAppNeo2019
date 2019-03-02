package com.walpolerobotics.scouting.matchappneo2019;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.walpolerobotics.scouting.matchappneo2019.util.Match;

public class EndGameFragment extends Fragment {

    private OnSubmitClickedListener mListener;

    private RadioGroup mHabClimbInput;
    private CheckBox mBrokenHcInput;
    private CheckBox mBrokenCcInput;
    private CheckBox mBrokenDriveInput;
    private CheckBox mBrokenClimbInput;

    public EndGameFragment() {
        // Required empty public constructor
    }

    public interface OnSubmitClickedListener {
        void onSubmitClicked();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_end_game, container, false);

        Button submit = view.findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

        mHabClimbInput = view.findViewById(R.id.habClimbInput);
        mBrokenHcInput = view.findViewById(R.id.brokenHc);
        mBrokenCcInput = view.findViewById(R.id.brokenCc);
        mBrokenDriveInput = view.findViewById(R.id.brokenDrive);
        mBrokenClimbInput = view.findViewById(R.id.brokenClimb);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnSubmitClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSubmitClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    public void submit() {
        mListener.onSubmitClicked();
    }

    public Match populateMatchData(Match in) {
        View habClimbSelected = mHabClimbInput.findViewById(mHabClimbInput.getCheckedRadioButtonId());
        int habClimbSelectedIndex = mHabClimbInput.indexOfChild(habClimbSelected);
        in.climb = habClimbSelectedIndex;

        // TODO: Add actual inputs for the following values
        in.numFouls = 0;
        in.playedDefense = false;

        // TODO: Make string building code more efficient
        StringBuilder sb = new StringBuilder();
        if (mBrokenHcInput.isChecked()) {
            sb.append("brokenHatchCollector");
        }
        if (mBrokenCcInput.isChecked()) {
            if (sb.length() > 0) {
                sb.append("-");
            }
            sb.append("brokenCargoCollector");
        }
        if (mBrokenDriveInput.isChecked()) {
            if (sb.length() > 0) {
                sb.append("-");
            }
            sb.append("brokenDriveTrain");
        }
        if (mBrokenClimbInput.isChecked()) {
            if (sb.length() > 0) {
                sb.append("-");
            }
            sb.append("brokenClimber");
        }
        in.robotCondition = sb.toString();

        return in;
    }
}
