package model;

import java.util.Arrays;

/**
 * Created by Kevin on 7/2/2017.
 */
public class DoubleSection {
    private CourseSection mainSection;
    private CourseSection[] subSections;

    public DoubleSection(CourseSection mainSection, CourseSection[] subSections) {
        this.mainSection = mainSection;
        this.subSections = subSections;
    }

    public CourseSection getMainSection() {
        return mainSection;
    }

    public void setMainSection(CourseSection mainSection) {
        this.mainSection = mainSection;
    }

    public CourseSection[] getSubSections() {
        return subSections;
    }

    public void setSubSections(CourseSection[] subSections) {
        this.subSections = subSections;
    }

    @Override
    public String toString() {
        return "DoubleSection{" +
                "mainSection=" + mainSection +
                ", subSections=" + Arrays.toString(subSections) +
                '}';
    }
}
