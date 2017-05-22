package com.example.myothiha09.coursehelper.model;

import com.example.myothiha09.coursehelper.controller.RegisterActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Myo on 5/17/2017.
 */

public class Model {
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
        }

        //lines for making it possible to choose category then class
        list.put("APPH", new ArrayList<Course>());
        list.get("APPH").add(APPH1040);
        list.put("English", new ArrayList<Course>());
        list.get("English").add(ENGL1102);




    }
}
