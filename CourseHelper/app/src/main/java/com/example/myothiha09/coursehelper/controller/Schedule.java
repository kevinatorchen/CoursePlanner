package com.example.myothiha09.coursehelper.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.MeetingTime;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Section;
import com.example.myothiha09.coursehelper.model.Student;
import com.example.myothiha09.coursehelper.model.Time;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Myo on 5/18/2017.
 */

public class Schedule extends ListFragment {



    ScheduleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_schedule, container, false);
        Toast.makeText(getActivity(), "Click the course to view details", Toast.LENGTH_LONG).show();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Student student = Model.student;
        Set<Course> courselist = student.getCoursesList();
        CoursePlanner.planCourses(courselist.toArray(new Course[courselist.size()]));
        ArrayList<ArrayList<Section>> al = new ArrayList<>();
        for(ArrayList<Section> section: CoursePlanner.scheduleList) {
            al.add(section);
        }
        adapter = new ScheduleAdapter(getContext(), al);
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
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
    NOTE: not accounting for sat sunday classes if they exists.

    BUGS; drawing more sections than necessary!!!!!! FIXED
     */
}
