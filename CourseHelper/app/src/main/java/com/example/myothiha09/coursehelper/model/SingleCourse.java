package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;

/**
 * Created by Kevin on 5/17/2017.
 */
public class SingleCourse extends Course {
    private CourseSection[] sections;

    public SingleCourse(String name, CourseSection[] sections) {
        super(name);
        this.sections = sections;
    }

    public CourseSection[] getSections() {
        return sections;
    }

    @Override
    public CourseSection[] getSections(String[] professors) {
        if (professors == null || professors.equals("")) {
            return getSections();
        }

        ArrayList<CourseSection> matchingSections = new ArrayList<>();
        for (CourseSection current: sections) {
            if (contains(professors, current.getProf())) {
                matchingSections.add(current);
            }
        }
        return matchingSections.toArray(new CourseSection[matchingSections.size()]);
    }

    public int numberOfSections() {
        return sections.length;
    }
}



