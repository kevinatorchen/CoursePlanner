package com.example.myothiha09.coursehelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.layout_helper.CustomFontLight;
import com.example.myothiha09.coursehelper.layout_helper.CustomFontMedium;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.CourseRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/25/2017.
 */

public class CourseRecyclerViewAdapter
    extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {
  List<CourseRequest> list;
  ItemClickedListener listener;

  public CourseRecyclerViewAdapter(List<CourseRequest> list) {
    this.list = list;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_item_added_course, parent, false);

    return new ViewHolder(itemView);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Course current = list.get(position).getCourse();
    holder.courseName.setText(current.getName());
    holder.creditHour.setText("Credit Hour: " + current.getCreditHour());
    holder.professorList.setText(current.getProfessors().toString());
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void setListener(ItemClickedListener listener) {
    this.listener = listener;
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.courseName) CustomFontMedium courseName;
    @BindView(R.id.creditHour) CustomFontLight creditHour;
    @BindView(R.id.professorList) CustomFontLight professorList;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    @OnClick(R.id.editCourse) void onEditCourse() {
      int position = getAdapterPosition();
      listener.editCourse(position);
    }

    @OnClick(R.id.deleteCourse) void onDeleteCourse() {
      int position = getAdapterPosition();
      listener.deleteCourse(position);
    }
  }
}
