package com.example.myothiha09.coursehelper.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.AdvancedSortListener;
import com.example.myothiha09.coursehelper.controller.CoursePlanner;
import com.example.myothiha09.coursehelper.dialog.AdvancedSortDialog;
import com.example.myothiha09.coursehelper.dialog.SimpleAdvancedSortDialog;
import com.example.myothiha09.coursehelper.layout_helper.CustomFontLight;
import com.example.myothiha09.coursehelper.layout_helper.CustomFontRegular;
import com.example.myothiha09.coursehelper.model.Commitment;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.CourseSection;
import com.example.myothiha09.coursehelper.model.FewerDaysOfTheWeekComparator;
import com.example.myothiha09.coursehelper.model.GenericComparator;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.NoGapsComparator;
import com.example.myothiha09.coursehelper.model.NoMorningClassesComparator;
import com.example.myothiha09.coursehelper.model.Schedule;
import com.example.myothiha09.coursehelper.model.ScheduleSorter;
import com.example.myothiha09.coursehelper.model.Section;
import com.example.myothiha09.coursehelper.util.AlternativeSelection;
import java.util.List;

/**
 * Created by Myo on 5/22/2017.
 */
//TODO: improve UI for this screen
public class ScheduleOverviewFragment extends Fragment {
  LinearLayout card;
  @BindView(R.id.nested) LinearLayout nestedLayout;
  @BindView(R.id.sorting_spinner) Spinner spinner;
  @BindDimen(R.dimen.padding) int margin;
  @BindColor(R.color.title_font_color) int titleColor;
  @BindColor(R.color.content_font_color) int contentColor;
  @BindDrawable(R.drawable.bg_card) Drawable cardBG;
  int index;
  private AdvancedSortDialog advancedSortDialog;
  private SimpleAdvancedSortDialog simpleAdvancedSortDialog;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_schedule_overview, container, false);
    ButterKnife.bind(this, view);
    CoursePlanner.planCourses(Model.student.getCommitmentRequests());
    List<Schedule> list = CoursePlanner.scheduleList;
    initDialog();
    createSortingList();
    displaySchedules(list);
    return view;
  }

  private void initDialog() {
    if (simpleAdvancedSortDialog == null) {
      simpleAdvancedSortDialog = new SimpleAdvancedSortDialog(getContext());
      simpleAdvancedSortDialog.setListener(new AdvancedSortListener() {
        @Override public void onSortSettingChanged(GenericComparator comparator, List<Schedule> schedule,
            AlternativeSelection alternativeSelection, int maxDrop) {
          sortSchedules(comparator, schedule);
        }
      });
    }
    /*advancedSortDialog = new AdvancedSortDialog(getContext());
    advancedSortDialog.setListener(new AdvancedSortListener() {
      @Override
      public void onSortSettingChanged(GenericComparator comparator, List<Schedule> schedule,
          AlternativeSelection alternativeSelection, int maxDrop) {
        if (alternativeSelection == AlternativeSelection.NONE) {
          sortSchedules(comparator, schedule);
        } else {
          if (alternativeSelection == AlternativeSelection.PROFONLY) {
            CoursePlanner.planAltConservative(Model.student.getCommitmentRequests());
          } else if (alternativeSelection == AlternativeSelection.SOMEALT) {
            CoursePlanner.planAlternatives(Model.student.getCommitmentRequests(), maxDrop);
          } else { // alternativeSelection == AlternativeSelection.ANYALT
            CoursePlanner.planAltFull(Model.student.getCommitmentRequests());
          }
          List<Schedule> list = CoursePlanner.scheduleList;
          sortSchedules(comparator, list);
        }
      }
    });*/
  }

  public void displaySchedules(List<Schedule> list) {
    index = 1;
    for (final Schedule sections : list) {
      if (!sections.getSchedule().isEmpty()) {
        LinearLayout.LayoutParams layoutParams = createBackground();
        TextView tv = createTitle();
        createCoursesInfo(tv, layoutParams, sections);
      }
    }
  }

  @OnClick(R.id.viewScheduleVisually) void viewVisually() {
    ScheduleVisualFragment frag = new ScheduleVisualFragment();
    frag.putExtra(0);
    getActivity().getSupportFragmentManager()
        .beginTransaction()
        .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        .replace(R.id.container, frag)
        .addToBackStack(null)
        .commit();
  }

  private LinearLayout.LayoutParams createBackground() {
    card = new LinearLayout(getContext());
    LinearLayout.LayoutParams layoutParams =
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    layoutParams.bottomMargin = margin;
    card.setPadding(margin, margin, margin, margin);
    card.setBackground(cardBG);
    card.setOrientation(LinearLayout.VERTICAL);
    return layoutParams;
  }

  private TextView createTitle() {
    final TextView tv = new TextView(getContext());
    tv.setText("Schedule " + index++);
    tv.setTextSize(18);
    tv.setPadding(0, 0, 0, margin / 2);
    tv.setTextColor(titleColor);
    card.addView(tv);
    return tv;
  }

  private Spinner createSortingList() {
    ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(getContext(), R.array.sorting_array,
            R.layout.spinner_custom_layout);

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
          sortSchedules(new NoGapsComparator(), CoursePlanner.scheduleList);
        }
        if (position == 1) {
          sortSchedules(new NoMorningClassesComparator(), CoursePlanner.scheduleList);
        }
        if (position == 2) {
          sortSchedules(new FewerDaysOfTheWeekComparator(), CoursePlanner.scheduleList);
        }
        if (position == 3) {
          simpleAdvancedSortDialog.show();
          //advancedSortDialog.show();
        }
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {

      }
    });
    spinner.setAdapter(adapter);
    return spinner;
  }

  public void sortSchedules(GenericComparator comparator, List<Schedule> schedules) {
    nestedLayout.removeAllViewsInLayout();
    ScheduleSorter.sort(schedules, comparator);
    displaySchedules(schedules);
  }

  private void createCoursesInfo(final TextView tv, LinearLayout.LayoutParams layoutParams,
      final Schedule schedule) {
    for (Section section : schedule.getSchedule()) {
      Commitment commitment = section.getCommitment();
      String content = commitment.getName() + " " + section.getName();
      if (commitment instanceof Course) {
        String CRN = ((CourseSection) section).getCrn() + "";
        content += " " + CRN;
      }
      String meetingTimes = "";
      for (int i = 0; i < section.getMeetingTimes().length; i++) {
        meetingTimes += section.getMeetingTimes()[i] + ", ";
      }
      meetingTimes = meetingTimes.substring(0, meetingTimes.length() - 2);
      CustomFontRegular line1 = new CustomFontRegular(getContext());

      line1.setText(content);
      line1.setTextColor(contentColor);
      CustomFontLight line2 = new CustomFontLight(getContext());
      line2.setText(meetingTimes);
      line2.setTextColor(contentColor);
      line2.setPadding(0, 0, 0, margin / 2);
      card.setPadding(margin, margin, margin, margin);
      card.addView(line1);
      card.addView(line2);
      card.setTag((index - 2) + "");
    }
    card.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ScheduleVisualFragment frag = new ScheduleVisualFragment();
        int num = Integer.parseInt(v.getTag().toString());
        frag.putExtra(num);
        getActivity().getSupportFragmentManager()
            .beginTransaction()
            .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            .replace(R.id.container, frag)
            .addToBackStack(null)
            .commit();
      }
    });
    nestedLayout.addView(card, layoutParams);
  }
}
