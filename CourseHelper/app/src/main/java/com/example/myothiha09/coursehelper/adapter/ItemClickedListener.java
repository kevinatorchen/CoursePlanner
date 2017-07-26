package com.example.myothiha09.coursehelper.adapter;

import com.example.myothiha09.coursehelper.model.Course;

/**
 * Created by Myo on 7/18/2017.
 */

public interface ItemClickedListener {
  void courseChosen(Course course);
  void deleteCourse(int position);
  void editCourse(int position);
}
