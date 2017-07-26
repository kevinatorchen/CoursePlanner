package com.example.myothiha09.coursehelper.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.CourseRecyclerViewAdapter;
import com.example.myothiha09.coursehelper.adapter.ItemClickedListener;
import com.example.myothiha09.coursehelper.controller.Boast;
import com.example.myothiha09.coursehelper.dialog.ClassSearcherDialog;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.CourseRequest;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/25/2017.
 */

public class AddClassFragment extends Fragment {
  public static CourseRecyclerViewAdapter adapter;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  private RecyclerView.LayoutManager layoutManager;
  private Student student;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_add_class, container, false);
    ButterKnife.bind(this, view);
    student = Model.student;
    initRecycler();
    return view;
  }

  private void initRecycler() {
    recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    adapter = new CourseRecyclerViewAdapter(Model.student.getCoursesList());
    adapter.setListener(new ItemClickedListener() {
      @Override public void courseChosen(Course course) {

      }

      @Override public void deleteCourse(final int position) {
        final List<CourseRequest> courseRequests = Model.student.getCourseRequests();
        new MaterialDialog.Builder(getContext()).title("Are you sure?")
            .content(courseRequests.get(position).getCourse().getName() + " will be deleted.")
            .positiveText("Confirm")
            .onPositive(new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                courseRequests.remove(position);
              }
            })
            .negativeText("Cancel")
            .show();
      }

      @Override public void editCourse(int position) {

      }
    });
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
  }

  public void showCategoryChooser() {
    final List<String> stringList = new ArrayList<>();

    stringList.addAll(Model.CATEGORY);
    new MaterialDialog.Builder(getContext()).title("Category Chooser")
        .items(stringList)
        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
          @Override public boolean onSelection(MaterialDialog dialog, View view, int which,
              CharSequence text) {
            showClassChooser(stringList.get(which));
            return true;
          }
        })
        .positiveText("Next")
        .neutralText("Search for Class")
        .onNeutral(new MaterialDialog.SingleButtonCallback() {
          @Override
          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            dialog.dismiss();
            initClassSearcher();
          }
        })
        .negativeText("Cancel")
        .show();
  }

  private void initClassSearcher() {
    ClassSearcherDialog dialog = new ClassSearcherDialog(getContext());
    dialog.show();
  }

  private void showClassChooser(String category) {

    final List<Course> courseList = new ArrayList<>();
    for (Course x : Model.list.get(category)) {
      if (!student.getCoursesList().contains(x)) {
        courseList.add(x);
      }
    }
    new MaterialDialog.Builder(getContext()).title("Class Chooser")
        .items(courseList)
        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
          @Override public boolean onSelection(MaterialDialog dialog, View view, int which,
              CharSequence text) {
            if (!student.getCoursesList().contains(courseList.get(which))) {
              Course selectectedCourse = courseList.get(which);
              student.addCourse(selectectedCourse);
              student.addCourseRequest(new CourseRequest(selectectedCourse, null));
              adapter.notifyDataSetChanged();
            }
            return true;
          }
        })
        .positiveText("Add Class")
        .negativeText("Cancel")
        .neutralText("Choose Professor")
        .onNeutral(new MaterialDialog.SingleButtonCallback() {
          @Override
          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            if (!courseList.isEmpty() && !student.getCoursesList()
                .contains(courseList.get(dialog.getSelectedIndex()))) {
              showProfessorChooser(courseList.get(dialog.getSelectedIndex()));
            }
          }
        })
        .show();
  }

  private void showProfessorChooser(final Course course) {
    Integer[] preSelected = new Integer[course.getProfessors().size()];
    for (int i = 0; i < course.getProfessors().size(); i++) {
      preSelected[i] = i;
    }
    final List<String> selectedProfList = new ArrayList<>();
    new MaterialDialog.Builder(getContext()).title("Class Chooser")
        .items(course.getProfessors())
        .itemsCallbackMultiChoice(preSelected, new MaterialDialog.ListCallbackMultiChoice() {
          @Override
          public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
            if (which.length == 0) {
              Boast.makeText(getContext(),
                  "Course was not added because you did not select a professor", Toast.LENGTH_LONG)
                  .show();
              return false;
            }
            for (int x : which) {
              selectedProfList.add(course.getProfessors().get(x));
            }
            student.addCourse(course);
            student.addCourseRequest(new CourseRequest(course,
                selectedProfList.toArray(new String[selectedProfList.size()])));
            adapter.notifyDataSetChanged();
            return true;
          }
        })
        .positiveText("Add Class")
        .negativeText("Cancel")
        .show();
  }

  @OnClick(R.id.addClass) void onAddClassClicked() {
    if (student.getCoursesList().size() >= 10) {
      Boast.makeText(getContext(), "You cannot have more than 10 classes.", Toast.LENGTH_LONG)
          .show();
    } else {
      showCategoryChooser();
    }
  }

  @OnClick(R.id.addActivity) void onAddActivityClicked() {
    Boast.makeText(getContext(), "Clicked Add Activity.", Toast.LENGTH_SHORT).show();
  }
}



