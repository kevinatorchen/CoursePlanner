/**
 * Created by KChen10 on 7/25/2017.
 */
public abstract class Section {
    private String name;
    private MeetingTime[] meetingTimes;
    private String location;

    public Section(String name, MeetingTime[] meetingTimes, String location) {
        this.name = name;
        this.meetingTimes = meetingTimes;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "Name: " + name;
    }
}
