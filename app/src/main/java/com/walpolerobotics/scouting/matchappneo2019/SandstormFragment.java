package com.walpolerobotics.scouting.matchappneo2019;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.walpolerobotics.scouting.matchappneo2019.util.Match;
import com.walpolerobotics.scouting.matchappneo2019.widget.Counter;

public class SandstormFragment extends Fragment {

    private RadioGroup mHabInput;
    private Counter mScscInput;
    private Counter mScr1Input;
    private Counter mScr2Input;
    private Counter mScr3Input;
    private Counter mScshInput;
    private Counter mShr1Input;
    private Counter mShr2Input;
    private Counter mShr3Input;

    public SandstormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sandstorm, container, false);

        mHabInput = view.findViewById(R.id.habInput);
        mScscInput = view.findViewById(R.id.scscInput);
        mScr1Input = view.findViewById(R.id.scr1Input);
        mScr2Input = view.findViewById(R.id.scr2Input);
        mScr3Input = view.findViewById(R.id.scr3Input);
        mScshInput = view.findViewById(R.id.scshInput);
        mShr1Input = view.findViewById(R.id.shr1Input);
        mShr2Input = view.findViewById(R.id.shr2Input);
        mShr3Input = view.findViewById(R.id.shr3Input);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public Match populateMatchData(Match in) {
        View crossHABSelected = mHabInput.findViewById(mHabInput.getCheckedRadioButtonId());
        int crossHABSelectedIndex = mHabInput.indexOfChild(crossHABSelected);
        in.crossHAB = crossHABSelectedIndex;
        in.sandstormCargoShipCargo = mScscInput.getValue();
        in.scr1 = mScr1Input.getValue();
        in.scr2 = mScr2Input.getValue();
        in.scr3 = mScr3Input.getValue();
        in.sandstormCargoShipHatches = mScshInput.getValue();
        in.shr1 = mShr1Input.getValue();
        in.shr2 = mShr2Input.getValue();
        in.shr3 = mShr3Input.getValue();

        return in;
    }
}
