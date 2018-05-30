package model;

/**
 * Created by KChen10 on 7/25/2017.
 */
public abstract class Section {
    private String name;
    private MeetingTime[] meetingTimes;
    private Commitment commitment;

    public Section(String name, MeetingTime[] meetingTimes) {
        this(name, meetingTimes, null);
    }
    public Section(String name, MeetingTime[] meetingTimes, Commitment commitment) {
        this.name = name;
        this.meetingTimes = meetingTimes;
        this.commitment = commitment;
    }

    public Commitment getCommitment() {
        return commitment;
    }

    public void setCommitment(Commitment commitment) {
        this.commitment = commitment;
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
