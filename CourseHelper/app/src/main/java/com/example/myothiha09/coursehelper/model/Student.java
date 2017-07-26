package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/17/2017.
 */

public class Student {
  private String name;
  private String university;
  private List<CourseRequest> courseRequestList;

  public Student(String name, String university) {
    this.name = name;
    this.university = university;
    courseRequestList = new ArrayList<>();
  }

  public void editCourseRequest(int position, CourseRequest request) {
    courseRequestList.remove(position);
    courseRequestList.add(position, request);
  }

  public void addCourseRequest(CourseRequest request) {
    if (!courseRequestList.contains(request)) courseRequestList.add(request);
  }

  public CourseRequest[] getCourseRequestsAsArray() {
    return courseRequestList.toArray(new CourseRequest[courseRequestList.size()]);
  }

  public List<CourseRequest> getCourseRequests() {
    return courseRequestList;
  }

  public boolean takeThisCourse(Course course) {
    for (CourseRequest request : courseRequestList) {
      if (request.getCourse().getName().equals(course.getName())) return true;
    }
    return false;
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
}
