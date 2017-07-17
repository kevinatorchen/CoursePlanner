package com.example.myothiha09.coursehelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.MeetingTime;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Section;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Myo on 5/21/2017.
 */

public class VisualScheduleAdapter extends ArrayAdapter<ArrayList<Section>> {
    final Context context;
    final ArrayList<ArrayList<Section>> list;
    int day;
    int timeInt;
    View rowView;
    private RelativeLayout layout;
    private View view = new View(getContext());

    public VisualScheduleAdapter(Context context, ArrayList<ArrayList<Section>> list) {
        super(context, -1, list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.schedule_screen, parent, false);
        ArrayList<Section> current = list.get(position);
        TextView tv = (TextView) rowView.findViewById(R.id.textTitle);
        int title = position + 1;
        tv.setText("Schedule " + title);
        tv.setTextSize(18);
        makeTimeTable(current);
        return rowView;
    }

    public void makeTimeTable(ArrayList<Section> schedule) {
        int hourHeight = (int) getContext().getResources().getDimension(R.dimen.hourHeight);
        int hour30Height = (int) getContext().getResources().getDimension(R.dimen.hour30Height);
        ArrayList<Course> coursesList = Model.student.getCoursesList();
        Course[] courseList = coursesList.toArray(new Course[coursesList.size()]);
        int color = getContext().getResources().getColor(R.color.colorAccent);
        Course tempCourse;
        int courseArrayLength = courseList.length;
        for (Section x : schedule) {
            tempCourse = x.getCourse();
            if (courseArrayLength >= 1 && x.getCourse().getName().equals(courseList[0].getName())) {
                color = getContext().getResources().getColor(R.color.course1);
            } else if (courseArrayLength >= 2 && x.getCourse().getName().equals(courseList[1].getName())) {
                color = getContext().getResources().getColor(R.color.course2);
            } else if (courseArrayLength >= 3 && x.getCourse().getName().equals(courseList[2].getName())) {
                color = getContext().getResources().getColor(R.color.course3);
            } else if (courseArrayLength >= 4 && x.getCourse().getName().equals(courseList[3].getName())) {
                color = getContext().getResources().getColor(R.color.course4);
            } else if (courseArrayLength >= 5 && x.getCourse().getName().equals(courseList[4].getName())) {
                color = getContext().getResources().getColor(R.color.course5);
            } else if (courseArrayLength >= 6 && x.getCourse().getName().equals(courseList[5].getName())) {
                color = getContext().getResources().getColor(R.color.course6);
            } else if (courseArrayLength >= 7 && x.getCourse().getName().equals(courseList[6].getName())) {
                color = getContext().getResources().getColor(R.color.course7);
            } else if (courseArrayLength >= 8 && x.getCourse().getName().equals(courseList[7].getName())) {
                color = getContext().getResources().getColor(R.color.course8);
            } else if (courseArrayLength >= 9 && x.getCourse().getName().equals(courseList[8].getName())) {
                color = getContext().getResources().getColor(R.color.course9);
            } else if (courseArrayLength >= 10 && x.getCourse().getName().equals(courseList[9].getName())) {
                color = getContext().getResources().getColor(R.color.course10);
            }
            MeetingTime[] times = x.getMeetingTimes();
            for (MeetingTime time : times) {
                if ((time.getDaysOfWeek() & 1) != 0) {
                    day = R.id.mondayRelativeLayout;
                } else if ((time.getDaysOfWeek() & 2) != 0) {
                    day = R.id.tuesdayRelativeLayout;
                } else if ((time.getDaysOfWeek() & 4) != 0) {
                    day = R.id.wednesdayRelativeLayout;
                } else if ((time.getDaysOfWeek() & 8) != 0) {
                    day = R.id.thursdayRelativeLayout;
                } else if ((time.getDaysOfWeek() & 16) != 0) {
                    day = R.id.fridayRelativeLayout;
                }
                for (int i = 8; i < 21; i++) {
                    if (time.getStartTime().getHour() == i) {
                        TextView tv = new TextView(getContext());
                        tv.setTextColor(getContext().getResources().getColor(R.color.white));
                        tv.setText(tempCourse.getName());
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(R.dimen.courseSize));
                        tv.setPadding(15, 15, 0, 0);
                        if (time.getStartTime().getMinute() != 5) {
                            switch (time.getStartTime().getHour()) {
                                case 7:
                                    timeInt = R.id.am730;
                                    break;
                                case 8:
                                    timeInt = R.id.am830;
                                    break;
                                case 9:
                                    timeInt = R.id.am930;
                                    break;
                                case 10:
                                    timeInt = R.id.am1030;
                                    break;
                                case 11:
                                    timeInt = R.id.am1130;
                                    break;
                                case 12:
                                    timeInt = R.id.pm1230;
                                    break;
                                case 13:
                                    timeInt = R.id.pm130;
                                    break;
                                case 14:
                                    timeInt = R.id.pm230;
                                    break;
                                case 15:
                                    timeInt = R.id.pm330;
                                    break;
                                case 16:
                                    timeInt = R.id.pm430;
                                    break;
                                case 17:
                                    timeInt = R.id.pm530;
                                    break;
                                case 18:
                                    timeInt = R.id.pm630;
                                    break;
                                case 19:
                                    timeInt = R.id.pm730;
                                    break;
                                case 20:
                                    timeInt = R.id.pm830;
                                    break;

                            }
                            populateViews(day, timeInt);
                        } else {
                            switch (time.getStartTime().getHour()) {
                                case 7:
                                    timeInt = R.id.am7;
                                    break;
                                case 8:
                                    timeInt = R.id.am8;
                                    break;
                                case 9:
                                    timeInt = R.id.am9;
                                    break;
                                case 10:
                                    timeInt = R.id.am10;
                                    break;
                                case 11:
                                    timeInt = R.id.am11;
                                    break;
                                case 12:
                                    timeInt = R.id.pm12;
                                    break;
                                case 13:
                                    timeInt = R.id.pm1;
                                    break;
                                case 14:
                                    timeInt = R.id.pm2;
                                    break;
                                case 15:
                                    timeInt = R.id.pm3;
                                    break;
                                case 16:
                                    timeInt = R.id.pm4;
                                    break;
                                case 17:
                                    timeInt = R.id.pm5;
                                    break;
                                case 18:
                                    timeInt = R.id.pm6;
                                    break;
                                case 19:
                                    timeInt = R.id.pm7;
                                    break;
                                case 20:
                                    timeInt = R.id.pm8;
                                    break;
                            }
                            populateViews(day, timeInt);
                        }
                        if (time.getStartTime().compareTo(time.getEndTime()) <= 60) {
                            view.getLayoutParams().height = hourHeight;
                        } else {
                            view.getLayoutParams().height = hour30Height;
                        }
                        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        tv.setY(rlp.topMargin);
                        view.setBackgroundColor(color);
                        String meetingTimes = "";
                        for (MeetingTime mT : times) {
                            meetingTimes += mT + "\n";
                        }
                        final MaterialDialog.Builder dialog = new MaterialDialog.Builder(getContext());
                        dialog.title("Course Details")
                                .content("Course: " + tempCourse.getName() + "\n" +
                                        "Course Section: " + x.getName() + "\n" +
                                        "Course Prof: " + x.getProf() + "\n" +
                                        "Course CRN: " + x.getCrn() + "\n" +
                                        "Course Location: " + x.getLocation() +
                                        "Course MeetingTimes: " + "\n" + meetingTimes)
                                .neutralText("OK");

                        view.setOnClickListener(new View.OnClickListener() {
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
        //colorize(schedule);
    }
/*
    public void colorize(ArrayList<Section> schedule) {

        Set<Course> set = Model.student.getCoursesList();
        Course[] courseList = set.toArray(new Course[set.size()]);
        int color = getContext().getResources().getColor(R.color.colorAccent);
        Course tempCourse;
        int courseArrayLength = courseList.length;
        for (Section x : schedule) {
            tempCourse = x.getCourse();
            if (courseArrayLength >= 1 && x.getCourse().getName().equals(courseList[0].getName())) {
                color = getContext().getResources().getColor(R.color.course1);
            } else if (courseArrayLength >= 2 && x.getCourse().getName().equals(courseList[1].getName())) {
                color = getContext().getResources().getColor(R.color.course2);
            } else if (courseArrayLength >= 3 && x.getCourse().getName().equals(courseList[2].getName())) {
                color = getContext().getResources().getColor(R.color.course3);
            } else if (courseArrayLength >= 4 && x.getCourse().getName().equals(courseList[3].getName())) {
                color = getContext().getResources().getColor(R.color.course4);
            } else if (courseArrayLength >= 5 && x.getCourse().getName().equals(courseList[4].getName())) {
                color = getContext().getResources().getColor(R.color.course5);
            } else if (courseArrayLength >= 6 && x.getCourse().getName().equals(courseList[5].getName())) {
                color = getContext().getResources().getColor(R.color.course6);
            } else if (courseArrayLength >= 7 && x.getCourse().getName().equals(courseList[6].getName())) {
                color = getContext().getResources().getColor(R.color.course7);
            } else if (courseArrayLength >= 8 && x.getCourse().getName().equals(courseList[7].getName())) {
                color = getContext().getResources().getColor(R.color.course8);
            } else if (courseArrayLength >= 9 && x.getCourse().getName().equals(courseList[8].getName())) {
                color = getContext().getResources().getColor(R.color.course9);
            } else if (courseArrayLength >= 10 && x.getCourse().getName().equals(courseList[9].getName())) {
                color = getContext().getResources().getColor(R.color.course10);
            }
            MeetingTime[] times = x.getMeetingTimes();
            for (MeetingTime time : times) {
                if (time.getDayOfWeek() == 0) {
                    day = R.id.mondayRelativeLayout;
                } else if (time.getDayOfWeek() == 1) {
                    day = R.id.tuesdayRelativeLayout;
                } else if (time.getDayOfWeek() == 2) {
                    day = R.id.wednesdayRelativeLayout;
                } else if (time.getDayOfWeek() == 3) {
                    day = R.id.thursdayRelativeLayout;
                } else if (time.getDayOfWeek() == 4) {
                    day = R.id.fridayRelativeLayout;
                }
                for (int i = 8; i < 21; i++) {
                    if (time.getStartTime().getHour() == i) {
                        TextView tv = new TextView(getContext());
                        tv.setTextColor(getContext().getResources().getColor(R.color.white));
                        tv.setText(tempCourse.getName());
                        tv.setTextSize(11);
                        tv.setPadding(15, 15, 0, 0);
                        View v;
                        if (time.getStartTime().getMinute() != 5) {

                            // tv.setText(tempCourse.getName() + "\n" + "Prof: " + x.getProf() + "\n" + "CRN: " + x.getCrn());
                            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) view.getLayoutParams();
                            tv.setY(rlp.topMargin);
                            view.setBackgroundColor(color);
                            v = view;
                        } else {
                            //tv.setText(tempCourse.getName() + "\n" + "Prof: " + x.getProf() + "\n" + "CRN: " + x.getCrn());
                                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) view.getLayoutParams();
                                tv.setY(rlp.topMargin);
                                view.setBackgroundColor(color);
                                v = view;
                            }
                            String meetingTimes = "";
                            for (MeetingTime mT : times) {
                                meetingTimes += mT + "\n";
                            }
                            final MaterialDialog.Builder dialog = new MaterialDialog.Builder(getContext());
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

    public void populateViews() {
       *//* monLayout = (RelativeLayout) rowView.findViewById(R.id.mondayRelativeLayout);
        viewMon[0] = monLayout.findViewById(R.id.am7);
        viewMon[1] = monLayout.findViewById(R.id.am8);
        viewMon[2] = monLayout.findViewById(R.id.am9);
        viewMon[3] = monLayout.findViewById(R.id.am10);
        viewMon[4] = monLayout.findViewById(R.id.am11);
        viewMon[5] = monLayout.findViewById(R.id.pm12);
        viewMon[6] = monLayout.findViewById(R.id.pm1);
        viewMon[7] = monLayout.findViewById(R.id.pm2);
        viewMon[8] = monLayout.findViewById(R.id.pm3);
        viewMon[9] = monLayout.findViewById(R.id.pm4);
        viewMon[10] = monLayout.findViewById(R.id.pm5);
        viewMon[11] = monLayout.findViewById(R.id.pm6);
        viewMon[12] = monLayout.findViewById(R.id.pm7);
        viewMon[13] = monLayout.findViewById(R.id.pm8);
        viewMon30[0] = monLayout.findViewById(R.id.am730);
        viewMon30[1] = monLayout.findViewById(R.id.am830);
        viewMon30[2] = monLayout.findViewById(R.id.am930);
        viewMon30[3] = monLayout.findViewById(R.id.am1030);
        viewMon30[4] = monLayout.findViewById(R.id.am1130);
        viewMon30[5] = monLayout.findViewById(R.id.pm1230);
        viewMon30[6] = monLayout.findViewById(R.id.pm130);
        viewMon30[7] = monLayout.findViewById(R.id.pm230);
        viewMon30[8] = monLayout.findViewById(R.id.pm330);
        viewMon30[9] = monLayout.findViewById(R.id.pm430);
        viewMon30[10] = monLayout.findViewById(R.id.pm530);
        viewMon30[11] = monLayout.findViewById(R.id.pm630);
        viewMon30[12] = monLayout.findViewById(R.id.pm730);
        viewMon30[13] = monLayout.findViewById(R.id.pm830);
        tuesLayout = (RelativeLayout) rowView.findViewById(R.id.tuesdayRelativeLayout);
        viewTues[0] = tuesLayout.findViewById(R.id.am7);
        viewTues[1] = tuesLayout.findViewById(R.id.am8);
        viewTues[2] = tuesLayout.findViewById(R.id.am9);
        viewTues[3] = tuesLayout.findViewById(R.id.am10);
        viewTues[4] = tuesLayout.findViewById(R.id.am11);
        viewTues[5] = tuesLayout.findViewById(R.id.pm12);
        viewTues[6] = tuesLayout.findViewById(R.id.pm1);
        viewTues[7] = tuesLayout.findViewById(R.id.pm2);
        viewTues[8] = tuesLayout.findViewById(R.id.pm3);
        viewTues[9] = tuesLayout.findViewById(R.id.pm4);
        viewTues[10] = tuesLayout.findViewById(R.id.pm5);
        viewTues[11] = tuesLayout.findViewById(R.id.pm6);
        viewTues[12] = tuesLayout.findViewById(R.id.pm7);
        viewTues[13] = tuesLayout.findViewById(R.id.pm8);
        viewTues30[0] = tuesLayout.findViewById(R.id.am730);
        viewTues30[1] = tuesLayout.findViewById(R.id.am830);
        viewTues30[2] = tuesLayout.findViewById(R.id.am930);
        viewTues30[3] = tuesLayout.findViewById(R.id.am1030);
        viewTues30[4] = tuesLayout.findViewById(R.id.am1130);
        viewTues30[5] = tuesLayout.findViewById(R.id.pm1230);
        viewTues30[6] = tuesLayout.findViewById(R.id.pm130);
        viewTues30[7] = tuesLayout.findViewById(R.id.pm230);
        viewTues30[8] = tuesLayout.findViewById(R.id.pm330);
        viewTues30[9] = tuesLayout.findViewById(R.id.pm430);
        viewTues30[10] = tuesLayout.findViewById(R.id.pm530);
        viewTues30[11] = tuesLayout.findViewById(R.id.pm630);
        viewTues30[12] = tuesLayout.findViewById(R.id.pm730);
        viewTues30[13] = tuesLayout.findViewById(R.id.pm830);
        wedLayout = (RelativeLayout) rowView.findViewById(R.id.wednesdayRelativeLayout);
        viewWed[0] = wedLayout.findViewById(R.id.am7);
        viewWed[1] = wedLayout.findViewById(R.id.am8);
        viewWed[2] = wedLayout.findViewById(R.id.am9);
        viewWed[3] = wedLayout.findViewById(R.id.am10);
        viewWed[4] = wedLayout.findViewById(R.id.am11);
        viewWed[5] = wedLayout.findViewById(R.id.pm12);
        viewWed[6] = wedLayout.findViewById(R.id.pm1);
        viewWed[7] = wedLayout.findViewById(R.id.pm2);
        viewWed[8] = wedLayout.findViewById(R.id.pm3);
        viewWed[9] = wedLayout.findViewById(R.id.pm4);
        viewWed[10] = wedLayout.findViewById(R.id.pm5);
        viewWed[11] = wedLayout.findViewById(R.id.pm6);
        viewWed[12] = wedLayout.findViewById(R.id.pm7);
        viewWed[13] = wedLayout.findViewById(R.id.pm8);
        viewWed30[0] = wedLayout.findViewById(R.id.am730);
        viewWed30[1] = wedLayout.findViewById(R.id.am830);
        viewWed30[2] = wedLayout.findViewById(R.id.am930);
        viewWed30[3] = wedLayout.findViewById(R.id.am1030);
        viewWed30[4] = wedLayout.findViewById(R.id.am1130);
        viewWed30[5] = wedLayout.findViewById(R.id.pm1230);
        viewWed30[6] = wedLayout.findViewById(R.id.pm130);
        viewWed30[7] = wedLayout.findViewById(R.id.pm230);
        viewWed30[8] = wedLayout.findViewById(R.id.pm330);
        viewWed30[9] = wedLayout.findViewById(R.id.pm430);
        viewWed30[10] = wedLayout.findViewById(R.id.pm530);
        viewWed30[11] = wedLayout.findViewById(R.id.pm630);
        viewWed30[12] = wedLayout.findViewById(R.id.pm730);
        viewWed30[13] = wedLayout.findViewById(R.id.pm830);
        thursLayout = (RelativeLayout) rowView.findViewById(R.id.thursdayRelativeLayout);
        viewThurs[0] = thursLayout.findViewById(R.id.am7);
        viewThurs[1] = thursLayout.findViewById(R.id.am8);
        viewThurs[2] = thursLayout.findViewById(R.id.am9);
        viewThurs[3] = thursLayout.findViewById(R.id.am10);
        viewThurs[4] = thursLayout.findViewById(R.id.am11);
        viewThurs[5] = thursLayout.findViewById(R.id.pm12);
        viewThurs[6] = thursLayout.findViewById(R.id.pm1);
        viewThurs[7] = thursLayout.findViewById(R.id.pm2);
        viewThurs[8] = thursLayout.findViewById(R.id.pm3);
        viewThurs[9] = thursLayout.findViewById(R.id.pm4);
        viewThurs[10] = thursLayout.findViewById(R.id.pm5);
        viewThurs[11] = thursLayout.findViewById(R.id.pm6);
        viewThurs[12] = thursLayout.findViewById(R.id.pm7);
        viewThurs[13] = thursLayout.findViewById(R.id.pm8);
        viewThurs30[0] = thursLayout.findViewById(R.id.am730);
        viewThurs30[1] = thursLayout.findViewById(R.id.am830);
        viewThurs30[2] = thursLayout.findViewById(R.id.am930);
        viewThurs30[3] = thursLayout.findViewById(R.id.am1030);
        viewThurs30[4] = thursLayout.findViewById(R.id.am1130);
        viewThurs30[5] = thursLayout.findViewById(R.id.pm1230);
        viewThurs30[6] = thursLayout.findViewById(R.id.pm130);
        viewThurs30[7] = thursLayout.findViewById(R.id.pm230);
        viewThurs30[8] = thursLayout.findViewById(R.id.pm330);
        viewThurs30[9] = thursLayout.findViewById(R.id.pm430);
        viewThurs30[10] = thursLayout.findViewById(R.id.pm530);
        viewThurs30[11] = thursLayout.findViewById(R.id.pm630);
        viewThurs30[12] = thursLayout.findViewById(R.id.pm730);
        viewThurs30[13] = thursLayout.findViewById(R.id.pm830);
        friLayout = (RelativeLayout) rowView.findViewById(R.id.fridayRelativeLayout);
        viewFri[0] = friLayout.findViewById(R.id.am7);
        viewFri[1] = friLayout.findViewById(R.id.am8);
        viewFri[2] = friLayout.findViewById(R.id.am9);
        viewFri[3] = friLayout.findViewById(R.id.am10);
        viewFri[4] = friLayout.findViewById(R.id.am11);
        viewFri[5] = friLayout.findViewById(R.id.pm12);
        viewFri[6] = friLayout.findViewById(R.id.pm1);
        viewFri[7] = friLayout.findViewById(R.id.pm2);
        viewFri[8] = friLayout.findViewById(R.id.pm3);
        viewFri[9] = friLayout.findViewById(R.id.pm4);
        viewFri[10] = friLayout.findViewById(R.id.pm5);
        viewFri[11] = friLayout.findViewById(R.id.pm6);
        viewFri[12] = friLayout.findViewById(R.id.pm7);
        viewFri[13] = friLayout.findViewById(R.id.pm8);
        viewFri30[0] = friLayout.findViewById(R.id.am730);
        viewFri30[1] = friLayout.findViewById(R.id.am830);
        viewFri30[2] = friLayout.findViewById(R.id.am930);
        viewFri30[3] = friLayout.findViewById(R.id.am1030);
        viewFri30[4] = friLayout.findViewById(R.id.am1130);
        viewFri30[5] = friLayout.findViewById(R.id.pm1230);
        viewFri30[6] = friLayout.findViewById(R.id.pm130);
        viewFri30[7] = friLayout.findViewById(R.id.pm230);
        viewFri30[8] = friLayout.findViewById(R.id.pm330);
        viewFri30[9] = friLayout.findViewById(R.id.pm430);
        viewFri30[10] = friLayout.findViewById(R.id.pm530);
        viewFri30[11] = friLayout.findViewById(R.id.pm630);
        viewFri30[12] = friLayout.findViewById(R.id.pm730);
        viewFri30[13] = friLayout.findViewById(R.id.pm830);*//*
    }*/

    public void populateViews(int x, int y) {
        layout = (RelativeLayout) rowView.findViewById(x);
        view = layout.findViewById(y);
    }
}
