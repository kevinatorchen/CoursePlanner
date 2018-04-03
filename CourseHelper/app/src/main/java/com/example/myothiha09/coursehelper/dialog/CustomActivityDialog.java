package com.example.myothiha09.coursehelper.dialog;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.ItemClickedListener;
import com.example.myothiha09.coursehelper.model.MeetingTime;
import com.example.myothiha09.coursehelper.model.StudentActivity;
import com.example.myothiha09.coursehelper.model.StudentActivitySection;
import com.example.myothiha09.coursehelper.model.Time;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KChen10 on 8/11/2017.
 */

public class CustomActivityDialog extends AppCompatDialog {
    private final Context context;
    ItemClickedListener<StudentActivity> listener;
    @BindView(R.id.activityName) EditText activityName;
    @BindView(R.id.activityLocation) EditText activityLocation;
    @BindView(R.id.day_of_week_spinner) Spinner dayOfWeekSpinner;
    @BindView(R.id.startTimeHour) EditText startTimeHour;
    @BindView(R.id.startTimeMinute) EditText startTimeMinute;
    @BindView(R.id.endTimeHour) EditText endTimeHour;
    @BindView(R.id.endTimeMinute) EditText endTimeMinute;
    @BindView(R.id.addActivityButton) Button addActivityButton;
    @BindView(R.id.cancelActivityButton) Button cancelActivityButton;
    @BindDimen(R.dimen.dialog_width) int dialog_width;

    public CustomActivityDialog(Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_activity);
        ButterKnife.bind(this);
        initDialog();
    }

    public void setListener(ItemClickedListener<StudentActivity> listener) {
        this.listener = listener;
    }

    private void initDialog() {
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getContext(), R.array.days_of_week_array,
                        R.layout.spinner_custom_layout);
        dayOfWeekSpinner.setAdapter(adapter);
        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time startTime = new Time(Integer.parseInt(startTimeHour.getText().toString()),
                        Integer.parseInt(startTimeMinute.getText().toString()));
                Time endTime = new Time(Integer.parseInt(endTimeHour.getText().toString()),
                        Integer.parseInt(endTimeMinute.getText().toString()));
                MeetingTime meetingTime = new MeetingTime(1 << dayOfWeekSpinner.getSelectedItemPosition(),
                        startTime, endTime);
                MeetingTime[] meetingTimes = {meetingTime};
                StudentActivitySection section = new StudentActivitySection("",
                        meetingTimes, activityLocation.getText().toString());
                StudentActivitySection[] sections = {section};
                StudentActivity activity = new StudentActivity(activityName.getText().toString(), sections);
                section.setCommitment(activity);
                listener.itemChosen(activity);
                dismiss();
            }
        });
        cancelActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
