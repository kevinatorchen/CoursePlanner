package com.example.myothiha09.coursehelper.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.OnTextChanged;

/**
 * Created by KChen10 on 8/1/2017.
 */

public class Entry {
    private TextView textView;
    private EditText editText;
    private SeekBar seekBar;
    private int maxValue;

    public Entry(TextView textView, EditText editText, SeekBar seekBar, int maxValue) {
        this.textView = textView;
        this.editText = editText;
        this.seekBar = seekBar;
        this.maxValue = maxValue;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void initViews() {
        editText.setText(Integer.toString(0));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editText.setText(Integer.toString(progress - maxValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(maxValue);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int value = Integer.parseInt(s.toString());
                    if (value <= 100 && value > -1 * maxValue) {
                        seekBar.setProgress(value + maxValue);
                    }
                } catch (NumberFormatException e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editText.setText(Integer.toString(seekBar.getProgress() - maxValue));
                }
            }
        });
    }

    public void setEnabled(boolean enabled) {
        textView.setEnabled(enabled);
        editText.setEnabled(enabled);
        seekBar.setEnabled(enabled);
    }

    public void setVisible(boolean visible) {
        if (visible) {
            textView.setVisibility(TextView.VISIBLE);
            editText.setVisibility(TextView.VISIBLE);
            seekBar.setVisibility(TextView.VISIBLE);
        } else {
            textView.setVisibility(TextView.INVISIBLE);
            editText.setVisibility(TextView.INVISIBLE);
            seekBar.setVisibility(TextView.INVISIBLE);
        }
    }
}
