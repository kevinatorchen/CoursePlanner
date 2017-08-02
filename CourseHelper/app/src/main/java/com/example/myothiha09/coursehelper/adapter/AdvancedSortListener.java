package com.example.myothiha09.coursehelper.adapter;

import com.example.myothiha09.coursehelper.model.GenericComparator;
import com.example.myothiha09.coursehelper.model.Schedule;
import com.example.myothiha09.coursehelper.util.AlternativeSelection;

import java.util.List;

/**
 * Created by Myo on 8/1/2017.
 */

public interface AdvancedSortListener {
  void onSortSettingChanged(GenericComparator comparator, List<Schedule> schedule,
                            AlternativeSelection alternativeSelection, int maxDrop);
}
