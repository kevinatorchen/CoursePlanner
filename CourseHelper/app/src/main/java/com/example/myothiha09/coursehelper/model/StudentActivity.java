package com.example.myothiha09.coursehelper.model;

/**
 * Created by KChen10 on 7/27/2017.
 */

public class StudentActivity extends Commitment {
    private StudentActivitySection[] sections;

    public StudentActivity(String name, StudentActivitySection[] sections) {
        super(name, null);
        this.sections = sections;
    }

    @Override
    public Section[] getSections() {
        return sections;
    }

    @Override
    public Section[] getSections(String[] professors) {
        return getSections();
    }

    @Override
    public int numberOfSections() {
        return sections.length;
    }
}
