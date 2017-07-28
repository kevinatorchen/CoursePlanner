package com.example.myothiha09.coursehelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.controller.CoursePlanner;
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

public class ScheduleVisualDetailFragment extends Fragment {
  Schedule list;
  int timeInt;
  ArrayList<Integer> dayList = new ArrayList<>();
  MaterialDialog.Builder dialog;
  @BindView(R.id.textTitle) TextView textTitle;
  Unbinder unbinder;
  private RelativeLayout layout;
  private Schedule schedule;
  private View view;
  private String scheduleNumber;
  private View rowView;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    rowView = inflater.inflate(R.layout.schedule_screen, container, false);
    unbinder = ButterKnife.bind(this, rowView);
    textTitle.setText(scheduleNumber);
    CoursePlanner.planCourses(Model.student.getCommmitmentRequestAsArray());
    ArrayList<Schedule> al = new ArrayList<>();
    for (Schedule section : CoursePlanner.scheduleList) {
      al.add(section);
    }
    makeTimeTable(schedule);
    return rowView;
  }

  public void putExtra(Schedule schedule, String scheduleNumber) {
    this.schedule = schedule;
    this.scheduleNumber = scheduleNumber;
  }

  public void makeTimeTable(Schedule schedule) {
    int hourHeight = (int) getResources().getDimension(R.dimen.hourHeight);
    double minuteHeight = hourHeight / 60.0;
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
      } else if (courseArrayLength >= 2 && tempCommitment.getName()
          .equals(commitmentList[1].getName())) {
        color = getResources().getColor(R.color.course2);
      } else if (courseArrayLength >= 3 && tempCommitment.getName()
          .equals(commitmentList[2].getName())) {
        color = getResources().getColor(R.color.course3);
      } else if (courseArrayLength >= 4 && tempCommitment.getName()
          .equals(commitmentList[3].getName())) {
        color = getResources().getColor(R.color.course4);
      } else if (courseArrayLength >= 5 && tempCommitment.getName()
          .equals(commitmentList[4].getName())) {
        color = getResources().getColor(R.color.course5);
      } else if (courseArrayLength >= 6 && tempCommitment.getName()
          .equals(commitmentList[5].getName())) {
        color = getResources().getColor(R.color.course6);
      } else if (courseArrayLength >= 7 && tempCommitment.getName()
          .equals(commitmentList[6].getName())) {
        color = getResources().getColor(R.color.course7);
      } else if (courseArrayLength >= 8 && tempCommitment.getName()
          .equals(commitmentList[7].getName())) {
        color = getResources().getColor(R.color.course8);
      } else if (courseArrayLength >= 9 && tempCommitment.getName()
          .equals(commitmentList[8].getName())) {
        color = getResources().getColor(R.color.course9);
      } else if (courseArrayLength >= 10 && tempCommitment.getName()
          .equals(commitmentList[9].getName())) {
        color = getResources().getColor(R.color.course10);
      }
      dayList.clear();
      MeetingTime[] times = x.getMeetingTimes();
      for (MeetingTime time : times) {
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
          layout = (RelativeLayout) rowView.findViewById(day);
          TextView tv = new TextView(getContext());

          view = new View(getContext());
          layout.addView(view);
          layout.addView(tv);
          tv.setText(tempCommitment.getName());
          tv.setTextColor(getResources().getColor(R.color.white));
          tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
              getResources().getDimension(R.dimen.courseSize));
          tv.setPadding(hourHeight / 4, hourHeight / 4, 0, 0);
          int topMargin =
              (time.getStartTime().getHour() - 7) * hourHeight + (int) (time.getStartTime()
                  .getMinute() * minuteHeight);
          view.setY(topMargin);
          tv.setY(topMargin);
          int height = (int) (time.getLength() * minuteHeight);
          view.setMinimumHeight(height);
          view.getLayoutParams().height = height;
          view.setBackgroundColor(color);
          dialog = new MaterialDialog.Builder(getContext()).titleColor(
              getResources().getColor(R.color.title_font_color))
              .contentColor(getResources().getColor(R.color.content_font_color));
          createDialog(x);

          view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
              dialog.show();
            }
          });
        }
      }
    }
  }

  private void createDialog(Section x) {
    String meetingTimes = "";
    for (MeetingTime mT : x.getMeetingTimes()) {
      meetingTimes += mT + "\n";
    }
    if (x.getCommitment() instanceof Course) {
      Course tempCourse = (Course) x.getCommitment();
      CourseSection courseSection = (CourseSection) x;
      dialog.title("Course Details")
          .content("Name: "
              + tempCourse.getName()
              + "\n"
              + "Section: "
              + courseSection.getName()
              + "\n"
              + "Professor: "
              + courseSection.getProf()
              + "\n"
              + "CRN: "
              + courseSection.getCrn()
              + "\n"
              + "Location: "
              + x.getLocation()
              + "Meeting Times: "
              + "\n"
              + meetingTimes)
          .neutralText("OK");
    } else {
      dialog = new MaterialDialog.Builder(getContext());
      dialog.title("Activity Details")
          .content("Activity: "
              + x.getCommitment().getName()
              + "\n"
              + "Location: "
              + x.getLocation()
              + "Meeting Times: "
              + "\n"
              + meetingTimes)
          .neutralText("OK");
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}