package com.example.myothiha09.coursehelper.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.ItemClickedListener;
import com.example.myothiha09.coursehelper.adapter.SearchRecyclerAdapter;
import com.example.myothiha09.coursehelper.fragment.AddClassFragment;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * The Custom Dialog that is shown when user click the name field. This allows the user
 * to search for item to add from a list of items or create a new one.
 */
public class ClassSearcherDialog extends AppCompatDialog {

  private final Context context;
  @BindView(R.id.search_bar) EditText searchBar;
  @BindView(R.id.searchRecyclerView) RecyclerView searchRecyclerView;
  @BindView(R.id.dialog_root) LinearLayout dialogRoot;
  @BindDimen(R.dimen.dialog_width) int dialog_width;
  private SearchRecyclerAdapter searchRecyclerAdapter;
  private LinearLayoutManager searchLayoutManager;
  private RecyclerView.SmoothScroller smoothScroller;

  public ClassSearcherDialog(@NonNull Context context) {
    super(context);
    this.context = context;
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.dialog_add_item);
    ButterKnife.bind(this);
    initDialog();
  }

  private void initDialog() {
    dialogRoot.getLayoutParams().width = dialog_width;
    searchLayoutManager = new LinearLayoutManager(context);
    searchRecyclerView.addItemDecoration(
        new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    searchRecyclerView.setLayoutManager(searchLayoutManager);
    searchRecyclerAdapter = new SearchRecyclerAdapter(context);
    searchRecyclerView.setAdapter(searchRecyclerAdapter);
    searchRecyclerAdapter.setListener(new ItemClickedListener() {
      @Override public void courseChosen(Course course) {
        Model.student.addCourse(course);
        AddClassFragment.adapter.notifyDataSetChanged();
        dismiss();
      }

      @Override public void deleteCourse(int position) {}

      @Override public void editCourse(int position) {}
    });
    searchRecyclerAdapter.populateWithData();
  }

  @OnTextChanged(R.id.search_bar) void onTextChanged(CharSequence s) {
    searchRecyclerAdapter.getFilter().filter(s.toString());
  }
}
