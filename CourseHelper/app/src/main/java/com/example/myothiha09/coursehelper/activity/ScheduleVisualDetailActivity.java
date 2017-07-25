package com.example.myothiha09.coursehelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.controller.Boast;
import com.example.myothiha09.coursehelper.fragment.ScheduleOverviewFragment;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.MeetingTime;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Schedule;
import com.example.myothiha09.coursehelper.model.Section;
import java.util.ArrayList;

/**
 * Created by Myo on 5/22/2017.
 */

public class ScheduleVisualDetailActivity extends AppCompatActivity {
  Schedule list;
  int timeInt;
  ArrayList<Integer> dayList = new ArrayList<>();
  private RelativeLayout layout;
  private View view;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.schedule_screen);
    list = ScheduleOverviewFragment.getCurrent();
    view = new View(this);
    Boast.makeText(getApplicationContext(), "Click back to go back", Toast.LENGTH_LONG).show();
    TextView tv = (TextView) findViewById(R.id.textTitle);
    tv.setText(ScheduleOverviewFragment.getScheduleNumber());
    makeTimeTable(list);
  }

  public void makeTimeTable(Schedule schedule) {
    int hourHeight = (int) getResources().getDimension(R.dimen.hourHeight);
    int hour30Height = (int) getResources().getDimension(R.dimen.hour30Height);
    ArrayList<Course> coursesList = Model.student.getCoursesList();
    Course[] courseList = coursesList.toArray(new Course[coursesList.size()]);
    int color = getResources().getColor(R.color.colorAccent);
    Course tempCourse;
    int courseArrayLength = courseList.length;
    for (Section x : schedule) {
      tempCourse = x.getCourse();
      if (courseArrayLength >= 1 && x.getCourse().getName().equals(courseList[0].getName())) {
        color = getResources().getColor(R.color.course1);
      } else if (courseArrayLength >= 2 && x.getCourse()
          .getName()
          .equals(courseList[1].getName())) {
        color = getResources().getColor(R.color.course2);
      } else if (courseArrayLength >= 3 && x.getCourse()
          .getName()
          .equals(courseList[2].getName())) {
        color = getResources().getColor(R.color.course3);
      } else if (courseArrayLength >= 4 && x.getCourse()
          .getName()
          .equals(courseList[3].getName())) {
        color = getResources().getColor(R.color.course4);
      } else if (courseArrayLength >= 5 && x.getCourse()
          .getName()
          .equals(courseList[4].getName())) {
        color = getResources().getColor(R.color.course5);
      } else if (courseArrayLength >= 6 && x.getCourse()
          .getName()
          .equals(courseList[5].getName())) {
        color = getResources().getColor(R.color.course6);
      } else if (courseArrayLength >= 7 && x.getCourse()
          .getName()
          .equals(courseList[6].getName())) {
        color = getResources().getColor(R.color.course7);
      } else if (courseArrayLength >= 8 && x.getCourse()
          .getName()
          .equals(courseList[7].getName())) {
        color = getResources().getColor(R.color.course8);
      } else if (courseArrayLength >= 9 && x.getCourse()
          .getName()
          .equals(courseList[8].getName())) {
        color = getResources().getColor(R.color.course9);
      } else if (courseArrayLength >= 10 && x.getCourse()
          .getName()
          .equals(courseList[9].getName())) {
        color = getResources().getColor(R.color.course10);
      }
      MeetingTime[] times = x.getMeetingTimes();
      for (MeetingTime time : times) {
        dayList.clear();
        if ((time.getDaysOfWeek() & 1) != 0) {
          dayList.add(R.id.mondayRelativeLayout);
        }
        if ((time.getDaysOfWeek() & 2) != 0) {
          dayList.add(R.id.tuesdayRelativeLayout);
        }
        if ((time.getDaysOfWeek() & 4) != 0) {
          dayList.add(R.id.wednesdayRelativeLayout);
        }
        if ((time.getDaysOfWeek() & 8) != 0) {
          dayList.add(R.id.thursdayRelativeLayout);
        }
        if ((time.getDaysOfWeek() & 16) != 0) {
          dayList.add(R.id.fridayRelativeLayout);
        }
        for (Integer day : dayList) {
          for (int i = 8; i < 21; i++) {
            if (time.getStartTime().getHour() == i) {
              TextView tv = new TextView(this);
              tv.setTextColor(getResources().getColor(R.color.white));
              tv.setText(tempCourse.getName());
              tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                  getResources().getDimension(R.dimen.courseSize));
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
              double scale = time.getLength() / 50.0;
              view.getLayoutParams().height = (int) scale * hourHeight;
              RelativeLayout.LayoutParams rlp =
                  (RelativeLayout.LayoutParams) view.getLayoutParams();
              tv.setY(rlp.topMargin);
              view.setBackgroundColor(color);
              String meetingTimes = "";
              for (MeetingTime mT : times) {
                meetingTimes += mT + "\n";
              }
              final MaterialDialog.Builder dialog = new MaterialDialog.Builder(this);
              dialog.title("Course Details")
                  .content("Course: "
                      + tempCourse.getName()
                      + "\n"
                      + "Course Section: "
                      + x.getName()
                      + "\n"
                      + "Course Prof: "
                      + x.getProf()
                      + "\n"
                      + "Course CRN: "
                      + x.getCrn()
                      + "\n"
                      + "Course Location: "
                      + x.getLocation()
                      + "Course MeetingTimes: "
                      + "\n"
                      + meetingTimes)
                  .neutralText("OK");

              view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
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

  public void populateViews(int x, int y) {
    layout = (RelativeLayout) findViewById(x);
    view = layout.findViewById(y);
  }
}