package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;

/**
 * Created by Kevin on 5/17/2017.
 */
public class SingleCourse extends Course {
    private Section[] sections;

    public SingleCourse(String name, Section[] sections) {
        super(name);
        this.sections = sections;
    }

    public Section[] getSections() {
        return sections;
    }

    public Section[] getSections(String professor) {
        if (professor == null || professor.equals("")) {
            return getSections();
        }

        Schedule matchingSections = new ArrayList<>();
        for (Section current: sections) {
            if (professor.equals(current.getProf())) {
                matchingSections.add(current);
            }
        }
        return matchingSections.toArray(new Section[matchingSections.size()]);
    }

    public int numberOfSections() {
        return sections.length;
    }

    @Override
    public int compareTo(Course o) {
        return numberOfSections() - o.numberOfSections();
    }
}

