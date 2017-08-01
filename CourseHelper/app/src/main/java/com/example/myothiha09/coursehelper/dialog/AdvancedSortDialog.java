package com.example.myothiha09.coursehelper.dialog;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.AdvancedSortListener;
import com.example.myothiha09.coursehelper.controller.CoursePlanner;
import com.example.myothiha09.coursehelper.model.GenericComparator;
import com.example.myothiha09.coursehelper.model.Schedule;
import com.example.myothiha09.coursehelper.model.ScheduleFilter;
import com.example.myothiha09.coursehelper.util.Entry;
import com.example.myothiha09.coursehelper.util.EntryWithTextBox;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 7/30/2017.
 */

public class AdvancedSortDialog extends AppCompatDialog {

    private final Context context;

    @BindView(R.id.maxCommitmentsLabel) TextView maxCommitmentsLabel;
    @BindView(R.id.maxCommitmentsEditText) EditText maxCommitmentsEditText;
    private final Button sortButton;
    private final Button cancelButton;
    private AdvancedSortListener listener;
    private HashMap<String, Entry> entries;

    public AdvancedSortDialog(Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_advanced_sort);
        ButterKnife.bind(this);
        setTitle(context.getString(R.string.advanced_sort));

        entries = new HashMap<>();
        entries.put("Morning Classes",
                new EntryWithTextBox((TextView) findViewById(R.id.morningClassesLabel),
                        (EditText) findViewById(R.id.morningClassesEditText),
                        (SeekBar) findViewById(R.id.morningClassesSeekBar), 100,
                        (CheckBox) findViewById(R.id.morningClassesFilter)));
        entries.put("Gaps",
                new Entry((TextView) findViewById(R.id.gapsLabel),
                        (EditText) findViewById(R.id.gapsEditText),
                        (SeekBar) findViewById(R.id.gapsSeekBar), 100));
        entries.put("Fewer Days",
                new Entry((TextView) findViewById(R.id.fewerDaysLabel),
                        (EditText) findViewById(R.id.fewerDaysEditText),
                        (SeekBar) findViewById(R.id.fewerDaysSeekBar), 100));
        entries.put("Meal Times",
                new EntryWithTextBox((TextView) findViewById(R.id.mealTimeLabel),
                        (EditText) findViewById(R.id.mealTimeEditText),
                        (SeekBar) findViewById(R.id.mealTimeSeekBar), 100,
                        (CheckBox) findViewById(R.id.mealTimeCheckBox)));
        entries.put("Requested Professors",
                new Entry((TextView) findViewById(R.id.requestedProfessorsLabel),
                        (EditText) findViewById(R.id.requestedProfessorsEditText),
                        (SeekBar) findViewById(R.id.requestedProfessorsSeekBar), 100));
        entries.put("Requested Commitments",
                new Entry((TextView) findViewById(R.id.requestedCommitmentsLabel),
                        (EditText) findViewById(R.id.requestedCommitmentsEditText),
                        (SeekBar) findViewById(R.id.requestedCommitmentsSeekBar), 100));


        for (Entry currentEntry: entries.values()) {
            currentEntry.initViews();
        }
        sortButton = (Button) findViewById(R.id.sortButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        /*
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
                listener.onSortSettingChanged(comparator, schedules);
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
        */
    }
    public void setListener(AdvancedSortListener listener) {
        this.listener = listener;
    }

}
