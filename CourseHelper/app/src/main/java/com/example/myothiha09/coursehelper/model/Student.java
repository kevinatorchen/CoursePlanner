package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Myo on 5/17/2017.
 */

public class Student {
    private String name;
    private String university;
    private Set<Course> coursesList;

    public Student(String name, String university) {
        this.name = name;
        this.university = university;
        coursesList = new HashSet<>();
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

    public Set<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(Set<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public void addCourse(Course course) {
        coursesList.add(course);
    }
    public Course removeCourse(Course course) {
        int index = 0;
        for(Course x: coursesList) {
            if (course.getName().equals(x.getName())) {
                coursesList.remove(index);
            }
            index++;
        }
        return null;
    }

}
