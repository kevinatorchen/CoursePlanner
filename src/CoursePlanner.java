import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kevin on 1/3/2017.
 */
public class CoursePlanner {
    public static void main(String[] args) {
        MeetingTime[] APPH1040AsectionMTs = {new MeetingTime(5, new Time(8, 5), new Time(9, 55))};
        MeetingTime[] APPH1040BsectionMTs = {new MeetingTime(1, new Time(13, 5), new Time(13, 55)), new MeetingTime(3, new Time(13, 5), new Time(13, 55))};
        MeetingTime[] APPH1040CsectionMTs = {new MeetingTime(5, new Time(13, 5), new Time(14, 55))};
        MeetingTime[] APPH1040DsectionMTs = {new MeetingTime(1, new Time(14, 5), new Time(14, 55)), new MeetingTime(3, new Time(14, 5), new Time(14, 55))};


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
                for (Section sectionInSchedule: schedule) {
                    if (!currentSection.conflictsWith(sectionInSchedule)) {
                        schedule.add(currentSection);
                        planCourses(courses, currentCourse + 1, schedule);
                    }
                }
            }
            schedule.remove(schedule.size() - 1);
        }
    }
}
