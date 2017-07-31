import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Kevin on 7/20/2017.
 */
public class Schedule {
    private static final int HOUR = 60;
    private static final Interval EARLY_MORNING = new Interval(new Time(8,0), new Time(9, 0));
    private static final Interval LATE_MORNING = new Interval(new Time(9, 0), new Time(10, 0));
    private static final Interval LUNCH_TIME = new Interval(new Time(11, 0), new Time(14, 0));
    private static final Interval DINNER_TIME = new Interval(new Time(17, 0), new Time(20, 0));
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
        int noMealTimes = 0;
        int bitmask = 1;
        int daysOfWeek = 0;
        for (int i = 0; i < 5; i++) {
            ArrayList<Interval> dailyMeetingTimes = new ArrayList<>();
            for (Section currentSection: schedule) {
                for (MeetingTime currentMeetingTime: currentSection.getMeetingTimes()) {
                    if ((currentMeetingTime.getDaysOfWeek() & bitmask) != 0) {
                        dailyMeetingTimes.add(currentMeetingTime.getInterval());
                    }
                }
            }
            if (dailyMeetingTimes.size() > 0) {
                daysOfWeek++;
            }
            Collections.sort(dailyMeetingTimes);
            for (int j = 1; j < dailyMeetingTimes.size(); j++) {
                Interval currentInterval = dailyMeetingTimes.get(j);
                Interval previousInterval = dailyMeetingTimes.get(j - 1);
                totalGaps += currentInterval.getStartTime().getMinuteDifference(previousInterval.getEndTime());
            }
            for (Interval currentInterval: dailyMeetingTimes) {
                morningMinutes += currentInterval.amountOfOverlap(EARLY_MORNING) * 2;
                morningMinutes += currentInterval.amountOfOverlap(LATE_MORNING);
            }

            if (!hasHourGap(dailyMeetingTimes, LUNCH_TIME)) {
                noMealTimes++;
            }
            if (!hasHourGap(dailyMeetingTimes, DINNER_TIME)) {
                noMealTimes++;
            }

            bitmask = bitmask << 1;
        }

        comparatorValues = new ComparatorValues(totalGaps, morningMinutes, daysOfWeek, noMealTimes, wrongProfessors, missingCommitments);
    }

    private boolean hasHourGap(ArrayList<Interval> dailyMeetingTimes, Interval interval) {
        if (interval.getEndTime().getMinuteDifference(interval.getStartTime()) < HOUR) {
            return false;
        }
        Interval currentInterval = null;
        int currentIntervalIndex = -1;
        //Find the first interval that is inside the "interval" parameter.
        for (int i = 0; i < dailyMeetingTimes.size(); i++) {
            if (dailyMeetingTimes.get(i).getEndTime().compareTo(interval.getStartTime()) > 0) {
                currentIntervalIndex = i;
                currentInterval = dailyMeetingTimes.get(i);
                break;
            }
        }
        if (currentInterval == null) {
            return true;
        }
        for (Time freeTimeBeginning = (currentInterval.getStartTime().compareTo(interval.getStartTime()) > 0) ?
                interval.getStartTime() : currentInterval.getEndTime();
                freeTimeBeginning.compareTo(interval.getEndTime()) < 0;
                freeTimeBeginning = (currentInterval.getStartTime().compareTo(freeTimeBeginning) > 0) ?
                     currentInterval.getEndTime() : interval.getEndTime()) {
            currentIntervalIndex++;
            Time busyTimeBeginning = (currentIntervalIndex < dailyMeetingTimes.size()
                    && (currentInterval = dailyMeetingTimes.get(currentIntervalIndex))
                    .getStartTime().compareTo(interval.getEndTime()) < 0)
                    ? currentInterval.getStartTime() : interval.getEndTime();
            if (busyTimeBeginning.getMinuteDifference(freeTimeBeginning) >= HOUR) {
                return true;
            }
        }
        return false;
    }

    public ComparatorValues getComparatorValues() {
        return comparatorValues;
    }

    public void setSchedule(ArrayList<Section> schedule) {
        this.schedule = schedule;
    }
}
