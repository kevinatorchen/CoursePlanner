package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;

/**
 * Created by Kevin on 1/3/2017.
 */
public abstract class Course implements Comparable<Course> {
  private String name;
  private String category;
  private String longName;
  private int creditHour;

  public Course(String name) {
    this.name = name;
  }

  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public ArrayList<String> getProfessors() {
    ArrayList<String> professorList = new ArrayList<>();
    for (Section x : getSections()) {
      if (!professorList.contains(x.getProf())) professorList.add(x.getProf());
    }
    return professorList;
  }

  public int getCreditHour() {
    return creditHour;
  }

  public abstract Section[] getSections();

  public abstract Section[] getSections(String[] professors);

  public boolean contains(String[] arr, String professor) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i].equals(professor)) {
        return true;
      }
    }
    return false;
  }

  @Override public abstract int compareTo(Course other);

  public abstract int numberOfSections();

  @Override public String toString() {
    return name;
  }
}

