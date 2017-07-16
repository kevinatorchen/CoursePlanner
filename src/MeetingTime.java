/**
 * Created by Kevin on 1/3/2017.
 */
public class MeetingTime {
    //least sig = Mon, second least is Tue, etc.
    private int daysOfWeek;
    private Time startTime;
    private Time endTime;


    public MeetingTime(int daysOfWeek, Time startTime, Time endTime) {
        this.daysOfWeek = daysOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public boolean conflictsWith(MeetingTime other) {
        if ((daysOfWeek & other.daysOfWeek) == 0) {
            return false;
        }
        if (this.endTime.compareTo(other.startTime) < 0) {
            return false;
        }
        if (other.endTime.compareTo(this.startTime) < 0) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if ((daysOfWeek & 1) != 0) {
            builder.append("M/");
        }
        if ((daysOfWeek & 2) != 0) {
            builder.append("T/");
        }
        if ((daysOfWeek & 4) != 0) {
            builder.append("W/");
        }
        if ((daysOfWeek & 8) != 0) {
            builder.append("T/");
        }
        if ((daysOfWeek & 16) != 0) {
            builder.append("F/");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
