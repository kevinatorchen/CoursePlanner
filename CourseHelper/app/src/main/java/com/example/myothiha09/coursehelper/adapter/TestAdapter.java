package com.example.myothiha09.coursehelper.adapter;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.CourseRequest;
import com.example.myothiha09.coursehelper.model.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Myo on 7/18/2017.
 */

public class TestAdapter extends RecyclerView.Adapter {

  private static final int PENDING_REMOVAL_TIMEOUT = 1500; // 3sec

  List<Course> items;
  List<Course> itemsPendingRemoval;
  List<CourseRequest> courseRequests = Model.student.getCourseRequests();
  boolean undoOn; // is undo on, you can turn it on from the toolbar menu
  HashMap<Course, Runnable> pendingRunnables = new HashMap<>();
  private Handler handler = new Handler(); // hanlder for running delayed runnables
      // map of items to pending runnables, so we can cancel a removal if need be

  public TestAdapter(ArrayList<Course> courseList) {
    items = courseList;
    itemsPendingRemoval = new ArrayList<>();
    undoOn = true;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new TestViewHolder(parent);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    TestViewHolder viewHolder = (TestViewHolder) holder;
    final Course item = items.get(position);

    if (itemsPendingRemoval.contains(item)) {
      // we need to show the "undo" state of the row
      viewHolder.itemView.setBackgroundColor(Color.RED);
      viewHolder.titleTextView.setVisibility(View.GONE);
      viewHolder.undoButton.setVisibility(View.VISIBLE);
      viewHolder.undoButton.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          // user wants to undo the removal, let's cancel the pending task
          Runnable pendingRemovalRunnable = pendingRunnables.get(item);
          pendingRunnables.remove(item);
          if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
          itemsPendingRemoval.remove(item);
          // this will rebind the row in "normal" state
          notifyItemChanged(items.indexOf(item));
        }
      });
    } else {
      // we need to show the "normal" state
      viewHolder.itemView.setBackgroundColor(Color.WHITE);
      viewHolder.titleTextView.setVisibility(View.VISIBLE);
      viewHolder.titleTextView.setText(item.getName());
      viewHolder.undoButton.setVisibility(View.GONE);
      viewHolder.undoButton.setOnClickListener(null);
    }
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public boolean isUndoOn() {
    return undoOn;
  }

  public void setUndoOn(boolean undoOn) {
    this.undoOn = undoOn;
  }

  public void pendingRemoval(int position) {
    final Course item = items.get(position);
    if (!itemsPendingRemoval.contains(item)) {
      itemsPendingRemoval.add(item);
      // this will redraw row in "undo" state
      notifyItemChanged(position);
      // let's create, store and post a runnable to remove the item
      Runnable pendingRemovalRunnable = new Runnable() {
        @Override public void run() {
          remove(items.indexOf(item));
        }
      };
      handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
      pendingRunnables.put(item, pendingRemovalRunnable);
    }
  }

  public void remove(int position) {
    Course item = items.get(position);
    if (itemsPendingRemoval.contains(item)) {
      itemsPendingRemoval.remove(item);
    }
    if (items.contains(item)) {
      items.remove(position);
      courseRequests.remove(position);
      notifyItemRemoved(position);
    }
  }

  public boolean isPendingRemoval(int position) {
    Course item = items.get(position);
    return itemsPendingRemoval.contains(item);
  }

  /**
   * ViewHolder capable of presenting two states: "normal" and "undo" state.
   */
  class TestViewHolder extends RecyclerView.ViewHolder {

    TextView titleTextView;
    Button undoButton;

    public TestViewHolder(ViewGroup parent) {
      super(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.course_list_view, parent, false));
      titleTextView = (TextView) itemView.findViewById(R.id.courseName);
      undoButton = (Button) itemView.findViewById(R.id.undo_button);
    }
  }
}
