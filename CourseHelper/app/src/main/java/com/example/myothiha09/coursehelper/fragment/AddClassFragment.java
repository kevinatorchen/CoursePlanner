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
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.CourseRecyclerViewAdapter;
import com.example.myothiha09.coursehelper.adapter.ItemClickedListener;
import com.example.myothiha09.coursehelper.dialog.ClassSearcherDialog;
import com.example.myothiha09.coursehelper.model.CommitmentRequest;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Student;
import com.github.clans.fab.FloatingActionMenu;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/25/2017.
 */
//TODO: make use of string resources for everything
public class AddClassFragment extends Fragment {
  public static CourseRecyclerViewAdapter adapter;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  @BindView(R.id.floatingMenu) FloatingActionMenu fabMenu;
  @BindColor(R.color.content_font_color) int contentColor;
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
    adapter = new CourseRecyclerViewAdapter(getContext(), student.getCourseRequests());
    adapter.setListener(new ItemClickedListener() {
      final List<CommitmentRequest> commitmentRequests = Model.student.getCourseRequests();

      @Override public void courseChosen(Course course) {

      }

      @Override public void deleteCourse(final int position) {
        new MaterialDialog.Builder(getContext()).title("Are you sure?")
            .content(commitmentRequests.get(position).getCourse().getName() + " will be deleted.")
            .positiveText("Confirm").contentColor(contentColor)
            .onPositive(new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                commitmentRequests.remove(position);
                adapter.notifyItemRemoved(position);
              }
            })
            .negativeText("Cancel")
            .show();
      }

      @Override public void editCourse(int position) {
        editProfessor(commitmentRequests.get(position).getCourse(), position);
      }
    });
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
  }

  private void enableProfEditing() {
    Toast.makeText(getContext(), "Edit Course Clicked", Toast.LENGTH_SHORT).show();
  }

  public void showCategoryChooser() {
    final List<String> stringList = new ArrayList<>();

    stringList.addAll(Model.ALL_COURSE_CATEGORY);
    new MaterialDialog.Builder(getContext()).title("Category Chooser")
        .items(stringList).contentColor(contentColor)
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
    dialog.setListener(new ItemClickedListener() {
      @Override public void courseChosen(Course course) {
        showProfessorChooser(course);
      }

      @Override public void deleteCourse(int position) {

      }

      @Override public void editCourse(int position) {

      }
    });
    dialog.show();
  }

  private void showClassChooser(String category) {
    final List<Course> courseList = new ArrayList<>();
    for (Course x : Model.ALL_COURSE_DATA.get(category)) {
      if (!(student.takeThisCourse(x))) {
        courseList.add(x);
      }
    }
    new MaterialDialog.Builder(getContext()).title("Class Chooser")
        .items(courseList)
        .contentColor(contentColor)
        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
          @Override public boolean onSelection(MaterialDialog dialog, View view, int which,
              CharSequence text) {
            Course selected = courseList.get(which);
            student.addCourseRequest(new CommitmentRequest(selected,
                selected.getProfessors().toArray(new String[selected.getProfessors().size()])));
            adapter.notifyItemInserted(student.getCourseRequests().size());
            return true;
          }
        })
        .positiveText("Add Class")
        .negativeText("Cancel")
        .neutralText("Choose Professor")
        .onNeutral(new MaterialDialog.SingleButtonCallback() {
          @Override
          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            if (!courseList.isEmpty()) {
              showProfessorChooser(courseList.get(dialog.getSelectedIndex()));
            }
          }
        })
        .show();
  }

  private void editProfessor(final Course course, final int position) {
    String[] selectedProf = student.getCourseRequests().get(position).getProf();
    ArrayList<String> professorList = course.getProfessors();
    Integer[] preSelected = new Integer[selectedProf.length];
    for (int i = 0; i < preSelected.length; i++) {
      preSelected[i] = professorList.indexOf(selectedProf[i]);
    }
    final List<String> selectedProfList = new ArrayList<>();
    new MaterialDialog.Builder(getContext()).title("Professor Chooser")
        .items(professorList).contentColor(contentColor)
        .itemsCallbackMultiChoice(preSelected, new MaterialDialog.ListCallbackMultiChoice() {
          @Override
          public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
            if (which.length == 0) {
              Toast.makeText(getContext(),
                  "No change was made because you did not select a professor", Toast.LENGTH_LONG)
                  .show();
              return false;
            }
            for (int x : which) {
              selectedProfList.add(course.getProfessors().get(x));
            }
            student.editCourseRequest(position, new CommitmentRequest(course,
                selectedProfList.toArray(new String[selectedProfList.size()])));
            adapter.notifyItemChanged(position);
            return true;
          }
        })
        .positiveText("Save Changes")
        .negativeText("Cancel")
        .show();
  }

  private void showProfessorChooser(final Course course) {
    ArrayList<String> professorList = course.getProfessors();
    Integer[] preSelected = new Integer[professorList.size()];
    for (int i = 0; i < preSelected.length; i++) {
      preSelected[i] = i;
    }
    final List<String> selectedProfList = new ArrayList<>();
    new MaterialDialog.Builder(getContext()).title("Professor Chooser")
        .items(professorList).contentColor(contentColor)
        .itemsCallbackMultiChoice(preSelected, new MaterialDialog.ListCallbackMultiChoice() {
          @Override
          public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
            if (which.length == 0) {
              Toast.makeText(getContext(),
                  "Course was not added because you did not select a professor", Toast.LENGTH_LONG)
                  .show();
              return false;
            }
            for (int x : which) {
              selectedProfList.add(course.getProfessors().get(x));
            }
            student.addCourseRequest(new CommitmentRequest(course,
                selectedProfList.toArray(new String[selectedProfList.size()])));
            adapter.notifyItemInserted(student.getCourseRequests().size());
            return true;
          }
        })
        .positiveText("Add Class")
        .negativeText("Cancel")
        .show();
  }

  @OnClick(R.id.addClass) void onAddClassClicked() {
    fabMenu.close(true);
    if (student.getCourseRequests().size() >= 10) {
      Toast.makeText(getContext(), "You cannot have more than 10 classes.", Toast.LENGTH_LONG)
          .show();
    } else {
      showCategoryChooser();
    }
  }

  @OnClick(R.id.addActivity) void onAddActivityClicked() {
    fabMenu.close(true);
    Toast.makeText(getContext(), "Clicked Add Activity.", Toast.LENGTH_SHORT).show();
  }
}



