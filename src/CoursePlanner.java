import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kevin on 1/3/2017.
 */
public class CoursePlanner {
    public static void main(String[] args) {
        MeetingTime[] APPH1040AsectionMTs = {new MeetingTime(5, new Time(8, 5), new Time(9, 55))};
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

        planCourses(courses);
    }

    public static ArrayList<Section> planCourses(Course[] courses) {
        Arrays.sort(courses);
        ArrayList<Section> schedule = new ArrayList<>();
        planCourses(courses, 0, schedule);
        return schedule;
    }

    public static void planCourses(Course[] courses, int currentCourse, ArrayList<Section> schedule) {
        if (currentCourse >= courses.length) {
            System.out.println(Arrays.toString(schedule.toArray()));
        } else {
            for (Section currentSection: courses[currentCourse].getSections()) {
                boolean conflicts = false;
                for (Section sectionInSchedule: schedule) {
                    if (currentSection.conflictsWith(sectionInSchedule)) {
                        conflicts = true;
                        break;
                    }
                }
                if (!conflicts) {
                    schedule.add(currentSection);
                    planCourses(courses, currentCourse + 1, schedule);
                    schedule.remove(schedule.size() - 1);
                }
            }
        }
    }
}
