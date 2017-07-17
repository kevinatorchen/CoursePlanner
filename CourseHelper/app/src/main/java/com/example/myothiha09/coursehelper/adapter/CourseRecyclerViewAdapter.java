package com.example.myothiha09.coursehelper.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Course;

import java.util.ArrayList;

/**
 * Created by Myo on 5/25/2017.
 */

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {
    ArrayList<Course> list;

    public CourseRecyclerViewAdapter(ArrayList<Course> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseRecyclerViewAdapter.ViewHolder holder, int position) {
        Course current = list.get(position);
        holder.courseLabel.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public static TextView courseLabel;
        public ViewHolder(View rowView) {
            super(rowView);
            courseLabel = (TextView) rowView.findViewById(R.id.courseName);
        }
    }

}
