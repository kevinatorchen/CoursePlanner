package com.example.myothiha09.coursehelper.dialog;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatDialog;
import android.view.Window;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.AdvancedSortListener;
import com.example.myothiha09.coursehelper.controller.CoursePlanner;
import com.example.myothiha09.coursehelper.layout_helper.AgreementSpinner;
import com.example.myothiha09.coursehelper.model.GenericComparator;
import com.example.myothiha09.coursehelper.model.Schedule;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 8/3/2017.
 */

public class SimpleAdvancedSortDialog extends AppCompatDialog {
  @BindDimen(R.dimen.dialog_width) int dialog_width;
  @BindView(R.id.dialog_root) ConstraintLayout dialogRoot;
  @BindView(R.id.morningSpinner) AgreementSpinner morningSpinner;
  @BindView(R.id.gapSpinner) AgreementSpinner gapSpinner;
  @BindView(R.id.fewerDaysSpinner) AgreementSpinner fewerDaysSpinner;
  @BindView(R.id.mealSpinner) AgreementSpinner mealSpinner;
  private AdvancedSortListener listener;

  public SimpleAdvancedSortDialog(Context context) {
    super(context);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.dialog_simple_advaned_sort);
    ButterKnife.bind(this);
    initDialog();
  }

  private void initDialog() {
    dialogRoot.getLayoutParams().width = dialog_width;
  }

  public void setListener(AdvancedSortListener listener) {
    this.listener = listener;
  }

  @OnClick (R.id.sortButton) void onSort() {
    List<Schedule> schedules = CoursePlanner.scheduleList;
    int morningClassValue = morningSpinner.getValue(morningSpinner.getSelectedItem().toString());
    int gapValue = gapSpinner.getValue(gapSpinner.getSelectedItem().toString());
    int fewerDaysValue = fewerDaysSpinner.getValue(fewerDaysSpinner.getSelectedItem().toString());
    int mealValue = mealSpinner.getValue(mealSpinner.getSelectedItem().toString());
    GenericComparator genericComparator = new GenericComparator(gapValue, morningClassValue, fewerDaysValue, mealValue);
    listener.onSortSettingChanged(genericComparator, schedules, null, -1);
    dismiss();
  }
  @OnClick (R.id.cancelButton) void onCancel() {
    dismiss();
  }
}
