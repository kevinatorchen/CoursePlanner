package com.example.myothiha09.coursehelper.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Course;

import java.util.ArrayList;

/**
 * Created by Myo on 5/18/2017.
 */

public class CourseListViewAdapter extends ArrayAdapter<Course>{
    private Context context;
    private ArrayList<Course> list;
    public CourseListViewAdapter(Context context, ArrayList<Course> list) {
        super(context, -1, list);
        this.context = context;
        this.list = list;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        if (convertView == null) {
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_view, parent, false);
        } else {
            rowView = convertView;
        }
        TextView courseName = (TextView) rowView.findViewById(R.id.courseName);
        courseName.setText(list.get(position).getName());
        return rowView;
    }

    public class ViewHolder {
    }
}
