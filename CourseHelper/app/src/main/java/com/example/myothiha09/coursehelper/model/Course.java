package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;

/**
 * Created by Kevin on 1/3/2017.
 */
public abstract class Course extends Commitment {
  private String longName;
  private int creditHour;

  public Course(String name) {
    super(name);
  }

  public int getCreditHour() {
    return creditHour;
  }

  public ArrayList<String> getProfessors() {
    ArrayList<String> professorList = new ArrayList<>();
    for (Section x : getSections()) {
      if (x instanceof CourseSection) {
        CourseSection section = (CourseSection) x;
        if (!professorList.contains(section.getProf())) {
          professorList.add(section.getProf());
        }
      }
    }
    return professorList;
  }

  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }


  public boolean contains(String[] arr, String professor) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i].equals(professor)) {
        return true;
      }
    }
    return false;
  }

}

