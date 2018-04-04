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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.CourseRecyclerViewAdapter;
import com.example.myothiha09.coursehelper.adapter.ItemClickedListener;
import com.example.myothiha09.coursehelper.dialog.ClassSearcherDialog;
import com.example.myothiha09.coursehelper.dialog.CustomActivityDialog;
import com.example.myothiha09.coursehelper.model.Commitment;
import com.example.myothiha09.coursehelper.model.CommitmentRequest;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.CourseSection;
import com.example.myothiha09.coursehelper.model.MeetingTime;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.SingleCourse;
import com.example.myothiha09.coursehelper.model.Student;
import com.example.myothiha09.coursehelper.model.StudentActivity;
import com.example.myothiha09.coursehelper.model.Time;
import com.example.myothiha09.coursehelper.rest.RestClient;
import com.example.myothiha09.coursehelper.temp.TempCourse;
import com.example.myothiha09.coursehelper.temp.TempMajor;
import com.example.myothiha09.coursehelper.temp.TempPeriod;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/25/2017.
 */
public class AddClassFragment extends Fragment {
    public static CourseRecyclerViewAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.floatingMenu)
    FloatingActionMenu fabMenu;
    @BindColor(R.color.content_font_color)
    int contentColor;
    private RecyclerView.LayoutManager layoutManager;
    private Student student;

    @Nullable
    @Override
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
        adapter = new CourseRecyclerViewAdapter(getContext(), student.getCommitmentRequests());
        adapter.setListener(new ItemClickedListener<Commitment>() {
            final List<CommitmentRequest> commitmentRequests = Model.student.getCommitmentRequests();

            @Override
            public void itemChosen(Commitment commitment) {
                if (commitment instanceof Course) {

                    Course course = (Course) commitment;
                    String content = course.getName()
                            + " - "
                            + course.getLongName()
                            + "\n"
                            + "Credit: "
                            + course.getCreditHour()
                            + "\n"
                            + "All Professors: "
                            + course.getProfessors();
                    new MaterialDialog.Builder(getContext()).title("Course Details")
                            .contentColor(contentColor)
                            .content(content)
                            .positiveText("OK")
                            .show();
                } else {
                    new MaterialDialog.Builder(getContext()).title("Activity Details")
                            .contentColor(contentColor)
                            .content(commitment.getName())
                            .positiveText("OK")
                            .show();
                }
            }

            @Override
            public void delItem(final int position) {
                new MaterialDialog.Builder(getContext()).title("Are you sure?")
                        .content(
                                commitmentRequests.get(position).getCommitment().getName() + " will be deleted.")
                        .positiveText("Confirm")
                        .contentColor(contentColor)
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

            @Override
            public void editItem(int position) {
                editProfessor(commitmentRequests.get(position).getCommitment(), position);
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void showCategoryChooser() {
        final List<TempMajor> stringList = new ArrayList<>();
        RestClient.getInstance().getService().listMajors().enqueue(new Callback<List<TempMajor>>() {
            @Override
            public void onResponse(Call<List<TempMajor>> call, Response<List<TempMajor>> response) {
                stringList.addAll(response.body());
                new MaterialDialog.Builder(getContext()).title("Category Chooser")
                        .items(stringList)
                        .contentColor(contentColor)
                        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which,
                                                       CharSequence text) {
                                showClassChooser(stringList.get(which).ident);
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

            @Override
            public void onFailure(Call<List<TempMajor>> call, Throwable t) {

            }
        });
        //stringList.addAll(Model.ALL_COURSE_CATEGORY);

//    stringList.addAll(Model.ALL_COURSE_CATEGORY);
//    new MaterialDialog.Builder(getContext()).title("Category Chooser")
//        .items(stringList)
//        .contentColor(contentColor)
//        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
//          @Override public boolean onSelection(MaterialDialog dialog, View view, int which,
//              CharSequence text) {
//            showClassChooser(stringList.get(which));
//            return true;
//          }
//        })
//        .positiveText("Next")
//        .neutralText("Search for Class")
//        .onNeutral(new MaterialDialog.SingleButtonCallback() {
//          @Override
//          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//            dialog.dismiss();
//            initClassSearcher();
//          }
//        })
//        .negativeText("Cancel")
//        .show();
    }

    private void initClassSearcher() {
        ClassSearcherDialog dialog = new ClassSearcherDialog(getContext());
        dialog.setListener(new ItemClickedListener<Commitment>() {
            @Override
            public void itemChosen(Commitment commitment) {
                showProfessorChooser((Course) commitment);
            }

            @Override
            public void delItem(int position) {

            }

            @Override
            public void editItem(int position) {

            }
        });
        dialog.show();
    }

    private void showClassChooser(final String category) {
        final List<TempCourse> courseList = new ArrayList<>();
        RestClient.getInstance().getService().listCoursesForMajor(category).enqueue(new Callback<List<TempCourse>>() {
            @Override
            public void onResponse(Call<List<TempCourse>> call, Response<List<TempCourse>> response) {
                courseList.addAll(response.body());
                new MaterialDialog.Builder(getContext()).title("Course Chooser ")
                        .items(courseList)
                        .contentColor(contentColor)
                        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which,
                                                       CharSequence text) {
                                if (courseList.isEmpty()) {
                                    Toast.makeText(getContext(), "No course was added since nothing was selected.",
                                            Toast.LENGTH_SHORT).show();
                                    return false;
                                }
                                final TempCourse selected = courseList.get(which);
                                RestClient.getInstance().getService().listSectionsForCourse(category, selected.ident).enqueue(new Callback<List<TempPeriod>>() {
                                    @Override
                                    public void onResponse(Call<List<TempPeriod>> call, Response<List<TempPeriod>> response) {
                                        String name = category + " " + selected.ident;
                                        List<TempPeriod> tempList = response.body();

                                        CourseSection[] sections = new CourseSection[tempList.size()];
                                        Course course = new SingleCourse(name, sections);
                                        for (int i = 0; i < sections.length; i++) {
                                            TempPeriod t = tempList.get(i);
                                            course.setCreditHour(t.credits);
                                            //TODO: progress bar and disable click when making calls to server
                                            //TODO: handle allowing to add duplicate classes
                                            //TODO: lag when creating a schedule (DEAL with loading bar and multithreading in the future)
                                            //TODO: app crashes will null (data not filled in???)g
                                            //TODO: deal with double section courses T_T will be so painful
                                            //TODO: deal with weird classes that is causing the app to crash (some classes don't have meeting times? very very rare cases) not sure what causes the bug
                                            MeetingTime[] times = new MeetingTime[t.timeslots.size()];
                                            for (int l = 0; l < t.timeslots.size(); l++) {
                                                Time startTime = new Time(t.timeslots.get(l).start_time / 60, t.timeslots.get(l).start_time % 60);
                                                Time endTime = new Time(t.timeslots.get(l).end_time / 60, t.timeslots.get(l).end_time % 60);
                                                int day = 1;
                                                if (t.timeslots.get(l).day == 'M') day = 1;
                                                if (t.timeslots.get(l).day == 'T') day = 2;
                                                if (t.timeslots.get(l).day == 'W') day = 4;
                                                if (t.timeslots.get(l).day == 'R') day = 8;
                                                if (t.timeslots.get(l).day == 'F') day = 16;
                                                times[l] = new MeetingTime(day, startTime, endTime);
                                                times[l].setLocation(t.timeslots.get(l).location);
                                            }
                                            CourseSection tempSection = new CourseSection(course, t.ident, times,
                                                    t.call_number, t.instructor.toString(), "TEMP");
                                            sections[i] = tempSection;
                                        }


                                        student.addCommitmentRequest(new CommitmentRequest(course,
                                                course.getProfessors().toArray(new String[course.getProfessors().size()])));
                                        adapter.notifyItemInserted(student.getCommitmentRequests().size());
                                    }

                                    @Override
                                    public void onFailure(Call<List<TempPeriod>> call, Throwable t) {

                                    }
                                });

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
                                    //showProfessorChooser(courseList.get(dialog.getSelectedIndex()));
                                }
                            }
                        })
                        .show();
            }

            @Override
            public void onFailure(Call<List<TempCourse>> call, Throwable t) {

            }
        });

//    for (Course x : Model.ALL_COURSE_DATA.get(category)) {
//      if (!(student.haveThisCommitment(x))) {
//        courseList.add(x);
//      }
//    }
//    new MaterialDialog.Builder(getContext()).title("Course Chooser ")
//        .items(courseList)
//        .contentColor(contentColor)
//        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
//          @Override public boolean onSelection(MaterialDialog dialog, View view, int which,
//              CharSequence text) {
//            if (courseList.isEmpty()) {
//              Toast.makeText(getContext(), "No course was added since nothing was selected.",
//                  Toast.LENGTH_SHORT).show();
//              return false;
//            }
//            Course selected = courseList.get(which);
//            student.addCommitmentRequest(new CommitmentRequest(selected,
//                selected.getProfessors().toArray(new String[selected.getProfessors().size()])));
//            adapter.notifyItemInserted(student.getCommitmentRequests().size());
//            return true;
//          }
//        })
//        .positiveText("Add Class")
//        .negativeText("Cancel")
//        .neutralText("Choose Professor")
//        .onNeutral(new MaterialDialog.SingleButtonCallback() {
//          @Override
//          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//            if (!courseList.isEmpty()) {
//              showProfessorChooser(courseList.get(dialog.getSelectedIndex()));
//            }
//          }
//        })
//        .show();
    }

    private void editProfessor(final Commitment commitment, final int position) {
        if (commitment instanceof Course) {
            final Course course = (Course) commitment;
            String[] selectedProf = student.getCommitmentRequests().get(position).getProf();
            ArrayList<String> professorList = course.getProfessors();
            Integer[] preSelected = new Integer[selectedProf.length];
            for (int i = 0; i < preSelected.length; i++) {
                preSelected[i] = professorList.indexOf(selectedProf[i]);
            }
            final List<String> selectedProfList = new ArrayList<>();
            new MaterialDialog.Builder(getContext()).title("Professor Chooser")
                    .items(professorList)
                    .contentColor(contentColor)
                    .itemsCallbackMultiChoice(preSelected, new MaterialDialog.ListCallbackMultiChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, Integer[] which,
                                                   CharSequence[] text) {
                            if (which.length == 0) {
                                Toast.makeText(getContext(),
                                        "No change was made because you did not select a professor", Toast.LENGTH_LONG)
                                        .show();
                                return false;
                            }
                            for (int x : which) {
                                selectedProfList.add(course.getProfessors().get(x));
                            }
                            student.editCommitmentRequest(position, new CommitmentRequest(course,
                                    selectedProfList.toArray(new String[selectedProfList.size()])));
                            adapter.notifyItemChanged(position);
                            return true;
                        }
                    })
                    .positiveText("Save Changes")
                    .negativeText("Cancel")
                    .show();
        } else {

            StudentActivity studentActivity = (StudentActivity) commitment;
            Toast.makeText(getContext(),
                    studentActivity.getName() + " is an activity. So, it can't be edited.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void showProfessorChooser(final Course course) {
        ArrayList<String> professorList = course.getProfessors();
        Integer[] preSelected = new Integer[professorList.size()];
        for (int i = 0; i < preSelected.length; i++) {
            preSelected[i] = i;
        }
        final List<String> selectedProfList = new ArrayList<>();
        new MaterialDialog.Builder(getContext()).title("Professor Chooser")
                .items(professorList)
                .contentColor(contentColor)
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
                        student.addCommitmentRequest(new CommitmentRequest(course,
                                selectedProfList.toArray(new String[selectedProfList.size()])));
                        adapter.notifyItemInserted(student.getCommitmentRequests().size());
                        return true;
                    }
                })
                .positiveText("Add Class")
                .negativeText("Cancel")
                .show();
    }

    @OnClick(R.id.addClass)
    void onAddClassClicked() {
        fabMenu.close(true);
        if (student.getCommitmentRequests().size() >= 8) {
            Toast.makeText(getContext(), "You cannot have more than 8 classes.", Toast.LENGTH_LONG)
                    .show();
        } else {
            showCategoryChooser();
            //initClassSearcher();
        }
    }

    @OnClick(R.id.addActivity)
    void onAddActivityClicked() {
        fabMenu.close(true);
        initCustomActivity();
    }

    private void showActivityChooser() {
        final List<StudentActivity> activityList = new ArrayList<>();
        for (StudentActivity x : Model.ALL_STUDENT_ACTIVITY) {
            if (!(student.haveThisCommitment(x))) {
                activityList.add(x);
            }
        }
        new MaterialDialog.Builder(getContext()).title("Activity Chooser")
                .items(activityList)
                .contentColor(contentColor)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which,
                                               CharSequence text) {
                        StudentActivity selected = activityList.get(which);
                        student.addCommitmentRequest(new CommitmentRequest(selected, null));
                        adapter.notifyItemInserted(student.getCommitmentRequests().size());
                        return true;
                    }
                })
                .positiveText("Add Activity")
                .negativeText("Cancel")
                .show();
    }

    private void initCustomActivity() {
        CustomActivityDialog dialog = new CustomActivityDialog(getContext());
        dialog.setListener(new ItemClickedListener<StudentActivity>() {
            @Override
            public void itemChosen(StudentActivity activity) {
                student.addCommitmentRequest(new CommitmentRequest(activity, null));
                adapter.notifyItemInserted(student.getCommitmentRequests().size());
            }

            @Override
            public void delItem(int position) {

            }

            @Override
            public void editItem(int position) {

            }
        });
        dialog.show();
    }
}



