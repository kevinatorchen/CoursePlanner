package com.example.myothiha09.coursehelper.util;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by KChen10 on 8/1/2017.
 */

public class EntryWithTextBox extends Entry {
    private CheckBox checkBox;

    public EntryWithTextBox(TextView textView, EditText editText, SeekBar seekBar, int maxValue, CheckBox checkBox) {
        super(textView, editText, seekBar, maxValue);
        this.checkBox = checkBox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public void initViews() {
        super.initViews();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setEnabled(false);
                } else {
                    setEnabled(true);
                }
            }
        });
    }
}
