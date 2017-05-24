package com.example.myothiha09.coursehelper.controller;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.MeetingTime;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Section;

import java.util.ArrayList;
import java.util.Set;

import static java.security.AccessController.getContext;

/**
 * Created by Myo on 5/22/2017.
 */

public class ScheduleIndividualDetail extends AppCompatActivity {
    ArrayList<Section> list;
    private RelativeLayout layout;
    private View[] view = new View[14];
    private View[] view30 = new View[14];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_screen);
        list = ScheduleOverviewRecycler.getCurrent();
        Boast.makeText(getApplicationContext(), "Click back to go back", Toast.LENGTH_LONG).show();
        TextView tv = (TextView) findViewById(R.id.textTitle);
        tv.setText(ScheduleOverviewRecycler.getScheduleNumber());
        makeTimeTable(list);
    }

    public void makeTimeTable(ArrayList<Section> schedule) {
        int hourHeight = (int) getResources().getDimension(R.dimen.hourHeight);
        int hour30Height = (int) getResources().getDimension(R.dimen.hour30Height);
        for (Section x : schedule) {
            MeetingTime[] times = x.getMeetingTimes();
            for (MeetingTime time : times) {
                if (time.getDayOfWeek() == 0) {
                    populateViews(R.id.mondayRelativeLayout);
                } else if (time.getDayOfWeek() == 1) {
                    populateViews(R.id.tuesdayRelativeLayout);
                } else if (time.getDayOfWeek() == 2) {
                    populateViews(R.id.wednesdayRelativeLayout);
                } else if (time.getDayOfWeek() == 3) {
                    populateViews(R.id.thursdayRelativeLayout);
                } else if (time.getDayOfWeek() == 4) {
                    populateViews(R.id.fridayRelativeLayout);
                }
                for (int i = 8; i < 21; i++) {
                    if (time.getStartTime().getHour() == i) {
                        if (time.getStartTime().getMinute() != 5) {
                            if (time.getStartTime().compareTo(time.getEndTime()) <= 60) {
                                view30[time.getStartTime().getHour() - 7].getLayoutParams().height = hourHeight;
                            } else {
                                view30[time.getStartTime().getHour() - 7].getLayoutParams().height = hour30Height;
                            }
                        } else {
                            if (time.getStartTime().compareTo(time.getEndTime()) <= 60) {
                                view[time.getStartTime().getHour() - 7].getLayoutParams().height = hourHeight;
                            } else {
                                view[time.getStartTime().getHour() - 7].getLayoutParams().height = hour30Height;
                            }
                        }
                        i = 21;
                    }
                }
            }
        }
        colorize(schedule);
    }

    public void populateViews(int x) {
        layout = (RelativeLayout) findViewById(x);
        view[0] = layout.findViewById(R.id.am7);
        view[1] = layout.findViewById(R.id.am8);
        view[2] = layout.findViewById(R.id.am9);
        view[3] = layout.findViewById(R.id.am10);
        view[4] = layout.findViewById(R.id.am11);
        view[5] = layout.findViewById(R.id.pm12);
        view[6] = layout.findViewById(R.id.pm1);
        view[7] = layout.findViewById(R.id.pm2);
        view[8] = layout.findViewById(R.id.pm3);
        view[9] = layout.findViewById(R.id.pm4);
        view[10] = layout.findViewById(R.id.pm5);
        view[11] = layout.findViewById(R.id.pm6);
        view[12] = layout.findViewById(R.id.pm7);
        view[13] = layout.findViewById(R.id.pm8);
        view30[0] = layout.findViewById(R.id.am730);
        view30[1] = layout.findViewById(R.id.am830);
        view30[2] = layout.findViewById(R.id.am930);
        view30[3] = layout.findViewById(R.id.am1030);
        view30[4] = layout.findViewById(R.id.am1130);
        view30[5] = layout.findViewById(R.id.pm1230);
        view30[6] = layout.findViewById(R.id.pm130);
        view30[7] = layout.findViewById(R.id.pm230);
        view30[8] = layout.findViewById(R.id.pm330);
        view30[9] = layout.findViewById(R.id.pm430);
        view30[10] = layout.findViewById(R.id.pm530);
        view30[11] = layout.findViewById(R.id.pm630);
        view30[12] = layout.findViewById(R.id.pm730);
        view30[13] = layout.findViewById(R.id.pm830);
    }

    public void colorize(ArrayList<Section> schedule) {
        int hourHeight = (int) getResources().getDimension(R.dimen.hourHeight);
        int hour30Height = (int) getResources().getDimension(R.dimen.hour30Height);
        Set<Course> set = Model.student.getCoursesList();
        Course[] courseList = set.toArray(new Course[set.size()]);
        int color = getResources().getColor(R.color.colorAccent);
        Course tempCourse = null;
        int courseArrayLength = courseList.length;
        for (Section x : schedule) {
            tempCourse = x.getCourse();
            if (courseArrayLength >= 1 && x.getCourse().getName().equals(courseList[0].getName())) {
                color = getResources().getColor(R.color.course1);
            } else if (courseArrayLength >= 2 && x.getCourse().getName().equals(courseList[1].getName())) {
                color = getResources().getColor(R.color.course2);
            } else if (courseArrayLength >= 3 && x.getCourse().getName().equals(courseList[2].getName())) {
                color = getResources().getColor(R.color.course3);
            } else if (courseArrayLength >= 4 && x.getCourse().getName().equals(courseList[3].getName())) {
                color = getResources().getColor(R.color.course4);
            } else if (courseArrayLength >= 5 && x.getCourse().getName().equals(courseList[4].getName())) {
                color = getResources().getColor(R.color.course5);
            } else if (courseArrayLength >= 6 && x.getCourse().getName().equals(courseList[5].getName())) {
                color = getResources().getColor(R.color.course6);
            } else if (courseArrayLength >= 7 && x.getCourse().getName().equals(courseList[6].getName())) {
                color = getResources().getColor(R.color.course7);
            } else if (courseArrayLength >= 8 && x.getCourse().getName().equals(courseList[7].getName())) {
                color = getResources().getColor(R.color.course8);
            } else if (courseArrayLength >= 9 && x.getCourse().getName().equals(courseList[8].getName())) {
                color = getResources().getColor(R.color.course9);
            } else if (courseArrayLength >= 10 && x.getCourse().getName().equals(courseList[9].getName())) {
                color = getResources().getColor(R.color.course10);
            }
            MeetingTime[] times = x.getMeetingTimes();
            for (MeetingTime time : times) {
                if (time.getDayOfWeek() == 0) {
                    populateViews(R.id.mondayRelativeLayout);
                } else if (time.getDayOfWeek() == 1) {
                    populateViews(R.id.tuesdayRelativeLayout);
                } else if (time.getDayOfWeek() == 2) {
                    populateViews(R.id.wednesdayRelativeLayout);
                } else if (time.getDayOfWeek() == 3) {
                    populateViews(R.id.thursdayRelativeLayout);
                } else if (time.getDayOfWeek() == 4) {
                    populateViews(R.id.fridayRelativeLayout);
                }
                for (int i = 8; i < 21; i++) {
                    if (time.getStartTime().getHour() == i) {
                        TextView tv = new TextView(this);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        tv.setText(tempCourse.getName());
                        tv.setTextSize(11);
                        tv.setPadding(15, 15, 0, 0);
                        View v;
                        if (time.getStartTime().getMinute() != 5) {
                            // tv.setText(tempCourse.getName() + "\n" + "Prof: " + x.getProf() + "\n" + "CRN: " + x.getCrn());
                            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) view30[time.getStartTime().getHour() - 7].getLayoutParams();
                            tv.setY(rlp.topMargin);
                            view30[time.getStartTime().getHour() - 7].setBackgroundColor(color);
                            v = view30[time.getStartTime().getHour() - 7];
                        } else {
                            //tv.setText(tempCourse.getName() + "\n" + "Prof: " + x.getProf() + "\n" + "CRN: " + x.getCrn());
                            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) view[time.getStartTime().getHour() - 7].getLayoutParams();
                            tv.setY(rlp.topMargin);
                            view[time.getStartTime().getHour() - 7].setBackgroundColor(color);
                            v = view[time.getStartTime().getHour() - 7];
                        }
                        String meetingTimes = "";
                        for (MeetingTime mT : times) {
                            meetingTimes += mT + "\n";
                        }
                        final MaterialDialog.Builder dialog = new MaterialDialog.Builder(this);
                        dialog.title("Course Details")
                                .content("Course: " + tempCourse.getName() + "\n" +
                                        "Course Section: " + x.getName() + "\n" +
                                        "Course Prof: " + x.getProf() + "\n" +
                                        "Course CRN: " + x.getCrn() + "\n" +
                                        "Course Location: " + x.getLocation() +
                                        "Course MeetingTimes: " + "\n" + meetingTimes)
                                .neutralText("OK");

                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.show();
                            }
                        });
                        layout.addView(tv);
                        i = 21;
                    }
                }
            }
        }

    }
}