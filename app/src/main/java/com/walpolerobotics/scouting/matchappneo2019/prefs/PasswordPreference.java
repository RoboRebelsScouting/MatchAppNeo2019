package com.walpolerobotics.scouting.matchappneo2019.prefs;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;

public class PasswordPreference extends EditTextPreference {

    public PasswordPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public PasswordPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PasswordPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        super.setOnBindEditTextListener(new OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }
}
