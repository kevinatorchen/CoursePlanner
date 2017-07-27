package com.example.myothiha09.coursehelper.adapter;

import android.content.Context;
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
import com.example.myothiha09.coursehelper.model.CommitmentRequest;

import java.util.List;

/**
 * Created by Myo on 5/25/2017.
 */

public class CourseRecyclerViewAdapter
    extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {
  List<CommitmentRequest> list;
  Context context;
  ItemClickedListener listener;

  public CourseRecyclerViewAdapter(Context context, List<CommitmentRequest> list) {
    this.context = context;
    this.list = list;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_item_added_course, parent, false);

    return new ViewHolder(itemView);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    CommitmentRequest current = list.get(position);
    holder.courseName.setText(current.getCourse().getName());
    holder.creditHour.setText("Credit Hour: " + current.getCourse().getCreditHour());
    String prof = "Selected Profs: ";
    for (String x : current.getProf()) {
      prof += x + ", ";
    }
    prof = prof.substring(0, prof.length() - 2);
    holder.professorList.setText(prof);
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
