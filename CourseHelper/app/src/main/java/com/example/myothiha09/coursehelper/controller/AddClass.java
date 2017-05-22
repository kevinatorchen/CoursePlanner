package com.example.myothiha09.coursehelper.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Myo on 5/17/2017.
 */

public class AddClass extends ListFragment {

    public static CourseListViewAdapter adapter;
    private Student student;
    Set<Course> courseSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_class_screen, container, false);
        Toast.makeText(getActivity(), "Click the course to delete it", Toast.LENGTH_LONG).show();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        student = Model.student;
        courseSet = student.getCoursesList();
        ArrayList<Course> courseArrayList = new ArrayList<>();
        courseArrayList.addAll(courseSet);
        adapter = new CourseListViewAdapter(getActivity(), courseArrayList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);
        final String courseName = ((Course)l.getItemAtPosition(position)).getName();
        new MaterialDialog.Builder(getActivity())
                .title("Course: " + courseName + " Selected")
                .content("Do you want to delete the course?")
                .positiveText("Yes").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                Set<Course> toBeRemoved = new HashSet<Course>();
                for(Course x: courseSet) {
                    if (x.getName().equals(courseName)) {
                        toBeRemoved.add(x);
                    }
                }
                for (Course x: toBeRemoved) {
                    adapter.remove(x);
                    courseSet.remove(x);
                }
            }
        })
                .negativeText("No").onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        })
                .show();

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
