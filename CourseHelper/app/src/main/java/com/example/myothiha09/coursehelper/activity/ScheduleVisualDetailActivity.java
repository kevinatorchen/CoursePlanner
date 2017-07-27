package com.example.myothiha09.coursehelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.fragment.ScheduleOverviewFragment;
import com.example.myothiha09.coursehelper.model.StudentActivity;
import com.example.myothiha09.coursehelper.model.StudentActivitySection;
import com.example.myothiha09.coursehelper.model.Commitment;
import com.example.myothiha09.coursehelper.model.CommitmentRequest;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.CourseSection;
import com.example.myothiha09.coursehelper.model.MeetingTime;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Schedule;
import com.example.myothiha09.coursehelper.model.Section;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/22/2017.
 */

public class ScheduleVisualDetailActivity extends AppCompatActivity {
  Schedule list;
  int timeInt;
  ArrayList<Integer> dayList = new ArrayList<>();
  private RelativeLayout layout;
  MaterialDialog.Builder dialog;
  private View view;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.schedule_screen);
    list = ScheduleOverviewFragment.getCurrent();
    view = new View(this);
    TextView tv = (TextView) findViewById(R.id.textTitle);
    tv.setText(ScheduleOverviewFragment.getScheduleNumber());
    makeTimeTable(list);
  }

  public void makeTimeTable(Schedule schedule) {
    int hourHeight = (int) getResources().getDimension(R.dimen.hourHeight);
    List<CommitmentRequest> commitmentRequestList = Model.student.getCommitmentRequests();
    Commitment[] commitmentList = new Commitment[commitmentRequestList.size()];
    for (int i = 0; i < commitmentList.length; i++) {
      commitmentList[i] = commitmentRequestList.get(i).getCommitment();
    }
    int color = getResources().getColor(R.color.colorAccent);
    Commitment tempCommitment;
    int courseArrayLength = commitmentList.length;
    for (Section x : schedule.getSchedule()) {
      tempCommitment = x.getCommitment();
      if (courseArrayLength >= 1 && tempCommitment.getName().equals(commitmentList[0].getName())) {
        color = getResources().getColor(R.color.course1);
      } else if (courseArrayLength >= 2 && tempCommitment
          .getName()
          .equals(commitmentList[1].getName())) {
        color = getResources().getColor(R.color.course2);
      } else if (courseArrayLength >= 3 && tempCommitment
          .getName()
          .equals(commitmentList[2].getName())) {
        color = getResources().getColor(R.color.course3);
      } else if (courseArrayLength >= 4 && tempCommitment
          .getName()
          .equals(commitmentList[3].getName())) {
        color = getResources().getColor(R.color.course4);
      } else if (courseArrayLength >= 5 && tempCommitment
          .getName()
          .equals(commitmentList[4].getName())) {
        color = getResources().getColor(R.color.course5);
      } else if (courseArrayLength >= 6 && tempCommitment
          .getName()
          .equals(commitmentList[5].getName())) {
        color = getResources().getColor(R.color.course6);
      } else if (courseArrayLength >= 7 && tempCommitment
          .getName()
          .equals(commitmentList[6].getName())) {
        color = getResources().getColor(R.color.course7);
      } else if (courseArrayLength >= 8 && tempCommitment
          .getName()
          .equals(commitmentList[7].getName())) {
        color = getResources().getColor(R.color.course8);
      } else if (courseArrayLength >= 9 && tempCommitment
          .getName()
          .equals(commitmentList[8].getName())) {
        color = getResources().getColor(R.color.course9);
      } else if (courseArrayLength >= 10 && tempCommitment
          .getName()
          .equals(commitmentList[9].getName())) {
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
              tv.setText(tempCommitment.getName());
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
              if (x.getCommitment() instanceof Course) {
                Course tempCourse = (Course) x.getCommitment();
                CourseSection courseSection = (CourseSection) x;
                dialog = new MaterialDialog.Builder(this);
                dialog.title("Course Details")
                    .content("Course: "
                        + tempCourse.getName()
                        + "\n"
                        + "Course Section: "
                        + courseSection.getName()
                        + "\n"
                        + "Course Prof: "
                        + courseSection.getProf()
                        + "\n"
                        + "Course CRN: "
                        + courseSection.getCrn()
                        + "\n"
                        + "Course Location: "
                        + x.getLocation()
                        + "Course MeetingTimes: "
                        + "\n"
                        + meetingTimes)
                    .neutralText("OK");
              } else {
                dialog = new MaterialDialog.Builder(this);
                dialog.title("Activity Details")
                    .content("Activity: "
                        + x.getCommitment().getName()
                        + "\n"
                        + "Activity Location: "
                        + x.getLocation()
                        + "Activity MeetingTimes: "
                        + "\n"
                        + meetingTimes)
                    .neutralText("OK");
              }

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