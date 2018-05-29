package wrapper_classes.CF;

import java.util.List;

/**
 * Created by Kevin on 5/28/2018.
 */
public class ActivityCF {
    private String name;
    private List<TimeSlotCF> meetingTimes;

    public ActivityCF() {

    }

    public String getName() {
        return name;
    }

    public List<TimeSlotCF> getMeetingTimes() {
        return meetingTimes;
    }
}
