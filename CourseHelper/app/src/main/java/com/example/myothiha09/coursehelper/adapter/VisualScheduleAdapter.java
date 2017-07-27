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
import com.example.myothiha09.coursehelper.model.CommitmentRequest;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.MeetingTime;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Schedule;
import com.example.myothiha09.coursehelper.model.Section;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/21/2017.
 */

public class VisualScheduleAdapter extends ArrayAdapter<Schedule> {
  final Context context;
  final ArrayList<Schedule> list;
  int timeInt;
  View rowView;
  ArrayList<Integer> dayList = new ArrayList<>();
  private RelativeLayout layout;
  private View view = new View(getContext());

  public VisualScheduleAdapter(Context context, ArrayList<Schedule> list) {
    super(context, -1, list);
    this.context = context;
    this.list = list;
  }

  public View getView(int position, View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    rowView = inflater.inflate(R.layout.schedule_screen, parent, false);
    Schedule current = list.get(position);
    TextView tv = (TextView) rowView.findViewById(R.id.textTitle);
    int title = position + 1;
    tv.setText("Schedule " + title);
    tv.setTextSize(18);
    makeTimeTable(current);
    return rowView;
  }

  public void makeTimeTable(Schedule schedule) {
    int hourHeight = (int) getContext().getResources().getDimension(R.dimen.hourHeight);
    List<CommitmentRequest> commitmentRequestList = Model.student.getCourseRequests();
    Course[] courseList = new Course[commitmentRequestList.size()];
    for (int i = 0; i < courseList.length; i++) {
      courseList[i] = commitmentRequestList.get(i).getCourse();
    }
    int color = getContext().getResources().getColor(R.color.colorAccent);
    Course tempCourse;
    int courseArrayLength = courseList.length;
    for (Section x : schedule.getSchedule()) {
      tempCourse = x.getCourse();
      if (courseArrayLength >= 1 && x.getCourse().getName().equals(courseList[0].getName())) {
        color = getContext().getResources().getColor(R.color.course1);
      } else if (courseArrayLength >= 2 && x.getCourse()
          .getName()
          .equals(courseList[1].getName())) {
        color = getContext().getResources().getColor(R.color.course2);
      } else if (courseArrayLength >= 3 && x.getCourse()
          .getName()
          .equals(courseList[2].getName())) {
        color = getContext().getResources().getColor(R.color.course3);
      } else if (courseArrayLength >= 4 && x.getCourse()
          .getName()
          .equals(courseList[3].getName())) {
        color = getContext().getResources().getColor(R.color.course4);
      } else if (courseArrayLength >= 5 && x.getCourse()
          .getName()
          .equals(courseList[4].getName())) {
        color = getContext().getResources().getColor(R.color.course5);
      } else if (courseArrayLength >= 6 && x.getCourse()
          .getName()
          .equals(courseList[5].getName())) {
        color = getContext().getResources().getColor(R.color.course6);
      } else if (courseArrayLength >= 7 && x.getCourse()
          .getName()
          .equals(courseList[6].getName())) {
        color = getContext().getResources().getColor(R.color.course7);
      } else if (courseArrayLength >= 8 && x.getCourse()
          .getName()
          .equals(courseList[7].getName())) {
        color = getContext().getResources().getColor(R.color.course8);
      } else if (courseArrayLength >= 9 && x.getCourse()
          .getName()
          .equals(courseList[8].getName())) {
        color = getContext().getResources().getColor(R.color.course9);
      } else if (courseArrayLength >= 10 && x.getCourse()
          .getName()
          .equals(courseList[9].getName())) {
        color = getContext().getResources().getColor(R.color.course10);
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
              TextView tv = new TextView(getContext());
              tv.setTextColor(getContext().getResources().getColor(R.color.white));
              tv.setText(tempCourse.getName());
              tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                  getContext().getResources().getDimension(R.dimen.courseSize));
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
              final MaterialDialog.Builder dialog = new MaterialDialog.Builder(getContext());
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
    layout = (RelativeLayout) rowView.findViewById(x);
    view = layout.findViewById(y);
  }
}
