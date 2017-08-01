package com.example.myothiha09.coursehelper.dialog;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.controller.CoursePlanner;
import com.example.myothiha09.coursehelper.model.GenericComparator;
import com.example.myothiha09.coursehelper.model.Schedule;
import com.example.myothiha09.coursehelper.model.ScheduleFilter;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Kevin on 7/30/2017.
 */

public class AdvancedSortDialog extends AppCompatDialog {
    private final Context context;

    private final CheckBox morningClassesCheckBox;
    private final CheckBox mealTimeCheckBox;

    private final TextView morningClassesText;
    private final TextView gapsText;
    private final TextView daysEachWeekText;
    private final TextView mealsText;
    private final TextView requestedProfessorsText;
    private final TextView requestedCommitmentsText;

    private final TextView morningClassesValue;
    private final TextView gapsValue;
    private final TextView daysEachWeekValue;
    private final TextView mealsValue;
    private final TextView requestedProfessorsValue;
    private final TextView requestedCommitmentValue;

    private final SeekBar morningClassesSeekBar;
    private final SeekBar gapsSeekBar;
    private final SeekBar daysEachWeekSeekBar;
    private final SeekBar mealsSeekBar;
    private final SeekBar requestedProfessorsSeekBar;
    private final SeekBar requestedCommitmentSeekBar;

    private final Button sortButton;
    private final Button cancelButton;

    public AdvancedSortDialog(Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_advanced_sort);
        ButterKnife.bind(this);
        setTitle(context.getString(R.string.advanced_sort));

        morningClassesValue = (TextView) findViewById(R.id.morningClassesValue);
        gapsValue = (TextView) findViewById(R.id.gapsValue);
        daysEachWeekValue = (TextView) findViewById(R.id.daysOfWeekValue);
        mealsValue = (TextView) findViewById(R.id.mealsValue);
        requestedProfessorsValue = (TextView) findViewById(R.id.requestedProfessorsValue);
        requestedCommitmentValue = (TextView) findViewById(R.id.requestedCommitmentsValue);

        morningClassesText = (TextView) findViewById(R.id.morningClassesText);
        gapsText = (TextView) findViewById(R.id.gapsText);
        daysEachWeekText = (TextView) findViewById(R.id.daysEachWeekText);
        mealsText = (TextView) findViewById(R.id.mealsText);
        requestedProfessorsText = (TextView) findViewById(R.id.requestedProfessorsText);
        requestedCommitmentsText = (TextView) findViewById(R.id.requestedCommitmentsText);

        /*
        morningClassesValue.setText(Integer.toString(-100));
        gapsValue.setText(Integer.toString(-100));
        mealsValue.setText(Integer.toString(-100));
        requestedProfessorsValue.setText(Integer.toString(0));
        requestedCommitmentValue.setText(Integer.toString(0));
        */

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
        morningClassesSeekBar.setProgress(100);
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
        gapsSeekBar.setProgress(100);
        daysEachWeekSeekBar = (SeekBar) findViewById(R.id.daysEachWeekSeekBar);
        daysEachWeekSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                daysEachWeekValue.setText(Integer.toString(progress - 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        daysEachWeekSeekBar.setProgress(100);
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
        mealsSeekBar.setProgress(100);
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
        requestedProfessorsSeekBar.setProgress(0);
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

        requestedCommitmentSeekBar.setProgress(0);
        morningClassesCheckBox = (CheckBox) findViewById(R.id.filterMorningClasses);
        morningClassesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    morningClassesText.setEnabled(false);
                    morningClassesValue.setEnabled(false);
                    morningClassesSeekBar.setEnabled(false);
                } else {
                    morningClassesText.setEnabled(true);
                    morningClassesValue.setEnabled(true);
                    morningClassesSeekBar.setEnabled(true);
                }
            }
        });
        mealTimeCheckBox = (CheckBox) findViewById(R.id.mealTimeFilter);
        mealTimeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mealsText.setEnabled(false);
                    mealsValue.setEnabled(false);
                    mealsSeekBar.setEnabled(false);
                } else {
                    mealsText.setEnabled(true);
                    mealsValue.setEnabled(true);
                    mealsSeekBar.setEnabled(true);
                }
            }
        });

        sortButton = (Button) findViewById(R.id.sortButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Schedule> schedules = CoursePlanner.scheduleList;
                int morningClasses = 0;
                if (morningClassesCheckBox.isChecked()) {
                    schedules = ScheduleFilter.filterMorningClasses(schedules);
                } else {
                    morningClasses = Integer.parseInt(morningClassesValue.getText().toString());
                }
                int gaps = Integer.parseInt(gapsValue.getText().toString());
                int daysEachWeek = Integer.parseInt(daysEachWeekValue.getText().toString());
                int meals = 0;
                if (mealTimeCheckBox.isChecked()) {
                    schedules = ScheduleFilter.filterNoMeals(schedules);
                } else {
                    meals = Integer.parseInt(mealsValue.getText().toString());
                }
                int requestedProfessors = Integer.parseInt(requestedProfessorsValue.getText().toString());
                int requestedCommitments = Integer.parseInt(requestedCommitmentValue.getText().toString());
                GenericComparator comparator = new GenericComparator(gaps, morningClasses, daysEachWeek,
                        meals, requestedProfessors, requestedCommitments);
                //TODO: pass back "comparator" and "schedules", calling sortSchedules() from ScheduleOverviewFragment.
                dismiss();

            }
        });
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
