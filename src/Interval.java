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

    /*
    //TODO: Finish coding this method.
    public int amountOfOverlap(Interval other) {
        if (this.compareTo(other) < 0) {
            return
        }
    }
    */

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
        builder.append(startTime.getHour());
        builder.append(":");
        if (startTime.getMinute() < 10) builder.append("0");
        builder.append(startTime.getMinute());
        builder.append("-");
        builder.append(endTime.getHour());
        builder.append(":");
        if (endTime.getMinute() < 10) builder.append("0");
        builder.append(endTime.getMinute());
        return builder.toString();
    }

    @Override
    public int compareTo(Interval o) {
        return this.startTime.compareTo(o.startTime);
    }
}
