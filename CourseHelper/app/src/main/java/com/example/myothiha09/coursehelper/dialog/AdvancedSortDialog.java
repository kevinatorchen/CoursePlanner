package com.example.myothiha09.coursehelper.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;

import butterknife.ButterKnife;

/**
 * Created by Kevin on 7/30/2017.
 */

public class AdvancedSortDialog extends AppCompatDialog {
    private final Context context;

    private final TextView morningClassesValue;
    private final TextView gapsValue;
    private final TextView mealsValue;
    private final TextView requestedProfessorsValue;
    private final TextView requestedCommitmentValue;

    private final SeekBar morningClassesSeekBar;
    private final SeekBar gapsSeekBar;
    private final SeekBar mealsSeekBar;
    private final SeekBar requestedProfessorsSeekBar;
    private final SeekBar requestedCommitmentSeekBar;

    public AdvancedSortDialog(Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_advanced_sort);
        ButterKnife.bind(this);
        setTitle(context.getString(R.string.advanced_sort));

        morningClassesValue = (TextView) findViewById(R.id.morningClassesValue);
        gapsValue = (TextView) findViewById(R.id.gapsValue);
        mealsValue = (TextView) findViewById(R.id.mealsValue);
        requestedProfessorsValue = (TextView) findViewById(R.id.requestedProfessorsValue);
        requestedCommitmentValue = (TextView) findViewById(R.id.requestedCommitmentsValue);

        morningClassesValue.setText(Integer.toString(-100));
        gapsValue.setText(Integer.toString(-100));
        mealsValue.setText(Integer.toString(-100));
        requestedProfessorsValue.setText(Integer.toString(0));
        requestedCommitmentValue.setText(Integer.toString(0));

        morningClassesSeekBar = (SeekBar) findViewById(R.id.morningClassesSeekBar);
        morningClassesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                morningClassesValue.setText(Integer.toString(progress - 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        gapsSeekBar = (SeekBar) findViewById(R.id.gapsSeekBar);
        gapsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gapsValue.setText(Integer.toString(progress - 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mealsSeekBar = (SeekBar) findViewById(R.id.mealTimesSeekBar);
        mealsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mealsValue.setText(Integer.toString(progress - 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        requestedProfessorsSeekBar = (SeekBar) findViewById(R.id.requestedProfessorsSeekBar);
        requestedProfessorsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                requestedProfessorsValue.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        requestedCommitmentSeekBar = (SeekBar) findViewById(R.id.requestedCommitmentsSeekBar);
        requestedCommitmentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                requestedCommitmentValue.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new MaterialDialog.Builder(getContext())
                .positiveText("Search")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .negativeText("Cancel")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .show();
    }

}
