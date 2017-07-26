package com.example.myothiha09.coursehelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.VisualScheduleAdapter;
import com.example.myothiha09.coursehelper.controller.Boast;
import com.example.myothiha09.coursehelper.controller.CoursePlanner;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Schedule;
import java.util.ArrayList;

/**
 * Created by Myo on 5/18/2017.
 */

public class ScheduleVisualFragment extends ListFragment {

  VisualScheduleAdapter adapter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.list_schedule, container, false);
    Boast.makeText(getActivity(), "Click the course to view details", Toast.LENGTH_LONG).show();
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    CoursePlanner.planCourses(Model.student.getCourseRequestsAsArray());
    ArrayList<Schedule> al = new ArrayList<>();
    for (Schedule section : CoursePlanner.scheduleList) {
      al.add(section);
    }
    adapter = new VisualScheduleAdapter(getContext(), al);
    setListAdapter(adapter);
  }

  @Override public void onResume() {
    super.onResume();
    adapter.notifyDataSetChanged();
  }

    /*
    TODO:
    show classes that start at 30min mark DONE!!!
    show more details onClick of the views DONE!!!!!
    shows all the possible schedules instead of one DONE!!!
    changes color for the courses in colors.xml
    changes color theme to GT color theme for nav view header
    ****Proper implementation for persistense for courses.***
    ****Performance issues for ListView***** FIXED??
    CHANGE ICON!!!!!! THE NAV VIEW
    SCHEDULE VISUAL CHANGE???
    Feedback screen.
    NOTE: not accounting for sat sunday classes if they exists.

    BUGS; drawing more sections than necessary!!!!!! FIXED
     */
}
