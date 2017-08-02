package com.example.myothiha09.coursehelper.dialog;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.AdvancedSortListener;
import com.example.myothiha09.coursehelper.controller.CoursePlanner;
import com.example.myothiha09.coursehelper.model.GenericComparator;
import com.example.myothiha09.coursehelper.model.Schedule;
import com.example.myothiha09.coursehelper.model.ScheduleFilter;
import com.example.myothiha09.coursehelper.util.AlternativeSelection;
import com.example.myothiha09.coursehelper.util.Entry;
import com.example.myothiha09.coursehelper.util.EntryWithCheckBox;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kevin on 7/30/2017.
 */

public class AdvancedSortDialog extends AppCompatDialog {

  private final Context context;
  private final Button sortButton;
  private final Button cancelButton;
  @BindView(R.id.alternativeCheckBox) CheckBox alternativeCheckBox;
  @BindView(R.id.maxCommitmentsLabel) TextView maxCommitmentsLabel;
  @BindView(R.id.maxCommitmentsEditText) EditText maxCommitmentsEditText;
  @BindView(R.id.otherProfessorsButton) RadioButton otherProfessorsButton;
  @BindView(R.id.someCommitmentsButton) RadioButton someCommitmentsButton;
  @BindView(R.id.anyCommitmentsButton) RadioButton anyCommitmentsButton;
  @BindView(R.id.alternativeSettingsBox) ConstraintLayout alternativeSettingsBox;
  private AdvancedSortListener listener;
  private HashMap<String, Entry> entries;

  public AdvancedSortDialog(Context context) {
    super(context);
    this.context = context;
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.fragment_advanced_sort);
    ButterKnife.bind(this);
    setTitle(context.getString(R.string.advanced_sort));

    entries = new HashMap<>();
    entries.put("Morning Classes",
        new EntryWithCheckBox((TextView) findViewById(R.id.morningClassesLabel),
            (EditText) findViewById(R.id.morningClassesEditText),
            (SeekBar) findViewById(R.id.morningClassesSeekBar), 100,
            (CheckBox) findViewById(R.id.morningClassesFilter)));
    entries.put("Gaps", new Entry((TextView) findViewById(R.id.gapsLabel),
        (EditText) findViewById(R.id.gapsEditText), (SeekBar) findViewById(R.id.gapsSeekBar), 100));
    entries.put("Fewer Days", new Entry((TextView) findViewById(R.id.fewerDaysLabel),
        (EditText) findViewById(R.id.fewerDaysEditText),
        (SeekBar) findViewById(R.id.fewerDaysSeekBar), 100));
    entries.put("Meal Times", new EntryWithCheckBox((TextView) findViewById(R.id.mealTimeLabel),
        (EditText) findViewById(R.id.mealTimeEditText),
        (SeekBar) findViewById(R.id.mealTimeSeekBar), 100,
        (CheckBox) findViewById(R.id.mealTimeCheckBox)));
    entries.put("Requested Professors",
        new Entry((TextView) findViewById(R.id.requestedProfessorsLabel),
            (EditText) findViewById(R.id.requestedProfessorsEditText),
            (SeekBar) findViewById(R.id.requestedProfessorsSeekBar), 0));
    entries.put("Requested Commitments",
        new Entry((TextView) findViewById(R.id.requestedCommitmentsLabel),
            (EditText) findViewById(R.id.requestedCommitmentsEditText),
            (SeekBar) findViewById(R.id.requestedCommitmentsSeekBar), 0));

    for (Entry currentEntry : entries.values()) {
      currentEntry.initViews();
    }
    sortButton = (Button) findViewById(R.id.sortButton);
    cancelButton = (Button) findViewById(R.id.cancelButton);

    initRadioButton(otherProfessorsButton, true, false, false);
    initRadioButton(someCommitmentsButton, true, true, true);
    initRadioButton(anyCommitmentsButton, true, true, false);

    maxCommitmentsEditText.setText(Integer.toString(0));

