package com.example.myothiha09.coursehelper.model;

import com.example.myothiha09.coursehelper.database.FirebaseCourseReader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Myo on 5/17/2017.
 */

public class Model {
  public static final ArrayList<String> ALL_COURSE_CATEGORY = new ArrayList<>();
  public static final ArrayList<Course> ALL_COURSE_CATEGORY_VALUES = new ArrayList<>();

  public static final ArrayList<StudentActivity> ALL_STUDENT_ACTIVITY = new ArrayList<>();

  public static final HashMap<String, ArrayList<Course>> ALL_COURSE_DATA = new HashMap<>();
  private static final int MONDAY = 1;
  private static final int TUESDAY = 2;
  private static final int WEDNESDAY = 4;
  private static final int THURSDAY = 8;
  private static final int FRIDAY = 16;
  public static Student student;
  private static Model model;

  private Model() {
    coursePopulater();
    ALL_COURSE_CATEGORY.addAll(ALL_COURSE_DATA.keySet());
  }

  public static Model getInstance() {
    if (model == null) {
      model = new Model();
    }
    return model;
  }

  private void coursePopulater() {
    FirebaseCourseReader.populateCourses(ALL_COURSE_DATA);
  }


//  private void coursePopulater() {
//        /*
//        MeetingTime[] APPH1040AsectionMTs = {new MeetingTime(4, new Time(8, 5), new Time(9, 55))};
//        Section APPH1040A = new Section("A", APPH1040AsectionMTs);
//        MeetingTime[] APPH1040BsectionMTs = {new MeetingTime(1, new Time(13, 5), new Time(13, 55)), new MeetingTime(3, new Time(13, 5), new Time(13, 55))};
//        Section APPH1040B = new Section("B", APPH1040BsectionMTs);
//        MeetingTime[] APPH1040CsectionMTs = {new MeetingTime(2, new Time(13, 5), new Time(14, 55))};
//        Section APPH1040C = new Section("C", APPH1040CsectionMTs);
//        MeetingTime[] APPH1040DsectionMTs = {new MeetingTime(1, new Time(14, 5), new Time(14, 55)), new MeetingTime(3, new Time(14, 5), new Time(14, 55))};
//        Section APPH1040D = new Section("D", APPH1040DsectionMTs);
//        Section[] APPH1040Sections = {APPH1040A, APPH1040B, APPH1040C, APPH1040D};
//        Course APPH1040 = new Course("APPH 1040", APPH1040Sections);
//
//
//        for (Section currentSection: APPH1040.getSections()) {
//            currentSection.setCourse(APPH1040);
//        }
//
//        MeetingTime[] ENGL1102ASectionMTs = {new MeetingTime(1, new Time(13, 25), new Time (14, 35)), new MeetingTime(3, new Time(13, 25), new Time (14, 35)), new MeetingTime(4, new Time(13, 5), new Time (13, 55))};
//        Section ENGL1102A = new Section("A", ENGL1102ASectionMTs);
//        //MeetingTime[] ENGL1102BSectionMTs = {new MeetingTime(2, new Time(15, 35), new Time(15, 05)), new MeetingTime(16, new Time(16, 35), new Time(16, 5))};
//        //Section ENGL1102B = new Section("B", ENGL1102BSectionMTs);
//        Section[] ENGL1102Sections = {ENGL1102A};
//        Course ENGL1102 = new Course("ENGL 1102", ENGL1102Sections);
//        for (Section currentSection: ENGL1102.getSections()) {
//            currentSection.setCourse(ENGL1102);
//        }*/
//    MeetingTime[] APPH1040AsectionMTs =
//        { new MeetingTime(FRIDAY, new Time(8, 5), new Time(9, 55)) };
//    CourseSection APPH1040A = new CourseSection("A", APPH1040AsectionMTs, 5234, "Nidhi", "Howey");
//    MeetingTime[] APPH1040EsectionMTs =
//        { new MeetingTime(MONDAY + WEDNESDAY, new Time(13, 5), new Time(13, 55)) };
//    CourseSection APPH1040E =
//        new CourseSection("E", APPH1040EsectionMTs, 5235, "Kevin", "Scheller");
//    MeetingTime[] APPH1040LsectionMTs =
//        { new MeetingTime(FRIDAY, new Time(13, 5), new Time(14, 55)) };
//    CourseSection APPH1040L = new CourseSection("L", APPH1040LsectionMTs, 5236, "Thiha", "CULC");
//    CourseSection[] APPH1040Sections = { APPH1040A, APPH1040E, APPH1040L };
//    Course APPH1040 = new SingleCourse("APPH 1040", APPH1040Sections);
//    for (CourseSection currentSection : APPH1040Sections) {
//      currentSection.setCommitment(APPH1040);
//    }
//
//    MeetingTime[] ENGL1102ASectionMTs =
//        { new MeetingTime(MONDAY + WEDNESDAY + FRIDAY, new Time(8, 5), new Time(8, 55)) };
//    MeetingTime[] ENGL1102BSectionMTs =
//        { new MeetingTime(TUESDAY + THURSDAY, new Time(9, 35), new Time(10, 55)) };
//    MeetingTime[] ENGL1102CSectionMTs =
//        { new MeetingTime(MONDAY + WEDNESDAY + FRIDAY, new Time(12, 5), new Time(12, 55)) };
//    CourseSection ENGL1102A = new CourseSection("A", ENGL1102ASectionMTs, 5512, "Kevin", "IC");
//    CourseSection ENGL1102B = new CourseSection("B", ENGL1102BSectionMTs, 5513, "Kevin", "IC");
//    CourseSection ENGL1102C = new CourseSection("C", ENGL1102CSectionMTs, 5514, "Thiha", "IC");
//    CourseSection[] ENGL1102Sections = { ENGL1102A, ENGL1102B, ENGL1102C };
//    Course ENGL1102 = new SingleCourse("ENGL 1102", ENGL1102Sections);
//    for (CourseSection currentSection : ENGL1102Sections) {
//      currentSection.setCommitment(ENGL1102);
//    }
//
//    MeetingTime[] PHYS2211ASectionMTs =
//        { new MeetingTime(MONDAY + WEDNESDAY + FRIDAY, new Time(8, 5), new Time(8, 55)) };
//    MeetingTime[] PHYS2211A01SectionMTs = {
//        new MeetingTime(MONDAY, new Time(12, 5), new Time(12, 55)),
//        new MeetingTime(WEDNESDAY, new Time(10, 5), new Time(11, 55))
//    };
//    MeetingTime[] PHYS2211A02SectionMTs = {
//        new MeetingTime(MONDAY, new Time(18, 5), new Time(18, 55)),
//        new MeetingTime(WEDNESDAY, new Time(12, 5), new Time(13, 55))
//    };
//    CourseSection PHYS2211AMainSection = new CourseSection("A", PHYS2211ASectionMTs);
//    CourseSection PHYS2211A01 =
//        new CourseSection("A01", PHYS2211A01SectionMTs, 8921, "Nidhi", "Howey");
//    CourseSection PHYS2211A02 =
//        new CourseSection("A02", PHYS2211A02SectionMTs, 8922, "Nidhi", "Howey");
//    CourseSection[] PHYS2211ASubsections = { PHYS2211A01, PHYS2211A02 };
//    DoubleSection PHYS2211A = new DoubleSection(PHYS2211AMainSection, PHYS2211ASubsections);
//    MeetingTime[] PHYS2211BSectionMTs =
//        { new MeetingTime(TUESDAY + THURSDAY, new Time(9, 5), new Time(10, 35)) };
//    MeetingTime[] PHYS2211B01SectionMTs = {
//        new MeetingTime(MONDAY, new Time(12, 35), new Time(13, 25)),
//        new MeetingTime(WEDNESDAY, new Time(12, 35), new Time(1, 25))
//    };
//    MeetingTime[] PHYS2211B02SectionMTs = {
//        new MeetingTime(TUESDAY, new Time(11, 5), new Time(11, 55)),
//        new MeetingTime(THURSDAY, new Time(1, 5), new Time(1, 55))
//    };
//    CourseSection PHYS2211BMainSection = new CourseSection("B", PHYS2211BSectionMTs);
//    CourseSection PHYS2211B01 =
//        new CourseSection("B01", PHYS2211B01SectionMTs, 8923, "Kevin", "Howey");
//    CourseSection PHYS2211B02 =
//        new CourseSection("B02", PHYS2211B02SectionMTs, 8924, "Kevin", "Howey");
//    CourseSection[] PHYS2211BSubsections = { PHYS2211B01, PHYS2211B02 };
//    DoubleSection PHYS2211B = new DoubleSection(PHYS2211BMainSection, PHYS2211BSubsections);
//    DoubleSection[] PHYS2211Sections = { PHYS2211A, PHYS2211B };
//    DoubleCourse PHYS2211 = new DoubleCourse("PHYS 2211", PHYS2211Sections);
//    for (DoubleSection currentDoubleSection : PHYS2211Sections) {
//      currentDoubleSection.getMainSection().setCommitment(PHYS2211);
//      for (CourseSection currentSubSection : currentDoubleSection.getSubSections()) {
//        currentSubSection.setCommitment(PHYS2211);
//      }
//    }
//    for (CourseSection currentSingleSection : PHYS2211.getSections()) {
//      currentSingleSection.setCommitment(PHYS2211);
//    }
//
//    MeetingTime[] gitmadMTs =
//        { new MeetingTime(MONDAY + TUESDAY, new Time(12, 35), new Time(13, 25)) };
//    StudentActivitySection gitMadSection =
//        new StudentActivitySection("", gitmadMTs, "Klaus 2443");
//
//    StudentActivitySection[] gitMadSections = { gitMadSection };
//    StudentActivity gitmad = new StudentActivity("GITMAD", gitMadSections);
//    gitMadSection.setCommitment(gitmad);
//
//    //Sample course requests:
//    //CommitmentRequest[] courseRequests = new CommitmentRequest[3];
//    //courseRequests[0] = new CommitmentRequest(APPH1040, "Thiha");
//    //courseRequests[1] = new CommitmentRequest(ENGL1102, "Kevin");
//    //courseRequests[2] = new CommitmentRequest(PHYS2211, "Nidhi");
//
//    //Course[] courses = {APPH1040, ENGL1102, PHYS2211};
//
//    //lines for making it possible to choose category then class
//    ALL_COURSE_DATA.put("APPH", new ArrayList<Course>());
//    ALL_COURSE_DATA.get("APPH").add(APPH1040);
//    ALL_COURSE_DATA.put("English", new ArrayList<Course>());
//    ALL_COURSE_DATA.get("English").add(ENGL1102);
//    ALL_COURSE_DATA.put("Physics", new ArrayList<Course>());
//    ALL_COURSE_DATA.get("Physics").add(PHYS2211);
//
//    ALL_STUDENT_ACTIVITY.add(gitmad);
//
//    for (ArrayList<Course> x : ALL_COURSE_DATA.values()) {
//      ALL_COURSE_CATEGORY_VALUES.addAll(x);
//    }
//  }
}
