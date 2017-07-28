package com.example.myothiha09.coursehelper.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.controller.CoursePlanner;
import com.example.myothiha09.coursehelper.layout_helper.CustomFontLight;
import com.example.myothiha09.coursehelper.layout_helper.CustomFontRegular;
import com.example.myothiha09.coursehelper.model.Commitment;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.CourseSection;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Schedule;
import com.example.myothiha09.coursehelper.model.Section;
import java.util.Set;

/**
 * Created by Myo on 5/22/2017.
 */
//TODO: improve UI for this screen
public class ScheduleOverviewFragment extends Fragment {
  static Schedule current = new Schedule();
  static String scheduleNumber;
  LinearLayout nested;
  @BindView(R.id.nested) LinearLayout layout;
  @BindDimen(R.dimen.padding) int margin;
  @BindColor(R.color.title_font_color) int titleColor;
  @BindColor(R.color.content_font_color) int contentColor;
  @BindDrawable(R.drawable.bg_card_button) Drawable cardBG;
  int index;

  public static Schedule getCurrent() {
    return current;
  }

  public static String getScheduleNumber() {
    return scheduleNumber;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_schedule_overview, container, false);
    ButterKnife.bind(this, view);
    //ArrayList<Course> ALL_COURSE_CATEGORY_VALUES = Model.student.getCoursesList();

    CoursePlanner.planCourses(Model.student.getCommmitmentRequestAsArray());
    Set<Schedule> list = CoursePlanner.scheduleList;
    index = 1;
    for (final Schedule sections : list) {
      if (!sections.getSchedule().isEmpty()) {
        LinearLayout.LayoutParams layoutParams = createBackground();
        TextView tv = createTitle();
        createCoursesInfo(tv, layoutParams, sections);
      }
    }
    return view;
  }

  @OnClick(R.id.viewScheduleVisually) void viewVisually() {
    getActivity().getSupportFragmentManager()
        .beginTransaction()
        .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        .replace(R.id.container, new ScheduleVisualFragment())
        .addToBackStack(null)
        .commit();
  }

  private LinearLayout.LayoutParams createBackground() {
    nested = new LinearLayout(getContext());
    LinearLayout.LayoutParams layoutParams =
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    layoutParams.bottomMargin = margin;
    nested.setPadding(margin, margin, margin, margin);
    nested.setBackground(cardBG);
    nested.setOrientation(LinearLayout.VERTICAL);
    return layoutParams;
  }

  private TextView createTitle() {
    final TextView tv = new TextView(getContext());
    tv.setText("Schedule " + index++);
    tv.setTextSize(18);
    tv.setPadding(0, 0, 0, margin / 2);
    tv.setTextColor(titleColor);
    nested.addView(tv);
    return tv;
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
      nested.setPadding(margin, margin, margin, margin);
      nested.addView(line1);
      nested.addView(line2);
    }
    nested.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        current = schedule;
        scheduleNumber = tv.getText().toString();
        ScheduleVisualDetailFragment frag = new ScheduleVisualDetailFragment();
        frag.putExtra(current, scheduleNumber);
        getActivity().getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            .replace(R.id.container, frag)
            .addToBackStack(null)
            .commit();
      }
    });
    layout.addView(nested, layoutParams);
  }
}
