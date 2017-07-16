
/**
 * Created by Kevin on 1/3/2017.
 */
public class Section {
    private Course course;
    private String name;
    private MeetingTime[] meetingTimes;

    public Section(String name, MeetingTime[] meetingTimes) {
        this.name = name;
        this.meetingTimes = meetingTimes;
    }

    /*
    public boolean conflictsWith(Course other) {
        if (startTime.compareTo(other.startTime) < 0 && endTime.compareTo(other.startTime) > 0) {
            return true;
        } else if (startTime.compareTo(other.endTime) < 0 && endTime.compareTo(other.startTime) > 0) {
            return true;
        } else {
            return false;
        }
    }
    */

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public MeetingTime[] getMeetingTimes() {
        return meetingTimes;
    }

    public boolean conflictsWith(Section other) {
        for (MeetingTime m1: meetingTimes) {
            for (MeetingTime m2: other.meetingTimes) {
                if (m1.conflictsWith(m2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Course: " + course.getName() + ", Section: " + name;
    }
}
