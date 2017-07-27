package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;

/**
 * Created by Kevin on 5/17/2017.
 */
public class DoubleCourse extends Course {
    private DoubleSection[] sections;
    private CourseSection[] individualSections;
    private int numberOfSections;

    public DoubleCourse(String name, DoubleSection[] sections) {
        super(name);
        this.sections = sections;
        generateNumberOfSections();
        generateIndividualSections();
    }

    private void generateIndividualSections() {
        individualSections = new CourseSection[numberOfSections];
        int k = 0;
        for (DoubleSection currentDouble: sections) {
            for (CourseSection currentSubsection: currentDouble.getSubSections()) {
                String name = currentDouble.getMainSection().getName() + " + " + currentSubsection.getName();
                MeetingTime[] currentDoubleMT = currentDouble.getMainSection().getMeetingTimes();
                MeetingTime[] currentSubsectionMT = currentSubsection.getMeetingTimes();
                MeetingTime[] meetingTimes = new MeetingTime[currentDoubleMT.length
                        + currentSubsectionMT.length];
                for (int i = 0; i < currentDoubleMT.length; i++) {
                    meetingTimes[i] = currentDoubleMT[i];
                }
                for (int i = currentDoubleMT.length, j = 0; i < meetingTimes.length; i++, j++) {
                    meetingTimes[i] = currentSubsectionMT[j];
                }
                CourseSection newSection = new CourseSection(currentSubsection.getCourse(), name, meetingTimes,
                        currentSubsection.getCrn(), currentSubsection.getProf(), currentSubsection.getLocation());
                individualSections[k++] = newSection;
            }
        }
    }

    private void generateNumberOfSections() {
        int number = 0;
        for (DoubleSection current: sections) {
            number += current.getSubSections().length;
        }
        numberOfSections = number;
    }

    @Override
    public CourseSection[] getSections() {
        return individualSections;
    }

    @Override
    public CourseSection[] getSections(String[] professors) {
        if (professors == null || professors.length == 0) {
            return getSections();
        }

        ArrayList<CourseSection> sections = new ArrayList<>();
        for (CourseSection current: individualSections) {
            if (contains(professors, current.getProf())) {
                sections.add(current);
            }
        }
        return sections.toArray(new CourseSection[sections.size()]);
    }



    @Override
    public int numberOfSections() {
        return numberOfSections;
    }
}

