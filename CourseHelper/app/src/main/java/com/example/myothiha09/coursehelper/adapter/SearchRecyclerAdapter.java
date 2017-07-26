package com.example.myothiha09.coursehelper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
  Created by Myo on 6/13/2017.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>
    implements Filterable {
  private final Context context;
  ItemClickedListener listener;
  private ArrayList<Course> mOriginalValues;
  private ArrayList<Course> mDisplayedValues;

  public SearchRecyclerAdapter(Context context) {
    this.context = context;
    this.mOriginalValues = new ArrayList<>();
    this.mDisplayedValues = new ArrayList<>();
  }

  @Override
  public SearchRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView;
    itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_item_class_search, parent, false);
    return new ViewHolder(itemView);
  }

  public void setListener(ItemClickedListener listener) {
    this.listener = listener;
  }

  @Override public void onBindViewHolder(SearchRecyclerAdapter.ViewHolder holder, int position) {
    Course course = mDisplayedValues.get(position);
    holder.courseName.setText(course.getName());
  }

  @Override public int getItemCount() {
    return mDisplayedValues.size();
  }

  /**
   * Do the filtering based on user's search input
   *
   * @return the filtered result
   */
  public Filter getFilter() {
    return new Filter() {

      @SuppressWarnings("unchecked") @Override
      protected void publishResults(CharSequence constraint, FilterResults results) {

        mDisplayedValues = (ArrayList<Course>) results.values; // has the filtered values
        //Log.e("List", mDisplayedValues+"");
        Collections.sort(mDisplayedValues);
        //Log.e("List", mDisplayedValues+"");
        notifyDataSetChanged();  // notifies the data with new filtered values
      }

      @Override protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results =
            new FilterResults();        // Holds the results of a filtering operation in values
        ArrayList<Course> FilteredArrList = new ArrayList<>();

        if (mOriginalValues == null) {
          mOriginalValues =
              new ArrayList<>(mDisplayedValues); // saves the original data in mOriginalValues
        }

        /*
         *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
         *  else does the Filtering and returns FilteredArrList(Filtered)
         */
        if (constraint == null || constraint.length() == 0) {

          // set the Original result to return
          results.count = mOriginalValues.size();
          results.values = mOriginalValues;
        } else {
          constraint = constraint.toString().toLowerCase();
          for (int i = 0; i < mOriginalValues.size(); i++) {
            String data = mOriginalValues.get(i).getName();
            if (data.toLowerCase().contains(constraint.toString())) {
              FilteredArrList.add(mOriginalValues.get(i));
            }
          }
          // set the Filtered result to return
          results.count = FilteredArrList.size();
          results.values = FilteredArrList;
        }
        return results;
      }
    };
  }

  /**
   * Populating the lists with real data
   */
  public void populateWithData() {
    mOriginalValues.clear();
    createDisplayedValue(Model.ALL_COURSE_CATEGORY_VALUES);
  }

  private void createDisplayedValue(ArrayList<Course> list) {
    for (Course course : list) {
      if (!Model.student.takeThisCourse(course)) mOriginalValues.add(course);
    }
    mDisplayedValues = mOriginalValues;
    Collections.sort(mDisplayedValues, new Comparator<Course>() {
      @Override public int compare(Course o1, Course o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });
    notifyDataSetChanged();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemName) TextView courseName;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.searchRowItemContainer) void onClick() {
      int position = getAdapterPosition();
      List<Course> searchItemList = mDisplayedValues;
      listener.courseChosen(searchItemList.get(position));
    }
  }
}
