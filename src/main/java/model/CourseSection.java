package model;

/**
 * Created by Kevin on 1/3/2017.
 */
public class CourseSection extends Section {
    private int crn;
    private String prof;


    public CourseSection(String name, MeetingTime[] meetingTimes, int crn, String prof) {
        super(name, meetingTimes);
        this.crn = crn;
        this.prof = prof;
    }

    public CourseSection(String name, MeetingTime[] meetingTimes) {
        this(name, meetingTimes, 0, null);
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

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    @Override
    public String toString() {
        return "Section: " + getName() + " Professor: " + prof;
    }
}