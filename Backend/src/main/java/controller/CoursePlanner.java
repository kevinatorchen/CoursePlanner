package controller;

import model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kevin on 1/3/2017.
 */
public class CoursePlanner {
  public static final int MAX_RESULTS = 1000;
  public static List<Schedule> scheduleList = new ArrayList<>();

  public static void main(String[] args) {
        /*MeetingTime[] APPH1040AsectionMTs = {new MeetingTime(5, new Time(8, 5), new Time(9, 55))};
        Section APPH1040A = new Section("A", APPH1040AsectionMTs);
        MeetingTime[] APPH1040BsectionMTs = {new MeetingTime(1, new Time(13, 5), new Time(13, 55)), new MeetingTime(3, new Time(13, 5), new Time(13, 55))};
        Section APPH1040B = new Section("B", APPH1040BsectionMTs);
        MeetingTime[] APPH1040CsectionMTs = {new MeetingTime(5, new Time(13, 5), new Time(14, 55))};
        Section APPH1040C = new Section("C", APPH1040CsectionMTs);
        MeetingTime[] APPH1040DsectionMTs = {new MeetingTime(1, new Time(14, 5), new Time(14, 55)), new MeetingTime(3, new Time(14, 5), new Time(14, 55))};
        Section APPH1040D = new Section("D", APPH1040DsectionMTs);
        Section[] APPH1040Sections = {APPH1040A, APPH1040B, APPH1040C, APPH1040D};
        Course APPH1040 = new Course("APPH 1040", APPH1040Sections);
        for (Section currentSection: APPH1040.getSections()) {
            currentSection.setCourse(APPH1040);
        }

        MeetingTime[] ENGL1102ASectionMTs = {new MeetingTime(1, new Time(13, 5), new Time (13, 55)), new MeetingTime(3, new Time(13, 5), new Time (13, 55)), new MeetingTime(5, new Time(13, 5), new Time (13, 55))};
        Section ENGL1102A = new Section("A", ENGL1102ASectionMTs);
        MeetingTime[] ENGL1102BSectionMTs = {new MeetingTime(2, new Time(15, 35), new Time(15, 05)), new MeetingTime(16, new Time(16, 35), new Time(16, 5))};
        Section ENGL1102B = new Section("B", ENGL1102BSectionMTs);
        Section[] ENGL1102Sections = {ENGL1102A, ENGL1102B};
        Course ENGL1102 = new Course("ENGL 1102", ENGL1102Sections);
        for (Section currentSection: ENGL1102.getSections()) {
            currentSection.setCourse(ENGL1102);
        }

        Course[] courses = {APPH1040, ENGL1102};

        planCourses(courses);*/
  }

  public static void planCourses(List<CommitmentRequest> courseRequests) {
    planCourses(courseRequests, false);
  }

  public static void planCourses(List<CommitmentRequest> courseRequests, boolean ignoreProfessor) {
    scheduleList.clear();
    planCourses(courseRequests, ignoreProfessor, 0);
  }

  private static void planCourses(List<CommitmentRequest> courseRequests, boolean ignoreProfessor, int droppedCommitments) {
    Collections.sort(courseRequests);
    Schedule schedule = new Schedule();
    planCourses(courseRequests, 0, schedule, ignoreProfessor, 0, droppedCommitments);
  }

  public static void planCourses(List<CommitmentRequest> courseRequests, int currentCourse, Schedule schedule,
                                 boolean ignoreProfessor, int altProfessors, int droppedCommitments) {
    if (currentCourse >= courseRequests.size()) {
      if (scheduleList.size() > MAX_RESULTS) {
        return;
      }
      Schedule temp = new Schedule();
      for (Section x : schedule.getSchedule()) {
        temp.getSchedule().add(x);
      }
      temp.generateComparatorValues(altProfessors, droppedCommitments);
      scheduleList.add(temp);


    } else {
      CommitmentRequest currentCourseRequest = courseRequests.get(currentCourse);
      Section[] sections = (ignoreProfessor) ?
              currentCourseRequest.getCommitment().getSections() :
              currentCourseRequest.getCommitment().getSections(currentCourseRequest.getProf());
      for (Section currentSection: sections) {
        boolean conflicts = false;
        for (Section sectionInSchedule: schedule.getSchedule()) {
          if (currentSection.conflictsWith(sectionInSchedule)) {
            conflicts = true;
            break;
          }
        }
        if (!conflicts) {
          schedule.getSchedule().add(currentSection);
          int updatedAltProfessors = altProfessors;
          if (ignoreProfessor && currentSection instanceof CourseSection) {
            CourseSection currentCourseSection = (CourseSection) currentSection;
            if (!currentCourseRequest.containsProf(currentCourseSection.getProf())) {
              updatedAltProfessors++;
            }
          }
          planCourses(courseRequests, currentCourse + 1, schedule, ignoreProfessor, updatedAltProfessors, droppedCommitments);
          schedule.getSchedule().remove(schedule.getSchedule().size() - 1);
        }
      }
    }
  }

  //Tries allowing all professors only.
  public static void planAltConservative(List<CommitmentRequest> courseRequests) {
    planAlternatives(courseRequests, 0);
  }

  //Does whatever it can to find alternatives.
  public static void planAltFull(List<CommitmentRequest> courseRequests) {
    planAlternatives(courseRequests, courseRequests.size() - 1);
  }

  //Tries allowing all professors and/or dropping "maxDrop" number of commitments.
  public static void planAlternatives(List<CommitmentRequest> courseRequests, int maxDrop) {
    scheduleList.clear();
    List<ArrayList<CommitmentRequest>> alternativeRequests = CoursePlanner.powerSet(courseRequests, courseRequests.size() - maxDrop);
    for (ArrayList<CommitmentRequest> currentAlternative: alternativeRequests) {
      int droppedCourses = courseRequests.size() - currentAlternative.size();
      planCourses(currentAlternative, true, droppedCourses);
    }
  }

  public static <T> List<ArrayList<T>> powerSet(List<T> elements, int minSize) {
    List<ArrayList<T>> result = new ArrayList<>();
    result.add(new ArrayList<T>());
    for (T current: elements) {
      int size = result.size();
      for (int i = 0; i < size; i++) {
        ArrayList<T> newSet = (ArrayList<T>) result.get(i).clone();
        newSet.add(current);
        result.add(newSet);
      }
    }
    List<ArrayList<T>> truncated = new ArrayList<>();
    for (ArrayList<T> current: result) {
      if (current.size() >= minSize) {
        truncated.add(current);
      }
    }
    return truncated;
  }
}
