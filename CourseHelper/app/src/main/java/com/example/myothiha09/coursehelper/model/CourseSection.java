package com.example.myothiha09.coursehelper.model;

/**
 * Created by Kevin on 1/3/2017.
 */
public class CourseSection extends Section {
    private int crn;
    private String prof;


    public CourseSection(Commitment commitment, String name, MeetingTime[] meetingTimes, int crn, String prof, String location) {
        super(name, meetingTimes, location, commitment);
        this.crn = crn;
        this.prof = prof;
    }

    public CourseSection(String name, MeetingTime[] meetingTimes) {
        this(null, name, meetingTimes, 0, null, null);
    }

    public CourseSection(String name, MeetingTime[] meetingTimes, int crn, String prof, String location) {
        this(null, name, meetingTimes, crn, prof, location);
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
        return "Course: " + super.getCommitment().getName() + " Section: " + getName() + " Professor: " + prof;
    }
}
