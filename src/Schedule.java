import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Kevin on 7/20/2017.
 */
public class Schedule {
    private ArrayList<Section> schedule;
    private ComparatorValues comparatorValues;

    public Schedule() {
        schedule = new ArrayList<>();
    }

    public Schedule(ArrayList<Section> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Section> getSchedule() {
        return schedule;
    }

    public void generateComparatorValues() {
        generateComparatorValues(0, 0);
    }

    public void generateComparatorValues(int wrongProfessors, int missingCommitments) {
        int totalGaps = 0;
        int morningMinutes = 0;
        int bitmask = 1;
        for (int i = 0; i < 5; i++) {
            ArrayList<Interval> dailyMeetingTimes = new ArrayList<>();
            for (Section currentSection: schedule) {
                for (MeetingTime currentMeetingTime: currentSection.getMeetingTimes()) {
                    if ((currentMeetingTime.getDaysOfWeek() & bitmask) != 0) {
                        dailyMeetingTimes.add(currentMeetingTime.getInterval());
                    }
                }
            }
            Collections.sort(dailyMeetingTimes);
            for (int j = 1; j < dailyMeetingTimes.size(); j++) {
                Interval currentInterval = dailyMeetingTimes.get(j);
                Interval previousInterval = dailyMeetingTimes.get(j - 1);
                totalGaps += currentInterval.getStartTime().getMinuteDifference(previousInterval.getEndTime());
            }
            for (int j = 0; j < dailyMeetingTimes.size(); j++) {

            }
            bitmask = bitmask << 1;
        }

        comparatorValues = new ComparatorValues(totalGaps, 0, 0, wrongProfessors, missingCommitments);
    }

    public ComparatorValues getComparatorValues() {
        return comparatorValues;
    }

    public void setSchedule(ArrayList<Section> schedule) {
        this.schedule = schedule;
    }
}
