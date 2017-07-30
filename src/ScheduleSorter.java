import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * Created by KChen10 on 7/26/2017.
 */
public class ScheduleSorter {
    public static void sort(ArrayList<Schedule> schedules, GenericComparator comparator) {
        TreeMap<Long, ArrayList<Schedule>> scoreToSchedule = new TreeMap<>(Collections.reverseOrder());
        for (Schedule current: schedules) {
            long score = comparator.computeScheduleValue(current);
            if (!scoreToSchedule.containsKey(score)) {
                scoreToSchedule.put(score, new ArrayList<>());
            }
            scoreToSchedule.get(score).add(current);
        }
        schedules.clear();
        for (ArrayList<Schedule> currentSchedules: scoreToSchedule.values()) {
            for (Schedule currentSchedule: currentSchedules) {
                schedules.add(currentSchedule);
            }
        }
    }
}
