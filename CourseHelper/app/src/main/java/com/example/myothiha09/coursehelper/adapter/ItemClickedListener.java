package com.example.myothiha09.coursehelper.adapter;

import com.example.myothiha09.coursehelper.model.Commitment;
import com.example.myothiha09.coursehelper.model.Course;

/**
 * Created by Myo on 7/18/2017.
 */

public interface ItemClickedListener {
  void commitmentChosen(Commitment commitment);
  void delCommitment(int position);
  void editCommitment(int position);
}
