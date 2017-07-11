/**
 * Created by Kevin on 1/3/2017.
 */
public class MeetingTime {
    //0 = Monday, 7 = Sunday
    private int[] daysOfWeek;
    private Time startTime;
    private Time endTime;


    public MeetingTime(int[] daysOfWeek, Time startTime, Time endTime) {
        this.daysOfWeek = daysOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public boolean conflictsWith(MeetingTime other) {
        if (dayOfWeek == other.dayOfWeek) {
            if (startTime.compareTo(other.startTime) > 0 && startTime.compareTo(other.endTime) < 0) {
                return true;
            } else if (endTime.compareTo(other.startTime) > 0 && endTime.compareTo(other.endTime) < 0) {
                return true;
            } else if (startTime.compareTo(other.startTime) <= 0 && endTime.compareTo(other.startTime) >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
