package wrapper_classes.CF;

/**
 * Created by Kevin on 5/28/2018.
 */
public class TimeSlotCF {
    private String day;
    private int end_time;
    private String location;
    private int start_time;

    public TimeSlotCF() {

    }

    public String getDay() {
        return day;
    }

    public int getEnd_time() {
        return end_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStart_time() {
        return start_time;
    }
}
