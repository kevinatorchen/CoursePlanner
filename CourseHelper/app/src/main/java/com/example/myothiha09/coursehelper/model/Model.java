package com.example.myothiha09.coursehelper.model;

import com.example.myothiha09.coursehelper.activity.RegisterActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Myo on 5/17/2017.
 */

public class Model {
    private static final int MONDAY = 1;
    private static final int TUESDAY = 2;
    private static final int WEDNESDAY = 4;
    private static final int THURSDAY  = 8;
    private static final int FRIDAY = 16;

    public static final ArrayList<String> CATEGORY = new ArrayList<>();
    public static Student student;
    public static final HashMap <String, ArrayList<Course>> list = new HashMap<>();
    private static Model model;
    private Model() {
        coursePopulater();
        studentPopulater();
        CATEGORY.addAll(list.keySet());
    }
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }
    private void studentPopulater() {
        student = new Student(RegisterActivity.prefs.getString("StudentName", "Student"),
                RegisterActivity.prefs.getString("UniName", "University"));
    }
    private void coursePopulater() {
        /*
        MeetingTime[] APPH1040AsectionMTs = {new MeetingTime(4, new Time(8, 5), new Time(9, 55))};
        Section APPH1040A = new Section("A", APPH1040AsectionMTs);
        MeetingTime[] APPH1040BsectionMTs = {new MeetingTime(1, new Time(13, 5), new Time(13, 55)), new MeetingTime(3, new Time(13, 5), new Time(13, 55))};
        Section APPH1040B = new Section("B", APPH1040BsectionMTs);
        MeetingTime[] APPH1040CsectionMTs = {new MeetingTime(2, new Time(13, 5), new Time(14, 55))};
        Section APPH1040C = new Section("C", APPH1040CsectionMTs);
        MeetingTime[] APPH1040DsectionMTs = {new MeetingTime(1, new Time(14, 5), new Time(14, 55)), new MeetingTime(3, new Time(14, 5), new Time(14, 55))};
        Section APPH1040D = new Section("D", APPH1040DsectionMTs);
        Section[] APPH1040Sections = {APPH1040A, APPH1040B, APPH1040C, APPH1040D};
        Course APPH1040 = new Course("APPH 1040", APPH1040Sections);


        for (Section currentSection: APPH1040.getSections()) {
            currentSection.setCourse(APPH1040);
        }

        MeetingTime[] ENGL1102ASectionMTs = {new MeetingTime(1, new Time(13, 25), new Time (14, 35)), new MeetingTime(3, new Time(13, 25), new Time (14, 35)), new MeetingTime(4, new Time(13, 5), new Time (13, 55))};
        Section ENGL1102A = new Section("A", ENGL1102ASectionMTs);
        //MeetingTime[] ENGL1102BSectionMTs = {new MeetingTime(2, new Time(15, 35), new Time(15, 05)), new MeetingTime(16, new Time(16, 35), new Time(16, 5))};
        //Section ENGL1102B = new Section("B", ENGL1102BSectionMTs);
        Section[] ENGL1102Sections = {ENGL1102A};
        Course ENGL1102 = new Course("ENGL 1102", ENGL1102Sections);
        for (Section currentSection: ENGL1102.getSections()) {
            currentSection.setCourse(ENGL1102);
        }*/

        MeetingTime[] APPH1040AsectionMTs = {new MeetingTime(FRIDAY, new Time(8, 5), new Time(9, 55))};
        Section APPH1040A = new Section("A", APPH1040AsectionMTs);
        MeetingTime[] APPH1040EsectionMTs = {new MeetingTime(MONDAY + WEDNESDAY, new Time(13, 5), new Time(13, 55))};
        Section APPH1040E = new Section("E", APPH1040EsectionMTs);
        MeetingTime[] APPH1040LsectionMTs = {new MeetingTime(FRIDAY, new Time(13, 5), new Time(14, 55))};
        Section APPH1040L = new Section("L", APPH1040LsectionMTs);
        Section[] APPH1040Sections = {APPH1040A, APPH1040E, APPH1040L};
        Course APPH1040 = new SingleCourse("APPH 1040", APPH1040Sections);
        for (Section currentSection: APPH1040Sections) {
            currentSection.setCourse(APPH1040);
        }

        MeetingTime[] ENGL1102ASectionMTs = {new MeetingTime(MONDAY + WEDNESDAY + FRIDAY, new Time(8, 5), new Time(8, 55))};
        MeetingTime[] ENGL1102BSectionMTs = {new MeetingTime(TUESDAY + THURSDAY, new Time(9, 35), new Time (10, 55))};
        MeetingTime[] ENGL1102CSectionMTs = {new MeetingTime(MONDAY + WEDNESDAY + FRIDAY, new Time(12, 5), new Time(12, 55))};
        Section ENGL1102A = new Section("A", ENGL1102ASectionMTs);
        Section ENGL1102B = new Section("B", ENGL1102BSectionMTs);
        Section ENGL1102C = new Section("C", ENGL1102CSectionMTs);
        Section[] ENGL1102Sections = {ENGL1102A, ENGL1102B, ENGL1102C};
        Course ENGL1102 = new SingleCourse("ENGL 1102", ENGL1102Sections);
        for (Section currentSection: ENGL1102Sections) {
            currentSection.setCourse(ENGL1102);
        }

        MeetingTime[] PHYS2211ASectionMTs = {new MeetingTime(MONDAY + WEDNESDAY + FRIDAY, new Time(8, 5), new Time(8, 55))};
        MeetingTime[] PHYS2211A01SectionMTs = {new MeetingTime(MONDAY, new Time(12, 5), new Time(12, 55)),
                new MeetingTime(WEDNESDAY, new Time(10, 5), new Time(11, 55))};
        MeetingTime[] PHYS2211A02SectionMTs = {new MeetingTime(MONDAY, new Time(18, 5), new Time(18, 55)),
                new MeetingTime(WEDNESDAY, new Time(12, 5), new Time(13, 55))};
        Section PHYS2211AMainSection = new Section("A", PHYS2211ASectionMTs);
        Section PHYS2211A01 = new Section("A01", PHYS2211A01SectionMTs);
        Section PHYS2211A02 = new Section("A02", PHYS2211A02SectionMTs);
        Section[] PHYS2211ASubsections = {PHYS2211A01, PHYS2211A02};
        DoubleSection PHYS2211A = new DoubleSection(PHYS2211AMainSection, PHYS2211ASubsections);
        MeetingTime[] PHYS2211BSectionMTs = {new MeetingTime(TUESDAY + THURSDAY, new Time(9, 5), new Time(10, 35))};
        MeetingTime[] PHYS2211B01SectionMTs = {new MeetingTime(MONDAY, new Time(12, 35), new Time(13, 25)),
                new MeetingTime(WEDNESDAY, new Time(12, 35), new Time(1, 25))};
        MeetingTime[] PHYS2211B02SectionMTs = {new MeetingTime(TUESDAY, new Time(11, 5), new Time(11, 55)),
                new MeetingTime(THURSDAY, new Time(13, 5), new Time(13, 55))};
        Section PHYS2211BMainSection = new Section("B", PHYS2211BSectionMTs);
        Section PHYS2211B01 = new Section("B01", PHYS2211B01SectionMTs);
        Section PHYS2211B02 = new Section("B02", PHYS2211B02SectionMTs);
        Section[] PHYS2211BSubsections = {PHYS2211B01, PHYS2211B02};
        DoubleSection PHYS2211B = new DoubleSection(PHYS2211BMainSection, PHYS2211BSubsections);
        DoubleSection[] PHYS2211Sections = {PHYS2211A, PHYS2211B};
        DoubleCourse PHYS2211 = new DoubleCourse("PHYS 2211", PHYS2211Sections);
        for (DoubleSection currentDoubleSection: PHYS2211Sections) {
            currentDoubleSection.getMainSection().setCourse(PHYS2211);
            for (Section currentSubSection: currentDoubleSection.getSubSections()) {
                currentSubSection.setCourse(PHYS2211);
            }
        }
        for (Section currentSingleSection: PHYS2211.getSections()) {
            currentSingleSection.setCourse(PHYS2211);
        }

        //Course[] courses = {APPH1040, ENGL1102, PHYS2211};

        //lines for making it possible to choose category then class
        list.put("APPH", new ArrayList<Course>());
        list.get("APPH").add(APPH1040);
        list.put("English", new ArrayList<Course>());
        list.get("English").add(ENGL1102);
        list.put("Physics", new ArrayList<Course>());
        list.get("Physics").add(PHYS2211);
    }
}
