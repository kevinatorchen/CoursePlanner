package model;

/**
 * Created by KChen10 on 7/25/2017.
 */
public abstract class Section {
    private String name;
    private MeetingTime[] meetingTimes;
    private int creditHours;
    private String courseName;

    public Section(String name, MeetingTime[] meetingTimes, int creditHours, String courseName) {
        this.name = name;
        this.meetingTimes = meetingTimes;
        this.creditHours = creditHours;
        this.courseName = courseName;
    }

    public String getName() {
        return name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getCourseName() {
        return courseName;
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
        return "Name: " + name;
    }
}
