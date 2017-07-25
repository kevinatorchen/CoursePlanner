package com.example.myothiha09.coursehelper.model;

/**
 * Created by Kevin on 1/3/2017.
 */
public abstract class Course implements Comparable<Course> {
  private String name;
  private String category;
  private String longName;

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

