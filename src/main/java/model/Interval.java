package model;

/**
 * Created by KChen10 on 7/25/2017.
 */
public class Interval implements Comparable<Interval> {
    private Time startTime;
    private Time endTime;

    public Interval(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return endTime.getMinuteDifference(startTime);
    }

    public int amountOfOverlap(Interval other) {
        Time minimumStartTime = (startTime.compareTo(other.startTime) < 0) ? startTime : other.startTime;
        Time maximumEndTime = (endTime.compareTo(other.endTime) > 0) ? endTime : other.endTime;
        int overallDuration = maximumEndTime.getMinuteDifference(minimumStartTime);
        int overlap = getDuration() + other.getDuration() - overallDuration;
        return (overlap > 0) ? overlap : 0;
    }

    public boolean includesTime(Time time) {
        return startTime.compareTo(time) < 0 && endTime.compareTo(time) > 0;
    }

    public boolean conflictsWith(Interval other) {
        if (this.endTime.compareTo(other.startTime) < 0) {
            return false;
        }
        if (other.endTime.compareTo(this.startTime) < 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(startTime.toString());
        builder.append("-");
        builder.append(endTime.toString());
        return builder.toString();
    }

    @Override
    public int compareTo(Interval o) {
        return this.startTime.compareTo(o.startTime);
    }
}
