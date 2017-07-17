package com.example.myothiha09.coursehelper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.activity.ScheduleVisualDetailActivity;
import com.example.myothiha09.coursehelper.controller.Boast;
import com.example.myothiha09.coursehelper.controller.CoursePlanner;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Section;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Myo on 5/22/2017.
 */

public class ScheduleOverviewFragment extends Fragment{
    static ArrayList<Section> current = new ArrayList<>();
    static String scheduleNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schedule_overview, container, false);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.nested);
        ArrayList<Course> courseList = Model.student.getCoursesList();
        Boast.makeText(getContext(), "Click the schedule to view it visually", Toast.LENGTH_LONG).show();
        CoursePlanner.planCourses(courseList.toArray(new Course[courseList.size()]));
        Set<ArrayList<Section>> list = CoursePlanner.scheduleList;
        int index = 1;
        for(final ArrayList<Section> sections: list) {
            LinearLayout nested = new LinearLayout(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = (int) getResources().getDimension(R.dimen.dp10);
            int margin = (int) getResources().getDimension(R.dimen.dp10);
            nested.setPadding(margin, margin, margin, margin);
            nested.setBackground(getResources().getDrawable(R.drawable.bg_card));
            nested.setOrientation(LinearLayout.VERTICAL);
            final TextView tv = new TextView(getContext());
            tv.setText("Schedule " + index++);
            tv.setTextSize(18);
            tv.setTextColor(getResources().getColor(R.color.strong_blue));
            nested.addView(tv);
            for (Section section: sections) {
                String courseName = "Course: " + section.getCourse().getName();
                String courseSection = "Section: " + section.getName();
                String CRN = "CRN: " + section.getCrn();
                String meetingTimes = "Meeting Times: ";
                for (int i = 0; i < section.getMeetingTimes().length; i++) {
                    meetingTimes += section.getMeetingTimes()[0] + " ";
                }

                TextView line1 = new TextView(getContext());
                line1.setText(courseName + " " + courseSection + " " + CRN);
                TextView line2 = new TextView(getContext());
                line2.setText(meetingTimes);
                nested.addView(line1);
                nested.addView(line2);
            }
            nested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current = sections;
                    scheduleNumber = tv.getText().toString();
                    startActivity(new Intent(getContext(), ScheduleVisualDetailActivity.class));
                }
            });
            layout.addView(nested, layoutParams);
        }
        return view;
    }

    public static ArrayList<Section> getCurrent() {
        return current;
    }
    public static String getScheduleNumber() {
        return scheduleNumber;
    }
}
