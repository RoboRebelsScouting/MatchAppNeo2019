package com.walpolerobotics.scouting.matchappneo2019;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walpolerobotics.scouting.matchappneo2019.util.Match;
import com.walpolerobotics.scouting.matchappneo2019.widget.Counter;

public class TeleOpFragment extends Fragment {

    private Counter mTcscInput;
    private Counter mTcr1Input;
    private Counter mTcr2Input;
    private Counter mTcr3Input;
    private Counter mTcshInput;
    private Counter mThr1Input;
    private Counter mThr2Input;
    private Counter mThr3Input;

    public TeleOpFragment() {
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
        View view = inflater.inflate(R.layout.fragment_tele_op, container, false);

        mTcscInput = view.findViewById(R.id.tcscInput);
        mTcr1Input = view.findViewById(R.id.tcr1Input);
        mTcr2Input = view.findViewById(R.id.tcr2Input);
        mTcr3Input = view.findViewById(R.id.tcr3Input);
        mTcshInput = view.findViewById(R.id.tcshInput);
        mThr1Input = view.findViewById(R.id.thr1Input);
        mThr2Input = view.findViewById(R.id.thr2Input);
        mThr3Input = view.findViewById(R.id.thr3Input);

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
        in.teleopCargoShipCargo = mTcscInput.getValue();
        in.tcr1 = mTcr1Input.getValue();
        in.tcr2 = mTcr2Input.getValue();
        in.tcr3 = mTcr3Input.getValue();
        in.teleopCargoShipHatches = mTcshInput.getValue();
        in.thr1 = mThr1Input.getValue();
        in.thr2 = mThr2Input.getValue();
        in.thr3 = mThr3Input.getValue();

        return in;
    }
}