    alternativeCheckBox.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setAlternativeViewsVisible(alternativeCheckBox.isChecked());
        otherProfessorsButton.setChecked(true);
        Entry requestedCommitments = entries.get("Requested Commitments");
        requestedCommitments.setEnabled(false);
        maxCommitmentsLabel.setEnabled(false);
        maxCommitmentsEditText.setEnabled(false);
      }
    });
    setAlternativeViewsVisible(false);

    sortButton.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {
        List<Schedule> schedules = CoursePlanner.scheduleList;
        int morningClassesValue = 0;
        EntryWithCheckBox morningClassesEntry = (EntryWithCheckBox) entries.get("Morning Classes");
        if (morningClassesEntry.getCheckBox().isChecked()) {
          schedules = ScheduleFilter.filterMorningClasses(schedules);
        } else {
          morningClassesValue =
              Integer.parseInt(morningClassesEntry.getEditText().getText().toString());
        }
        Entry gapsEntry = entries.get("Gaps");
        int gapsValue = Integer.parseInt(gapsEntry.getEditText().getText().toString());
        Entry fewerDaysEntry = entries.get("Fewer Days");
        int fewerDaysValue = Integer.parseInt(fewerDaysEntry.getEditText().getText().toString());
        int mealsValue = 0;
        EntryWithCheckBox mealTimeEntry = (EntryWithCheckBox) entries.get("Meal Times");
        if (mealTimeEntry.getCheckBox().isChecked()) {
          schedules = ScheduleFilter.filterNoMeals(schedules);
        } else {
          mealsValue = Integer.parseInt(mealTimeEntry.getEditText().getText().toString());
        }

        Entry requestedProfessorsEntry = entries.get("Requested Professors");
        Entry requestedCommitmentsEntry = entries.get("Requested Commitments");

        int requestedProfessorsValue =
            Integer.parseInt(requestedProfessorsEntry.getEditText().getText().toString());
        int requestedCommitmentsValue =
            Integer.parseInt(requestedCommitmentsEntry.getEditText().getText().toString());
        int maxDropValue = Integer.parseInt(maxCommitmentsEditText.getText().toString());
        AlternativeSelection alternativeSelection;
        if (alternativeCheckBox.isChecked()) {
          if (otherProfessorsButton.isChecked()) {
            alternativeSelection = AlternativeSelection.PROFONLY;
          } else if (someCommitmentsButton.isChecked()) {
            alternativeSelection = AlternativeSelection.SOMEALT;
          } else { //anyCommitmentsButton is checked
            alternativeSelection = AlternativeSelection.ANYALT;
          }
        } else {
          alternativeSelection = AlternativeSelection.NONE;
        }
        GenericComparator comparator =
            new GenericComparator(gapsValue, morningClassesValue, fewerDaysValue, mealsValue,
                requestedProfessorsValue, requestedCommitmentsValue);
        listener.onSortSettingChanged(comparator, schedules, alternativeSelection, maxDropValue);
        dismiss();
      }
    });
    Button cancelButton = (Button) findViewById(R.id.cancelButton);
    cancelButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dismiss();
      }
    });
  }

  public void initRadioButton(RadioButton button, final boolean showProfessors,
      final boolean showCommitments, final boolean showMaxCommitments) {
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Entry requestedProfessors = entries.get("Requested Professors");
        requestedProfessors.setEnabled(showProfessors);
        Entry requestedCommitments = entries.get("Requested Commitments");
        requestedCommitments.setEnabled(showCommitments);
        maxCommitmentsLabel.setEnabled(showMaxCommitments);
        maxCommitmentsEditText.setEnabled(showMaxCommitments);
      }
    });
  }

  public void setAlternativeViewsVisible(boolean visible) {
    if (visible) {
      alternativeSettingsBox.setVisibility(View.VISIBLE);
    } else {
      alternativeSettingsBox.setVisibility(View.GONE);
    }
/*        Entry requestedProfessors = entries.get("Requested Professors");
        requestedProfessors.setVisible(visible);
        Entry requestedCommitments = entries.get("Requested Commitments");
        requestedCommitments.setVisible(visible);
        if (visible) {
            maxCommitmentsLabel.setVisibility(TextView.VISIBLE);
            maxCommitmentsEditText.setVisibility(TextView.VISIBLE);
            otherProfessorsButton.setVisibility(TextView.VISIBLE);
            someCommitmentsButton.setVisibility(TextView.VISIBLE);
            anyCommitmentsButton.setVisibility(TextView.VISIBLE);
        } else {
            maxCommitmentsLabel.setVisibility(TextView.GONE);
            maxCommitmentsEditText.setVisibility(TextView.GONE);
            otherProfessorsButton.setVisibility(TextView.GONE);
            someCommitmentsButton.setVisibility(TextView.GONE);
            anyCommitmentsButton.setVisibility(TextView.GONE);
        }*/
  }

  public void setListener(AdvancedSortListener listener) {
    this.listener = listener;
  }
}
