package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;

/**
 * Created by Kevin on 5/17/2017.
 */
public class DoubleCourse extends Course {
    private DoubleSection[] sections;
    private Section[] individualSections;
    private int numberOfSections;

    public DoubleCourse(String name, DoubleSection[] sections) {
        super(name);
        this.sections = sections;
        generateNumberOfSections();
        generateIndividualSections();
    }

    private void generateIndividualSections() {
        individualSections = new Section[numberOfSections];
        int k = 0;
        for (DoubleSection currentDouble: sections) {
            for (Section currentSubsection: currentDouble.getSubSections()) {
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
                Section newSection = new Section(currentSubsection.getCourse(), name, meetingTimes,
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
    public Section[] getSections() {
        return individualSections;
    }

    @Override
    public Section[] getSections(String professor) {
        if (professor == null || professor.equals("")) {
            return getSections();
        }

        ArrayList<Section> sections = new ArrayList<>();
        for (Section current: individualSections) {
            if (professor.equals(current.getProf())) {
                sections.add(current);
            }
        }
        return sections.toArray(new Section[sections.size()]);
    }

    @Override
    public int compareTo(Course other) {
        return this.numberOfSections() - other.numberOfSections();
    }

    @Override
    public int numberOfSections() {
        return numberOfSections;
    }
}
