package com.walpolerobotics.scouting.matchappneo2019.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.walpolerobotics.scouting.matchappneo2019.R;

public class Counter extends RelativeLayout {

    private static final int MIN_VALUE = 0;

    private TextView mValueLabel;
    private int mValue = MIN_VALUE;

    public Counter(Context context) {
        super(context);

        init(context);
    }

    public Counter(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public Counter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.widget_counter, this);

        mValueLabel = findViewById(R.id.value);
        mValueLabel.setText(String.valueOf(mValue));

        ImageButton dec = findViewById(R.id.decButton);
        dec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mValueLabel.setText(String.valueOf(dec()));
            }
        });
        ImageButton inc = findViewById(R.id.incButton);
        inc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mValueLabel.setText(String.valueOf(inc()));
            }
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.value = mValue;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setValue(ss.value);
    }

    public int inc() {
        mValue++;
        return mValue;
    }

    public int dec() {
        if (mValue > MIN_VALUE) {
            mValue--;
        }
        return mValue;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int in) {
        if (in >= MIN_VALUE) {
            mValue = in;
            mValueLabel.setText(String.valueOf(mValue));
        }
    }

    static class SavedState extends BaseSavedState {

        int value;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            value = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(value);
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
