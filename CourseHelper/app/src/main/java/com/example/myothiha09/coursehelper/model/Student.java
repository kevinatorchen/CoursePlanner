package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/17/2017.
 */

public class Student {
  private String name;
  private String university;
  private ArrayList<Course> coursesList;
  private List<CourseRequest> courseRequestList = new ArrayList<>();

  public Student(String name, String university) {
    this.name = name;
    this.university = university;
    coursesList = new ArrayList<>();
  }

  public void addCourseRequest(CourseRequest request) {
    courseRequestList.add(request);
  }
  public CourseRequest[] getCourseRequestsAsArray() {
    return courseRequestList.toArray(new CourseRequest[courseRequestList.size()]);
  }
  public List<CourseRequest> getCourseRequests() {
    return courseRequestList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUniversity() {
    return university;
  }

  public void setUniversity(String university) {
    this.university = university;
  }

  public ArrayList<Course> getCoursesList() {
    return coursesList;
  }

  public void setCoursesList(ArrayList<Course> coursesList) {
    this.coursesList = coursesList;
  }

  public void addCourse(Course course) {
    if (!coursesList.contains(course)) {
      coursesList.add(course);
    }
  }

  public Course removeCourse(Course course) {
    int index = 0;
    for (Course x : coursesList) {
      if (course.getName().equals(x.getName())) {
        coursesList.remove(index);
      }
      index++;
    }
    return null;
  }
}
